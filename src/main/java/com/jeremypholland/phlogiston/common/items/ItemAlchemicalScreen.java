package com.jeremypholland.phlogiston.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by jeremy on 12/13/15.
 */
public class ItemAlchemicalScreen extends Item {
    public static final String UL_NAME = "alchemical_screen";

    public ItemAlchemicalScreen() {
        super();
        setUnlocalizedName(UL_NAME);
        setCreativeTab(CreativeTabs.tabMisc);
    }
}
