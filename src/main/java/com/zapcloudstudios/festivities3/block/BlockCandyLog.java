package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;

public class BlockCandyLog extends BlockLog
{
	public static final PropertyBool MELT_PROP = PropertyBool.create("melt");

	public BlockCandyLog()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS_PROP, BlockLog.EnumAxis.Y).withProperty(MELT_PROP, false));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(MELT_PROP, ((meta >> 2) & 1) != 0).withProperty(AXIS_PROP, BlockLog.EnumAxis.values()[meta & 3]);
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i += ((Boolean) state.getValue(MELT_PROP)) ? 1 : 0;
		i <<= 2;
		i += ((BlockLog.EnumAxis) state.getValue(AXIS_PROP)).ordinal();
		return i;
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AXIS_PROP, MELT_PROP });
	}
}
