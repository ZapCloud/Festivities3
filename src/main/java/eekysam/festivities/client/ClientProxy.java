package eekysam.festivities.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import eekysam.festivities.CommonProxy;
import eekysam.festivities.Festivities;
import eekysam.festivities.client.render.RenderBlockFestivitiesHandler;
import eekysam.festivities.client.render.entity.RenderCandyCreeper;
import eekysam.festivities.client.render.tileEntity.TileEntityPlateRenderer;
import eekysam.festivities.client.render.tileEntity.TileEntitySnowMachineRenderer;
import eekysam.festivities.client.render.tileEntity.TileEntitySnowglobeRenderer;
import eekysam.festivities.entity.EntityCandyCreeper;
import eekysam.festivities.tile.TileEntityPlate;
import eekysam.festivities.tile.TileEntitySnowMachine;
import eekysam.festivities.tile.TileEntitySnowglobe;

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

		RenderingRegistry.registerEntityRenderingHandler(EntityCandyCreeper.class, new RenderCandyCreeper());
	}
}
