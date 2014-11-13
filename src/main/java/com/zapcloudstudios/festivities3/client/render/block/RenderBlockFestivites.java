package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public abstract class RenderBlockFestivites
{
	public RenderBlockFestivites()
	{
		
	}
	
	public abstract void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer);
	
	public abstract boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer);
}
