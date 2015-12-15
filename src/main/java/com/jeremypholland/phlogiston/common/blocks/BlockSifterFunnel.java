package com.jeremypholland.phlogiston.common.blocks;

import com.jeremypholland.phlogiston.Phlogiston;
import com.jeremypholland.phlogiston.common.tileentities.TileEntitySifter;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by jeremy on 12/12/15.
 */
public class BlockSifterFunnel extends Block implements ITileEntityProvider {
    public static final float HARDNESS = 2.2F;
    public static final String UL_NAME = "sifter_funnel";

    public BlockSifterFunnel() {
        super(Material.iron);
        setHardness(HARDNESS);
        setUnlocalizedName(UL_NAME);
        setLightLevel(0);
        setLightOpacity(2);
        setHarvestLevel(Phlogiston.TOOL_PICKAXE, 0);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySifter();
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        System.out.println("LIGHT OPACITY: " + lightOpacity);
        if (!worldIn.isRemote) {
            TileEntitySifter tes = (TileEntitySifter)worldIn.getTileEntity(pos);
            ItemStack equipped = playerIn.getCurrentEquippedItem();
            if (equipped != null && equipped.getItem() == Phlogiston.alScreen && tes != null) {
                return tes.setScreen(equipped);
            }
        }
        return false;
    }
}
