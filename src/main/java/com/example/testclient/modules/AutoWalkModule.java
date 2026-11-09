package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

public class AutoWalkModule extends Module {
	public AutoWalkModule() {
		super("AutoWalk", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		mc.options.forwardKey.setPressed(true);
	}

	@Override
	protected void onDisable() {
		mc.options.forwardKey.setPressed(false);
	}
}
