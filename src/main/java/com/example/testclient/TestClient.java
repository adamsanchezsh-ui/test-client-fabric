package com.example.testclient;

import com.example.testclient.gui.ClickGUIScreen;
import com.example.testclient.modules.ModuleManager;
import com.example.testclient.render.EspRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TestClient implements ClientModInitializer {
	public static final String MOD_ID = "testclient";
	public static ModuleManager modules;
	private static KeyBinding guiKey;

	@Override
	public void onInitializeClient() {
		modules = new ModuleManager();
		EspRenderer.init();
		guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.testclient.gui",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				"category.testclient"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (guiKey.wasPressed()) {
				if (client.currentScreen == null) {
					client.setScreen(new ClickGUIScreen());
				}
			}
			modules.tick(client);
		});
	}
}
