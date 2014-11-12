package com.zapcloudstudios.festivities3.client;

import com.zapcloudstudios.festivities3.client.render.block.RenderBlockFestivites;

public interface IRenderableBlock
{
	public Class<? extends RenderBlockFestivites> getRenderer();

	public RenderBlockFestivites getRendererInstance();

	public boolean shouldRender3D();
}
