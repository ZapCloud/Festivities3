package com.zapcloudstudios.festivities3;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.base.Function;
import com.zapcloudstudios.festivities3.block.BlockCandyLog;
import com.zapcloudstudios.festivities3.block.BlockIceBrick;
import com.zapcloudstudios.festivities3.block.BlockSnowglobe;
import com.zapcloudstudios.festivities3.block.EnumBrickType;
import com.zapcloudstudios.festivities3.item.ItemSnowglobe;

public class FestiveBlocks
{
	public static Block candyLog;
	public static Block snowglobe;
	public static Block iceBricks;
	
	public static void loadBlocks()
	{
		candyLog = new BlockCandyLog().setUnlocalizedName("candyLog");
		snowglobe = new BlockSnowglobe().setUnlocalizedName("snowglobe");
		iceBricks = new BlockIceBrick().setUnlocalizedName("iceBrick");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(candyLog, "candy_log");
		registerBlockStates(candyLog);
		
		GameRegistry.registerBlock(snowglobe, ItemSnowglobe.class, "snowglobe");
		registerBlockStates(snowglobe);
		
		GameRegistry.registerBlock(iceBricks, ItemMultiTexture.class, "ice_brick", iceBricks, new Function()
		{
            public String apply(ItemStack stack)
            {
                return EnumBrickType.getStateFromMeta(stack.getMetadata()).getUnlocalizedName();
            }
            public Object apply(Object obj)
            {
                return this.apply((ItemStack)obj);
            }
		});
		registerBlockStates(iceBricks);
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
		proxy.setBlockItemModel(snowglobe, 0, "snowglobe");
		proxy.setBlockItemModel(snowglobe, 1, "snowglobe");
	}
}
