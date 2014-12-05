package com.zapcloudstudios.utils.draw;

import java.util.Arrays;
import java.util.List;

import com.zapcloudstudios.utils.EnumDirection;

public abstract class BoxDraw extends SimpleDraw
{
	protected int width;
	protected int height;
	protected int length;
	
	protected boolean inside = false;
	
	public BoxDraw()
	{
		super();
	}
	
	public void faceOut()
	{
		this.inside = false;
	}
	
	public void faceIn()
	{
		this.inside = true;
	}
	
	public void cube(float x, float y, float z, int width, int height, int length)
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
	
	@Override
	public void forwardTo(DrawBase to)
	{
		super.forwardTo(to);
		if (to instanceof BoxDraw)
		{
			BoxDraw boxto = (BoxDraw) to;
			boxto.width = this.width;
			boxto.height = this.height;
			boxto.length = this.length;
			boxto.inside = this.inside;
		}
	}
	
	public void drawSide(EnumDirection dir)
	{
		switch (dir)
		{
			case XUp:
				this.XUp();
				break;
			case XDown:
				this.XDown();
				break;
			case YUp:
				this.YUp();
				break;
			case YDown:
				this.YDown();
				break;
			case ZUp:
				this.ZUp();
				break;
			case ZDown:
				this.ZDown();
				break;
		}
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
	
	public void drawAllBut(EnumDirection dir)
	{
		for (EnumDirection d : EnumDirection.values())
		{
			if (d != dir)
			{
				this.drawSide(d);
			}
		}
	}
	
	public void drawAllButMulti(EnumDirection... dirs)
	{
		List<EnumDirection> list = Arrays.asList(dirs);
		for (EnumDirection d : EnumDirection.values())
		{
			if (!list.contains(d))
			{
				this.drawSide(d);
			}
		}
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
