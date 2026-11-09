package com.example.testclient.render;

import net.minecraft.util.math.Box;

import java.util.List;

public interface EspSource {
	List<Box> boxes();

	/** RGBA, each 0..1 */
	float[] color();
}
