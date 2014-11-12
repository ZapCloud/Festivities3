package eekysam.festivities.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import eekysam.festivities.Festivities;
import eekysam.festivities.ITipItem;
import eekysam.festivities.client.IRenderableBlock;
import eekysam.festivities.client.render.block.RenderBlockFestivites;

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
