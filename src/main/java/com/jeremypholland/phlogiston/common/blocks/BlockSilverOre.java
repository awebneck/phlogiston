package com.jeremypholland.phlogiston.common.blocks;

import net.minecraft.block.BlockOre;

/**
 * Created by jeremy on 12/12/15.
 */
public class BlockSilverOre extends BlockOre {
    public static final String UL_NAME = "silverOre";

    public BlockSilverOre() {
        super();
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypePiston);
        setUnlocalizedName(UL_NAME);
    }
}
