package com.zapcloudstudios.festivities3;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class CommonProxy
{
	public void grabRenders()
	{
		
	}
	
	public void setItemModel(Item item, int meta, String model)
	{
		
	}
	
	public void setItemModel(Item item, String model)
	{
		this.setItemModel(item, 0, model);
	}
	
	public void setBlockItemModel(Block block, int itemMeta, String model)
	{
		this.setItemModel(Item.getItemFromBlock(block), itemMeta, model);
	}
	
	public void setBlockItemModel(Block block, String model)
	{
		this.setBlockItemModel(block, 0, model);
	}
	
	public String addPrefix(String name)
	{
		int index = name.lastIndexOf(':');
		String oldPrefix = "";
		String prefix = FestivitiesMod.MODID;
		
		if (index != -1)
		{
			oldPrefix = name.substring(0, index);
			name = name.substring(index + 1);
		}
		
		if (!oldPrefix.isEmpty())
		{
			prefix = oldPrefix;
		}
		
		return prefix + ":" + name;
	}
}
