package com.example.testclient.modules;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
	private final List<Module> modules = new ArrayList<>();

	public ModuleManager() {
		modules.add(new FlyModule());
		modules.add(new SpeedModule());
		modules.add(new FullbrightModule());
		modules.add(new SprintModule());
		modules.add(new EspModule());
		modules.add(new StepModule());
		modules.add(new KillAuraModule());
		modules.add(new NoFallModule());
		modules.add(new SpiderModule());
		modules.add(new AutoJumpModule());
	}

	public List<Module> getModules() {
		return modules;
	}

	public void tick(MinecraftClient client) {
		for (Module m : modules) {
			m.pollKey();
		}
		if (client.player == null || client.world == null) {
			return;
		}
		for (Module m : modules) {
			if (m.isEnabled()) {
				m.onTick();
			}
		}
	}
}
