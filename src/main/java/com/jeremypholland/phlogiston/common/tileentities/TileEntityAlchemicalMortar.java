package com.jeremypholland.phlogiston.common.tileentities;

import com.jeremypholland.phlogiston.Phlogiston;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremy on 12/12/15.
 */
public class TileEntityAlchemicalMortar extends TileEntityBase implements IUpdatePlayerListBox {
    public static final String UL_NAME = "TEalchemical_mortar";
    public static List<Item> allowedItems;

    static {
        allowedItems = new ArrayList<>(4);
        allowedItems.add(Item.getItemFromBlock(Blocks.iron_ore));
        allowedItems.add(Item.getItemFromBlock(Blocks.gold_ore));
        allowedItems.add(Item.getItemFromBlock(Phlogiston.cinnabarOre));
        allowedItems.add(Item.getItemFromBlock(Phlogiston.silverOre));
    }

    private int filledWith = -1;
    private int ticksFilled = 0;
    private int grindingDamage = 0;
    private int coolDown = 0;
    private GrindFactory grindFactory;

    public TileEntityAlchemicalMortar() {
        super();
        grindFactory = new GrindFactory();
    }

    public int getTicksFilled() {
        return ticksFilled;
    }

    @Override
    protected void readCustomNBT(NBTTagCompound compound) {
        filledWith = compound.getInteger("filledWith");
        grindingDamage = compound.getInteger("grindingDamage");
        coolDown = compound.getInteger("coolDown");
    }

    @Override
    protected void writeCustomNBT(NBTTagCompound compound) {
        compound.setInteger("filledWith", filledWith);
        compound.setInteger("grindingDamage", grindingDamage);
        compound.setInteger("coolDown", coolDown);
    }

    @Override
    public void update() {
        ticksFilled++;
        coolDown--;
        if (coolDown < 0)
            coolDown = 0;
        markDirty();
    }

    public boolean fill(ItemStack stack) {
        if (filledWith == -1 && isGrindable(stack)) {
            filledWith = Item.getIdFromItem(stack.getItem());
            ticksFilled = 0;
            grindingDamage = 0;
            stack.stackSize--;
            coolDown = 20;
            markDirty();
            return true;
        } else {
            return false;
        }
    }

    public boolean grind(ItemStack tool) {
        if (!isFull() || !isGrindable(getStack()))
            return false;
        grindingDamage += 10;
        coolDown = 20;
        if (grindingDamage >= 100) {
            filledWith = Item.getIdFromItem(grindFactory.grind(getStack()).getItem());
            ticksFilled = 0;
            grindingDamage = 0;
            tool.attemptDamageItem(10, worldObj.rand);
        }
        markDirty();
        return true;
    }

    public ItemStack empty() {
        ItemStack stack = getStack();
        filledWith = -1;
        ticksFilled = 0;
        coolDown = 20;
        grindingDamage = 0;
        markDirty();
        return stack;
    }

    public boolean isFull() {
        return filledWith != -1;
    }

    public boolean isCoolingDown() {
        return coolDown > 0;
    }

    public ItemStack getStack() {
        return new ItemStack(Item.getItemById(filledWith));
    }

    public float getDamagePercentage() {
        return (100-grindingDamage)/100.0F;
    }

    private boolean isGrindable(ItemStack stack) {
        return allowedItems.contains(stack.getItem());
    }

    private class GrindFactory {
        public GrindFactory() {
        }

        public ItemStack grind(ItemStack stack) {
            if (stack.getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
                return new ItemStack(Phlogiston.powderedIron);
            } else if (stack.getItem() == Item.getItemFromBlock(Phlogiston.cinnabarOre)) {
                return new ItemStack(Phlogiston.powderedCinnabar);
            } else if (stack.getItem() == Item.getItemFromBlock(Phlogiston.silverOre)) {
                return new ItemStack(Phlogiston.powderedSilver);
            }
            return null;
        }
    }
}
