package com.zapcloudstudios.festivities3.kringle.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.zapcloudstudios.festivities3.kringle.biome.BiomeGenKringle;

public class KringleGenLayerBiomes extends GenLayer
{
	public KringleGenLayerBiomes(long seed)
	{
		super(seed);
	}
	
	public KringleGenLayerBiomes(long seed, GenLayer genlayer)
	{
		super(seed);
		this.parent = genlayer;
	}
	
	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = BiomeGenKringle.kringleBiomes.get(this.nextInt(BiomeGenKringle.kringleBiomes.size())).biomeID;
			}
		}
		return dest;
	}
}
