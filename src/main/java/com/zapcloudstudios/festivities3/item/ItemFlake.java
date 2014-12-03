package com.zapcloudstudios.festivities3.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.zapcloudstudios.festivities3.tile.SnowglobeScene;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlake extends ItemFestive
{
	public ItemFlake()
	{
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(item, 1, 0));
		for (SnowglobeScene scene : SnowglobeScene.getScenes())
		{
			ItemStack stack = new ItemStack(item, 1, 1);
			NBTTagCompound data = new NBTTagCompound();
			data.setString("Scene", scene.name);
			stack.setTagCompound(data);
			list.add(stack);
		}
	}

	@Override
	public ItemStack getContainerItem(ItemStack item)
	{
		if (item.getItemDamage() != 1)
		{
			return null;
		}
		if (item.hasTagCompound() && item.getTagCompound().hasKey("SceneItem"))
		{
			return ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("SceneItem"));
		}
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean advanced)
	{
		if (stack.hasTagCompound() && stack.getItemDamage() == 1)
		{
			NBTTagCompound tag = stack.getTagCompound();
			if (tag.hasKey("Scene"))
			{
				String scene = SnowglobeScene.getFromName(tag.getString("Scene")).getUnlocalizedForTranslate();
				scene = StatCollector.translateToLocal(scene).trim();
				info.add("Scene: " + EnumChatFormatting.GOLD + scene);
			}
		}
		super.addInformation(stack, player, info, advanced);
	}

	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return stack.getItemDamage() == 1;
	}

	@Override
	public boolean hasContainerItem(ItemStack item)
	{
		return item.getItemDamage() == 1 && item.hasTagCompound() && item.getTagCompound().hasKey("SceneItem");
	}
}
