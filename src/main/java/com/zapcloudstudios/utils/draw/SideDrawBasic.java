package com.zapcloudstudios.utils.draw;

public class SideDrawBasic extends SideDraw
{
	public SideDrawBasic()
	{
		super();
	}
	
	@Override
	public void draw()
	{
		switch (this.dir)
		{
			case XUp:
				this.XUp();
				return;
			case XDown:
				this.XDown();
				return;
			case YUp:
				this.YUp();
				return;
			case YDown:
				this.YDown();
				return;
			case ZUp:
				this.ZUp();
				return;
			case ZDown:
				this.ZDown();
				return;
		}
	}
	
	protected void XUp()
	{
		int u2;
		int v2;
		int u1 = 0;
		int v1 = 0;
		if (this.rotUVWorldMapping)
		{
			u2 = this.height;
			v2 = this.width;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.width, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.width, this.textureU + u2, this.textureV + v2);
		}
		else
		{
			u2 = this.width;
			v2 = this.height;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.width, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.width, this.textureU + u2, this.textureV + v2);
		}
	}
	
	protected void XDown()
	{
		int u2;
		int v2;
		int u1 = 0;
		int v1 = 0;
		if (this.rotUVWorldMapping)
		{
			u2 = this.height;
			v2 = this.width;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.width, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.width, this.textureU + u2, this.textureV + v2);
		}
		else
		{
			u2 = this.width;
			v2 = this.height;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos + this.width, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.width, this.textureU + u2, this.textureV + v2);
		}
	}
	
	protected void YUp()
	{
		int u2;
		int v2;
		int u1 = 0;
		int v1 = 0;
		if (this.rotUVWorldMapping)
		{
			u2 = this.height;
			v2 = this.width;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.height, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.height, this.textureU + u2, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u1, this.textureV + v2);
		}
		else
		{
			u2 = this.width;
			v2 = this.height;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.height, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.height, this.textureU + u2, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u2, this.textureV + v1);
		}
	}
	
	protected void YDown()
	{
		int u2;
		int v2;
		int u1 = 0;
		int v1 = 0;
		if (this.rotUVWorldMapping)
		{
			u2 = this.height;
			v2 = this.width;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.height, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.height, this.textureU + u2, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u1, this.textureV + v2);
		}
		else
		{
			u2 = this.width;
			v2 = this.height;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos + this.height, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos + this.height, this.textureU + u2, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u2, this.textureV + v1);
		}
	}
	
	protected void ZUp()
	{
		int u2;
		int v2;
		int u1 = 0;
		int v1 = 0;
		if (this.rotUVWorldMapping)
		{
			u2 = this.height;
			v2 = this.width;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u2, this.textureV + v2);
		}
		else
		{
			u2 = this.width;
			v2 = this.height;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u2, this.textureV + v2);
		}
	}
	
	protected void ZDown()
	{
		int u2;
		int v2;
		int u1 = 0;
		int v1 = 0;
		if (this.rotUVWorldMapping)
		{
			u2 = this.height;
			v2 = this.width;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u2, this.textureV + v2);
		}
		else
		{
			u2 = this.width;
			v2 = this.height;
			if (this.flipU)
			{
				u1 = u2;
				u2 = 0;
			}
			if (this.flipV)
			{
				v1 = v2;
				v2 = 0;
			}
			this.addVertexWithUV(this.xpos, this.ypos, this.zpos, this.textureU + u1, this.textureV + v2);
			this.addVertexWithUV(this.xpos, this.ypos + this.height, this.zpos, this.textureU + u1, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos + this.height, this.zpos, this.textureU + u2, this.textureV + v1);
			this.addVertexWithUV(this.xpos + this.width, this.ypos, this.zpos, this.textureU + u2, this.textureV + v2);
		}
	}
}
