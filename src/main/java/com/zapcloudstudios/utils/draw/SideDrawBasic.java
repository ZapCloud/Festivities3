package com.zapcloudstudios.utils.draw;

public class SideDrawBasic extends SideDraw
{
	private BoxDrawBasic boxDraw;
	
	public SideDrawBasic()
	{
		super();
		this.boxDraw = new BoxDrawBasic();
	}
	
	@Override
	public void draw()
	{
		this.forwardTo(this.boxDraw);
		switch (this.dir)
		{
			case XUp:
			case XDown:
				this.boxDraw.cube(0, this.height, this.width);
				break;
			case YUp:
			case YDown:
				this.boxDraw.cube(this.height, 0, this.width);
				break;
			case ZUp:
			case ZDown:
				this.boxDraw.cube(this.width, this.height, 0);
				break;
		}
		this.boxDraw.faceOut();
		this.boxDraw.drawSide(this.dir);
		if (this.doubleSided)
		{
			this.boxDraw.faceIn();
			this.boxDraw.drawSide(this.dir);
		}
	}
}
