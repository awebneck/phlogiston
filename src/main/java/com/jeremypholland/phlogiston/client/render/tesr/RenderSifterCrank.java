package com.jeremypholland.phlogiston.client.render.tesr;

import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifter;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifterCrank;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

/**
 * Created by jeremy on 12/13/15.
 */
public class RenderSifterCrank extends TileEntitySpecialRenderer {
    private static final double SCALE = 0.0625;
    private float zRotationAngle = 0.0F;

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        TileEntitySifterCrank tesc = (TileEntitySifterCrank)te;
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer wr = tess.getWorldRenderer();

        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.translate(x + 0.5F, y + 0.5F, z + 0.5F);
        GlStateManager.scale(SCALE, SCALE, SCALE);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        // GlStateManager.disableLighting();
        // GlStateManager.disableRescaleNormal();
        bindTexture(new ResourceLocation("phlogiston:textures/blocks/sifter_crank.png"));
        EnumFacing facing = tesc.getSifterFacing();
        if (facing != null) {
            TileEntitySifter tes = (TileEntitySifter)te.getWorld().getTileEntity(te.getPos().offset(facing));
            if (tes != null && tes.hasScreen()) {
                int br = te.getWorld().getCombinedLight(tes.getPos(), 0);
                int varl1 = br % 65536;
                int varl2 = br / 65536;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, varl1, varl2);
                if (facing == EnumFacing.EAST) {
                    GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
                } else if (facing == EnumFacing.NORTH) {
                    GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
                } else if (facing == EnumFacing.WEST) {
                    GlStateManager.rotate(-90, 0.0F, 1.0F, 0.0F);
                }
                zRotationAngle += partialTicks*2.0F;
                if (zRotationAngle >= 360) {
                    zRotationAngle -= 360;
                }
                GlStateManager.rotate(zRotationAngle, 0.0F, 0.0F, 1.0F);
                renderCrank(wr, facing);
                tess.draw();
            }
        }
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void renderCrank(WorldRenderer wr, EnumFacing facing) {
        wr.startDrawingQuads();

        // Crankshaft
        wr.addVertexWithUV(1, -1,  6, 9*SCALE, 2*SCALE);
        wr.addVertexWithUV(1,  1,  6, 9*SCALE, 0);
        wr.addVertexWithUV(1,  1,  10, 13*SCALE, 0);
        wr.addVertexWithUV(1, -1,  10, 13*SCALE, 2*SCALE);

        wr.addVertexWithUV(1,  1,  6, 9*SCALE, 2*SCALE);
        wr.addVertexWithUV(-1, 1,  6, 9*SCALE, 0);
        wr.addVertexWithUV(-1, 1,  10, 13*SCALE, 0);
        wr.addVertexWithUV(1,  1,  10, 13*SCALE, 2*SCALE);

        wr.addVertexWithUV(-1, -1, 10, 9*SCALE, 2*SCALE);
        wr.addVertexWithUV(-1, 1,  10, 9*SCALE, 0);
        wr.addVertexWithUV(-1, 1,  6, 13*SCALE, 0);
        wr.addVertexWithUV(-1, -1, 6, 13*SCALE, 2*SCALE);

        wr.addVertexWithUV(1,  -1, 10, 9*SCALE, 2*SCALE);
        wr.addVertexWithUV(-1, -1, 10, 9*SCALE, 0);
        wr.addVertexWithUV(-1, -1, 6, 13*SCALE, 0);
        wr.addVertexWithUV(1,  -1, 6, 13*SCALE, 2*SCALE);

        // Connecting Rod
        wr.addVertexWithUV(-1, -1, 5, 7*SCALE, 9*SCALE);
        wr.addVertexWithUV(-1, 8, 5, 7*SCALE, 0);
        wr.addVertexWithUV(1,  8, 5, 9*SCALE, 0);
        wr.addVertexWithUV(1,  -1, 5, 9*SCALE, 9*SCALE);

        wr.addVertexWithUV(1, -1, 5, 8*SCALE, 9*SCALE);
        wr.addVertexWithUV(1, 8, 5, 8*SCALE, 0);
        wr.addVertexWithUV(1,  8, 6, 9*SCALE, 0);
        wr.addVertexWithUV(1,  -1, 6, 9*SCALE, 9*SCALE);

        wr.addVertexWithUV(1, -1, 6, 7*SCALE, 9*SCALE);
        wr.addVertexWithUV(1, 8, 6, 7*SCALE, 0);
        wr.addVertexWithUV(-1,  8, 6, 9*SCALE, 0);
        wr.addVertexWithUV(-1,  -1, 6, 9*SCALE, 9*SCALE);

        wr.addVertexWithUV(-1, -1, 6, 8*SCALE, 9*SCALE);
        wr.addVertexWithUV(-1, 8, 6, 8*SCALE, 0);
        wr.addVertexWithUV(-1,  8, 5, 9*SCALE, 0);
        wr.addVertexWithUV(-1,  -1, 5, 9*SCALE, 9*SCALE);

        wr.addVertexWithUV(-1, -1, 6, 11*SCALE, 1*SCALE);
        wr.addVertexWithUV(-1, -1, 5, 11*SCALE, 0);
        wr.addVertexWithUV(1,  -1, 5, 9*SCALE, 0);
        wr.addVertexWithUV(1,  -1, 6, 9*SCALE, 1*SCALE);

        wr.addVertexWithUV(-1, 8, 5, 11*SCALE, 1*SCALE);
        wr.addVertexWithUV(-1, 8, 6, 11*SCALE, 0);
        wr.addVertexWithUV(1,  8, 6, 9*SCALE, 0);
        wr.addVertexWithUV(1,  8, 5, 9*SCALE, 1*SCALE);

        // Handle
        wr.addVertexWithUV(1, 6,  0, 2*SCALE, 2*SCALE);
        wr.addVertexWithUV(1,  8,  0, 2*SCALE, 0);
        wr.addVertexWithUV(1,  8,  5, 7*SCALE, 0);
        wr.addVertexWithUV(1, 6,  5, 7*SCALE, 2*SCALE);

        wr.addVertexWithUV(1,  8,  0, 2*SCALE, 2*SCALE);
        wr.addVertexWithUV(-1, 8,  0, 2*SCALE, 0);
        wr.addVertexWithUV(-1, 8,  5, 7*SCALE, 0);
        wr.addVertexWithUV(1,  8,  5, 7*SCALE, 2*SCALE);

        wr.addVertexWithUV(-1, 6, 5, 2*SCALE, 2*SCALE);
        wr.addVertexWithUV(-1, 8,  5, 2*SCALE, 0);
        wr.addVertexWithUV(-1, 8,  0, 7*SCALE, 0);
        wr.addVertexWithUV(-1, 6, 0, 7*SCALE, 2*SCALE);

        wr.addVertexWithUV(1,  6, 5, 2*SCALE, 2*SCALE);
        wr.addVertexWithUV(-1, 6, 5, 2*SCALE, 0);
        wr.addVertexWithUV(-1, 6, 0, 7*SCALE, 0);
        wr.addVertexWithUV(1,  6, 0, 7*SCALE, 2*SCALE);

        wr.addVertexWithUV(-1,  6, 0, 0, 2*SCALE);
        wr.addVertexWithUV(-1, 8, 0, 0, 0);
        wr.addVertexWithUV(1, 8, 0, 2*SCALE, 0);
        wr.addVertexWithUV(1,  6, 0, 2*SCALE, 2*SCALE);
    }
}
