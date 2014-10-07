package com.zapcloudstudios.festivities3;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zapcloudstudios.festivities3.block.BlockCandyLog;

public class FestiveBlocks
{
	public static Block candyLog;
	
	public static void loadBlocks()
	{
		candyLog = new BlockCandyLog().setUnlocalizedName("candyLog");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(candyLog, "candy_log");
	}
}
