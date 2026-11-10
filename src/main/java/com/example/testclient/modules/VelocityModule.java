package com.example.testclient.modules;

import org.lwjgl.glfw.GLFW;

/** Ignores knockback / velocity packets sent for your own player. */
public class VelocityModule extends Module {
	public static volatile boolean active;

	public VelocityModule() {
		super("Velocity", GLFW.GLFW_KEY_UNKNOWN);
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
