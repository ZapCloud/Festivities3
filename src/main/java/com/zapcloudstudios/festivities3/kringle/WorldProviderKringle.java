package com.zapcloudstudios.festivities3.kringle;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.kringle.biome.WorldChunkManagerKringle;
import com.zapcloudstudios.festivities3.kringle.gen.ChunkProviderKringle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderKringle extends WorldProvider
{
	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerKringle(this.worldObj.getSeed(), WorldType.DEFAULT);
		this.dimensionId = Festivities.kringleId;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderKringle(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	public String getDimensionName()
	{
		return "Kringle";
	}

	@Override
	public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
	{
		Block i = this.worldObj.getBlock(x, y, z);
		return (i == Blocks.water || i == Blocks.flowing_water) && this.worldObj.getBlockMetadata(x, y, z) == 0;
	}

	@Override
	public boolean canSnowAt(int x, int y, int z, boolean checkLight)
	{
		Block l = this.worldObj.getBlock(x, y - 1, z);
		Block i1 = this.worldObj.getBlock(x, y, z);

		if (i1.isAir(this.worldObj, x, y, z) && Blocks.snow.canPlaceBlockAt(this.worldObj, x, y, z) && !l.isAir(this.worldObj, x, y, z) && l != Blocks.ice && l.getMaterial().blocksMovement())
		{
			return true;
		}
		return false;
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified
	 * time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long par1, float par3)
	{
		return 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		return 1.0F;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Entering the Kringle";
	}
}
