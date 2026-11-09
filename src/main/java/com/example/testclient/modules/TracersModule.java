package com.example.testclient.modules;

import com.example.testclient.render.EspSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class TracersModule extends Module implements EspSource {
	public TracersModule() {
		super("Tracers", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public List<Box> boxes() {
		return List.of();
	}

	@Override
	public List<Vec3d> tracerTargets() {
		List<Vec3d> out = new ArrayList<>();
		if (mc.world == null) {
			return out;
		}
		for (PlayerEntity p : mc.world.getPlayers()) {
			if (p != mc.player) {
				out.add(p.getPos().add(0, p.getHeight() / 2.0, 0));
			}
		}
		return out;
	}

	@Override
	public float[] color() {
		return new float[]{1.0f, 0.3f, 0.3f, 1.0f};
	}
}
