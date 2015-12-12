package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by jeremy on 12/12/15.
 */
public class BlockRetort extends Block {
    public static final float HARDNESS = 0.5F;
    public static final String UL_NAME = "retort";

    public BlockRetort(boolean isBurning) {
        super(Material.rock);
        setHardness(HARDNESS);
        setUnlocalizedName(UL_NAME);
        setHarvestLevel(Phlogiston.TOOL_PICKAXE, 2);
        setStepSound(Block.soundTypeStone);
        if (!isBurning) {
            setCreativeTab(CreativeTabs.tabBlock);
        } else {
            setTickRandomly(true);
        }
    }
}
