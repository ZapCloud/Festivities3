package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

import com.zapcloudstudios.festivities3.FestivitiesMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCandyLog extends BlockLog
{
	public BlockCandyLog()
	{
		super();
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.field_150167_a = new IIcon[] { reg.registerIcon(FestivitiesMod.ASSETLOC + "log_candy_top") };
		this.field_150166_b = new IIcon[] { reg.registerIcon(FestivitiesMod.ASSETLOC + "log_candy_side") };
	}
}
