package com.zapcloudstudios.utils.perlin;

interface IPerlinLayer extends IPerlinGroup
{
	public float[] getChunk(float u, float v);
}
