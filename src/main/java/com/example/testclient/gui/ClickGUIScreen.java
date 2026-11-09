package com.example.testclient.gui;

import com.example.testclient.TestClient;
import com.example.testclient.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class ClickGUIScreen extends Screen {
	private static final int PANEL_W = 130;
	private static final int HEADER_H = 16;
	private static final int ROW_H = 14;

	public ClickGUIScreen() {
		super(Text.literal("Test Client"));
	}

	@Override
	public boolean shouldPause() {
		return false;
	}

	private int panelX() {
		return this.width / 2 - PANEL_W / 2;
	}

	private int panelY() {
		return 30;
	}

	@Override
	public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
		List<Module> mods = TestClient.modules.getModules();
		int x = panelX();
		int y = panelY();

		ctx.fill(x, y, x + PANEL_W, y + HEADER_H, 0xFF202020);
		ctx.drawText(this.textRenderer, "Test Client", x + 4, y + 4, 0xFFFFFFFF, true);

		for (int i = 0; i < mods.size(); i++) {
			Module m = mods.get(i);
			int ry = y + HEADER_H + i * ROW_H;
			boolean hover = mouseX >= x && mouseX < x + PANEL_W && mouseY >= ry && mouseY < ry + ROW_H;
			int bg = m.isEnabled() ? 0xFF2E7D32 : (hover ? 0xFF404040 : 0xFF303030);
			ctx.fill(x, ry, x + PANEL_W, ry + ROW_H, bg);
			ctx.drawText(this.textRenderer, m.getName(), x + 4, ry + 3, 0xFFFFFFFF, true);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		List<Module> mods = TestClient.modules.getModules();
		int x = panelX();
		int y = panelY() + HEADER_H;
		if (button == 0 && mouseX >= x && mouseX < x + PANEL_W) {
			int idx = (int) ((mouseY - y) / ROW_H);
			if (mouseY >= y && idx >= 0 && idx < mods.size()) {
				mods.get(idx).toggle();
				return true;
			}
		}
		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
			this.close();
			return true;
		}
		return super.keyPressed(keyCode, scanCode, modifiers);
	}
}
