package eekysam.festivities.client;

import eekysam.festivities.client.render.block.RenderBlockFestivites;

public interface IRenderableBlock
{
	public Class<? extends RenderBlockFestivites> getRenderer();

	public RenderBlockFestivites getRendererInstance();

	public boolean shouldRender3D();
}
