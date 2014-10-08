package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCandyLog extends BlockLog
{
	public static final PropertyBool MELT_PROP = PropertyBool.create("melt");

	public BlockCandyLog()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS_PROP, BlockLog.EnumAxis.Y).withProperty(MELT_PROP, Boolean.FALSE));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(MELT_PROP, Boolean.valueOf((meta & 1) > 0)).withProperty(AXIS_PROP, BlockLog.EnumAxis.values()[(meta >> 2) & 3]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i += ((BlockLog.EnumAxis) state.getValue(AXIS_PROP)).ordinal();
		i <<= 2;
		i += ((Boolean) state.getValue(MELT_PROP)).booleanValue() ? 1 : 0;
		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AXIS_PROP, MELT_PROP });
	}
}
