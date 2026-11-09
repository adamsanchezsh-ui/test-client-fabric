package com.example.testclient.modules;

import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class SpiderModule extends Module {
	public SpiderModule() {
		super("Spider", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (p.horizontalCollision) {
			Vec3d v = p.getVelocity();
			p.setVelocity(v.x, 0.2, v.z);
		}
	}
}
