package com.zapcloudstudios.festivities3.client.player;

import com.zapcloudstudios.festivities3.player.PlayerData;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerClientData extends PlayerData
{
	public float getSnowgobePortal(long worldTime)
	{
		if (this.snowglobe)
		{
			int t = this.snowgobePortalTime + (int) (worldTime - this.lastWorldTime);
			return t / (float) TileEntitySnowglobe.portalTime;
		}
		else
		{
			return 0.0F;
		}
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		// super.saveNBTData(compound);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		super.loadNBTData(compound);

		this.snowgobePortalTime = compound.getInteger("snowglobePortal");
	}
}
