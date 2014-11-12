package eekysam.festivities.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import eekysam.festivities.client.IRenderableBlock;
import eekysam.festivities.client.render.block.RenderBlockFestivites;

public class RenderBlockFestivitiesHandler implements ISimpleBlockRenderingHandler
{
	public final int id;
	private final boolean item3d;

	public RenderBlockFestivitiesHandler(int id, boolean item3d)
	{
		this.id = id;
		this.item3d = item3d;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		RenderBlockFestivites render = this.getRender(block);
		if (render != null)
		{
			render.renderInventoryBlock(block, metadata, renderer);
		}
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return this.item3d;
	}
	
	@Override
	public int getRenderId()
	{
		return this.id;
	}
	
	private RenderBlockFestivites getRender(Block block)
	{
		if (block instanceof IRenderableBlock)
		{
			IRenderableBlock renderable = (IRenderableBlock) block;
			return renderable.getRendererInstance();
		}
		return null;
	}
}
