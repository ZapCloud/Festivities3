package eekysam.festivities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import eekysam.festivities.Festivities;

public class SnowglobeScene
{
	public static List<SnowglobeScene> list = new ArrayList<SnowglobeScene>();
	public static SnowglobeScene SnowTree = new SnowglobeScene("snowWorld", Items.stick, Item.getItemFromBlock(Blocks.log), Item.getItemFromBlock(Blocks.log2), Item.getItemFromBlock(Blocks.planks));
	public static SnowglobeScene CandyWorld = new SnowglobeScene("candyWorld", Item.getItemFromBlock(Festivities.candyLog), Festivities.peppermintStick, Festivities.candyCane);
	public static SnowglobeScene HillWorld = new SnowglobeScene("hillWorld", Item.getItemFromBlock(Blocks.ice), Item.getItemFromBlock(Festivities.cobbleIce));
	
	public String texture;
	public Item[] items;
	
	public SnowglobeScene(String tex, Item... items)
	{
		this.texture = tex;
		this.items = items;
		this.list.add(this);
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
		for (int i = 0; i < list.size(); i++)
		{
			SnowglobeScene scene = list.get(i);
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
