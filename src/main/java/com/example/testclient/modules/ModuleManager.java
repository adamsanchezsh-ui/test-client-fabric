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
		modules.add(new BlockEspModule());
		modules.add(new SoundEspModule());
		modules.add(new TracersModule());
		modules.add(new StepModule());
		modules.add(new KillAuraModule());
		modules.add(new NoFallModule());
		modules.add(new SpiderModule());
		modules.add(new AutoJumpModule());
		modules.add(new ScaffoldModule());
		modules.add(new GlideModule());
		modules.add(new HighJumpModule());
		modules.add(new AntiAfkModule());
		modules.add(new AutoClickerModule());
		modules.add(new AutoToolModule());
		modules.add(new AutoWalkModule());
		modules.add(new JetpackModule());
		modules.add(new AirJumpModule());
		modules.add(new AutoRespawnModule());
		modules.add(new AutoTotemModule());
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
