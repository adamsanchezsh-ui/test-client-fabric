package com.example.testclient.modules;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import org.lwjgl.glfw.GLFW;

public class AutoClickerModule extends Module {
	public AutoClickerModule() {
		super("AutoClicker", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (p.getAttackCooldownProgress(0.5f) < 1.0f) {
			return;
		}
		if (mc.crosshairTarget instanceof EntityHitResult hit
				&& hit.getEntity() instanceof LivingEntity le && le.isAlive()) {
			mc.interactionManager.attackEntity(p, le);
			p.swingHand(Hand.MAIN_HAND);
		}
	}
}
