package com.zapcloudstudios.festivities3.kringle.gen.feature;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class KringleScatteredFeatures extends MapGenStructure
{
	@Override
	public String func_143025_a()
	{
		return "KringleScattered";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_)
	{
		return false;
	}

	@Override
	protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_)
	{
		return null;
	}
}
