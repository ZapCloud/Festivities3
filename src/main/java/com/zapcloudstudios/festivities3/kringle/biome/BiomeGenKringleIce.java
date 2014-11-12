package com.zapcloudstudios.festivities3.kringle.biome;

import net.minecraft.init.Blocks;

public class BiomeGenKringleIce extends BiomeGenKringle
{
	public BiomeGenKringleIce(int id, float candy, float plant)
	{
		super(id, candy, plant);
		
		this.topBlock = Blocks.ice;
		this.fillerBlock = Blocks.ice;
		
		this.getDecorator().peppermintPolesPerChunk = 0;
	}
	
}
