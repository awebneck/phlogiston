package com.jeremypholland.phlogiston.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by jeremy on 12/13/15.
 */
public class TileEntitySifter extends TileEntityBase {
    public static final String UL_NAME = "TEsifter";
    private int numChutes = 0;

    @Override
    protected void readCustomNBT(NBTTagCompound compound) {
        numChutes = compound.getInteger("numChutes");
    }

    @Override
    protected void writeCustomNBT(NBTTagCompound compound) {
        compound.setInteger("numChutes", numChutes);
    }

    public void setNumChutes(int numChutes) {
        this.numChutes = numChutes;
        markDirty();
    }
}
