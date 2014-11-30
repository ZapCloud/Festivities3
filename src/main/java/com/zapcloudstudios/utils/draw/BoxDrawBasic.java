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
		int u1 = this.textureU;
		int v1 = this.textureV;
		int u2;
		int v2;
		if (this.rotUVWorldMapping)
		{
			u2 = u1 + this.height;
			v2 = v1 + this.length;
		}
		else
		{
			u2 = u1 + this.length;
			v2 = v1 + this.height;
		}
		if (this.flipU)
		{
			u1 = u2;
			u2 = this.textureU;
		}
		if (this.flipV)
		{
			v1 = v2;
			v2 = this.textureV;
		}
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
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z1, u2, v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z1, u1, v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z2, u1, v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z2, u2, v2);
		}
		else
		{
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z1, u1, v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z1, u1, v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, z2, u2, v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, z2, u2, v2);
		}
	}
	
	@Override
	public void XDown()
	{
		int u1 = this.textureU;
		int v1 = this.textureV;
		int u2;
		int v2;
		if (this.rotUVWorldMapping)
		{
			u2 = u1 + this.height;
			v2 = v1 + this.length;
		}
		else
		{
			u2 = u1 + this.length;
			v2 = v1 + this.height;
		}
		if (this.flipU)
		{
			u1 = u2;
			u2 = this.textureU;
		}
		if (this.flipV)
		{
			v1 = v2;
			v2 = this.textureV;
		}
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
			this.addVertexWithUV(this.xpos, this.ypos, z1, u2, v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z1, u1, v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z2, u1, v2);
			this.addVertexWithUV(this.xpos, this.ypos, z2, u2, v2);
		}
		else
		{
			this.addVertexWithUV(this.xpos, this.ypos, z1, u1, v2);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z1, u1, v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, z2, u2, v1);
			this.addVertexWithUV(this.xpos, this.ypos, z2, u2, v2);
		}
	}
	
	@Override
	public void YUp()
	{
		int u1 = this.textureU;
		int v1 = this.textureV;
		int u2;
		int v2;
		if (this.rotUVWorldMapping)
		{
			u2 = u1 + this.length;
			v2 = v1 + this.width;
		}
		else
		{
			u2 = u1 + this.width;
			v2 = v1 + this.length;
		}
		if (this.flipU)
		{
			u1 = u2;
			u2 = this.textureU;
		}
		if (this.flipV)
		{
			v1 = v2;
			v2 = this.textureV;
		}
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
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, u1, v1);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, u2, v1);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, u2, v2);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, u1, v2);
		}
		else
		{
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, u1, v1);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, u1, v2);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, u2, v2);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, u2, v1);
		}
	}
	
	@Override
	public void YDown()
	{
		int u1 = this.textureU;
		int v1 = this.textureV;
		int u2;
		int v2;
		if (this.rotUVWorldMapping)
		{
			u2 = u1 + this.length;
			v2 = v1 + this.width;
		}
		else
		{
			u2 = u1 + this.width;
			v2 = v1 + this.length;
		}
		if (this.flipU)
		{
			u1 = u2;
			u2 = this.textureU;
		}
		if (this.flipV)
		{
			v1 = v2;
			v2 = this.textureV;
		}
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
			this.addVertexWithUV(x1, this.ypos, this.zpos, u1, v1);
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, u2, v1);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, u2, v2);
			this.addVertexWithUV(x2, this.ypos, this.zpos, u1, v2);
		}
		else
		{
			this.addVertexWithUV(x1, this.ypos, this.zpos, u1, v1);
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, u1, v2);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, u2, v2);
			this.addVertexWithUV(x2, this.ypos, this.zpos, u2, v1);
		}
	}
	
	@Override
	public void ZUp()
	{
		int u1 = this.textureU;
		int v1 = this.textureV;
		int u2;
		int v2;
		if (this.rotUVWorldMapping)
		{
			u2 = u1 + this.height;
			v2 = v1 + this.width;
		}
		else
		{
			u2 = u1 + this.width;
			v2 = v1 + this.height;
		}
		if (this.flipU)
		{
			u1 = u2;
			u2 = this.textureU;
		}
		if (this.flipV)
		{
			v1 = v2;
			v2 = this.textureV;
		}
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
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, u2, v1);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, u1, v1);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, u1, v2);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, u2, v2);
		}
		else
		{
			this.addVertexWithUV(x1, this.ypos, this.zpos + this.length, u1, v2);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos + this.length, u1, v1);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos + this.length, u2, v1);
			this.addVertexWithUV(x2, this.ypos, this.zpos + this.length, u2, v2);
		}
	}
	
	@Override
	public void ZDown()
	{
		int u1 = this.textureU;
		int v1 = this.textureV;
		int u2;
		int v2;
		if (this.rotUVWorldMapping)
		{
			u2 = u1 + this.height;
			v2 = v1 + this.width;
		}
		else
		{
			u2 = u1 + this.width;
			v2 = v1 + this.height;
		}
		if (this.flipU)
		{
			u1 = u2;
			u2 = this.textureU;
		}
		if (this.flipV)
		{
			v1 = v2;
			v2 = this.textureV;
		}
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
			this.addVertexWithUV(x1, this.ypos, this.zpos, u2, v1);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, u1, v1);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, u1, v2);
			this.addVertexWithUV(x2, this.ypos, this.zpos, u2, v2);
		}
		else
		{
			this.addVertexWithUV(x1, this.ypos, this.zpos, u1, v2);
			this.addVertexWithUV(x1, this.ypos + this.height, this.zpos, u1, v1);
			this.addVertexWithUV(x2, this.ypos + this.height, this.zpos, u2, v1);
			this.addVertexWithUV(x2, this.ypos, this.zpos, u2, v2);
		}
	}
}
