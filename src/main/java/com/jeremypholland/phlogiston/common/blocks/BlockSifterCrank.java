package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifter;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifterCrank;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
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
        applyToSifter(worldIn, pos);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        System.out.println("DESTROYING");
        TileEntitySifter tes = getAvailableSifter(worldIn, pos);
        System.out.println("SIFTER: " + tes);
        if (tes != null) tes.setCrankFacing(null);
    }

    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        TileEntitySifter tes = getAvailableSifter(worldIn, pos);
        if (tes != null) tes.setCrankFacing(null);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        applyToSifter(worldIn, pos);
    }

    private void applyToSifter(World worldIn, BlockPos pos) {
        EnumFacing facing = getAvailableSifterFacing(worldIn, pos);
        if (facing != null) {
            TileEntity te = worldIn.getTileEntity(pos.offset(facing));
            TileEntitySifter tes = (TileEntitySifter)te;
            if (tes == null || tes.hasCrank() || !tes.hasScreen())
                dropCrank(worldIn, pos);
            else {
                TileEntitySifterCrank tec = (TileEntitySifterCrank)worldIn.getTileEntity(pos);
                tec.setSifterFacing(facing);
                tes.setCrankFacing(facing.getOpposite());
            }
        } else
            dropCrank(worldIn, pos);
    }

    private TileEntitySifter getAvailableSifter(World worldIn, BlockPos pos) {
        EnumFacing facing = getAvailableSifterFacing(worldIn, pos);
        System.out.println("GAS FACING: " + facing);
        if (facing != null) {
            System.out.println("GAS FACING FOUND: " + facing);
            TileEntity te = worldIn.getTileEntity(pos.offset(facing));
            if (te != null)
                return (TileEntitySifter)te;
        }
        return null;
    }

    private EnumFacing getAvailableSifterFacing(World worldIn, BlockPos pos) {
        EnumFacing[] potentialSifters = {
                EnumFacing.NORTH,
                EnumFacing.EAST,
                EnumFacing.SOUTH,
                EnumFacing.WEST,
        };
        for (EnumFacing facing : potentialSifters) {
            System.out.println("FACING: " + facing);
            TileEntity te = worldIn.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntitySifter) {
                System.out.println("FACING FOUND: " + facing);
                return facing;
            }
        }
        return null;
    }

    private void dropCrank(World worldIn, BlockPos pos) {
        TileEntitySifter tes = getAvailableSifter(worldIn, pos);
        TileEntitySifterCrank tec = (TileEntitySifterCrank)worldIn.getTileEntity(pos);
        tec.setSifterFacing(null);
        if (tes != null) tes.setCrankFacing(null);
        worldIn.destroyBlock(pos, true);
        worldIn.markBlockForUpdate(pos);
    }
}
