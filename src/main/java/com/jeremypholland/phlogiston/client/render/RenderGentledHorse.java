package com.jeremypholland.phlogiston.client.render;

import net.minecraft.client.model.ModelHorse;
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
public class RenderGentledHorse extends RenderLiving {
    private static final ResourceLocation TEXTURE = new ResourceLocation("phlogiston:textures/entity/gentled_horse.png");

    public RenderGentledHorse(RenderManager rm) {
        super(rm, new ModelHorse(), 0.75f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
