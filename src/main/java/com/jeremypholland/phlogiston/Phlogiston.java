package com.jeremypholland.phlogiston;

import com.jeremypholland.phlogiston.common.CommonProxy;
import com.jeremypholland.phlogiston.common.FMLEventHandler;
import com.jeremypholland.phlogiston.common.MFEventHandler;
import com.jeremypholland.phlogiston.common.Recipes;
import com.jeremypholland.phlogiston.common.blocks.Wraithstone;
import com.jeremypholland.phlogiston.common.entities.*;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        syncConfig(new Configuration(e.getSuggestedConfigurationFile()));
        acDir = new File(e.getModConfigurationDirectory().getParentFile(), "/Phlogiston/");
        if (!acDir.exists()) {
            acDir.mkdirs();
            acDir.mkdir();
        }
        wraithstone = new Wraithstone(false);
        GameRegistry.registerBlock(wraithstone, Wraithstone.UL_NAME);
        burningWraithstone = new Wraithstone(true);
        GameRegistry.registerBlock(burningWraithstone, null, Wraithstone.UL_BURNING_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
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
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }

    private void syncConfig(Configuration config) {
    }
}
