package com.example.testclient.modules;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public abstract class Module {
	protected static final MinecraftClient mc = MinecraftClient.getInstance();

	private final String name;
	private final KeyBinding keyBinding;
	private boolean enabled;

	protected Module(String name, int defaultKey) {
		this.name = name;
		this.keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.testclient." + name.toLowerCase(),
				InputUtil.Type.KEYSYM,
				defaultKey,
				"category.testclient"));
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void toggle() {
		enabled = !enabled;
		if (enabled) {
			onEnable();
		} else {
			onDisable();
		}
		if (mc.player != null) {
			mc.player.sendMessage(Text.literal(name + (enabled ? ": ON" : ": OFF")), true);
		}
	}

	void pollKey() {
		while (keyBinding.wasPressed()) {
			toggle();
		}
	}

	protected void onEnable() {}

	protected void onDisable() {}

	public void onTick() {}
}
