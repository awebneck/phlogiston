package com.jeremypholland.phlogiston.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by jeremy on 12/12/15.
 */
public class ItemPowderedCinnabar extends Item {
    public static final String UL_NAME = "powdered_cinnabar";

    public ItemPowderedCinnabar() {
        super();
        setUnlocalizedName(UL_NAME);
        setCreativeTab(CreativeTabs.tabMisc);
    }
}
