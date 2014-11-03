package com.zapcloudstudios.festivities3.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSnowglobe extends ItemBlock
{
	public ItemSnowglobe(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String portal = ".default";
		if (stack.getItemDamage() % 2 == 1)
		{
			portal = ".portal";
		}
		return this.field_150939_a.getUnlocalizedName() + portal;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return stack.getItemDamage() % 2 == 1;
	}
	
	@Override
	public int getMetadata(int damage)
	{
		return damage % 2;
	}
}
