package com.zapcloudstudios.festivities3.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.tile.SnowglobeScene;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlake extends ItemFestive
{
	public ItemFlake()
	{
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		int num = SnowglobeScene.map.size();
		for (int i = 0; i <= num; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public ItemStack getContainerItem(ItemStack item)
	{
		if (item.getItemDamage() == 0)
		{
			return null;
		}
		return new ItemStack(Festivities.flake, 1, 0);
	}

	@Override
	public boolean hasContainerItem(ItemStack item)
	{
		return item.getItemDamage() != 0;

	}
}
