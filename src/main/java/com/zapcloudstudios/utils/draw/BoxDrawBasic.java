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
		int z1;
		int z2;
		if (this.inside)
		{
			z1 = this.zpos + this.length;
			z2 = this.zpos;
		}
		else
		{
			z1 = this.zpos;
			z2 = this.zpos + this.length;
		}
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.length;
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z1, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z1, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z2, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z2, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.length;
			v = this.height;
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z1, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z1, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z2, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z2, this.textureU + u, this.textureV + v);
		}
	}
	
	@Override
	public void XDown()
	{
		int u;
		int v;
		this.tess.setNormal(-this.getNormal(), 0.0F, 0.0F);
		int z1;
		int z2;
		if (this.inside)
		{
			z1 = this.zpos;
			z2 = this.zpos + this.length;
		}
		else
		{
			z1 = this.zpos + this.length;
			z2 = this.zpos;
		}
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.length;
			this.addVertexWithUV(this.xpos, this.ypos, z1, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z1, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z2, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos, this.ypos, z2, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.length;
			v = this.height;
			this.addVertexWithUV(this.xpos, this.ypos, z1, this.textureU, this.textureV + v);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z1, this.textureU, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z2, this.textureU + u, this.textureV);
			this.addVertexWithUV(this.xpos, this.ypos, z2, this.textureU + u, this.textureV + v);
		}
	}
	
	@Override
	public void YUp()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, this.getNormal(), 0.0F);
		int x1;
		int x2;
		if (this.inside)
		{
			x1 = this.xpos + this.width;
			x2 = this.xpos;
		}
		else
		{
			x1 = this.xpos;
			x2 = this.xpos + this.width;
		}
		if (this.rotUVWorldMapping)
		{
			u = this.length;
			v = this.width;
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, this.textureU, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.length;
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, this.textureU + u, this.textureV);
		}
	}
	
	@Override
	public void YDown()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, -this.getNormal(), 0.0F);
		int x1;
		int x2;
		if (this.inside)
		{
			x1 = this.xpos;
			x2 = this.xpos + this.width;
		}
		else
		{
			x1 = this.xpos + this.width;
			x2 = this.xpos;
		}
		if (this.rotUVWorldMapping)
		{
			u = this.length;
			v = this.width;
			this.addVertexWithUV(x1, this.ypos, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos, this.zpos, this.textureU, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.length;
			this.addVertexWithUV(x1, this.ypos, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos, this.zpos, this.textureU + u, this.textureV);
		}
	}
	
	@Override
	public void ZUp()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, 0.0F, this.getNormal());
		int x1;
		int x2;
		if (this.inside)
		{
			x1 = this.xpos;
			x2 = this.xpos + this.width;
		}
		else
		{
			x1 = this.xpos + this.width;
			x2 = this.xpos;
		}
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.width;
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.height;
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, this.textureU, this.textureV + v);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, this.textureU, this.textureV);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, this.textureU + u, this.textureV);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, this.textureU + u, this.textureV + v);
		}
	}
	
	@Override
	public void ZDown()
	{
		int u;
		int v;
		this.tess.setNormal(0.0F, 0.0F, -this.getNormal());
		int x1;
		int x2;
		if (this.inside)
		{
			x1 = this.xpos + this.width;
			x2 = this.xpos;
		}
		else
		{
			x1 = this.xpos;
			x2 = this.xpos + this.width;
		}
		if (this.rotUVWorldMapping)
		{
			u = this.height;
			v = this.width;
			this.addVertexWithUV(x1, this.ypos, this.zpos, this.textureU + u, this.textureV);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, this.textureU, this.textureV + v);
			this.addVertexWithUV(x2, this.ypos, this.zpos, this.textureU + u, this.textureV + v);
		}
		else
		{
			u = this.width;
			v = this.height;
			this.addVertexWithUV(x1, this.ypos, this.zpos, this.textureU, this.textureV + v);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, this.textureU, this.textureV);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, this.textureU + u, this.textureV);
			this.addVertexWithUV(x2, this.ypos, this.zpos, this.textureU + u, this.textureV + v);
		}
	}
}
