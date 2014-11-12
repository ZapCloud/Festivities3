package com.zapcloudstudios.festivities3.block;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.ITipItem;
import com.zapcloudstudios.festivities3.client.IRenderableBlock;
import com.zapcloudstudios.festivities3.client.render.block.RenderBlockFestivites;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockFestiveContainer extends BlockContainer implements ITipItem, IRenderableBlock
{
	private RenderBlockFestivites render = null;
	
	protected BlockFestiveContainer(Material material)
	{
		super(material);
	}
	
	@Override
	public int getRenderType()
	{
		if (this.shouldRender3D())
		{
			return Festivities.block3dItemRenderId;
		}
		else
		{
			return Festivities.block2dItemRenderId;
		}
	}
	
	@Override
	public Class<? extends RenderBlockFestivites> getRenderer()
	{
		return null;
	}
	
	@Override
	public RenderBlockFestivites getRendererInstance()
	{
		if (this.render == null)
		{
			Class<? extends RenderBlockFestivites> rclass = this.getRenderer();
			if (rclass != null)
			{
				try
				{
					this.render = rclass.newInstance();
				}
				catch (InstantiationException | IllegalAccessException e)
				{

				}
			}
		}
		return this.render;
	}
}
