package eekysam.festivities.block;

import net.minecraft.block.material.Material;
import eekysam.festivities.client.IRenderableBlock;
import eekysam.festivities.client.render.block.RenderBlockFestivites;

public class BlockFestiveComplex extends BlockFestive implements IRenderableBlock
{
	private RenderBlockFestivites render;
	private Class<? extends RenderBlockFestivites> renderClass = null;

	private boolean shouldRender3D = true;
	
	public BlockFestiveComplex(Material material)
	{
		super(material);
	}
	
	public BlockFestiveComplex setShouldRender3D(boolean shouldRender3D)
	{
		this.shouldRender3D = shouldRender3D;
		return this;
	}
	
	public BlockFestiveComplex setRenderer(Class<? extends RenderBlockFestivites> renderer)
	{
		this.renderClass = renderer;
		return this;
	}
	
	@Override
	public Class<? extends RenderBlockFestivites> getRenderer()
	{
		return this.renderClass;
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
	
	@Override
	public boolean shouldRender3D()
	{
		return this.shouldRender3D;
	}
}
