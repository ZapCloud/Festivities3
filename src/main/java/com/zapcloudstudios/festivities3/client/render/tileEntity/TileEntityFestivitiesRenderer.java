package com.zapcloudstudios.festivities3.client.render.tileEntity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

import com.zapcloudstudios.utils.draw.ITextureBinder;

public abstract class TileEntityFestivitiesRenderer extends TileEntitySpecialRenderer implements ITextureBinder
{
	@Override
	public void bindTexture(ResourceLocation loc)
	{
		super.bindTexture(loc);
	}
}
