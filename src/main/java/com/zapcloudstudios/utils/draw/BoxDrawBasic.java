package com.zapcloudstudios.utils.draw;

public class BoxDrawBasic extends BoxDraw
{
	public BoxDrawBasic()
	{
		super();
	}
	
	private float getNormal()
	{
		if (this.inside)
		{
			return -1.0F;
		}
		else
		{
			return 1.0F;
		}
	}
	
	@Override
	public void XUp()
	{
		int u;
		int v;
		this.tess.setNormal(this.getNormal(), 0.0F, 0.0F);
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.length;
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.length;
			v = this.height;
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
	}
	
	@Override
	public void XDown()
	{
		int u;
		int v;
		this.tess.setNormal(-this.getNormal(), 0.0F, 0.0F);
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.length;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.length;
			v = this.height;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
	}
	
	@Override
	public void YUp()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, this.getNormal(), 0.0F);
		if (this.rotUVWorldMapping)
		{
			u = this.length;
			v = this.width;
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.length;
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU + u, this.textureV);
		}
	}
	
	@Override
	public void YDown()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, -this.getNormal(), 0.0F);
		if (this.rotUVWorldMapping)
		{
			u = this.length;
			v = this.width;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.length;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u, this.textureV);
		}
	}
	
	@Override
	public void ZUp()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, 0.0F, this.getNormal());
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.width;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.height;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
	}
	
	@Override
	public void ZDown()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, 0.0F, -this.getNormal());
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.width;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.height;
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u, this.textureV + v);
		}
	}
}
