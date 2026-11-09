package com.example.testclient.modules;

import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class GlideModule extends Module {
	private static final double MAX_FALL_SPEED = -0.1;

	public GlideModule() {
		super("Glide", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		Vec3d v = p.getVelocity();
		if (!p.isOnGround() && !p.isTouchingWater() && v.y < MAX_FALL_SPEED) {
			p.setVelocity(v.x, MAX_FALL_SPEED, v.z);
		}
	}
}
