package com.example.testclient.modules;

import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class ScaffoldModule extends Module {
	public ScaffoldModule() {
		super("Scaffold", GLFW.GLFW_KEY_UNKNOWN);
	}

	@Override
	public void onTick() {
		var p = mc.player;
		if (!(p.getMainHandStack().getItem() instanceof BlockItem)) {
			return;
		}
		BlockPos target = BlockPos.ofFloored(p.getX(), p.getY() - 1.0, p.getZ());
		if (!mc.world.getBlockState(target).isReplaceable()) {
			return;
		}
		for (Direction d : Direction.values()) {
			if (d == Direction.UP) {
				continue;
			}
			BlockPos n = target.offset(d);
			if (mc.world.getBlockState(n).isReplaceable()) {
				continue;
			}
			Direction face = d.getOpposite();
			Vec3d hitPos = Vec3d.ofCenter(n).add(face.getOffsetX() * 0.5, face.getOffsetY() * 0.5, face.getOffsetZ() * 0.5);
			BlockHitResult hit = new BlockHitResult(hitPos, face, n, false);
			mc.interactionManager.interactBlock(p, Hand.MAIN_HAND, hit);
			p.swingHand(Hand.MAIN_HAND);
			return;
		}
	}
}
