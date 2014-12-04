package com.zapcloudstudios.festivities3.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.render.block.RenderBlockSnowman;

public class BlockSnowman extends BlockFestiveComplex
{
	public BlockSnowman()
	{
		super(Material.craftedSnow);
		this.setRenderer(RenderBlockSnowman.class);
		this.setBlockTextureName("snow");
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
	public void onNeighborBlockChange(World world, int x, int y, int z, Block changed)
	{
		boolean flag = false;
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 4)
		{
			if (world.getBlock(x, y + 1, z) != this || world.getBlockMetadata(x, y + 1, z) != 4)
			{
				world.setBlockToAir(x, y, z);
				flag = true;
			}
		}
		else
		{
			if (world.getBlock(x, y - 1, z) != this || world.getBlockMetadata(x, y - 1, z) >= 4)
			{
				world.setBlockToAir(x, y, z);
				flag = true;
			}
		}
		if (flag)
		{
			this.dropBlockAsItem(world, x, y, z, meta, 0);
		}
	}

	@Override
	public Item getItem(World world, int x, int y, int z)
	{
		return Festivities.snowmanParts;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		drops.add(new ItemStack(Items.snowball, 3));
		if (metadata < 4)
		{
			drops.add(new ItemStack(Festivities.snowmanParts));
		}
		return drops;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item)
	{
		if (world.getBlockMetadata(x, y, z) < 4)
		{
			int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			world.setBlockMetadataWithNotify(x, y, z, l, 2);
			world.setBlock(x, y + 1, z, this, 4, 3);
		}
	}
}
