package com.example.testclient.modules;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class EspModule extends Module {
	public EspModule() {
		super("ESP", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		for (Entity e : mc.world.getEntities()) {
			if (e instanceof PlayerEntity && e != mc.player) {
				e.setGlowing(true);
			}
		}
	}

	@Override
	protected void onDisable() {
		if (mc.world == null) {
			return;
		}
		for (Entity e : mc.world.getEntities()) {
			if (e instanceof PlayerEntity && e != mc.player) {
				e.setGlowing(false);
			}
		}
	}
}
