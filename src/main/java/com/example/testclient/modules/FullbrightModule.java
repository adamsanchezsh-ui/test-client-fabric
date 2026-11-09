package com.example.testclient.modules;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.glfw.GLFW;

public class FullbrightModule extends Module {
	public FullbrightModule() {
		super("Fullbright", GLFW.GLFW_KEY_J);
	}

	@Override
	public void onTick() {
		mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1000, 0, false, false));
	}

	@Override
	protected void onDisable() {
		if (mc.player != null) {
			mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
		}
	}
}
