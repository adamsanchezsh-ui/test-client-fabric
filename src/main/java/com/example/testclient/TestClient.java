package com.example.testclient;

import com.example.testclient.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class TestClient implements ClientModInitializer {
	public static final String MOD_ID = "testclient";
	public static ModuleManager modules;

	@Override
	public void onInitializeClient() {
		modules = new ModuleManager();
		ClientTickEvents.END_CLIENT_TICK.register(client -> modules.tick(client));
	}
}
