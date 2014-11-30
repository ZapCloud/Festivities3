package com.zapcloudstudios.festivities3.kringle.biome;

public class BiomeGenKringlePlains extends BiomeGenKringle
{
	public BiomeGenKringlePlains(int id, float candy, float plant)
	{
		super(id, candy, plant);

		this.getDecorator().peppermintPolesPerChunk = 14;
		this.getDecorator().peppermintCanePerChunk = 8;
		this.getDecorator().peppermintArchPerChunk = 4;
	}
}
