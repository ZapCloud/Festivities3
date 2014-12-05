package com.zapcloudstudios.festivities3.player;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.kringle.KringleTeleporter;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;

public class PlayerData implements IExtendedEntityProperties
{
	public static final int timeToPortal = 80;

	public int globex;
	public int globey;
	public int globez;

	protected boolean isLookingAtSnowglobePortal = false;
	private int snowglobeTime;

	protected float snowglobeTimeFraction;

	public long santaCooldown = 0;

	public boolean isLookingAtSnowglobePortal()
	{
		return this.isLookingAtSnowglobePortal;
	}

	public void tickSnowglobe(EntityPlayer player)
	{
		MovingObjectPosition look = this.serverRayTrace(player, 5);

		this.isLookingAtSnowglobePortal = false;
		if (look != null && look.typeOfHit == MovingObjectType.BLOCK)
		{
			Block block = player.worldObj.getBlock(look.blockX, look.blockY, look.blockZ);
			if (block == Festivities.snowglobe)
			{
				TileEntitySnowglobe tile = (TileEntitySnowglobe) player.worldObj.getTileEntity(look.blockX, look.blockY, look.blockZ);
				if (tile.isPortal())
				{
					this.isLookingAtSnowglobePortal = true;
				}
			}
		}

		if (player.dimension == Festivities.kringleId)
		{
			return;
		}

		if (this.isLookingAtSnowglobePortal)
		{
			this.snowglobeTime++;
		}
		else
		{
			this.snowglobeTime = 0;
		}

		this.snowglobeTimeFraction = this.snowglobeTime / (float) timeToPortal;

		if (this.snowglobeTimeFraction > 1.0F)
		{
			this.snowglobeTime = 0;
			if (!player.worldObj.isRemote && player instanceof EntityPlayerMP)
			{
				MinecraftServer mcserver = ((EntityPlayerMP) player).mcServer;
				mcserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, Festivities.kringleId, new KringleTeleporter(mcserver.worldServerForDimension(Festivities.kringleId)));
			}
		}
	}

	public MovingObjectPosition serverRayTrace(EntityPlayer player, double distance)
	{
		float eye = player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight();
		return this.serverRayTrace(player.worldObj, Vec3.createVectorHelper(player.posX, player.posY + eye, player.posZ), player.getLookVec(), distance);
	}

	public MovingObjectPosition serverRayTrace(World world, Vec3 position, Vec3 vector, double distance)
	{
		vector = vector.normalize();
		Vec3 ray = position.addVector(vector.xCoord * distance, vector.yCoord * distance, vector.zCoord * distance);
		return world.rayTraceBlocks(position, ray, false);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		compound.setInteger("globex", this.globex);
		compound.setInteger("globey", this.globey);
		compound.setInteger("globez", this.globez);

		//compound.setBoolean("isLookingAtSnowglobePortal", this.isLookingAtSnowglobePortal);
		//compound.setFloat("poralFraction", this.snowglobeTimeFraction);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		this.globex = compound.getInteger("globex");
		this.globey = compound.getInteger("globey");
		this.globez = compound.getInteger("globez");
	}

	@Override
	public void init(Entity entity, World world)
	{

	}
}
