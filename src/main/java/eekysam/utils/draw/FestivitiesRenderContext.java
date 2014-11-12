package eekysam.utils.draw;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class FestivitiesRenderContext
{
	public final TextureManager engine;

	public FestivitiesRenderContext()
	{
		this.engine = Minecraft.getMinecraft().renderEngine;
	}

	public void bindTexture(ResourceLocation loc)
	{
		this.engine.bindTexture(loc);
	}
	
	public int getTime()
	{
		return (int) Minecraft.getSystemTime();
	}
}
