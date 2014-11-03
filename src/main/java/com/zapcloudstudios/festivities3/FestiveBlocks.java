package com.zapcloudstudios.festivities3;

import net.minecraft.block.Block;

import com.zapcloudstudios.festivities3.block.BlockCandyLog;
import com.zapcloudstudios.festivities3.block.BlockIceBrick;
import com.zapcloudstudios.festivities3.block.BlockSnowglobe;
import com.zapcloudstudios.festivities3.block.EnumBrickType;
import com.zapcloudstudios.festivities3.item.CFMItemMultiTexture;
import com.zapcloudstudios.festivities3.item.ItemSnowglobe;

import cpw.mods.fml.common.registry.GameRegistry;

public class FestiveBlocks
{
	public static Block candyLog;
	public static Block snowglobe;
	public static Block iceBricks;
	
	public static void loadBlocks()
	{
		candyLog = register(new BlockCandyLog(), "candyLog");
		snowglobe = register(new BlockSnowglobe(), "snowglobe");
		iceBricks = register(new BlockIceBrick(), "iceBrick");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(candyLog, "candy_log");
		GameRegistry.registerBlock(snowglobe, ItemSnowglobe.class, "snowglobe");
		GameRegistry.registerBlock(iceBricks, CFMItemMultiTexture.class, "ice_brick", (Object) EnumBrickType.getUnlocalizedNames());
	}
	
	public static void registerItemRenders(ClientProxy proxy)
	{
		proxy.setBlockItemModel(candyLog, "candy_log");
		proxy.setBlockItemModel(snowglobe, 0, "snowglobe");
		proxy.setBlockItemModel(snowglobe, 1, "snowglobe");
		for (int i = 0; i < EnumBrickType.values().length; i++)
		{
			EnumBrickType type = EnumBrickType.values()[i];
			proxy.setBlockItemModel(iceBricks, i, "ice_" + type.getName());
		}
	}
	
	public static Block register(Block block, String name)
	{
		block.setBlockName(name);
		block.setBlockTextureName(FestivitiesMod.MODID + ":" + name);
		return block;
	}
}
