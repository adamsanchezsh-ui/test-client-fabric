package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

public class AutoJumpModule extends Module {
	public AutoJumpModule() {
		super("AutoJump", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		boolean moving = p.forwardSpeed != 0 || p.sidewaysSpeed != 0;
		if (moving && p.isOnGround()) {
			p.jump();
		}
	}
}
