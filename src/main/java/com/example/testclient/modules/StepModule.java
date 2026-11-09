package com.example.testclient.modules;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import org.lwjgl.glfw.GLFW;

public class StepModule extends Module {
	private static final double DEFAULT_STEP = 0.6;

	public StepModule() {
		super("Step", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		EntityAttributeInstance a = mc.player.getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT);
		if (a != null) {
			a.setBaseValue(1.0);
		}
	}

	@Override
	protected void onDisable() {
		if (mc.player == null) {
			return;
		}
		EntityAttributeInstance a = mc.player.getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT);
		if (a != null) {
			a.setBaseValue(DEFAULT_STEP);
		}
	}
}
