package com.zapcloudstudios.festivities3.events;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.player.PlayerClientData;
import com.zapcloudstudios.festivities3.player.PlayerData;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class EventHooks
{
	private float renderTickTime;

	@SubscribeEvent
	public void onClientTick(RenderTickEvent event)
	{
		this.renderTickTime = event.renderTickTime;
	}

	@SubscribeEvent
	public void getOffsetFOV(FOVUpdateEvent event)
	{
		float fov = event.fov;
		EntityPlayer player = event.entity;
		PlayerClientData data = (PlayerClientData) player.getExtendedProperties(Festivities.PLAYERDATA);
		event.newfov = data.updateSnowglobeFov(event.fov, this.renderTickTime, player.dimension == Festivities.kringleId);
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entity;
			Festivities.sendUpdateWarning(player);
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entity;
			player.registerExtendedProperties(Festivities.PLAYERDATA, new PlayerData());
		}
		else if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			player.registerExtendedProperties(Festivities.PLAYERDATA, new PlayerClientData());
		}
	}

	@SubscribeEvent
	public void entityUpdateEvent(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			PlayerData data = (PlayerData) player.getExtendedProperties(Festivities.PLAYERDATA);
			data.tickSnowglobe(player);
		}
	}

	@SubscribeEvent
	public void onBlockHarvest(HarvestDropsEvent event)
	{
		if (event.block == Blocks.grass)
		{
			if (event.world.rand.nextFloat() <= 0.08F)
			{
				event.drops.add(new ItemStack(Festivities.berries, 1));
			}
			if (event.world.rand.nextFloat() <= 0.1F)
			{
				event.drops.add(new ItemStack(Festivities.holly, 1));
			}
		}
		if (event.block instanceof BlockFlower)
		{
			if (event.world.rand.nextFloat() <= 0.15F)
			{
				event.drops.add(new ItemStack(Festivities.berries, 2));
			}
			if (event.world.rand.nextFloat() <= 0.24F)
			{
				event.drops.add(new ItemStack(Festivities.holly, 1));
			}
		}
	}
}
