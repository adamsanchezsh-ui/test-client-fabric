package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

public class ZoomModule extends Module {
	public static volatile boolean active;
	public static final double FACTOR = 4.0;

	public ZoomModule() {
		super("Zoom", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	protected void onEnable() {
		active = true;
	}

	@Override
	protected void onDisable() {
		active = false;
	}
}
