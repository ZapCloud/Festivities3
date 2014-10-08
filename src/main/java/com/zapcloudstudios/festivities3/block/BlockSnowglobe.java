package com.zapcloudstudios.festivities3.block;

import java.util.List;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSnowglobe extends BlockDirectional
{
	public static enum EnumGlobeWorld implements IStringSerializable
	{
		KRINGLE("kringle"),
		OVERWORLD("over");
		
		private final String name;
		
		EnumGlobeWorld(String name)
		{
			this.name = name;
		}

		@Override
		public String getName()
		{
			return null;
		}
	}
	
	public static final PropertyBool WORLD_PROP = PropertyBool.create("world");
	public static final PropertyBool PORTAL_PROP = PropertyBool.create("portal");
	
	public BlockSnowglobe()
	{
		super(Material.glass);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, EnumFacing.NORTH).withProperty(PORTAL_PROP, false).withProperty(WORLD_PROP, false));
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		float a = 1.0F / 16.0F;
		float b = 15.0F / 16.0F;
		this.setBlockBounds(a, 0.0F, a, b, 1.0F, b);
		this.setLightOpacity(1);
	}
	
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 2; i++)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		boolean w = false;
		if (worldIn.provider.getDimensionName().equals("kringle"))
		{
			w = true;
		}
		return this.getDefaultState().withProperty(AGE, placer.func_174811_aO().getOpposite()).withProperty(PORTAL_PROP, (meta & 1) > 0).withProperty(WORLD_PROP, w);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(AGE, EnumFacing.getHorizontal((meta >> 0) & 3)).withProperty(PORTAL_PROP, ((meta >> 2) & 1) > 0).withProperty(WORLD_PROP, ((meta >> 3) & 1) > 0);
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i += ((Boolean) state.getValue(WORLD_PROP)) ? 1 : 0;
		i <<= 1;
		i += ((Boolean) state.getValue(PORTAL_PROP)) ? 1 : 0;
		i <<= 2;
		i += ((EnumFacing) state.getValue(AGE)).getHorizontalIndex();
		return i;
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AGE, WORLD_PROP, PORTAL_PROP});
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isFullCube()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
}
