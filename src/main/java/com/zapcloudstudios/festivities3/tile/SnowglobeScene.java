package com.zapcloudstudios.festivities3.tile;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.zapcloudstudios.festivities3.Festivities;

public class SnowglobeScene
{
	static HashMap<String, SnowglobeScene> map = new HashMap<String, SnowglobeScene>();
	public static SnowglobeScene empty = null;
	public static SnowglobeScene snowTree = new SnowglobeScene("snowWorld", Items.stick, Item.getItemFromBlock(Blocks.log), Item.getItemFromBlock(Blocks.log2), Item.getItemFromBlock(Blocks.planks));
	public static SnowglobeScene candyWorld = new SnowglobeScene("candyWorld", Item.getItemFromBlock(Festivities.candyLog), Festivities.peppermintStick, Festivities.candyCane);
	public static SnowglobeScene hillWorld = new SnowglobeScene("hillWorld", Item.getItemFromBlock(Blocks.ice), Item.getItemFromBlock(Festivities.cobbleIce));
	
	public String texture;
	public Item[] items;
	
	protected SnowglobeScene(String tex, Item... items)
	{
		this.texture = tex;
		this.items = items;
		this.map.put(tex, this);
	}
	
	public String getTexture()
	{
		return "scene_" + this.texture + ".png";
	}
	
	public ResourceLocation getResource()
	{
		return new ResourceLocation(Festivities.ID, "textures/snowglobe/" + this.getTexture());
	}
	
	public static SnowglobeScene getFromItem(ItemStack stack)
	{
		Item item = stack.getItem();
		for (SnowglobeScene scene : map.values())
		{
			for (int j = 0; j < scene.items.length; j++)
			{
				if (scene.items[j] == item)
				{
					return scene;
				}
			}
		}
		return null;
	}
}
