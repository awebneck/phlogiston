package com.jeremypholland.phlogiston;

import com.jeremypholland.phlogiston.common.CommonProxy;
import com.jeremypholland.phlogiston.common.FMLEventHandler;
import com.jeremypholland.phlogiston.common.MFEventHandler;
import com.jeremypholland.phlogiston.common.Recipes;
import com.jeremypholland.phlogiston.common.blocks.*;
import com.jeremypholland.phlogiston.common.entities.*;
import com.jeremypholland.phlogiston.common.items.*;
import com.jeremypholland.phlogiston.common.tileentities.TileEntityAlchemicalMortar;
import com.jeremypholland.phlogiston.common.tileentities.TileEntityRetort;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifter;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifterCrank;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

/**
 * Created by jeremy on 12/6/15.
 */
@Mod(modid= Phlogiston.MODID, name= Phlogiston.MODNAME, version= Phlogiston.MODVERSION)
public class Phlogiston {
    public static final String MODID = "phlogiston";
    public static final String MODNAME = "Phlogiston";
    public static final String MODVERSION = "0.0.1";
    public static final String TOOL_PICKAXE = "pickaxe";

    @Mod.Instance(value= Phlogiston.MODID)
    public static Phlogiston instance;

    @SidedProxy(clientSide = "com.jeremypholland.phlogiston.client.ClientProxy", serverSide = "com.jeremypholland.phlogiston.common.CommonProxy")
    public static CommonProxy proxy;

    public static File acDir;

    public static Block wraithstone;
    public static Block burningWraithstone;
    public static Block retort;
    public static Block alMortar;
    public static Block sifterChute;
    public static Block sifterChuteContinuous;
    public static Block sifterBasin;
    public static Block sifterFunnel;
    public static Block sifterCrank;
    public static Item alPestle;
    public static Item alScreen;
    public static Item powderedIron;
    public static Item powderedCinnabar;
    public static Item powderedSilver;
    public static Block cinnabarOre;
    public static Block silverOre;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        syncConfig(new Configuration(e.getSuggestedConfigurationFile()));

        acDir = new File(e.getModConfigurationDirectory().getParentFile(), "/Phlogiston/");
        if (!acDir.exists()) {
            acDir.mkdirs();
            acDir.mkdir();
        }

        powderedIron = new ItemPowderedIron();
        GameRegistry.registerItem(powderedIron, ItemPowderedIron.UL_NAME);
        powderedCinnabar = new ItemPowderedCinnabar();
        GameRegistry.registerItem(powderedCinnabar, ItemPowderedCinnabar.UL_NAME);
        powderedSilver = new ItemPowderedSilver();
        GameRegistry.registerItem(powderedSilver, ItemPowderedSilver.UL_NAME);
        alPestle = new ItemAlchemicalPestle();
        GameRegistry.registerItem(alPestle, ItemAlchemicalPestle.UL_NAME);
        alScreen = new ItemAlchemicalScreen();
        GameRegistry.registerItem(alScreen, ItemAlchemicalScreen.UL_NAME);

        silverOre = new BlockSilverOre();
        GameRegistry.registerBlock(silverOre, BlockSilverOre.UL_NAME);
        cinnabarOre = new BlockCinnabarOre();
        GameRegistry.registerBlock(cinnabarOre, BlockCinnabarOre.UL_NAME);
        wraithstone = new BlockWraithstone(false);
        GameRegistry.registerBlock(wraithstone, BlockWraithstone.UL_NAME);
        burningWraithstone = new BlockWraithstone(true);
        GameRegistry.registerBlock(burningWraithstone, null, BlockWraithstone.UL_BURNING_NAME);
        retort = new BlockRetort();
        GameRegistry.registerBlock(retort, BlockRetort.UL_NAME);
        alMortar = new BlockAlchemicalMortar();
        GameRegistry.registerBlock(alMortar, BlockAlchemicalMortar.UL_NAME);
        sifterChute = new BlockSifterChute(false);
        GameRegistry.registerBlock(sifterChute, BlockSifterChute.UL_NAME);
        sifterChuteContinuous = new BlockSifterChute(true);
        GameRegistry.registerBlock(sifterChuteContinuous, null, BlockSifterChute.UL_CONTINUOUS_NAME);
        sifterBasin = new BlockSifterBasin();
        GameRegistry.registerBlock(sifterBasin, BlockSifterBasin.UL_NAME);
        sifterFunnel = new BlockSifterFunnel();
        GameRegistry.registerBlock(sifterFunnel, BlockSifterFunnel.UL_NAME);
        sifterCrank = new BlockSifterCrank();
        GameRegistry.registerBlock(sifterCrank, BlockSifterCrank.UL_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        GameRegistry.registerTileEntity(TileEntityRetort.class, TileEntityRetort.UL_NAME);
        GameRegistry.registerTileEntity(TileEntityAlchemicalMortar.class, TileEntityAlchemicalMortar.UL_NAME);
        GameRegistry.registerTileEntity(TileEntitySifter.class, TileEntitySifter.UL_NAME);
        GameRegistry.registerTileEntity(TileEntitySifterCrank.class, TileEntitySifterCrank.UL_NAME);
        EntityRegistry.registerModEntity(EntityGentledBat.class, EntityGentledBat.UL_NAME, 0, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledChicken.class, EntityGentledChicken.UL_NAME, 1, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledCow.class, EntityGentledCow.UL_NAME, 2, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledHorse.class, EntityGentledHorse.UL_NAME, 3, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledOcelot.class, EntityGentledOcelot.UL_NAME, 4, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledPig.class, EntityGentledPig.UL_NAME, 5, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledSheep.class, EntityGentledSheep.UL_NAME, 6, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledVillager.class, EntityGentledVillager.UL_NAME, 7, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityGentledWolf.class, EntityGentledWolf.UL_NAME, 8, this, 80, 1, true);
        proxy.load();
        proxy.registerRenderers();
        Recipes.addRecipes();
        MinecraftForge.EVENT_BUS.register(new MFEventHandler());
        FMLCommonHandler.instance().bus().register(new FMLEventHandler());
        FMLInterModComms.sendMessage("Waila", "register", "com.jeremypholland.phlogiston.integration.waila.TileHandler.callbackRegister");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }

    private void syncConfig(Configuration config) {
    }
}
