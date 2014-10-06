package com.zapcloudstudios.festivities3;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation()
	{
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setupGuiIngameForge()
	{
		/*
		net.minecraftforge.client.GuiIngameForge.renderHealth = false;
		net.minecraftforge.client.GuiIngameForge.renderArmor = false;
		net.minecraftforge.client.GuiIngameForge.renderExperiance = false;
		*/
	}
}
