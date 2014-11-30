package com.zapcloudstudios.festivities3.kringle.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.zapcloudstudios.festivities3.Festivities;

public class WorldGenChristmasTree extends WorldGenerator implements WorldGenFestive
{
	public int minHeight;
	public int minWidth;
	public int wvar;
	public int hvar;
	public int runs;

	public WorldGenChristmasTree(int minHeight, int heightvariance, int minWidth, int widthvariance, int runs)
	{
		this.minHeight = minHeight;
		this.hvar = heightvariance;
		this.minWidth = minWidth;
		this.wvar = widthvariance;
		this.runs = runs;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int t = 0; t < this.runs; t++)
		{
			int X = x;
			int Y = y + random.nextInt(16) - random.nextInt(16);
			int Z = z;

			if (this.canGrow(world, X, Y, Z))
			{
				this.genTree(world, random, X, Y, Z);
				return true;
			}
		}
		return false;
	}

	public boolean canGrow(World world, int x, int y, int z)
	{
		boolean flag = true;
		if (!world.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
		{
			return false;
		}
		for (int i = 0; i < this.minHeight; i++)
		{
			Block id = world.getBlock(x, y + i, z);
			if (!id.isReplaceable(world, x, y + i, z) && !id.isLeaves(world, x, y + i, z))
			{
				return false;
			}
		}
		return flag;
	}

	public void genTree(World world, Random random, int x, int y, int z)
	{
		int treeHeight = this.minHeight + random.nextInt(this.hvar);
		int treeWidth = this.minWidth + random.nextInt(this.wvar);
		int trunk = 1 + random.nextInt(3);

		float slope = (treeHeight - trunk) / treeWidth;
		float inslope = treeWidth / ((float) treeHeight - trunk);

		for (int i = 0; i < treeHeight; i++)
		{

			if (i < treeHeight - (int) slope - 3)
			{
				world.setBlock(x, y + i, z, Blocks.log, 1, 2);
			}
			else if (i < treeHeight - (int) slope + 1)
			{
				world.setBlock(x, y + i, z, Blocks.leaves, 5, 2);
			}
			if (i == trunk - 1)
			{
				int w = (int) (treeHeight * inslope);
				int wa = w + 1;
				int ws = w * w;
				int was = wa * wa;
				for (int j = -wa; j <= wa; j++)
				{
					for (int k = -wa; k <= wa; k++)
					{
						if (ws > j * j + k * k)
						{
							if ((j != 0 || k != 0) && random.nextFloat() < 0.1F)
							{
								world.setBlock(x + j, y + i, z + k, Festivities.coloredOrnamentBlock, random.nextInt(16), 2);
							}
						}
					}
				}
			}
			else if (i >= trunk)
			{
				int w = (int) ((treeHeight - i + trunk) * inslope);
				int wplusone = w + 1;
				for (int j = -wplusone; j <= wplusone; j++)
				{
					for (int k = -wplusone; k <= wplusone; k++)
					{
						if (w * w > j * j + k * k)
						{
							if (j != 0 || k != 0)
							{
								world.setBlock(x + j, y + i, z + k, Blocks.leaves, 5, 2);
							}
						}
						else if (wplusone * wplusone > j * j + k * k)
						{
							if ((j != 0 && j != -1 && j != 1) || (k != 0 && k != -1 && k != 1))
							{
								if (random.nextFloat() < 0.1F && Math.abs(j) != Math.abs(k))
								{
									world.setBlock(x + j, y + i, z + k, Festivities.coloredOrnamentBlock, random.nextInt(16), 2);
								}
							}
						}
					}
				}
			}
		}
	}
}
