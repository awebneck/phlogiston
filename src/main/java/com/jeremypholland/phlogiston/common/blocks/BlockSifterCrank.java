package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifter;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifterCrank;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Created by jeremy on 12/14/15.
 */
public class BlockSifterCrank extends Block implements ITileEntityProvider {
    public static final String UL_NAME = "sifter_crank";

    public BlockSifterCrank() {
        super(Material.iron);
        setLightOpacity(0);
        setUnlocalizedName(UL_NAME);
        setHarvestLevel(Phlogiston.TOOL_PICKAXE, 0);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySifterCrank();
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isVisuallyOpaque() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        // TileEntitySifter tes = getAvailableSifter(worldIn, pos);
        // if (tes == null)
        //     dropCrank(worldIn, pos);
        // else
        //     tes.setHasCrank(true);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        // TileEntitySifter tes = getAvailableSifter(worldIn, pos);
        // if (tes != null) tes.setHasCrank(false);
    }

    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        // TileEntitySifter tes = getAvailableSifter(worldIn, pos);
        // if (tes != null) tes.setHasCrank(false);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        // EnumFacing facing = getAvailableSifter(worldIn, pos);
        // if (tes == null)
        //     dropCrank(worldIn, pos);
        // else
        //     tes.setHasCrank(true);
    }

    private EnumFacing getAvailableSifter(World worldIn, BlockPos pos) {
        EnumFacing[] potentialSifters = {
                EnumFacing.NORTH,
                EnumFacing.EAST,
                EnumFacing.SOUTH,
                EnumFacing.WEST,
        };
        for (EnumFacing facing : potentialSifters) {
            TileEntity te = worldIn.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntitySifter) {
                TileEntitySifter tes = (TileEntitySifter)te;
                if (!tes.hasCrank())
                    return facing;
            }
        }
        return null;
    }

    private void dropCrank(World worldIn, BlockPos pos) {
        worldIn.destroyBlock(pos, true);
        worldIn.markBlockForUpdate(pos);
    }
}
