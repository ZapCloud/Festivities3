package com.zapcloudstudios.festivities3.kringle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.player.PlayerData;

public class KringleTeleporter extends Teleporter
{
	public KringleTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
	}

	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float par8)
	{
		EntityPlayerMP player = (EntityPlayerMP) entity;
		PlayerData dat = Festivities.getPlayerData(player);
		if (entity.dimension == Festivities.kringleId)
		{
			dat.globex = (int) x;
			dat.globey = (int) y;
			dat.globez = (int) z;
			dat.hasGlobe = true;
			entity.fallDistance = -1000.0F;
			if (dat.hasExit)
			{
				entity.setPosition(dat.exitx, 200, dat.exitz);
			}
			else
			{
				entity.setPosition(x, 200, z);
			}
			if (player.capabilities.isCreativeMode)
			{
				player.addChatComponentMessage(new ChatComponentText("Use \\gotokringle to exit the snowglobe."));
			}
			else
			{
				player.addChatComponentMessage(new ChatComponentText("Look up and hold shift to exit the snowglobe."));
			}
		}
		else
		{
			dat.exitx = (int) x;
			dat.exitz = (int) z;
			dat.hasExit = true;
			if (dat.hasGlobe)
			{
				entity.setPosition(dat.globex, dat.globey, dat.globez);
			}
			else
			{
				ChunkCoordinates spawn = player.worldObj.getSpawnPoint();
				entity.setPosition(spawn.posX, spawn.posY, spawn.posZ);
			}
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		return true;
	}

	@Override
	public boolean makePortal(Entity par1Entity)
	{
		return true;
	}
}
