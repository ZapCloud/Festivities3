package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.ITipItem;
import com.zapcloudstudios.festivities3.client.IRenderableBlock;
import com.zapcloudstudios.festivities3.client.render.block.RenderBlockFestivites;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockFestiveContainer extends BlockContainer implements ITipItem, IRenderableBlock
{
	private RenderBlockFestivites render = null;
	private Class<? extends RenderBlockFestivites> renderClass = null;
	
	private boolean shouldRender3D = true;
	
	private String itemTexture = null;
	
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
	
	public BlockFestiveContainer setShouldRender3D(boolean shouldRender3D)
	{
		this.shouldRender3D = shouldRender3D;
		return this;
	}
	
	@Override
	public boolean shouldRender3D()
	{
		return this.shouldRender3D;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.itemTexture;
	}
	
	@Override
	public Class<? extends RenderBlockFestivites> getRenderer()
	{
		return this.renderClass;
	}
	
	public BlockFestiveContainer setRenderer(Class<? extends RenderBlockFestivites> renderer)
	{
		this.renderClass = renderer;
		return this;
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
				catch (InstantiationException e)
				{
					
				}
				catch (IllegalAccessException e)
				{
					
				}
			}
		}
		return this.render;
	}
	
	public BlockFestiveContainer setBlockItemTextureName(String name)
	{
		this.itemTexture = name;
		return this;
	}
}
