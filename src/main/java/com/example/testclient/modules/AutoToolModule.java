package com.example.testclient.modules;

import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

public class AutoToolModule extends Module {
	public AutoToolModule() {
		super("AutoTool", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		if (!mc.options.attackKey.isPressed()) {
			return;
		}
		if (!(mc.crosshairTarget instanceof BlockHitResult hit) || hit.getType() != HitResult.Type.BLOCK) {
			return;
		}
		var inv = mc.player.getInventory();
		BlockState state = mc.world.getBlockState(hit.getBlockPos());
		int best = -1;
		float bestSpeed = inv.getStack(inv.selectedSlot).getMiningSpeedMultiplier(state);
		for (int i = 0; i < 9; i++) {
			float s = inv.getStack(i).getMiningSpeedMultiplier(state);
			if (s > bestSpeed) {
				bestSpeed = s;
				best = i;
			}
		}
		if (best != -1) {
			inv.selectedSlot = best;
		}
	}
}
