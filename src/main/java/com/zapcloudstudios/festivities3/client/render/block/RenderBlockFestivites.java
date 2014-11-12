package com.zapcloudstudios.festivities3.client.render.block;

import com.zapcloudstudios.utils.draw.FestivitiesRenderContext;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public abstract class RenderBlockFestivites
{
	protected FestivitiesRenderContext context;

	public RenderBlockFestivites()
	{
		this.context = new FestivitiesRenderContext();
	}

	public abstract void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer);

	public abstract boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer);
}
