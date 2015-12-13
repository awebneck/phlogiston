package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.entities.Gentler;
import com.jeremypholland.phlogiston.common.tileentities.TileEntityRetort;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by jeremy on 12/6/15.
 */
public class BlockWraithstone extends Block implements ITileEntityProvider {
    public static final float HARDNESS = 0.5F;
    public static final String UL_NAME = "wraithstone";
    public static final String UL_BURNING_NAME = "burning_wraithstone";

    private boolean isBurning = false;

    public BlockWraithstone(boolean isBurning) {
        super(Material.rock);
        this.isBurning = isBurning;
        setHardness(HARDNESS);
        setUnlocalizedName(UL_NAME);
        setHarvestLevel(Phlogiston.TOOL_PICKAXE, 2);
        setStepSound(Block.soundTypeStone);
        if (!isBurning) {
            setCreativeTab(CreativeTabs.tabBlock);
        } else {
            setTickRandomly(true);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRetort();
    }

    @Override
    public int tickRate(World worldIn) {
        return 30;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        BlockPos below = pos.down(1);
        if (worldIn.getBlockState(below).getBlock() == Blocks.fire)
            return Phlogiston.burningWraithstone.getDefaultState();
        else
            return Phlogiston.wraithstone.getDefaultState();
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        checkIsBurning(worldIn, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.randomDisplayTick(worldIn, pos, state, rand);
        if (isBurning)
            spawnParticles(worldIn, pos);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Phlogiston.wraithstone);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote && isBurning) {
            AxisAlignedBB aabb = new AxisAlignedBB(pos.west(5).north(5).down(5), pos.east(5).south(5).up(5));
            List entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, aabb);
            for (Object entity : entities) {
                EntityLiving entityLiving = (EntityLiving)entity;
                Gentler.gentleEntity(entityLiving, worldIn);
            }
        }
    }

    private void checkIsBurning(World worldIn, BlockPos pos) {
        if (!worldIn.isRemote) {
            BlockPos below = pos.down(1);
            if (worldIn.getBlockState(below).getBlock() == Blocks.fire)
                worldIn.setBlockState(pos, Phlogiston.burningWraithstone.getDefaultState());
            else
                worldIn.setBlockState(pos, Phlogiston.wraithstone.getDefaultState());
        }
    }

    private void spawnParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 5; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).getBlock().isOpaqueCube())
            {
                d2 = (double)pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.south()).getBlock().isOpaqueCube())
            {
                d3 = (double)pos.getZ() + d0 + 1.0D;
            }

            if (i == 2 && !worldIn.getBlockState(pos.north()).getBlock().isOpaqueCube())
            {
                d3 = (double)pos.getZ() - d0;
            }

            if (i == 3 && !worldIn.getBlockState(pos.east()).getBlock().isOpaqueCube())
            {
                d1 = (double)pos.getX() + d0 + 1.0D;
            }

            if (i == 4 && !worldIn.getBlockState(pos.west()).getBlock().isOpaqueCube())
            {
                d1 = (double)pos.getX() - d0;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

}
