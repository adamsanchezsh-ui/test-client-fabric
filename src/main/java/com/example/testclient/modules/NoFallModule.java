package com.example.testclient.modules;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NoFallModule extends Module {
	public NoFallModule() {
		super("NoFall", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (p.fallDistance > 2.0f && !p.isOnGround() && !p.isFallFlying()) {
			p.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
		}
	}
}
