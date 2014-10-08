package com.zapcloudstudios.festivities3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	public RenderItem itemRenderer;
	
	@Override
	public void grabRenders()
	{
		this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}
	
	@Override
    public void setItemModel(Item item, int meta, String model)
    {
        this.itemRenderer.getItemModelMesher().register(item, meta, new ModelResourceLocation(model, "inventory"));
    }
}
