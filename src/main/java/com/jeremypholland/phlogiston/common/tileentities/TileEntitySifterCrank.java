package com.jeremypholland.phlogiston.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * Created by jeremy on 12/14/15.
 */
public class TileEntitySifterCrank extends TileEntityBase {
    public static final String UL_NAME = "TEsifter_crank";

    private EnumFacing sifterFacing;

    @Override
    protected void readCustomNBT(NBTTagCompound compound) {
        int facing = compound.getInteger("sifterFacing");
        if (facing == -1)
            sifterFacing = null;
        else
            sifterFacing = EnumFacing.values()[facing];
    }

    @Override
    protected void writeCustomNBT(NBTTagCompound compound) {
        if (sifterFacing == null)
            compound.setInteger("sifterFacing", -1);
        else
            compound.setInteger("sifterFacing", sifterFacing.getIndex());
    }

    public void setSifterFacing(EnumFacing value) {
        sifterFacing = value;
        markDirty();
    }

    public EnumFacing getSifterFacing() {
        return sifterFacing;
    }
}
