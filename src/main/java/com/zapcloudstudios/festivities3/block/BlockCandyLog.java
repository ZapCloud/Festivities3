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
	public BlockCandyLog()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS_PROP, BlockLog.EnumAxis.Y));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(AXIS_PROP, BlockLog.EnumAxis.values()[(meta >> 0) & 3]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i += ((BlockLog.EnumAxis) state.getValue(AXIS_PROP)).ordinal();
		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AXIS_PROP });
	}
}
