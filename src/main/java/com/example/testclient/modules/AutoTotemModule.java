package com.example.testclient.modules;

import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;

public class AutoTotemModule extends Module {
	private static final int INTERVAL = 5;
	private int ticks;

	public AutoTotemModule() {
		super("AutoTotem", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		if (++ticks < INTERVAL) {
			return;
		}
		ticks = 0;
		var p = mc.player;
		if (p.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
			return;
		}
		var inv = p.getInventory();
		for (int i = 0; i < 36; i++) {
			if (inv.getStack(i).isOf(Items.TOTEM_OF_UNDYING)) {
				int slotId = i < 9 ? 36 + i : i;
				mc.interactionManager.clickSlot(p.playerScreenHandler.syncId, slotId, 40, SlotActionType.SWAP, p);
				return;
			}
		}
	}
}
