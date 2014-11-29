package com.zapcloudstudios.utils.draw;

import com.zapcloudstudios.utils.EnumDirection;

public abstract class SideDraw extends SimpleDraw
{
	protected int width = 0;
	protected int height = 0;
	
	protected boolean doubleSided = false;
	
	protected EnumDirection dir;
	
	public SideDraw()
	{
		
	}
	
	public void setDoubleSided(boolean doubleSided)
	{
		this.doubleSided = doubleSided;
	}
	
	@Override
	public void forwardTo(DrawBase to)
	{
		super.forwardTo(to);
		if (to instanceof SideDraw)
		{
			SideDraw sideto = (SideDraw) to;
			sideto.width = this.width;
			sideto.height = this.height;
			sideto.doubleSided = this.doubleSided;
			sideto.dir = this.dir;
		}
	}
	
	public void setDoubleSided()
	{
		this.setDoubleSided(true);
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
