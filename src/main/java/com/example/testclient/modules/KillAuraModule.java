package com.example.testclient.modules;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class KillAuraModule extends Module {
	private static final double RANGE = 3.5;

	public KillAuraModule() {
		super("KillAura", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (p.getAttackCooldownProgress(0.5f) < 1.0f) {
			return;
		}
		LivingEntity best = null;
		double bestDist = RANGE * RANGE;
		for (Entity e : mc.world.getEntities()) {
			if (!(e instanceof LivingEntity le) || e == p || !le.isAlive()) {
				continue;
			}
			double d = p.squaredDistanceTo(le);
			if (d < bestDist) {
				bestDist = d;
				best = le;
			}
		}
		if (best != null) {
			mc.interactionManager.attackEntity(p, best);
			p.swingHand(Hand.MAIN_HAND);
		}
	}
}
