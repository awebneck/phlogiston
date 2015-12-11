package com.jeremypholland.phlogiston.common;

import com.jeremypholland.phlogiston.Phlogiston;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by jeremy on 12/7/15.
 */
public class FMLEventHandler {
    private boolean blocksHidden = false;

    @SubscribeEvent
    public void onThingCrafted(PlayerEvent.ItemCraftedEvent e) {
        if (e.crafting.getItem() == Item.getItemFromBlock(Phlogiston.wraithstone) &&
                !e.player.worldObj.isRemote) {
            System.out.println("WRAITHSTONE CRAFTED");
        }
    }
}
