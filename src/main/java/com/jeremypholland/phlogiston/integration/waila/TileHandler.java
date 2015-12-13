package com.jeremypholland.phlogiston.integration.waila;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.blocks.BlockSifterChute;
import com.jeremypholland.phlogiston.common.blocks.BlockWraithstone;
import mcp.mobius.waila.api.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Optional;

/**
 * Created by jeremy on 12/11/15.
 */
@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class TileHandler implements IWailaDataProvider {

    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register) {
        TileHandler instance = new TileHandler();
        register.registerStackProvider(instance, BlockWraithstone.class);
        register.registerStackProvider(instance, BlockSifterChute.class);
    }

    @Override
    @Optional.Method(modid = "Waila")
    public NBTTagCompound getNBTData(TileEntity te, NBTTagCompound tag, IWailaDataAccessorServer accessor) {
        return tag;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ITaggedList.ITipList getWailaHead(ItemStack itemStack, ITaggedList.ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ITaggedList.ITipList getWailaBody(ItemStack itemStack, ITaggedList.ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ITaggedList.ITipList getWailaTail(ItemStack itemStack, ITaggedList.ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getBlock() == Phlogiston.burningWraithstone)
            return new ItemStack(Phlogiston.wraithstone);
        else if (accessor.getBlock() == Phlogiston.sifterChuteContinuous)
            return new ItemStack(Phlogiston.sifterChute);
        else
            return accessor.getStack();
    }
}
