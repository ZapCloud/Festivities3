package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.tile.TileEntityPlate;
import com.zapcloudstudios.festivities3.tile.TileEntityPlate.PlateFoods;
import com.zapcloudstudios.utils.FestiveUtils;

public class BlockTreatPlate extends BlockFestiveContainer
{
	public static boolean givetip = true;
	
	public BlockTreatPlate(Material par2Material)
	{
		super(par2Material);
		this.setBlockBounds(0 / 16.0F, 0 / 16.0F, 0 / 16.0F, 16 / 16.0F, 4 / 16.0F, 16 / 16.0F);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	@Override
	public void onBlockClicked(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		TileEntityPlate t = (TileEntityPlate) world.getTileEntity(par2, par3, par4);
		if (t != null)
		{
			ItemStack[] items = t.onClear();
			for (int i = 0; i < items.length; i++)
			{
				if (items[i] != null)
				{
					FestiveUtils.dropItem(world, items[i], world.rand, par2, par3, par4, 0.8F, 0.05F);
				}
			}
		}
	}
	
	@Override
	public void onBlockPreDestroy(World world, int par2, int par3, int par4, int par6)
	{
		TileEntityPlate t = (TileEntityPlate) world.getTileEntity(par2, par3, par4);
		if (t != null)
		{
			ItemStack[] items = t.onClear();
			for (int i = 0; i < items.length; i++)
			{
				if (items[i] != null)
				{
					FestiveUtils.dropItem(world, items[i], world.rand, par2, par3, par4, 1, 16, 0.8F, 0.05F);
				}
			}
		}
		super.onBlockPreDestroy(world, par2, par3, par4, par6);
	}
	
	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();

		if (itemstack == null)
		{
			TileEntityPlate t = (TileEntityPlate) world.getTileEntity(par2, par3, par4);
			ItemStack i = t.dropOneItem();
			if (i == null)
			{
				return false;
			}
			else
			{
				FestiveUtils.dropItem(world, i, world.rand, par2, par3, par4, 0.8F, 0.05F);
				return true;
			}
		}
		else
		{
			TileEntityPlate t = (TileEntityPlate) world.getTileEntity(par2, par3, par4);
			PlateFoods food = t.getFood(itemstack);
			if (food != null)
			{
				if (t.addItem(food))
				{
					t.markDirty();;
					if (!par5EntityPlayer.capabilities.isCreativeMode && itemstack.stackSize > 0)
					{
						itemstack.stackSize--;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityPlate();
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
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "A dish to hold all your tasty treats!" };
	}
	
	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Right-Click to add the treat you are holding to the plate", "If the treat isn't added, then it might not fit on the plate", "Remove treats from the plate by Right-Clicking while holding nothing" };
	}

	@Override
	public boolean shouldRender3D()
	{
		return false;
	}
}
