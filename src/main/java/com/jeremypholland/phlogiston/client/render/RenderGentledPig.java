package com.jeremypholland.phlogiston.client.render;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by jeremy on 12/10/15.
 */
@SideOnly(Side.CLIENT)
public class RenderGentledPig extends RenderLiving {
    private static final ResourceLocation TEXTURE = new ResourceLocation("phlogiston:textures/entity/gentled_pig.png");

    public RenderGentledPig(RenderManager rm) {
        super(rm, new ModelPig(), 0.7f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
