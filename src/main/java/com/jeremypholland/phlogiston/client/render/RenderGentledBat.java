package com.jeremypholland.phlogiston.client.render;

import com.jeremypholland.phlogiston.client.model.ModelGentledBat;
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
public class RenderGentledBat extends RenderLiving {
    private static final ResourceLocation TEXTURE = new ResourceLocation("phlogiston:textures/entity/gentled_bat.png");

    public RenderGentledBat(RenderManager rm) {
        super(rm, new ModelGentledBat(), 0.25f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
