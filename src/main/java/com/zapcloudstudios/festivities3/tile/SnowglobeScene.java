package com.zapcloudstudios.festivities3.tile;

import java.util.Collection;
import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

import com.zapcloudstudios.festivities3.Festivities;

public class SnowglobeScene
{
	private static HashMap<String, SnowglobeScene> namemap = new HashMap<String, SnowglobeScene>();
	
	public static SnowglobeScene snowTree = new SnowglobeScene("snowWorld", Items.stick, Blocks.log, Blocks.log2, Blocks.planks);
	public static SnowglobeScene candyWorld = new SnowglobeScene("candyWorld", Festivities.candyLog, Festivities.peppermintStick, Festivities.candyCane, Festivities.candyPlanks);
	public static SnowglobeScene hillWorld = new SnowglobeScene("hillWorld", Blocks.ice, Festivities.cobbleIce, Festivities.iceBrick);
	//public static SnowglobeScene testGrid = new SnowglobeScene("grid", Festivities.magicCandy);
	
	public static SnowglobeScene portal = candyWorld;
	
	public final String name;
	public final Object[] items;
	
	protected SnowglobeScene(String name, Object... items)
	{
		this.name = name;
		this.items = items;
		namemap.put(name, this);
	}
	
	public String getTexture()
	{
		return "scene_" + this.name + ".png";
	}
	
	public String getUnlocalizedForTranslate()
	{
		return "snowscene." + this.name;
	}
	
	public ResourceLocation getResource()
	{
		return new ResourceLocation(Festivities.ID, "textures/snowglobe/" + this.getTexture());
	}
	
	public static SnowglobeScene getFromName(String name)
	{
		return namemap.get(name);
	}
	
	public static Collection<SnowglobeScene> getScenes()
	{
		return namemap.values();
	}
}
