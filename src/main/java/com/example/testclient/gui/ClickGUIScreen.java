package com.example.testclient.gui;

import com.example.testclient.TestClient;
import com.example.testclient.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClickGUIScreen extends Screen {
	public static final int ACCENT = 0xFF2F80FF;
	private static final int ACCENT_DARK = 0xFF1F5FBF;
	private static final int BG = 0xE0141420;
	private static final int BG_HOVER = 0xE01E1E30;
	private static final int W = 100;
	private static final int HEADER = 16;
	private static final int ROW = 14;

	private static final Map<String, List<String>> CATEGORIES = new LinkedHashMap<>();

	static {
		CATEGORIES.put("Combat", List.of("KillAura", "AutoClicker", "AutoTotem", "Velocity", "Reach"));
		CATEGORIES.put("Movement", List.of("Fly", "Speed", "Sprint", "Step", "Glide", "HighJump", "AutoJump", "AirJump", "Jetpack", "Spider", "NoFall", "AutoWalk"));
		CATEGORIES.put("Render", List.of("Fullbright", "Xray", "Zoom", "ESP", "BlockESP", "SoundESP", "Tracers"));
		CATEGORIES.put("Player", List.of("AutoTool", "AutoRespawn", "AntiAFK"));
		CATEGORIES.put("World", List.of("Scaffold"));
	}

	private static final class Panel {
		final String title;
		final List<String> names;
		int x;
		int y;

		Panel(String title, List<String> names, int x, int y) {
			this.title = title;
			this.names = names;
			this.x = x;
			this.y = y;
		}
	}
	// static so positions are remembered between openings
	private static final List<Panel> panels = new ArrayList<>();

	private Panel dragging;

	public ClickGUIScreen() {
		super(Text.literal("Test Client"));
		if (panels.isEmpty()) {
			Set<String> listed = new HashSet<>();
			int x = 10;
			for (Map.Entry<String, List<String>> e : CATEGORIES.entrySet()) {
				listed.addAll(e.getValue());
				panels.add(new Panel(e.getKey(), e.getValue(), x, 20));
				x += W + 6;
			}
			// anything not listed above goes into an automatic Misc panel
			List<String> rest = new ArrayList<>();
			for (Module m : TestClient.modules.getModules()) {
				if (!listed.contains(m.getName())) {
					rest.add(m.getName());
				}
			}
			if (!rest.isEmpty()) {
				panels.add(new Panel("Misc", rest, x, 20));
			}
		}
	}

	@Override
	public boolean shouldPause() {
		return false;
	}

	private static Module find(String name) {
		for (Module m : TestClient.modules.getModules()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

	@Override
	public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
		ctx.fill(0, 0, this.width, this.height, 0x66000000);

		for (Panel p : panels) {
			ctx.fill(p.x, p.y, p.x + W, p.y + HEADER, ACCENT);
			int tw = this.textRenderer.getWidth(p.title);
			ctx.drawText(this.textRenderer, p.title, p.x + (W - tw) / 2, p.y + 4, 0xFFFFFFFF, true);

			int ry = p.y + HEADER;
			for (String name : p.names) {
				Module m = find(name);
				if (m == null) {
					continue;
				}
				boolean hover = mouseX >= p.x && mouseX < p.x + W && mouseY >= ry && mouseY < ry + ROW;
				int bg = m.isEnabled() ? ACCENT_DARK : (hover ? BG_HOVER : BG);
				ctx.fill(p.x, ry, p.x + W, ry + ROW, bg);
				if (m.isEnabled()) {
					ctx.fill(p.x, ry, p.x + 2, ry + ROW, ACCENT);
				}
				ctx.drawText(this.textRenderer, name, p.x + 6, ry + 3, m.isEnabled() ? 0xFFFFFFFF : 0xFFB0B0C0, true);
				ry += ROW;
			}
		}
	}

	@Override
	public boolean mouseClicked(double mx, double my, int button) {
		for (int i = panels.size() - 1; i >= 0; i--) {
			Panel p = panels.get(i);
			if (mx < p.x || mx >= p.x + W) {
				continue;
			}
			if (my >= p.y && my < p.y + HEADER) {
				if (button == 0) {
					dragging = p;
					panels.remove(i);
					panels.add(p);
				}
				return true;
			}
			int idx = (int) ((my - p.y - HEADER) / ROW);
			if (my >= p.y + HEADER && idx >= 0 && idx < p.names.size()) {
				Module m = find(p.names.get(idx));
				if (m != null && button == 0) {
					m.toggle();
				}
				return true;
			}
		}
		return super.mouseClicked(mx, my, button);
	}

	@Override
	public boolean mouseDragged(double mx, double my, int button, double dx, double dy) {
		if (dragging != null && button == 0) {
			dragging.x += (int) Math.round(dx);
			dragging.y += (int) Math.round(dy);
			return true;
		}
		return super.mouseDragged(mx, my, button, dx, dy);
	}

	@Override
	public boolean mouseReleased(double mx, double my, int button) {
		dragging = null;
		return super.mouseReleased(mx, my, button);
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
