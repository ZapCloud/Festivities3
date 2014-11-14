package com.zapcloudstudios.utils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class FestiveUtils
{
	public static byte[] getBytesFromGLTexture(int textureid, int width, int height)
	{
		byte[] pixels = new byte[width * height * 4];
		ByteBuffer buffer = ByteBuffer.allocateDirect(pixels.length).order(ByteOrder.nativeOrder());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureid);
		GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		buffer.get(pixels);
		return pixels;
	}
	
	public static int[] convertBytesRGBAToIntsARGB(byte[] bytes)
	{
		int[] ints = new int[bytes.length / 4];
		for (int i = 0; i < ints.length; i++)
		{
			int j = i * 4;
			int r = bytes[j] & 0xFF;
			int g = bytes[j + 1] & 0xFF;
			int b = bytes[j + 2] & 0xFF;
			int a = bytes[j + 3] & 0xFF;
			ints[i] = (a << 24) | (r << 16) | (g << 8) | b;
		}
		return ints;
	}
	
	public static int[] getIntsFromGLTexture(int textureid, int width, int height)
	{
		return convertBytesRGBAToIntsARGB(getBytesFromGLTexture(textureid, width, height));
	}
	
	public static BufferedImage getImageFromGLTexture(int textureid)
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureid);
		int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
		int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, width, height, getIntsFromGLTexture(textureid, width, height), 0, width);
		return img;
	}
	
	public static int getDyeColor(int meta)
	{
		return ItemDye.field_150922_c[meta];
	}
	
	public static int getDyeR(int meta)
	{
		return (getDyeColor(meta) >> 16) & 0xFF;
	}
	
	public static int getDyeG(int meta)
	{
		return (getDyeColor(meta) >> 8) & 0xFF;
	}
	
	public static int getDyeB(int meta)
	{
		return (getDyeColor(meta) >> 0) & 0xFF;
	}
	
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
	
	public static String[] wrapString(String string, int chars)
	{
		String[] words = string.split(" ");
		ArrayList<String> lines = new ArrayList<String>();
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
