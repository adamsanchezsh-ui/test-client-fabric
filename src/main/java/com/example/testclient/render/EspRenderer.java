package com.example.testclient.render;

import com.example.testclient.TestClient;
import com.example.testclient.modules.Module;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public final class EspRenderer {
	private EspRenderer() {}

	public static void init() {
		WorldRenderEvents.AFTER_ENTITIES.register(ctx -> {
			if (TestClient.modules == null) {
				return;
			}
			Vec3d cam = ctx.camera().getPos();
			MatrixStack ms = ctx.matrixStack();

			BufferBuilder buf = null;
			for (Module m : TestClient.modules.getModules()) {
				if (!m.isEnabled() || !(m instanceof EspSource src)) {
					continue;
				}
				if (buf == null) {
					RenderSystem.setShader(GameRenderer::getRenderTypeLinesProgram);
					RenderSystem.disableDepthTest();
					RenderSystem.lineWidth(2.0f);
					buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
				}
				float[] c = src.color();
				ms.push();
				ms.translate(-cam.x, -cam.y, -cam.z);
				for (Box box : src.boxes()) {
					WorldRenderer.drawBox(ms, buf, box, c[0], c[1], c[2], c[3]);
				}
				ms.pop();
			}
			if (buf != null) {
				try {
					BufferRenderer.drawWithGlobalProgram(buf.end());
				} catch (IllegalStateException ignored) {
					// nothing was drawn
				}
				RenderSystem.enableDepthTest();
			}
		});
	}
}
