package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

public class AirJumpModule extends Module {
	private boolean wasPressed;

	public AirJumpModule() {
		super("AirJump", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		boolean pressed = mc.options.jumpKey.isPressed();
		if (pressed && !wasPressed && !mc.player.isOnGround()) {
			mc.player.jump();
		}
		wasPressed = pressed;
	}
}
