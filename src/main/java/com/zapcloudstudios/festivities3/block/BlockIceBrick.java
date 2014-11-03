package com.zapcloudstudios.festivities3.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIceBrick extends Block
{
	public BlockIceBrick()
	{
		super(Material.ice);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
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
}
