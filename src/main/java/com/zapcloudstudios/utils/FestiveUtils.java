package com.zapcloudstudios.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class FestiveUtils
{
	public static ItemStack addData(ItemStack itemstack, NBTTagCompound data)
	{
		if (data != null)
		{
			itemstack.setTagCompound(data);
		}
		return itemstack;
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, double x, double y, double z, float velocity)
	{
		if (!world.isRemote)
		{
			EntityItem entityitem = new EntityItem(world, x, y, z, itemstack);

			entityitem.motionX = rand.nextGaussian() * velocity;
			entityitem.motionY = rand.nextGaussian() * velocity + 0.2D;
			entityitem.motionZ = rand.nextGaussian() * velocity;
			world.spawnEntityInWorld(entityitem);
		}
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, int x, int y, int z, float velocity)
	{
		dropItem(world, itemstack, rand, x + 0.5D, y + 0.5D, z + 0.5D, velocity);
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, int x, int y, int z, float randomoffset, float velocity)
	{
		dropItem(world, itemstack, rand, x + 0.5D, y + 0.5D, z + 0.5D, randomoffset, velocity);
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, double x, double y, double z, float randomoffset, float velocity)
	{
		float off = (1.0F - randomoffset) / 2.0F;
		x += rand.nextFloat() * randomoffset + off;
		y += rand.nextFloat() * randomoffset + off;
		z += rand.nextFloat() * randomoffset + off;
		dropItem(world, itemstack, rand, x, y, z, velocity);
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, int x, int y, int z, int mincount, int maxcount, float randomoffset, float velocity)
	{
		dropItem(world, itemstack, rand, x + 0.5D, y + 0.5D, z + 0.5D, mincount, maxcount, randomoffset, velocity);
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, double x, double y, double z, int mincount, int maxcount, float randomoffset, float veclocity)
	{
		if (itemstack == null)
		{
			return;
		}
		ItemStack newstack = itemstack.copy();
		float off = (1.0F - randomoffset) / 2.0F;
		x += rand.nextFloat() * randomoffset + off;
		y += rand.nextFloat() * randomoffset + off;
		z += rand.nextFloat() * randomoffset + off;
		while (newstack.stackSize > 0)
		{
			int num = rand.nextInt(maxcount - mincount) + mincount;

			if (num > newstack.stackSize)
			{
				num = newstack.stackSize;
			}

			newstack.stackSize -= num;
			dropItem(world, addData(new ItemStack(newstack.getItem(), num, newstack.getItemDamage()), newstack.getTagCompound()), rand, x, y, z, veclocity);
		}
	}

	public static String[] mergeStringArrays(String[] ar1, String[] ar2)
	{
		String[] ar = new String[ar1.length + ar2.length];
		int j = 0;
		for (int i = 0; i < ar1.length; i++)
		{
			ar[j] = ar1[i];
			j++;
		}
		for (int i = 0; i < ar2.length; i++)
		{
			ar[j] = ar2[i];
			j++;
		}
		return ar;
	}

	public static String[] wrapString(String string, int chars)
	{
		String[] words = string.split(" ");
		List<String> lines = new ArrayList<String>();
		String line = "";

		for (int i = 0; i < words.length; i++)
		{
			String word = words[i];

			if (line.length() + word.length() + 1 > chars && !line.isEmpty())
			{
				lines.add(line);
				line = "";
			}

			if (!line.isEmpty())
			{
				line += " ";
			}

			line += word;
		}

		lines.add(line);

		return lines.toArray(new String[0]);
	}
}
