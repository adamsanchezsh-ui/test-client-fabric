package com.example.testclient.mixin;

import com.example.testclient.modules.VelocityModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Inject(method = "onEntityVelocityUpdate", at = @At("HEAD"), cancellable = true)
	private void testclient$velocity(EntityVelocityUpdateS2CPacket packet, CallbackInfo ci) {
		if (!VelocityModule.active) {
			return;
		}
		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc.player != null && packet.getEntityId() == mc.player.getId()) {
			ci.cancel();
		}
	}
}
