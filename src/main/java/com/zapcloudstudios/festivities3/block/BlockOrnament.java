package com.zapcloudstudios.festivities3.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.render.block.RenderBlockOrnament;
import com.zapcloudstudios.utils.FestiveUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOrnament extends BlockFestiveComplex
{
	public final boolean isClear;
	public static boolean canSit = false;
	public static boolean hangAny = true;
	public static boolean doHang = true;
	
	public static final String[] faces = new String[] { "top", "bottom", "front", "side" };
	public static final String[] types = new String[] { "clear", "color", "detail" };
	
	public static IIcon icons[] = new IIcon[12];
	
	public BlockOrnament(boolean clear)
	{
		super(Material.circuits);
		this.setShouldRender3D(false);
		this.isClear = clear;
		this.setBlockBounds(3 / 16.0F, 3 / 16.0F, 3 / 16.0F, 13 / 16.0F, 13 / 16.0F, 13 / 16.0F);
		this.setBlockTextureName(Festivities.ID + ":ornament");
		this.setRenderer(RenderBlockOrnament.class);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		int i = 0;
		if (!this.isClear)
		{
			i += 4;
		}
		if (i == 0)
		{
			i += 1;
		}
		else if (i != 1)
		{
			i += 3;
		}
		return icons[i];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		if (this.isClear)
		{
			return 0xFFFFFF;
		}
		return FestiveUtils.getDyeColor(world.getBlockMetadata(x, y, z));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int i = 0;
		if (!this.isClear)
		{
			i += 4;
		}
		if (side == 0)
		{
			i += 1;
		}
		else if (side != 1)
		{
			long poshash = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
			poshash = poshash * poshash * 42317861L + poshash * 11L;
			int dir = (int) (poshash >> 16 & 3L);
			if (dir == side - 2)
			{
				i += 2;
			}
			else
			{
				i += 3;
			}
		}
		return icons[i];
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getDetailIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int i = 8;
		if (side == 0)
		{
			i += 1;
		}
		else if (side != 1)
		{
			long poshash = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
			poshash = poshash * poshash * 42317861L + poshash * 11L;
			int dir = (int) (poshash >> 16 & 3L);
			if (dir == side - 2)
			{
				i += 2;
			}
			else
			{
				i += 3;
			}
		}
		return icons[i];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		int i = 0;
		for (String t : types)
		{
			for (String f : faces)
			{
				icons[i] = reg.registerIcon(this.getTextureName() + "_" + t + "_" + f);
				i++;
			}
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	public boolean isClear()
	{
		return this.isClear;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return super.canPlaceBlockAt(world, x, y, z) && this.canHangAt(world, x, y, z);
	}
	
	public boolean canHangAt(World world, int x, int y, int z)
	{
		if (this.doHang)
		{
			if (this.canHangFrom(world, x, y + 1, z, 0))
			{
				return true;
			}
			if (this.canHangFrom(world, x + 1, y, z, 1))
			{
				return true;
			}
			if (this.canHangFrom(world, x - 1, y, z, 1))
			{
				return true;
			}
			if (this.canHangFrom(world, x, y, z + 1, 1))
			{
				return true;
			}
			if (this.canHangFrom(world, x, y, z - 1, 1))
			{
				return true;
			}
			if (this.canHangFrom(world, x, y - 1, z, 2))
			{
				return true;
			}
			return false;
		}
		return true;
	}
	
	public boolean canHangFrom(World world, int x, int y, int z, int dir)
	{
		Block b = world.getBlock(x, y, z);
		if (b.isAir(world, x, y, z))
		{
			return false;
		}
		else
		{
			if (dir == 2 && this.canSit && world.doesBlockHaveSolidTopSurface(world, x, y, z))
			{
				return true;
			}
			if (this.hangAny)
			{
				return !(b instanceof BlockOrnament);
			}
		}
		if (b.isLeaves(world, x, y, z))
		{
			return true;
		}
		Material mat = b.getMaterial();
		if (dir == 0)
		{
			if (b.isSideSolid(world, x, y, z, ForgeDirection.DOWN))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block change)
	{
		if (!this.canHangAt(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
	
	/**
	 * Get the block's damage value (for use with pick block).
	 */
	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		if (this.isClear)
		{
			return 0;
		}
		else
		{
			return world.getBlockMetadata(x, y, z);
		}
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		if (this.isClear)
		{
			return Festivities.clearOrnament;
		}
		else
		{
			return Festivities.coloredOrnament;
		}
	}
	
	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Oops...", "Use the ornament item", "This is a technical block" };
	}
	
	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return null;
	}
}
