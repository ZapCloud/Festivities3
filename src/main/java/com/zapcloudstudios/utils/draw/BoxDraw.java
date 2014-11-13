package com.zapcloudstudios.utils.draw;

public abstract class BoxDraw extends DrawBase
{
	protected int xpos;
	protected int ypos;
	protected int zpos;
	
	protected int width;
	protected int height;
	protected int length;
	
	protected int textureU;
	protected int textureV;
	
	protected boolean rotUVWorldMapping = false;
	
	protected boolean inside = false;
	
	public BoxDraw()
	{
		super();
	}
	
	public void setRotUVWorldMapping(boolean rot)
	{
		this.rotUVWorldMapping = rot;
	}
	
	public void faceOut()
	{
		this.inside = false;
	}
	
	public void faceIn()
	{
		this.inside = true;
	}
	
	public void setPos(int x, int y, int z)
	{
		this.xpos = x;
		this.ypos = y;
		this.zpos = z;
	}
	
	public void cube(int x, int y, int z, int width, int height, int length)
	{
		this.setPos(x, y, z);
		this.cube(width, height, length);
	}
	
	public void cube(int width, int height, int length)
	{
		this.width = width;
		this.height = height;
		this.length = length;
	}
	
	public void selectV(int v)
	{
		this.textureV = v;
	}
	
	public void selectU(int u)
	{
		this.textureU = u;
	}
	
	public void selectUV(int u, int v)
	{
		this.textureU = u;
		this.textureV = v;
	}
	
	public abstract void XUp();
	
	public abstract void XDown();
	
	public abstract void YUp();
	
	public abstract void YDown();
	
	public abstract void ZUp();
	
	public abstract void ZDown();
	
	public void drawAll()
	{
		this.XUp();
		this.XDown();
		this.YUp();
		this.YDown();
		this.ZUp();
		this.ZDown();
	}
	
	public void drawSidesSameTexture()
	{
		this.XUp();
		this.XDown();
		this.ZUp();
		this.ZDown();
	}
	
	public void drawSidesGroupedTexture()
	{
		int u = this.textureU;
		int v = this.textureV;
		this.XUp();
		this.textureU += this.width;
		this.ZUp();
		this.textureU += this.length;
		this.XDown();
		this.textureU += this.width;
		this.ZDown();
		this.textureU = u;
		this.textureV = v;
	}
	
	public void drawAllNormalTextureShape()
	{
		int u = this.textureU;
		int v = this.textureV;
		this.textureU += this.width;
		this.YUp();
		this.textureU += this.width;
		this.YDown();
		this.textureU = u;
		this.textureV = v;
		this.textureV += this.length;
		this.drawSidesGroupedTexture();
		this.textureU = u;
		this.textureV = v;
	}
	
	public void drawAllLeftJustTextureShape(boolean topfirst)
	{
		int u = this.textureU;
		int v = this.textureV;
		if (topfirst)
		{
			this.YUp();
		}
		else
		{
			this.YDown();
		}
		this.textureU += this.width;
		if (!topfirst)
		{
			this.YUp();
		}
		else
		{
			this.YDown();
		}
		this.textureU = u;
		this.textureV = v;
		this.textureV += this.length;
		this.drawSidesGroupedTexture();
		this.textureU = u;
		this.textureV = v;
	}
}
