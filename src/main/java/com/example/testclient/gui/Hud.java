package com.example.testclient.gui;

import com.example.testclient.TestClient;
import com.example.testclient.modules.Module;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public final class Hud {
	private Hud() {}

	public static void init() {
		HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
			MinecraftClient mc = MinecraftClient.getInstance();
			if (mc.options.hudHidden || mc.player == null || TestClient.modules == null) {
				return;
			}
			TextRenderer tr = mc.textRenderer;
			ctx.drawText(tr, "Test Client", 4, 4, ClickGUIScreen.ACCENT, true);

			int y = 4;
			int w = ctx.getScaledWindowWidth();
			for (Module m : TestClient.modules.getModules()) {
				if (m.isEnabled()) {
					String n = m.getName();
					ctx.drawText(tr, n, w - tr.getWidth(n) - 4, y, 0xFF8FB8FF, true);
					y += 10;
				}
			}
		});
	}
}
