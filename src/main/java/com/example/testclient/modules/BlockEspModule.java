package com.example.testclient.modules;

import com.example.testclient.render.EspSource;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlockEspModule extends Module implements EspSource {
	private static final int RADIUS = 24;
	private static final int SCAN_INTERVAL = 40;
	private static final Set<Block> TARGETS = Set.of(
			Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.ANCIENT_DEBRIS,
			Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.SPAWNER);

	private final List<Box> found = new ArrayList<>();
	private int ticks;

	public BlockEspModule() {
		super("BlockESP", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	protected void onEnable() {
		ticks = SCAN_INTERVAL;
	}

	@Override
	protected void onDisable() {
		found.clear();
	}

	@Override
	public void onTick() {
		if (++ticks < SCAN_INTERVAL) {
			return;
		}
		ticks = 0;
		found.clear();
		BlockPos o = mc.player.getBlockPos();
		for (BlockPos p : BlockPos.iterate(o.add(-RADIUS, -RADIUS, -RADIUS), o.add(RADIUS, RADIUS, RADIUS))) {
			if (TARGETS.contains(mc.world.getBlockState(p).getBlock())) {
				found.add(new Box(p));
			}
		}
	}

	@Override
	public List<Box> boxes() {
		return found;
	}

	@Override
	public float[] color() {
		return new float[]{0.1f, 0.9f, 1.0f, 1.0f};
	}
}
