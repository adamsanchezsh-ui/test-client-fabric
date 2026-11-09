package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

public class SprintModule extends Module {
	public SprintModule() {
		super("Sprint", GLFW.GLFW_KEY_K);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (p.forwardSpeed > 0 && !p.isSneaking() && !p.horizontalCollision) {
			p.setSprinting(true);
		}
	}
}
