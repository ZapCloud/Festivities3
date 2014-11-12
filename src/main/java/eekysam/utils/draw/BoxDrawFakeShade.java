package eekysam.utils.draw;

public class BoxDrawFakeShade extends BoxDrawBasic
{
	public BoxDrawFakeShade(IRenderer parent)
	{
		super(parent);
	}
	
	@Override
	public void XUp()
	{
		if (!this.inside)
		{
			this.setBrightnessF(0.7F);
		}
		else
		{
			this.setBrightnessF(0.9F);
		}
		super.XUp();
	}
	
	@Override
	public void XDown()
	{
		if (this.inside)
		{
			this.setBrightnessF(0.7F);
		}
		else
		{
			this.setBrightnessF(0.9F);
		}
		super.XDown();
	}
	
	@Override
	public void YUp()
	{
		if (!this.inside)
		{
			this.setBrightnessF(0.85F);
		}
		else
		{
			this.setBrightnessF(0.6F);
		}
		super.YUp();
	}
	
	@Override
	public void YDown()
	{
		if (this.inside)
		{
			this.setBrightnessF(0.85F);
		}
		else
		{
			this.setBrightnessF(0.6F);
		}
		super.YDown();
	}
	
	@Override
	public void ZUp()
	{
		if (!this.inside)
		{
			this.setBrightnessF(0.8F);
		}
		else
		{
			this.setBrightnessF(0.8F);
		}
		super.ZUp();
	}
	
	@Override
	public void ZDown()
	{
		if (this.inside)
		{
			this.setBrightnessF(0.8F);
		}
		else
		{
			this.setBrightnessF(0.8F);
		}
		super.ZDown();
	}
	
	public void setBrightnessF(float f)
	{
		this.tess.setColorOpaque_F(f, f, f);
	}
}
