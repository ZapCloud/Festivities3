package com.zapcloudstudios.festivities3.client;

import com.zapcloudstudios.festivities3.CommonProxy;
import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.render.RenderBlockFestivitiesHandler;
import com.zapcloudstudios.festivities3.client.render.tileEntity.TileEntityPlateRenderer;
import com.zapcloudstudios.festivities3.client.render.tileEntity.TileEntitySnowMachineRenderer;
import com.zapcloudstudios.festivities3.client.render.tileEntity.TileEntitySnowglobeRenderer;
import com.zapcloudstudios.festivities3.tile.TileEntityPlate;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowMachine;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySnowglobe.class, new TileEntitySnowglobeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlate.class, new TileEntityPlateRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySnowMachine.class, new TileEntitySnowMachineRenderer());
		
		RenderingRegistry.registerBlockHandler(Festivities.block2dItemRenderId, new RenderBlockFestivitiesHandler(Festivities.block2dItemRenderId, false));
		RenderingRegistry.registerBlockHandler(Festivities.block3dItemRenderId, new RenderBlockFestivitiesHandler(Festivities.block3dItemRenderId, true));
	}
}
