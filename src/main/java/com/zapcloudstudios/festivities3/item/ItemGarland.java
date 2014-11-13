package com.zapcloudstudios.festivities3.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.Festivities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGarland extends ItemFestiveBlock
{
	private Block block;
	
	private IIcon[] icons = new IIcon[2];
	
	public ItemGarland(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setToUseItemTextureMap();
	}
	
	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		int j = MathHelper.clamp_int(par1, 0, 15);
		return this.icons[j];
	}
	
	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int j = 0; j < 2; ++j)
		{
			par3List.add(new ItemStack(par1, 1, j));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.icons[0] = par1IconRegister.registerIcon(Festivities.ID + ":garland" + "_" + "spruce");
		this.icons[1] = par1IconRegister.registerIcon(Festivities.ID + ":garland" + "_" + "bell");
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		if (side == 0)
		{
			meta += 6;
		}
		if (side == 1)
		{
			meta += 4;
		}
		if (side == 0 || side == 1)
		{
			meta += (int) ((player.rotationYaw + 45) / 90) % 2;
		}
		if (side == 2)
		{
			meta += 3;
		}
		if (side == 3)
		{
			meta += 2;
		}
		if (side == 4)
		{
			meta += 1;
		}
		if (side == 5)
		{
			meta += 0;
		}
		
		if (world.setBlock(x, y, z, this.block, meta, 3))
		{
			if (world.getBlock(x, y, z) == this.block)
			{
				this.block.onBlockPlacedBy(world, x, y, z, player, stack);
				this.block.onPostBlockPlaced(world, x, y, z, meta);
			}
			--stack.stackSize;
		}
		
		return true;
	}
}
