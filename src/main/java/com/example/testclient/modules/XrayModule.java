package com.example.testclient.modules;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.lwjgl.glfw.GLFW;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** Classic X-ray: only the listed blocks are rendered, everything else is see-through. Not compatible with Sodium. */
public class XrayModule extends Module {
	public static volatile boolean active;

	public static final Set<Block> TARGETS = new HashSet<>();

	static {
		Collections.addAll(TARGETS,
				Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE,
				Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE,
				Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE,
				Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.NETHER_GOLD_ORE,
				Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
				Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
				Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE,
				Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE,
				Blocks.NETHER_QUARTZ_ORE, Blocks.ANCIENT_DEBRIS,
				Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.SPAWNER);
	}

	public XrayModule() {
		super("Xray", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	protected void onEnable() {
		active = true;
		mc.worldRenderer.reload();
	}

	@Override
	protected void onDisable() {
		active = false;
		mc.worldRenderer.reload();
	}
}
