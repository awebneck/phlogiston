package com.jeremypholland.phlogiston.client;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.client.render.*;
import com.jeremypholland.phlogiston.common.CommonProxy;
import com.jeremypholland.phlogiston.common.blocks.*;
import com.jeremypholland.phlogiston.common.entities.*;
import com.jeremypholland.phlogiston.common.items.ItemAlchemicalPestle;
import com.jeremypholland.phlogiston.common.items.ItemPowderedCinnabar;
import com.jeremypholland.phlogiston.common.items.ItemPowderedIron;
import com.jeremypholland.phlogiston.common.items.ItemPowderedSilver;
import com.jeremypholland.phlogiston.common.tileentities.TileEntityAlchemicalMortar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

        Item itemAlchemicalMortar = GameRegistry.findItem(Phlogiston.MODID, BlockAlchemicalMortar.UL_NAME);
        ModelResourceLocation itemAlchemicalMortarMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockAlchemicalMortar.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemAlchemicalMortar, DEFAULT_ITEM_SUBTYPE, itemAlchemicalMortarMRL);

        Item itemSifterChute = GameRegistry.findItem(Phlogiston.MODID, BlockSifterChute.UL_NAME);
        ModelResourceLocation itemSifterChuteMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockSifterChute.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSifterChute, DEFAULT_ITEM_SUBTYPE, itemSifterChuteMRL);

        Item itemSifterChuteContinuous = GameRegistry.findItem(Phlogiston.MODID, BlockSifterChute.UL_CONTINUOUS_NAME);
        ModelResourceLocation itemSifterChuteContinuousMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockSifterChute.UL_CONTINUOUS_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSifterChuteContinuous, DEFAULT_ITEM_SUBTYPE, itemSifterChuteContinuousMRL);

        Item itemSifterBasin = GameRegistry.findItem(Phlogiston.MODID, BlockSifterBasin.UL_NAME);
        ModelResourceLocation itemSifterBasinMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockSifterBasin.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSifterBasin, DEFAULT_ITEM_SUBTYPE, itemSifterBasinMRL);

        Item itemSifterFunnel = GameRegistry.findItem(Phlogiston.MODID, BlockSifterFunnel.UL_NAME);
        ModelResourceLocation itemSifterFunnelMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockSifterFunnel.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSifterFunnel, DEFAULT_ITEM_SUBTYPE, itemSifterFunnelMRL);

        Item itemAlchemicalPestle = GameRegistry.findItem(Phlogiston.MODID, ItemAlchemicalPestle.UL_NAME);
        ModelResourceLocation itemAlchemicalPestleMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + ItemAlchemicalPestle.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemAlchemicalPestle, DEFAULT_ITEM_SUBTYPE, itemAlchemicalPestleMRL);

        Item itemCinnabarOre = GameRegistry.findItem(Phlogiston.MODID, BlockCinnabarOre.UL_NAME);
        ModelResourceLocation itemCinnabarOreMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockCinnabarOre.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemCinnabarOre, DEFAULT_ITEM_SUBTYPE, itemCinnabarOreMRL);

        Item itemSilverOre = GameRegistry.findItem(Phlogiston.MODID, BlockSilverOre.UL_NAME);
        ModelResourceLocation itemSilverOreMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + BlockSilverOre.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSilverOre, DEFAULT_ITEM_SUBTYPE, itemSilverOreMRL);

        Item itemPowderedIron = GameRegistry.findItem(Phlogiston.MODID, ItemPowderedIron.UL_NAME);
        ModelResourceLocation itemPowderedIronMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + ItemPowderedIron.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemPowderedIron, DEFAULT_ITEM_SUBTYPE, itemPowderedIronMRL);

        Item itemPowderedCinnabar = GameRegistry.findItem(Phlogiston.MODID, ItemPowderedCinnabar.UL_NAME);
        ModelResourceLocation itemPowderedCinnabarMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + ItemPowderedCinnabar.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemPowderedCinnabar, DEFAULT_ITEM_SUBTYPE, itemPowderedCinnabarMRL);

        Item itemPowderedSilver = GameRegistry.findItem(Phlogiston.MODID, ItemPowderedSilver.UL_NAME);
        ModelResourceLocation itemPowderedSilverMRL = new ModelResourceLocation(Phlogiston.MODID + ":" + ItemPowderedSilver.UL_NAME, INVENTORY);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemPowderedSilver, DEFAULT_ITEM_SUBTYPE, itemPowderedSilverMRL);

        RenderingRegistry.registerEntityRenderingHandler(EntityGentledBat.class, new RenderGentledBat(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledChicken.class, new RenderGentledChicken(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledCow.class, new RenderGentledCow(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledHorse.class, new RenderGentledHorse(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledOcelot.class, new RenderGentledOcelot(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledPig.class, new RenderGentledPig(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledSheep.class, new RenderGentledSheep(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledVillager.class, new RenderGentledVillager(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGentledWolf.class, new RenderGentledWolf(Minecraft.getMinecraft().getRenderManager()));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemicalMortar.class, new RenderAlchemicalMortar());
    }
}
