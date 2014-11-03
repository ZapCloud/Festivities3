package com.zapcloudstudios.festivities3.block;

import java.util.List;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSnowglobe extends BlockDirectional
{
	public static enum EnumSnowglobe
	{
		KRINGLE("kringle"),
		OVERWORLD("over");
		
		public static final int number = EnumSnowglobe.values().length;
		
		private final String name;
		
		EnumSnowglobe(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return this.name;
		}
	}
	
	public BlockSnowglobe()
	{
		super(Material.glass);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		float a = 1.0F / 16.0F;
		float b = 15.0F / 16.0F;
		this.setBlockBounds(a, 0.0F, a, b, 1.0F, b);
		this.setLightOpacity(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		for (int i = 0; i < EnumSnowglobe.number; i++)
		{
			list.add(new ItemStack(itemIn, 1, i));
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int X, int Y, int Z, EntityLivingBase player, ItemStack stack)
	{
		int l = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
		world.setBlockMetadataWithNotify(X, Y, Z, l + stack.getItemDamage() * EnumSnowglobe.number, 2);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean isFullCube()
	{
		return false;
	}
}
