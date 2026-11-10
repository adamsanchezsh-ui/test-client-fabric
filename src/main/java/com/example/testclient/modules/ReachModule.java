package com.example.testclient.modules;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import org.lwjgl.glfw.GLFW;

/** Raises the client-side interaction range attributes. The server still validates its own limits. */
public class ReachModule extends Module {
	private static final double BLOCK_REACH = 6.0;
	private static final double ENTITY_REACH = 6.0;
	private static final double DEFAULT_BLOCK = 4.5;
	private static final double DEFAULT_ENTITY = 3.0;

	public ReachModule() {
		super("Reach", GLFW.GLFW_KEY_UNKNOWN);
	}

	private static void set(double block, double entity) {
		EntityAttributeInstance b = mc.player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE);
		EntityAttributeInstance e = mc.player.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE);
		if (b != null) {
			b.setBaseValue(block);
		}
		if (e != null) {
			e.setBaseValue(entity);
		}
	}

	@Override
	public void onTick() {
		set(BLOCK_REACH, ENTITY_REACH);
	}

	@Override
	protected void onDisable() {
		if (mc.player != null) {
			set(DEFAULT_BLOCK, DEFAULT_ENTITY);
		}
	}
}
