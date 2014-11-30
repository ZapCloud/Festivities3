package com.zapcloudstudios.festivities3.kringle.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.zapcloudstudios.festivities3.Festivities;

public class WorldGenPeppermintArch extends WorldGenerator implements WorldGenFestive
{
	public int minHeight;
	public int minWidth;
	public int wvar;
	public int hvar;
	public int runs;
	public boolean cane;

	public WorldGenPeppermintArch(int minHeight, int heightvariance, int minWidth, int widthvariance, int runs, boolean cane)
	{
		this.minHeight = minHeight;
		this.hvar = heightvariance;
		this.minWidth = minWidth;
		this.wvar = widthvariance;
		this.runs = runs;
		this.cane = cane;
	}

	public boolean placeCandyLog(World world, int x, int y, int z, int meta)
	{
		Block id = world.getBlock(x, y, z);
		if (id.isReplaceable(world, x, y, z))
		{
			world.setBlock(x, y, z, Festivities.candyLog, 0, meta);
			return true;
		}
		return false;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int t = 0; t < this.runs; t++)
		{
			int X1 = x + random.nextInt(8) - random.nextInt(8);
			int Y1 = y + random.nextInt(8) - random.nextInt(8);
			int Z1 = z + random.nextInt(8) - random.nextInt(8);

			if (this.canGrow(world, X1, Y1, Z1))
			{
				int h = this.minHeight + random.nextInt(this.hvar);
				int h1 = (int) (h * 0.6F);
				int h2 = h - h1;

				double theta = random.nextFloat() * Math.PI * 2;

				int X2 = X1 + (int) ((random.nextInt(this.wvar * 2) + this.minWidth) * Math.cos(theta));
				int Z2 = Z1 + (int) ((random.nextInt(this.wvar * 2) + this.minWidth) * Math.cos(theta));
				int Y2 = Y1;

				for (int i = 0; i < h1; i++)
				{
					this.placeCandyLog(world, X1, Y1 + i, Z1, 0);
				}

				if (!this.cane)
				{
					int i = h1;
					while (this.placeCandyLog(world, X2, Y2 + i, Z2, 0))
					{
						i--;
					}
				}

				Y1 += h1;
				Y2 += h1;

				int dx = X2 - X1;
				int dz = Z2 - Z1;
				float ls = dx * dx + dz * dz;
				int xs = Integer.signum(dx);
				int zs = Integer.signum(dz);
				dx *= xs;
				dz *= zs;

				int X = 0;
				int Z = 0;
				while (dx != 0 && dz != 0)
				{
					float f = (dx * dx + dz * dz) / ls;
					f = (float) (Math.sqrt(f)) * 2 - 1;
					float F = (float) Math.sqrt(1 - f * f);
					F *= h2;

					int meta;
					if (f < -0.5F || f > 0.5F)
					{
						meta = 0;
					}
					else if (dx >= dz)
					{
						meta = 4;
					}
					else
					{
						meta = 8;
					}

					this.placeCandyLog(world, X + X1, Y1 + (int) F, Z + Z1, meta);

					if (dx >= dz)
					{
						dx--;
						X += xs;
					}
					else
					{
						dz--;
						Z += zs;
					}
				}

				return true;
			}
		}
		return false;
	}

	public boolean canGrow(World world, int x, int y, int z)
	{
		boolean flag = true;
		for (int i = 0; i < 2; i++)
		{
			flag &= world.getBlock(x, y + i, z).isReplaceable(world, x, y + i, z);
		}
		flag &= world.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
		return flag;
	}
}
