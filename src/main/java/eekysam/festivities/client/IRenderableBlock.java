package eekysam.festivities.client;

import eekysam.festivities.client.render.block.BlockRender;

public interface IRenderableBlock
{
	public Class<? extends BlockRender> getRenderer();
	
	public BlockRender getRendererInstance();
}
