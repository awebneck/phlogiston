package com.jeremypholland.phlogiston.client.render.tesr;

import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifterCrank;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by jeremy on 12/13/15.
 */
public class RenderSifterCrank extends TileEntitySpecialRenderer {
    private static final double SCALE = 0.0625;

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        TileEntitySifterCrank tes = (TileEntitySifterCrank)te;
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer wr = tess.getWorldRenderer();
        RenderHelper.disableStandardItemLighting();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(SCALE, SCALE, SCALE);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        // GlStateManager.disableLighting();
        // GlStateManager.disableRescaleNormal();
        bindTexture(new ResourceLocation("phlogiston:textures/blocks/sifter_screen.png"));
        int br = te.getWorld().getCombinedLight(te.getPos(), 0);
        int varl1 = br % 65536;
        int varl2 = br / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, varl1, varl2);
        renderCrank(wr);

        tess.draw();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void renderCrank(WorldRenderer wr) {
        wr.startDrawingQuads();

        wr.addVertexWithUV(2,  7, 2, 12*SCALE, 2*SCALE);
        wr.addVertexWithUV(2,  9, 2, 12*SCALE, 0);
        wr.addVertexWithUV(14, 9, 2, 0, 0);
        wr.addVertexWithUV(14, 7, 2, 0, 2*SCALE);

        wr.addVertexWithUV(2,  7, 14, 12*SCALE, 2*SCALE);
        wr.addVertexWithUV(2,  9, 14, 12*SCALE, 0);
        wr.addVertexWithUV(2,  9, 2,  0, 0);
        wr.addVertexWithUV(2,  7, 2,  0, 2*SCALE);

        wr.addVertexWithUV(14, 7, 14, 12*SCALE, 2*SCALE);
        wr.addVertexWithUV(14, 9, 14, 12*SCALE, 0);
        wr.addVertexWithUV(2,  9, 14,  0, 0);
        wr.addVertexWithUV(2,  7, 14,  0, 2*SCALE);

        wr.addVertexWithUV(14, 7, 2, 12*SCALE, 2*SCALE);
        wr.addVertexWithUV(14, 9, 2, 12*SCALE, 0);
        wr.addVertexWithUV(14, 9, 14,  0, 0);
        wr.addVertexWithUV(14, 7, 14,  0, 2*SCALE);

        wr.addVertexWithUV(2,  9, 2,  14*SCALE, 14*SCALE);
        wr.addVertexWithUV(2,  9, 14, 14*SCALE, 2*SCALE);
        wr.addVertexWithUV(14, 9, 14, 2*SCALE,  2*SCALE);
        wr.addVertexWithUV(14, 9, 2,  2*SCALE,  14*SCALE);

        wr.addVertexWithUV(2,  7, 14, 14*SCALE, 2*SCALE);
        wr.addVertexWithUV(2,  7, 2,  14*SCALE, 14*SCALE);
        wr.addVertexWithUV(14, 7, 2,  2*SCALE,  14*SCALE);
        wr.addVertexWithUV(14, 7, 14, 2*SCALE,  2*SCALE);
    }
}
