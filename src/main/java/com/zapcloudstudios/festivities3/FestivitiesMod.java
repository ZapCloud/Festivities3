package com.zapcloudstudios.festivities3;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

@Mod(modid = FestivitiesMod.MODID, version = FestivitiesMod.VERSION, name = "Festivities3")
public class FestivitiesMod
{
	@Mod.Instance("festivities3")
	public static FestivitiesMod instance;
	@SidedProxy(clientSide = "com.zapcloudstudios.festivities3.ClientProxy", serverSide = "com.zapcloudstudios.festivities3.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "festivities3";
	public static final String VERSION = "1.0";

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;

		FestiveBlocks.loadBlocks();

		FestiveBlocks.registerBlocks();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		if (this.proxy instanceof ClientProxy)
		{
			proxy.grabRenders();
			FestiveBlocks.registerItemRenders((ClientProxy) this.proxy);
		}
	}
}
