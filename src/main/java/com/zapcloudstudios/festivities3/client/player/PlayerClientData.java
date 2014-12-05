package com.zapcloudstudios.festivities3.client.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import com.zapcloudstudios.festivities3.player.PlayerData;

public class PlayerClientData extends PlayerData
{
	public final static float endFovMult = 0.1F;
	public final static float endFovMultExit = 1.5F;

	public float unchangedFov = Float.NaN;

	private float lastPortalTime;
	private float lastExitTime;

	public float updateFov(float currentfov, float partialTime, boolean isInKringle)
	{
		if (!Float.isNaN(this.unchangedFov))
		{
			return this.unchangedFov * this.getFovMult(partialTime);
		}
		else
		{
			this.unchangedFov = currentfov;
			return this.unchangedFov;
		}
	}

	@Override
	public void tickSnowglobe(EntityPlayer player)
	{
		this.lastPortalTime = this.snowglobeTimeFraction;
		super.tickSnowglobe(player);
	}

	@Override
	public void tickSnowglobeExit(EntityPlayer player)
	{
		this.lastExitTime = this.exitTimeFraction;
		super.tickSnowglobeExit(player);
	}

	public float getFovMult(float partialTime)
	{
		float portal = this.getPortalTimeFraction(partialTime);
		float exit = this.getExitTimeFraction(partialTime);
		float fov = 1.0F;
		fov *= (1.0F - portal) + (endFovMult * portal);
		fov *= (1.0F - exit) + (endFovMultExit * exit);
		return fov;
	}

	private float getExitTimeFraction(float partialTime)
	{
		return this.lastExitTime + (this.exitTimeFraction - this.lastExitTime) * partialTime;
	}

	public float getPortalTimeFraction(float partialTime)
	{
		return this.lastPortalTime + (this.snowglobeTimeFraction - this.lastPortalTime) * partialTime;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{

	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		super.loadNBTData(compound);

		//this.isLookingAtSnowglobePortal = compound.getBoolean("isLookingAtSnowglobePortal");
		//this.snowglobeTimeFraction = compound.getFloat("poralFraction");
	}
}
