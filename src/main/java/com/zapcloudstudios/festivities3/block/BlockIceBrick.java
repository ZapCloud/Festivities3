package com.zapcloudstudios.festivities3.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIceBrick extends Block
{
	public static final PropertyEnum VARIANT_PROP = EnumBrickType.makeProperty();

	public BlockIceBrick()
	{
		super(Material.ice);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, EnumBrickType.COBBLE));
	}

	public int damageDropped(IBlockState state)
	{
		return ((EnumBrickType) state.getValue(VARIANT_PROP)).getMetaFromState();
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		EnumBrickType[] types = EnumBrickType.values();

		for (int i = 0; i < types.length; ++i)
		{
			EnumBrickType enumtype = types[i];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetaFromState()));
		}
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT_PROP, EnumBrickType.getStateFromMeta(meta));
	}

	public int getMetaFromState(IBlockState state)
	{
		return ((EnumBrickType) state.getValue(VARIANT_PROP)).getMetaFromState();
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT_PROP });
	}

}
