package com.zapcloudstudios.utils.draw;

public abstract class SimpleDraw extends DrawBase
{
	protected float xpos;
	protected float ypos;
	protected float zpos;

	protected int textureU;
	protected int textureV;

	protected boolean rotUVWorldMapping = false;

	public void setRotUVWorldMapping(boolean rot)
	{
		this.rotUVWorldMapping = rot;
	}

	public void setPos(float x, float y, float z)
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
