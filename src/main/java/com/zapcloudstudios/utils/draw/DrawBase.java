package com.zapcloudstudios.utils.draw;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public abstract class DrawBase
{
	public Tessellator tess;
	
	protected int domainX = 16;
	protected int domainY = 16;
	protected int domainZ = 16;
	
	protected int textureDomainU = 16;
	protected int textureDomainV = 16;
	
	protected IIcon icon;
	
	protected boolean flipU = false;
	protected boolean flipV = false;
	
	public DrawBase()
	{
		this.tess = Tessellator.instance;
	}
	
	public void forwardTo(DrawBase to)
	{
		to.domainX = this.domainX;
		to.domainY = this.domainY;
		to.domainZ = this.domainZ;
		to.flipU = this.flipU;
		to.flipV = this.flipV;
		to.icon = this.icon;
		to.textureDomainU = this.textureDomainU;
		to.textureDomainV = this.textureDomainV;
	}
	
	public void addVertexWithUV(double x, double y, double z, int u, int v)
	{
		double U = u;
		double V = v;
		U /= this.textureDomainU;
		V /= this.textureDomainV;
		if (this.icon != null)
		{
			U = this.icon.getInterpolatedU(U * 16);
			V = this.icon.getInterpolatedV(V * 16);
		}
		this.tess.addVertexWithUV(x / this.domainX, y / this.domainY, z / this.domainZ, U, V);
	}
	
	public void setDomain(int xsize, int ysize, int zsize)
	{
		this.domainX = xsize;
		this.domainY = ysize;
		this.domainZ = zsize;
	}
	
	public void setTextureDomain(int usize, int vsize)
	{
		this.textureDomainU = usize;
		this.textureDomainV = vsize;
	}
	
	public void setTexture(IIcon icon)
	{
		this.icon = icon;
	}
	
	public void setTexture(IIcon icon, int usize, int vsize)
	{
		this.setTexture(icon);
		this.setTextureDomain(usize, vsize);
	}
	
	public void setTexture(ITextureBinder bind, String resourceDomain, String resourcePath, int usize, int vsize)
	{
		this.setTexture(bind, new ResourceLocation(resourceDomain, resourcePath), usize, vsize);
	}
	
	public void setTexture(ITextureBinder bind, ResourceLocation loc)
	{
		bind.bindTexture(loc);
	}
	
	public void setTexture(ITextureBinder bind, ResourceLocation loc, IIcon icon)
	{
		this.setTexture(icon);
		bind.bindTexture(loc);
	}
	
	public void setTexture(ITextureBinder bind, ResourceLocation loc, IIcon icon, int usize, int vsize)
	{
		this.setTexture(icon, usize, vsize);
		bind.bindTexture(loc);
	}
	
	public void setTexture(ITextureBinder bind, ResourceLocation loc, int usize, int vsize)
	{
		this.setTextureDomain(usize, vsize);
		this.setTexture(bind, loc);
	}
	
	public void setFlip(boolean u, boolean v)
	{
		this.flipU = u;
		this.flipV = v;
	}
}
