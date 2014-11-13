package com.zapcloudstudios.utils.draw;

import net.minecraft.util.IIcon;

public class FakeIcon implements IIcon
{
	private final int width;
	private final int height;
	
	public FakeIcon(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public float getMinU()
	{
		return 0.0F;
	}
	
	@Override
	public float getMaxU()
	{
		return 1.0F;
	}
	
	@Override
	public float getInterpolatedU(double x)
	{
		return (float) x / 16.0F;
	}
	
	@Override
	public float getMinV()
	{
		return 0.0F;
	}
	
	@Override
	public float getMaxV()
	{
		return 1.0F;
	}
	
	@Override
	public float getInterpolatedV(double x)
	{
		return (float) x / 16.0F;
	}
	
	@Override
	public String getIconName()
	{
		return null;
	}
	
	@Override
	public int getIconWidth()
	{
		return this.width;
	}
	
	@Override
	public int getIconHeight()
	{
		return this.height;
	}
	
}
