package com.zapcloudstudios.utils;

import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.util.vector.Vector3f;

public enum EnumDirection
{
	XUp(ForgeDirection.EAST),
	XDown(ForgeDirection.WEST),
	YUp(ForgeDirection.UP),
	YDown(ForgeDirection.DOWN),
	ZUp(ForgeDirection.SOUTH),
	ZDown(ForgeDirection.NORTH);

	public final ForgeDirection forge;
	
	private EnumDirection(ForgeDirection forge)
	{
		this.forge = forge;
	}

	public Vector3f getVector(float mag)
	{
		return new Vector3f(this.forge.offsetX * mag, this.forge.offsetY * mag, this.forge.offsetZ * mag);
	}

	public Vector3f getBadPlaneVector(float u, float v)
	{
		switch (this)
		{
			case XUp:
				return new Vector3f(0.0F, v, u);
			case XDown:
				return new Vector3f(0.0F, v, u);
			case YUp:
				return new Vector3f(u, 0.0F, v);
			case YDown:
				return new Vector3f(u, 0.0F, v);
			case ZUp:
				return new Vector3f(u, v, 0.0F);
			case ZDown:
				return new Vector3f(u, v, 0.0F);
			default:
				return new Vector3f(0.0F, 0.0F, 0.0F);
		}
	}
}
