package com.zapcloudstudios.festivities3.client.render.tileEntity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.zapcloudstudios.festivities3.block.BlockSnowglobe;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;
import com.zapcloudstudios.utils.EnumDirection;
import com.zapcloudstudios.utils.draw.SideDrawBasic;

public class TileEntitySnowglobeRenderer extends TileEntityFestivitiesRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		Tessellator tess = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		
		TileEntitySnowglobe globe = (TileEntitySnowglobe) tile;
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		if (globe.scene != null)
		{
			SideDrawBasic side = new SideDrawBasic();
			side.setTexture(this, globe.scene.getResource(), 98, 40);
			
			tess.startDrawingQuads();
			
			for (int i = 0; i < 7; i++)
			{
				side.side(EnumDirection.YUp, 14, 14, 1, i * 2 + 2, 1);
				side.selectUV(14 * i, 26);
				side.setFlip(false, true);
				side.draw();
			}
			
			for (int i = 0; i < 7; i++)
			{
				side.side(EnumDirection.ZUp, 14, 13, 1, 2, 14 - i * 2);
				side.selectUV(14 * i, 0);
				side.setFlip(true, false);
				side.draw();
			}
			
			for (int i = 0; i < 7; i++)
			{
				side.side(EnumDirection.XUp, 14, 13, i * 2 + 2, 2, 1);
				side.selectUV(14 * i, 13);
				side.setFlip(false, false);
				side.draw();
			}
			
			tess.draw();
		}
		else
		{
			SideDrawBasic side = new SideDrawBasic();
			side.setTexture(this, TextureMap.locationBlocksTexture, BlockSnowglobe.baseIcon);
			tess.startDrawingQuads();
			
			side.side(EnumDirection.YUp, 14, 14, 1, 3, 1);
			side.selectUV(1, 1);
			side.draw();
			
			tess.draw();
		}
		GL11.glPopMatrix();
	}
}
