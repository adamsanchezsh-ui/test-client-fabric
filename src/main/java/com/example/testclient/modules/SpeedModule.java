package com.example.testclient.modules;

import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class SpeedModule extends Module {
	private static final double MULTIPLIER = 1.15;
	private static final double MAX_HORIZONTAL = 0.6;

	public SpeedModule() {
		super("Speed", GLFW.GLFW_KEY_H);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		boolean moving = p.forwardSpeed != 0 || p.sidewaysSpeed != 0;
		if (!moving || !p.isOnGround()) {
			return;
		}
		Vec3d v = p.getVelocity();
		double x = v.x * MULTIPLIER;
		double z = v.z * MULTIPLIER;
		double h = Math.sqrt(x * x + z * z);
		if (h > MAX_HORIZONTAL) {
			x *= MAX_HORIZONTAL / h;
			z *= MAX_HORIZONTAL / h;
		}
		p.setVelocity(x, v.y, z);
	}
}
