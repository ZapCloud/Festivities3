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
		int i = 0;
		for (SnowglobeScene scene : SnowglobeScene.getScenes())
		{
			i++;
			ItemStack stack = new ItemStack(item, 1, i);
			NBTTagCompound data = new NBTTagCompound();
			data.setString("Scene", scene.name);
			stack.setTagCompound(data);
			list.add(stack);
		}
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack item)
	{
		if (item.getItemDamage() == 0)
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
		if (stack.hasTagCompound() && stack.getItemDamage() > 0)
		{
			NBTTagCompound tag = stack.getTagCompound();
			if (tag.hasKey("Scene"))
			{
				String scene = SnowglobeScene.getFromName(tag.getString("Scene")).getUnlocalizedForTranslate();
				scene = StatCollector.translateToLocal(scene + ".name").trim();
				info.add("Scene: " + EnumChatFormatting.GOLD + scene);
			}
			if (tag.hasKey("SceneItem"))
			{
				
			}
			info.add("");
		}
		super.addInformation(stack, player, info, advanced);
	}
	
	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		if (stack.getItemDamage() == 0)
		{
			return new String[] { "Scenic Snow!" };
		}
		else
		{
			return new String[] { "Catch one on your tongue!" };
		}
	}
	
	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		if (stack.getItemDamage() != 0)
		{
			return new String[] { "Set the interior of a snowglobe by right-clicking it" };
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return stack.getItemDamage() != 0;
	}
	
	@Override
	public boolean hasContainerItem(ItemStack item)
	{
		return item.getItemDamage() != 0 && item.hasTagCompound() && item.getTagCompound().hasKey("SceneItem");
	}
}
