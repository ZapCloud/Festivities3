package com.zapcloudstudios.festivities3.client.render.block;

import com.zapcloudstudios.utils.draw.FestivitiesRenderContext;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public abstract class TileEntityFestivitiesRenderer extends TileEntitySpecialRenderer
{
	protected FestivitiesRenderContext context = new FestivitiesRenderContext();
}
