package com.zapcloudstudios.festivities3.tile;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.player.PlayerClientData;
import com.zapcloudstudios.festivities3.kringle.KringleTeleporter;
import com.zapcloudstudios.festivities3.player.PlayerData;

public class TileEntitySnowglobe extends TileEntityFestive
{
	private long ticks;

	public static final int portalTime = 120;
	public static final int lookTick = 10;

	private ItemStack sceneItem = null;
	private boolean isPortal = false;
	private SnowglobeScene scene = null;

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setByte("IsPortal", (byte) (this.isPortal ? 1 : 0));
		if (this.scene == null)
		{
			tag.setString("Scene", "");
		}
		else
		{
			tag.setString("Scene", this.scene.name);
		}
		if (this.sceneItem != null)
		{
			NBTTagCompound itemdat = new NBTTagCompound();
			this.sceneItem.writeToNBT(itemdat);
			tag.setTag("SceneItem", itemdat);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.isPortal = tag.getByte("IsPortal") == 1;
		String scenename = tag.getString("Scene");
		if (scenename.isEmpty())
		{
			this.scene = null;
		}
		else
		{
			this.scene = SnowglobeScene.getFromName(scenename);
		}
		if (tag.hasKey("SceneItem"))
		{
			NBTTagCompound itemdat = tag.getCompoundTag("SceneItem");
			this.sceneItem = ItemStack.loadItemStackFromNBT(itemdat);
		}
		else
		{
			this.sceneItem = null;
		}
	}

	public ItemStack popSceneItem()
	{
		if (this.scene != null || this.sceneItem != null)
		{
			this.isPortal = false;
			this.scene = null;
			ItemStack item = this.sceneItem;
			this.sceneItem = null;
			this.markDirty();
			return item;
		}
		return null;
	}

	public boolean giveSceneItem(ItemStack item)
	{
		if (item != null && item.stackSize > 0)
		{
			if (item.isItemEqual(new ItemStack(Festivities.flake, 1, 1)))
			{
				if (item.hasTagCompound() && item.getTagCompound().hasKey("Scene"))
				{
					this.isPortal = false;
					this.scene = SnowglobeScene.getFromName(item.getTagCompound().getString("Scene"));
					this.sceneItem = item.copy();
					this.sceneItem.stackSize = 1;
					this.markDirty();
					return true;
				}
			}
			else if (item.isItemEqual(new ItemStack(Festivities.magicCandy)))
			{
				this.isPortal = true;
				this.scene = SnowglobeScene.portal;
				this.sceneItem = item;
				this.markDirty();
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateEntity()
	{
		this.ticks++;

		if (this.isPortal)
		{
			if (this.ticks % this.lookTick == 0)
			{
				boolean flag = false;

				AxisAlignedBB box = AxisAlignedBB.getBoundingBox(this.xCoord - 16, this.yCoord - 16, this.zCoord - 16, this.xCoord + 16, this.yCoord + 16, this.zCoord + 16);
				List entities = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, box);

				for (int i = 0; i < entities.size(); ++i)
				{
					EntityPlayer player = (EntityPlayer) entities.get(i);

					if (this.isLooking(player) && this.canSee(player))
					{
						long worldtime = this.worldObj.getWorldTime();
						if (this.worldObj.isRemote)
						{
							PlayerClientData data = (PlayerClientData) player.getExtendedProperties(Festivities.PLAYERDATA);
							data.testTimeOut(worldtime);

							int portaltime = data.incrementSnowglobe(worldtime);

							if (portaltime > this.portalTime)
							{
								data.resetSnowglobePortal();
							}
						}
						else
						{
							PlayerData data = (PlayerData) player.getExtendedProperties(Festivities.PLAYERDATA);
							data.testTimeOut(worldtime);

							int portaltime = data.incrementSnowglobe(worldtime);

							if (portaltime > this.portalTime)
							{
								data.resetSnowglobePortal();
								MinecraftServer mServer = MinecraftServer.getServer();
								EntityPlayerMP playermp = (EntityPlayerMP) player;
								if (playermp.dimension == Festivities.kringleId)
								{
									playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, 0, new KringleTeleporter(mServer.worldServerForDimension(0)));
								}
								else
								{
									playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, Festivities.kringleId, new KringleTeleporter(mServer.worldServerForDimension(Festivities.kringleId)));
								}
							}
						}
					}
				}
			}
		}
	}

	protected boolean isLooking(EntityPlayer player)
	{
		Vec3 look = player.getLook(1.0F).normalize();
		Vec3 pos = Vec3.createVectorHelper(this.xCoord - player.posX + 0.5D, this.yCoord - (player.posY + player.getEyeHeight()) + 0.5D, this.zCoord - player.posZ + 0.5D);
		double dist = pos.lengthVector();
		pos = pos.normalize();
		double stare = look.dotProduct(pos);

		return stare > 1.0D - 0.025D / dist;
	}

	protected boolean canSee(EntityPlayer player)
	{
		Vec3 look = player.getLook(1.0F).normalize();
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.xCoord + 0.5D + look.xCoord * -2, this.yCoord + 0.5D + look.yCoord * -2, this.zCoord + 0.5D + look.zCoord * -2), Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ)) == null;
	}

	public SnowglobeScene getScene()
	{
		return this.scene;
	}
}
