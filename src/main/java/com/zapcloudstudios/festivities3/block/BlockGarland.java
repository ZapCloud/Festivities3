package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.client.render.block.RenderBlockGarland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGarland extends BlockFestiveComplex
{
	public BlockGarland(Material par2Material)
	{
		super(par2Material);
		this.setShouldRender3D(false);
		this.setRenderer(RenderBlockGarland.class);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.updateGarlandBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	/**
	 * Update the ladder block bounds based on the given metadata value.
	 */
	public void updateGarlandBounds(int meta)
	{
		meta &= 7;

		int xpos = 8;
		int ypos = 8;
		int zpos = 8;

		int dir = meta & 1;

		if ((meta & 4) == 0)
		{
			switch (meta & 3)
			{
				case 0:
					xpos = 13;
					break;
				case 1:
					zpos = 13;
					break;
				case 2:
					xpos = 3;
					break;
				case 3:
					zpos = 3;
					break;
			}
		}
		else
		{
			if ((meta & 2) == 0)
			{
				ypos = 13;
			}
			else
			{
				ypos = 3;
			}
		}

		if (dir == 0)
		{
			this.setBlockBounds((xpos - 3) / 16.0F, (ypos - 3) / 16.0F, 0.0F, (xpos + 3) / 16.0F, (ypos + 3) / 16.0F, 1.0F);
		}
		else
		{
			this.setBlockBounds(0.0F, (ypos - 3) / 16.0F, (zpos - 3) / 16.0F, 1.0F, (ypos + 3) / 16.0F, (zpos + 3) / 16.0F);
		}
	}

	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Oops...", "Use the garland item", "This is a technical block" };
	}

	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return null;
	}
}
