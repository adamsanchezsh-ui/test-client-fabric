package com.example.testclient.modules;

import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class AntiAfkModule extends Module {
	private static final int INTERVAL_TICKS = 600;
	private int ticks;

	public AntiAfkModule() {
		super("AntiAFK", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	protected void onEnable() {
		ticks = 0;
	}

	@Override
	public void onTick() {
		if (++ticks < INTERVAL_TICKS) {
			return;
		}
		ticks = 0;
		var p = mc.player;
		if (p.isOnGround()) {
			p.jump();
		}
		p.swingHand(Hand.MAIN_HAND);
	}
}
