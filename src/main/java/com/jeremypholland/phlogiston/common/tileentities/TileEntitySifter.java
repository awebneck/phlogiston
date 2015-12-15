package com.jeremypholland.phlogiston.common.tileentities;

import com.jeremypholland.phlogiston.Phlogiston;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * Created by jeremy on 12/13/15.
 */
public class TileEntitySifter extends TileEntityBase {
    public static final String UL_NAME = "TEsifter";
    private int numChutes = 0;
    private boolean hasScreen = false;
    private EnumFacing crankFacing;

    public boolean hasScreen() {
        return hasScreen;
    }

    public boolean hasCrank() {
        return crankFacing != null;
    }

    public void setCrankFacing(EnumFacing value) {
        crankFacing = value;
        markDirty();
    }

    @Override
    protected void readCustomNBT(NBTTagCompound compound) {
        numChutes = compound.getInteger("numChutes");
        hasScreen = compound.getBoolean("hasScreen");
        int facing = compound.getInteger("crankFacing");
        if (facing == -1)
            crankFacing = null;
        else
            crankFacing = EnumFacing.values()[facing];
    }

    @Override
    protected void writeCustomNBT(NBTTagCompound compound) {
        compound.setInteger("numChutes", numChutes);
        compound.setBoolean("hasScreen", hasScreen);
        if (crankFacing == null)
            compound.setInteger("crankFacing", -1);
        else
            compound.setInteger("crankFacing", crankFacing.getIndex());
    }

    public boolean setScreen(ItemStack stack) {
        if (stack.getItem() == Phlogiston.alScreen) {
            hasScreen = true;
            stack.stackSize--;
            markDirty();
            return true;
        }
        return false;
    }

    public void setNumChutes(int numChutes) {
        this.numChutes = numChutes;
        markDirty();
    }
}
