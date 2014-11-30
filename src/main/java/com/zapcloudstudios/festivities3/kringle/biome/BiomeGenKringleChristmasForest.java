package com.zapcloudstudios.festivities3.kringle.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenKringleChristmasForest extends BiomeGenKringle
{
	public BiomeGenKringleChristmasForest(int id, float candy, float plant)
	{
		super(id, candy, plant);

		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;

		this.getDecorator().peppermintPolesPerChunk = 6;
		this.getDecorator().peppermintCanePerChunk = 6;
		this.getDecorator().treesPerChunk = 1;
		this.getDecorator().christmasTreesPerChunk = 10;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false);
	}
}