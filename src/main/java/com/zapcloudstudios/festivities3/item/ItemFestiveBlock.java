package com.zapcloudstudios.festivities3.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.ITipItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFestiveBlock extends ItemBlock implements ITipItem
{
	private String[] tip = null;
	private String[] shifttip = null;
	
	protected String itemIconName = null;
	
	private boolean useItemTexture = false;
	
	public ItemFestiveBlock(Block block)
	{
		super(block);
	}
	
	public ItemFestiveBlock setToUseItemTextureMap()
	{
		this.useItemTexture = true;
		return this;
	}
	
	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean advanced)
	{
		if (this.tip == null)
		{
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block instanceof ITipItem)
			{
				Festivities.addInformation((ITipItem) block, stack, player, info, advanced);
			}
		}
		else
		{
			Festivities.addInformation(this, stack, player, info, advanced);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getSpriteNumber()
	{
		if (this.useItemTexture)
		{
			return 1;
		}
		else
		{
			return super.getSpriteNumber();
		}
	}
	
	public ItemFestiveBlock setTip(String... tip)
	{
		this.tip = tip;
		return this;
	}
	
	public ItemFestiveBlock setShiftTip(String... tip)
	{
		this.shifttip = tip;
		return this;
	}
	
	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return this.tip;
	}
	
	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return this.shifttip;
	}
}
