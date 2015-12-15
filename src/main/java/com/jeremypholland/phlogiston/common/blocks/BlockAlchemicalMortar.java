package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.tileentities.TileEntityAlchemicalMortar;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by jeremy on 12/12/15.
 */
public class BlockAlchemicalMortar extends Block implements ITileEntityProvider {
    public static final float HARDNESS = 0.5F;
    public static final String UL_NAME = "alchemical_mortar";

    public BlockAlchemicalMortar() {
        super(Material.rock);
        setHardness(HARDNESS);
        setUnlocalizedName(UL_NAME);
        setHarvestLevel(Phlogiston.TOOL_PICKAXE, 2);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityAlchemicalMortar();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityAlchemicalMortar team = (TileEntityAlchemicalMortar)worldIn.getTileEntity(pos);
        if (team != null) {
            if (!team.isCoolingDown() && !worldIn.isRemote) {
                ItemStack equipped = playerIn.getCurrentEquippedItem();
                if (playerIn.isSneaking()) {
                    ItemStack nstack = team.empty();
                    EntityItem stackEntity = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), nstack);
                    worldIn.spawnEntityInWorld(stackEntity);
                } else if (equipped != null &&
                        equipped.getItem() == Phlogiston.alPestle) {
                    return team.grind(equipped);
                } else {
                    ItemStack item = playerIn.getCurrentEquippedItem();
                    return (item != null &&
                            team.fill(item));
                }
            }
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isVisuallyOpaque() {
        return false;
    }
}
