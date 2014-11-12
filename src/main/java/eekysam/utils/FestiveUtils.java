package eekysam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class FestiveUtils
{
	public static void dropItem(World world, ItemStack itemstack, NBTTagCompound nbtdata, Random rand, double x, double y, double z, float vel)
	{
		itemstack.setTagCompound(nbtdata);
		dropItem(world, itemstack, rand, x, y, z, vel);
	}

	public static void dropItem(World world, ItemStack itemstack, Random rand, double x, double y, double z, float vel)
	{
		EntityItem entityitem = new EntityItem(world, x, y, z, itemstack);
		
		entityitem.motionX = rand.nextGaussian() * vel;
		entityitem.motionY = rand.nextGaussian() * vel + 0.2D;
		entityitem.motionZ = rand.nextGaussian() * vel;
		world.spawnEntityInWorld(entityitem);
	}

	public static ItemStack dropItems(World world, ItemStack itemstack, Random rand, double x, double y, double z, int mincount, int maxcount, float var, float vel)
	{
		if (itemstack == null)
		{
			return null;
		}
		ItemStack newstack = itemstack.copy();
		float off = (1.0F - var) / 2.0F;
		x += rand.nextFloat() * var + off;
		y += rand.nextFloat() * var + off;
		z += rand.nextFloat() * var + off;
		while (newstack.stackSize > 0)
		{
			int num = rand.nextInt(maxcount - mincount) + mincount;
			
			if (num > newstack.stackSize)
			{
				num = newstack.stackSize;
			}
			
			newstack.stackSize -= num;
			dropItem(world, new ItemStack(newstack.getItem(), num, newstack.getItemDamage()), newstack.getTagCompound(), rand, x, y, z, vel);
		}
		return newstack;
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
