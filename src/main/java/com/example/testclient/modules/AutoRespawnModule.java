package com.example.testclient.modules;

import net.minecraft.client.gui.screen.DeathScreen;
import org.lwjgl.glfw.GLFW;

public class AutoRespawnModule extends Module {
	public AutoRespawnModule() {
		super("AutoRespawn", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		if (mc.currentScreen instanceof DeathScreen) {
			mc.player.requestRespawn();
		}
	}
}
