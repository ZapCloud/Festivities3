package eekysam.festivities.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import eekysam.festivities.Festivities;
import eekysam.festivities.ITipItem;
import eekysam.festivities.tile.TileEntityOrnament;

public class BlockOrnament extends BlockContainer implements ITipItem
{
	private boolean clear;
	public static boolean canSit = false;
	public static boolean hangAny = true;
	public static boolean doHang = true;
	
	public BlockOrnament(boolean clear)
	{
		super(Material.circuits);
		this.clear = clear;
		this.setBlockBounds(3 / 16.0F, 3 / 16.0F, 3 / 16.0F, 13 / 16.0F, 13 / 16.0F, 13 / 16.0F);
		this.setBlockTextureName(Festivities.ID + ":ornament");
	}
	
	@Override
	public int getRenderType()
	{
		return Festivities.blockItemRenderId;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	public boolean isClear()
	{
		return this.clear;
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
		if (this.clear)
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
		if (this.clear)
		{
			return Festivities.clearOrnament;
		}
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TileEntityOrnament();
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
