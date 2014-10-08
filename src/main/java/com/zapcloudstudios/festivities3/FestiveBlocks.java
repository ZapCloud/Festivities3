package com.zapcloudstudios.festivities3;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
		registerBlockStates(candyLog);
	}
	
	public static void registerBlockStates(Block block)
	{
		Iterator iterator1 = block.getBlockState().getValidStates().iterator();

        while (iterator1.hasNext())
        {
            IBlockState iblockstate = (IBlockState)iterator1.next();
            int i = Block.blockRegistry.getIDForObject(block) << 4 | block.getMetaFromState(iblockstate);
            Block.BLOCK_STATE_IDS.put(iblockstate, i);
        }
	}
}
