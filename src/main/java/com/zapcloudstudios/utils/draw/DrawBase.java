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
	
	protected int grid = -1;
	
	protected float[] matrixPreOff = new float[3];
	protected float[] matrixPostOff = new float[3];
	
	protected float[][] matrix = new float[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
	
	public DrawBase()
	{
		this.tess = Tessellator.instance;
	}
	
	public void setGridSnap(int grid)
	{
		this.grid = grid;
	}
	
	public void setGridSnapExp(int gridexp)
	{
		this.grid = 1 << gridexp;
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
	
	public void setOffsetMatrixPre(float x, float y, float z)
	{
		this.matrixPreOff = new float[] { x, y, z };
	}
	
	public void setOffsetMatrixPost(float x, float y, float z)
	{
		this.matrixPostOff = new float[] { x, y, z };
	}
	
	public void setMatrix(float[][] matrix)
	{
		this.matrix = matrix;
	}
	
	public void scaleMatrix(float scale)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.matrix[i][j] *= scale;
			}
		}
	}
	
	public void addVertexWithUV(double x, double y, double z, int u, int v)
	{
		double U = u;
		double V = v;
		U /= this.textureDomainU;
		V /= this.textureDomainV;
		if (this.icon != null)
		{
			U = this.icon.getInterpolatedU((int) (U * 16)); //TODO Why am I getting white lines!?!?!?
			V = this.icon.getInterpolatedV((int) (V * 16));
		}
		x /= this.domainX;
		x += this.matrixPreOff[0];
		y /= this.domainY;
		y += this.matrixPreOff[1];
		z /= this.domainZ;
		z += this.matrixPreOff[2];
		float[][] m = this.matrix;
		double X = m[0][0] * x + m[0][1] * y + m[0][2] * z + this.matrixPostOff[0];
		double Y = m[1][0] * x + m[1][1] * y + m[1][2] * z + this.matrixPostOff[1];
		double Z = m[2][0] * x + m[2][1] * y + m[2][2] * z + this.matrixPostOff[2];
		/*
		if (this.grid >= 1)
		{
			X = (int) (X * this.grid) / (double) this.grid;
			Y = (int) (Y * this.grid) / (double) this.grid;
			Z = (int) (Z * this.grid) / (double) this.grid;
		}
		*/
		this.tess.addVertexWithUV(X, Y, Z, U, V);
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
		bind.bindFestiveTexture(loc);
	}
	
	public void setTexture(ITextureBinder bind, ResourceLocation loc, IIcon icon)
	{
		this.setTexture(icon);
		bind.bindFestiveTexture(loc);
	}
	
	public void setTexture(ITextureBinder bind, ResourceLocation loc, IIcon icon, int usize, int vsize)
	{
		this.setTexture(icon, usize, vsize);
		bind.bindFestiveTexture(loc);
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
