package com.example.testclient.modules;

import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class HighJumpModule extends Module {
	private static final double JUMP_VELOCITY = 0.9;

	public HighJumpModule() {
		super("HighJump", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (mc.options.jumpKey.isPressed() && p.isOnGround()) {
			Vec3d v = p.getVelocity();
			p.setVelocity(v.x, JUMP_VELOCITY, v.z);
		}
	}
}
