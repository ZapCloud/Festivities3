package com.zapcloudstudios.festivities3.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.Festivities;

public class ItemSnowmanParts extends ItemFestive
{
	public ItemSnowmanParts()
	{
		super();
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz)
	{
		if (!player.canPlayerEdit(x, y, z, side, stack))
		{
			return false;
		}
		if (world.getBlock(x, y, z) == Blocks.snow)
		{
			boolean flag = true;
			if (world.getBlock(x, y + 1, z) == Blocks.snow)
			{

			}
			else if (world.getBlock(x, y - 1, z) == Blocks.snow)
			{
				y -= 1;
			}
			else
			{
				flag = false;
			}
			if (flag)
			{
				if (!player.canPlayerEdit(x, y, z, side, stack) || !player.canPlayerEdit(x, y + 1, z, side, stack))
				{
					return false;
				}
				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x, y + 1, z);
				this.placeBlockAt(stack, player, world, x, y, z, Festivities.snowman, 0);
				stack.stackSize--;
				return true;
			}
		}
		return false;
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, Block block, int metadata)
	{
		world.setBlock(x, y, z, block, metadata, 3);
		block.onBlockPlacedBy(world, x, y, z, player, stack);
		block.onPostBlockPlaced(world, x, y, z, metadata);
		return true;
	}
}
