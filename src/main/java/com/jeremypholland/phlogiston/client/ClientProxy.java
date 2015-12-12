package com.jeremypholland.phlogiston.client;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.client.render.*;
import com.jeremypholland.phlogiston.common.CommonProxy;
import com.jeremypholland.phlogiston.common.blocks.BlockRetort;
import com.jeremypholland.phlogiston.common.blocks.BlockWraithstone;
import com.jeremypholland.phlogiston.common.entities.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by jeremy on 12/7/15.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public static final String INVENTORY = "inventory";
    public static final int DEFAULT_ITEM_SUBTYPE = 0;

    @Override
    public void load() {
    }

    @Override
    public void registerRenderers() {
        Item itemWraithstone = GameRegistry.findItem(Phlogiston.MODID, BlockWraithstone.UL_NAME);
        ModelResourceLocation itemWraithstoneMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockWraithstone.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemWraithstone, DEFAULT_ITEM_SUBTYPE, itemWraithstoneMRL);

        Item itemBurningWraithstone = GameRegistry.findItem(Phlogiston.MODID, BlockWraithstone.UL_BURNING_NAME);
        ModelResourceLocation itemBurningWraithstoneMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockWraithstone.UL_BURNING_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBurningWraithstone, DEFAULT_ITEM_SUBTYPE, itemBurningWraithstoneMRL);

        Item itemRetort = GameRegistry.findItem(Phlogiston.MODID, BlockRetort.UL_NAME);
        ModelResourceLocation itemRetortMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockRetort.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemRetort, DEFAULT_ITEM_SUBTYPE, itemRetortMRL);

        RenderingRegistry.registerEntityRenderingHandler(EntityGentledBat.class, new RenderGentledBat(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledChicken.class, new RenderGentledChicken(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledCow.class, new RenderGentledCow(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledHorse.class, new RenderGentledHorse(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledOcelot.class, new RenderGentledOcelot(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledPig.class, new RenderGentledPig(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledSheep.class, new RenderGentledSheep(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledVillager.class, new RenderGentledVillager(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledWolf.class, new RenderGentledWolf(Minecraft.getMinecraft().getRenderManager()));
    }
}
