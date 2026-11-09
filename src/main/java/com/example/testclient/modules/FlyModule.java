package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

public class FlyModule extends Module {
	public FlyModule() {
		super("Fly", GLFW.GLFW_KEY_G);
	}

	@Override
	public void onTick() {
		mc.player.getAbilities().allowFlying = true;
		mc.player.getAbilities().flying = true;
	}

	@Override
	protected void onDisable() {
		if (mc.player != null && !mc.player.isCreative() && !mc.player.isSpectator()) {
			mc.player.getAbilities().flying = false;
			mc.player.getAbilities().allowFlying = false;
		}
	}
}
