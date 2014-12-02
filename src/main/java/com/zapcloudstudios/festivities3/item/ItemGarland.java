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

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (stack.getItemDamage() == 0)
		{
			return super.getUnlocalizedName() + ".spruce";
		}
		else
		{
			return super.getUnlocalizedName() + ".bell";
		}
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
		meta = (stack.getItemDamage() & 1) << 3;
		if (side == 2)
		{
			meta |= 1;
		}
		else if (side == 3)
		{
			meta |= 3;
		}
		else if (side == 4)
		{
			meta |= 0;
		}
		else if (side == 5)
		{
			meta |= 2;
		}
		else
		{
			meta |= 4;
			meta |= MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F - 0.5D) & 1;
			meta |= (side & 1) << 1;
		}
		System.out.println(meta);
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, meta);
	}
}
