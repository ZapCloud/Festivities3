package com.zapcloudstudios.utils.draw;

import com.zapcloudstudios.utils.EnumDirection;

public abstract class SideDraw extends DrawBase
{
	protected int xpos;
	protected int ypos;
	protected int zpos;
	
	protected int textureU;
	protected int textureV;
	
	protected int width = 0;
	protected int height = 0;
	
	protected boolean rotUVWorldMapping = false;
	
	protected boolean doubleSided = false;
	
	protected EnumDirection dir;
	
	public SideDraw()
	{
		
	}
	
	public void setDoubleSided(boolean doubleSided)
	{
		this.doubleSided = doubleSided;
	}
	
	public void setRotUVWorldMapping(boolean rot)
	{
		this.rotUVWorldMapping = rot;
	}
	
	public void setDoubleSided()
	{
		this.setDoubleSided(true);
	}
	
	public void setPos(int x, int y, int z)
	{
		this.xpos = x;
		this.ypos = y;
		this.zpos = z;
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
	
	public abstract void draw();
	
	public void side(EnumDirection dir, int width, int height, int x, int y, int z)
	{
		this.setPos(x, y, z);
		this.side(dir, width, height);
	}
	
	public void side(EnumDirection dir, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.dir = dir;
	}
}
