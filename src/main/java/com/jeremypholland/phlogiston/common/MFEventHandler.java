package com.jeremypholland.phlogiston.common;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by jeremy on 12/8/15.
 */
public class MFEventHandler {
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!e.world.isRemote)
            System.out.println("PLAYER INTERACTED");
    }
}
