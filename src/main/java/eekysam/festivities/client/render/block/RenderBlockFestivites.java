package eekysam.festivities.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import eekysam.utils.draw.FestivitiesRenderContext;

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
