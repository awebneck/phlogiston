package com.jeremypholland.phlogiston.client.render;

import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by jeremy on 12/10/15.
 */
@SideOnly(Side.CLIENT)
public class RenderGentledSheep extends RenderLiving {
    private static final ResourceLocation TEXTURE = new ResourceLocation("phlogiston:textures/entity/gentled_sheep.png");

    public RenderGentledSheep(RenderManager rm) {
        super(rm, new ModelSheep1(), 0.7f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
