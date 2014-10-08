package com.zapcloudstudios.festivities3;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zapcloudstudios.festivities3.block.BlockCandyLog;
import com.zapcloudstudios.festivities3.block.BlockSnowglobe;

public class FestiveBlocks
{
	public static Block candyLog;
	public static Block snowglobe;
	
	public static void loadBlocks()
	{
		candyLog = new BlockCandyLog().setUnlocalizedName("candyLog");
		snowglobe = new BlockSnowglobe().setUnlocalizedName("snowglobe");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(candyLog, "candy_log");
		registerBlockStates(candyLog);
		
		GameRegistry.registerBlock(snowglobe, "snowglobe");
		registerBlockStates(snowglobe);
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

	public static void registerItemRenders(CommonProxy proxy)
	{
		proxy.setBlockItemModel(candyLog, "candy_log");
	}
}
