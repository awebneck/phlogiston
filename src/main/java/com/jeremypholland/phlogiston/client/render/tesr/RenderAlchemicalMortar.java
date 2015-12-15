package com.jeremypholland.phlogiston.client.render.tesr;

import com.jeremypholland.phlogiston.common.tileentities.TileEntityAlchemicalMortar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by jeremy on 12/12/15.
 */
@SideOnly(Side.CLIENT)
public class RenderAlchemicalMortar extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        TileEntityAlchemicalMortar team = (TileEntityAlchemicalMortar)te;
        if (team.isFull()) {
            ItemStack stack = team.getStack();
            GlStateManager.pushMatrix();

            double d3 = x;
            double d4 = y;
            double d5 = z + .5;
            GlStateManager.translate(d3 + 0.5D, d4 + 0.5D, d5 + 0.5D);
            GlStateManager.rotate(180.0F , 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.0F, 0.5F);

            EntityItem entityitem = new EntityItem(team.getWorld(), 0.0D, 0.0D, 0.0D, stack);
            Item item = entityitem.getEntityItem().getItem();
            GlStateManager.translate(0, (float)(Math.sin(team.getTicksFilled()/10F)*0.1F), 0);
            int deg = (int)(team.getTicksFilled()*0.75F%360F);
            GlStateManager.rotate(deg, 0, 1, 0);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            TextureAtlasSprite textureatlassprite = null;
            float scale = 0.75F*team.getDamagePercentage();
            GlStateManager.scale(scale, scale, scale);

            if (!Minecraft.getMinecraft().getRenderItem().shouldRenderItemIn3D(entityitem.getEntityItem()) ) {
              GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItemModel(entityitem.getEntityItem());
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            if (textureatlassprite != null && textureatlassprite.getFrameCount() > 0) {
              textureatlassprite.updateAnimation();
            }
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();

            GlStateManager.popMatrix();
      }
    }
}
