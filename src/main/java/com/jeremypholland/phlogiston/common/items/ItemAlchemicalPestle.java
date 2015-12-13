package com.jeremypholland.phlogiston.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by jeremy on 12/12/15.
 */
public class ItemAlchemicalPestle extends Item {
    public static final String UL_NAME = "alchemical_pestle";
    public static final int MAX_DAMAGE = 500;

    public ItemAlchemicalPestle() {
        super();
        setUnlocalizedName(UL_NAME);
        setCreativeTab(CreativeTabs.tabTools);
        setMaxDamage(MAX_DAMAGE);
        setFull3D();
    }

    @Override
    public boolean doesSneakBypassUse(World world, BlockPos pos, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return (toRepair.getItemDamage() < toRepair.getMaxDamage() &&
                toRepair.getItemDamage() > 0 &&
                repair.getItem() == Items.diamond);
    }
}