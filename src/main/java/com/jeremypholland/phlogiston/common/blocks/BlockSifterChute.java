package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by jeremy on 12/12/15.
 */
public class BlockSifterChute extends Block {
    public static final float HARDNESS = 0.5F;
    public static final String UL_NAME = "sifter_chute";
    public static final String UL_CONTINUOUS_NAME = "sifter_chute_continuous";

    private boolean continuous;

    public BlockSifterChute(boolean continuous) {
        super(Material.rock);
        this.continuous = continuous;
        setHardness(HARDNESS);
        setUnlocalizedName(UL_NAME);
        setHarvestLevel(Phlogiston.TOOL_PICKAXE, 2);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        Block above = worldIn.getBlockState(pos.up(1)).getBlock();
        IBlockState state;
        if (above == Phlogiston.sifterChute ||
                above == Phlogiston.sifterChuteContinuous) {
            state = Phlogiston.sifterChuteContinuous.getDefaultState();
        } else {
            state =  Phlogiston.sifterChute.getDefaultState();
        }
        updateChuteLength(worldIn, pos);
        return state;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        checkContinuity(worldIn, pos);
        updateChuteLength(worldIn, pos);
    }

    private void updateChuteLength(World worldIn, BlockPos pos) {
        if (!worldIn.isRemote) {
            TileEntitySifter sifter = getSifterTE(worldIn, pos);
            if (sifter != null) {
                sifter.setNumChutes(getChuteLength(worldIn, getTopChutePos(worldIn, pos)));
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        return true;
    }

    private TileEntitySifter getSifterTE(World worldIn, BlockPos pos) {
        BlockPos topChutePos = getTopChutePos(worldIn, pos);
        BlockPos funnelPos = getFunnelPos(worldIn, topChutePos);
        if (funnelPos != null) {
            return (TileEntitySifter)worldIn.getTileEntity(funnelPos);
        } else {
            return null;
        }
    }

    private BlockPos getTopChutePos(World worldIn, BlockPos pos) {
        BlockPos abovePos = pos.up(1);
        Block above = worldIn.getBlockState(abovePos).getBlock();
        while (above == Phlogiston.sifterChute ||
                above == Phlogiston.sifterChuteContinuous) {
            abovePos = abovePos.up(1);
            above = worldIn.getBlockState(abovePos).getBlock();
        }
        return abovePos.down(1);
    }

    private int getChuteLength(World worldIn, BlockPos pos) {
        BlockPos belowPos = pos.down(1);
        Block below = worldIn.getBlockState(belowPos).getBlock();
        int length = 1;
        while (below == Phlogiston.sifterChute ||
                below == Phlogiston.sifterChuteContinuous) {
            belowPos = belowPos.down(1);
            below = worldIn.getBlockState(belowPos).getBlock();
            length++;
        }
        return length;
    }

    private BlockPos getFunnelPos(World worldIn, BlockPos pos) {
        BlockPos belowPos = pos.down(1);
        Block below = worldIn.getBlockState(belowPos).getBlock();
        while (below == Phlogiston.sifterChute ||
                below == Phlogiston.sifterChuteContinuous) {
            belowPos = belowPos.down(1);
            below = worldIn.getBlockState(belowPos).getBlock();
        }
        if (below == Phlogiston.sifterFunnel)
            return belowPos;
        else
            return null;
    }

    private void checkContinuity(World worldIn, BlockPos pos) {
        Block above = worldIn.getBlockState(pos.up(1)).getBlock();
        if (above == Phlogiston.sifterChute ||
                above == Phlogiston.sifterChuteContinuous) {
            worldIn.setBlockState(pos, Phlogiston.sifterChuteContinuous.getDefaultState());
        } else {
            worldIn.setBlockState(pos, Phlogiston.sifterChute.getDefaultState());
        }
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
