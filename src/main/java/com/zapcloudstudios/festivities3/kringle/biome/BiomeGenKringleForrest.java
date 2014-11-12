package com.zapcloudstudios.festivities3.kringle.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenKringleForrest extends BiomeGenKringle
{
	public BiomeGenKringleForrest(int id, float candy, float plant)
	{
		super(id, candy, plant);

		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;

		this.getDecorator().peppermintPolesPerChunk = 4;
		this.getDecorator().treesPerChunk = 4;
		this.getDecorator().christmasTreesPerChunk = 1;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false);
	}
}