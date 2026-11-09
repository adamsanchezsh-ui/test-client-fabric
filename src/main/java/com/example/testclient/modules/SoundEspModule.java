package com.example.testclient.modules;

import com.example.testclient.render.EspSource;
import net.minecraft.client.sound.SoundInstanceListener;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class SoundEspModule extends Module implements EspSource {
	private static final long LIFETIME_MS = 3000;
	private static final int MAX_MARKS = 200;

	private record Mark(Vec3d pos, long time) {}

	private final List<Mark> marks = new ArrayList<>();

	private final SoundInstanceListener listener = (sound, soundSet, range) -> {
		if (sound.isRelative() || mc.player == null) {
			return;
		}
		Vec3d pos = new Vec3d(sound.getX(), sound.getY(), sound.getZ());
		if (pos.squaredDistanceTo(mc.player.getPos()) < 4.0) {
			return;
		}
		if (marks.size() >= MAX_MARKS) {
			marks.remove(0);
		}
		marks.add(new Mark(pos, System.currentTimeMillis()));
	};

	public SoundEspModule() {
		super("SoundESP", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	protected void onEnable() {
		mc.getSoundManager().registerListener(listener);
	}

	@Override
	protected void onDisable() {
		mc.getSoundManager().unregisterListener(listener);
		marks.clear();
	}

	@Override
	public void onTick() {
		long now = System.currentTimeMillis();
		marks.removeIf(m -> now - m.time() > LIFETIME_MS);
	}

	@Override
	public List<Box> boxes() {
		List<Box> out = new ArrayList<>(marks.size());
		for (Mark m : marks) {
			out.add(Box.of(m.pos(), 0.5, 0.5, 0.5));
		}
		return out;
	}

	@Override
	public float[] color() {
		return new float[]{1.0f, 0.85f, 0.1f, 1.0f};
	}
}
