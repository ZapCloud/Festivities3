package com.zapcloudstudios.festivities3.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.zapcloudstudios.festivities3.Festivities;

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
			if (item.getItem() == Festivities.flake && item.getItemDamage() > 0)
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
				this.sceneItem = item.copy();
				this.sceneItem.stackSize = 1;
				this.markDirty();
				return true;
			}
		}
		return false;
	}

	public boolean isPortal()
	{
		return this.isPortal;
	}

	public SnowglobeScene getScene()
	{
		return this.scene;
	}
}
