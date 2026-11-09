package com.example.testclient.modules;

import com.example.testclient.render.EspSource;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Highlights selected blocks in every loaded chunk within render distance.
 * Chunks are scanned a few per tick so it does not cause lag spikes.
 */
public class BlockEspModule extends Module implements EspSource {
	private static final int CHUNKS_PER_TICK = 6;
	private static final int RESCAN_INTERVAL = 100;
	private static final Set<Block> TARGETS = Set.of(
			Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.ANCIENT_DEBRIS,
			Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.SPAWNER);

	private final Map<ChunkPos, List<Box>> byChunk = new HashMap<>();
	private final ArrayDeque<ChunkPos> queue = new ArrayDeque<>();
	private int cooldown;

	public BlockEspModule() {
		super("BlockESP", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	protected void onEnable() {
		cooldown = 0;
	}

	@Override
	protected void onDisable() {
		byChunk.clear();
		queue.clear();
	}

	@Override
	public void onTick() {
		if (queue.isEmpty() && --cooldown <= 0) {
			refill();
			cooldown = RESCAN_INTERVAL;
		}
		for (int i = 0; i < CHUNKS_PER_TICK && !queue.isEmpty(); i++) {
			scan(queue.poll());
		}
	}

	private void refill() {
		int r = mc.options.getClampedViewDistance();
		ChunkPos c = mc.player.getChunkPos();
		byChunk.keySet().removeIf(p -> Math.abs(p.x - c.x) > r || Math.abs(p.z - c.z) > r);
		for (int dx = -r; dx <= r; dx++) {
			for (int dz = -r; dz <= r; dz++) {
				queue.add(new ChunkPos(c.x + dx, c.z + dz));
			}
		}
	}

	private void scan(ChunkPos cp) {
		WorldChunk chunk = mc.world.getChunkManager().getWorldChunk(cp.x, cp.z);
		if (chunk == null) {
			byChunk.remove(cp);
			return;
		}
		List<Box> list = new ArrayList<>();
		ChunkSection[] sections = chunk.getSectionArray();
		for (int i = 0; i < sections.length; i++) {
			ChunkSection sec = sections[i];
			if (sec == null || sec.isEmpty() || !sec.hasAny(s -> TARGETS.contains(s.getBlock()))) {
				continue;
			}
			int baseY = (chunk.getBottomSectionCoord() + i) << 4;
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					for (int z = 0; z < 16; z++) {
						if (TARGETS.contains(sec.getBlockState(x, y, z).getBlock())) {
							double wx = cp.getStartX() + x;
							double wy = baseY + y;
							double wz = cp.getStartZ() + z;
							list.add(new Box(wx, wy, wz, wx + 1, wy + 1, wz + 1));
						}
					}
				}
			}
		}
		if (list.isEmpty()) {
			byChunk.remove(cp);
		} else {
			byChunk.put(cp, list);
		}
	}

	@Override
	public List<Box> boxes() {
		List<Box> all = new ArrayList<>();
		for (List<Box> l : byChunk.values()) {
			all.addAll(l);
		}
		return all;
	}

	@Override
	public float[] color() {
		return new float[]{0.1f, 0.9f, 1.0f, 1.0f};
	}
}
