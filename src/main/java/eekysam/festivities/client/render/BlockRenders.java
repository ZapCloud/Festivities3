package eekysam.festivities.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockRenders implements ISimpleBlockRenderingHandler
{
	private RenderBlocks renderer = new RenderBlocks();
	final int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static final BlockRenders instance = new BlockRenders();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}
	
	@Override
	public int getRenderId()
	{
		return this.renderID;
	}
}
