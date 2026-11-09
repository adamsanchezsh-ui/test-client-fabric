package com.example.testclient.render;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public interface EspSource {
	List<Box> boxes();

	/** RGBA, each 0..1 */
	float[] color();

	/** Points that get a line drawn from the crosshair to them. */
	default List<Vec3d> tracerTargets() {
		return List.of();
	}
}
