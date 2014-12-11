package com.zapcloudstudios.festivities3.item;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.ITipItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ItemChristmasRecord extends ItemRecord implements ITipItem
{
	private String[] tip = null;
        private String[] shifttip = null;

        public ItemChristmasRecord(String recordName)
        {
            super(recordName);
            this.maxStackSize = 1;
        }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean advanced)
	{
		Festivities.addInformation(this, stack, player, info, advanced);
	}
         
        @SideOnly(Side.CLIENT)
        public String getRecordNameLocal()
        {
            return StatCollector.translateToLocal("item." + this.recordName + ".desc");
        }
        
        @Override
        public ResourceLocation getRecordResource(String name)
        {
            return new ResourceLocation(Festivities.ID + ":" + name);
        }
        
	public ItemChristmasRecord setTip(String... tip)
	{
		this.tip = tip;
		return this;
	}
	
	public ItemChristmasRecord setShiftTip(String... tip)
	{
		this.shifttip = tip;
		return this;
	}
	
	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return this.tip;
	}
	
	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return this.shifttip;
	}
	
                
}