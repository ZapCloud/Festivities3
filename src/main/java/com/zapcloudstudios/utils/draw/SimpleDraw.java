package com.zapcloudstudios.utils.draw;

public abstract class SimpleDraw extends DrawBase
{
	protected int xpos;
	protected int ypos;
	protected int zpos;
	
	protected int textureU;
	protected int textureV;
	
	protected boolean rotUVWorldMapping = false;
	
	public void setRotUVWorldMapping(boolean rot)
	{
		this.rotUVWorldMapping = rot;
	}
	
	public void setPos(int x, int y, int z)
	{
		this.xpos = x;
		this.ypos = y;
		this.zpos = z;
	}
	
	@Override
	public void forwardTo(DrawBase to)
	{
		super.forwardTo(to);
		if (to instanceof SimpleDraw)
		{
			SimpleDraw simpleto = (SimpleDraw) to;
			simpleto.xpos = this.xpos;
			simpleto.ypos = this.ypos;
			simpleto.zpos = this.zpos;
			simpleto.textureU = this.textureU;
			simpleto.textureV = this.textureV;
			simpleto.rotUVWorldMapping = this.rotUVWorldMapping;
		}
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
}
