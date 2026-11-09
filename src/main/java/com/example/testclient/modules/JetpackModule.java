package com.example.testclient.modules;

import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class JetpackModule extends Module {
	private static final double UP_SPEED = 0.3;

	public JetpackModule() {
		super("Jetpack", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		if (mc.options.jumpKey.isPressed()) {
			Vec3d v = mc.player.getVelocity();
			mc.player.setVelocity(v.x, UP_SPEED, v.z);
		}
	}
}
