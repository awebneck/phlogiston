package com.jeremypholland.phlogiston.common;

import com.jeremypholland.phlogiston.Phlogiston;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by jeremy on 12/7/15.
 */
public class Recipes {
    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(Phlogiston.wraithstone, 1), new Object[]{
                "AAA",
                "AAA",
                "AAA",
                'A', Blocks.cobblestone
        });
    }
}
