package com.zapcloudstudios.festivities3.player;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
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
	public static final int timeToExit = 80;

	public boolean hasGlobe = false;
	public int globex;
	public int globey;
	public int globez;
	public int globeDimention;

	public boolean hasExit = false;
	public int exitx;
	public int exitz;

	private int failSnowglobeTime;

	protected boolean isLookingAtSnowglobePortal = false;
	private int snowglobeTime;
	protected float snowglobeTimeFraction;

	protected boolean isExiting = false;
	private int exitTime;
	protected float exitTimeFraction;

	public long santaCooldown = 0;

	public boolean isLookingAtSnowglobePortal()
	{
		return this.isLookingAtSnowglobePortal;
	}

	public void tickSnowglobeExit(EntityPlayer player)
	{
		this.isExiting = false;
		if (player.dimension == Festivities.kringleId && player.isSneaking())
		{
			if (Vec3.createVectorHelper(0, 1, 0).dotProduct(player.getLookVec()) > 0.95F)
			{
				this.isExiting = true;
			}
		}

		if (this.isExiting)
		{
			this.exitTime++;
		}
		else
		{
			this.exitTime = 0;
		}

		this.exitTimeFraction = this.exitTime / (float) timeToExit;

		if (this.exitTimeFraction > 1.0F)
		{
			this.exitTime = 0;
			if (!player.worldObj.isRemote && player instanceof EntityPlayerMP)
			{
				MinecraftServer mcserver = ((EntityPlayerMP) player).mcServer;
				int d = 0;
				if (this.hasGlobe)
				{
					d = this.globeDimention;
				}
				mcserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, d, new KringleTeleporter(mcserver.worldServerForDimension(d)));
			}
		}
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
			if (this.isLookingAtSnowglobePortal)
			{
				this.failSnowglobeTime++;
				if (this.failSnowglobeTime > 60)
				{
					if (!player.worldObj.isRemote && player instanceof EntityPlayerMP)
					{
						player.addChatComponentMessage(new ChatComponentText("Look up and hold shift to exit the kringle."));
					}
					this.failSnowglobeTime = -200;
				}
			}
			return;
		}

		this.failSnowglobeTime = 0;

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
		compound.setInteger("globeworld", this.globeDimention);
		compound.setBoolean("hasglobe", this.hasGlobe);

		compound.setInteger("exitx", this.exitx);
		compound.setInteger("exitz", this.exitz);
		compound.setBoolean("hasexit", this.hasExit);

		//compound.setBoolean("isLookingAtSnowglobePortal", this.isLookingAtSnowglobePortal);
		//compound.setFloat("poralFraction", this.snowglobeTimeFraction);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		this.globex = compound.getInteger("globex");
		this.globey = compound.getInteger("globey");
		this.globez = compound.getInteger("globez");
		this.globeDimention = compound.getInteger("globeworld");
		this.hasGlobe = compound.getBoolean("hasglobe");

		this.exitx = compound.getInteger("exitx");
		this.exitz = compound.getInteger("exitz");
		this.hasExit = compound.getBoolean("hasexit");
	}

	@Override
	public void init(Entity entity, World world)
	{

	}
}
