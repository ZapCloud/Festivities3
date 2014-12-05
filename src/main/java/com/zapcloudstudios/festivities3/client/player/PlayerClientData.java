package com.zapcloudstudios.festivities3.client.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import com.zapcloudstudios.festivities3.player.PlayerData;

public class PlayerClientData extends PlayerData
{
	public final static float endFovMult = 0.2F;

	public float unchangedFov = Float.NaN;

	private float lastPortalTime;

	public float updateSnowglobeFov(float currentfov, float partialTime, boolean isInKringle)
	{
		if (this.isLookingAtSnowglobePortal() && !isInKringle && !Float.isNaN(this.unchangedFov))
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

	public float getFovMult(float partialTime)
	{
		float portal = this.getPortalTimeFraction(partialTime);
		return (1.0F - portal) + (endFovMult * portal);
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
