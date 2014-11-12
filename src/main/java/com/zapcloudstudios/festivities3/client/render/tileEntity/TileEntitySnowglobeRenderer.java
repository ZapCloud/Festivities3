package com.zapcloudstudios.festivities3.client.render.tileEntity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.render.block.TileEntityFestivitiesRenderer;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;
import com.zapcloudstudios.utils.EnumDirection;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;
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
			SideDrawBasic side = new SideDrawBasic(this.context);
			side.setDoubleSided();
			ResourceLocation loc = globe.scene.getResource();
			side.setTexture(loc.getResourceDomain(), loc.getResourcePath(), 98, 40);
			
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
				side.setFlip(false, false);
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
			SideDrawBasic side = new SideDrawBasic(this.context);
			side.setTexture(Festivities.ID, "textures/snowglobe/base.png", 14, 14);
			tess.startDrawingQuads();
			
			side.side(EnumDirection.YUp, 14, 14, 1, 2, 1);
			side.selectUV(0, 0);
			side.draw();
			
			tess.draw();
		}
		
		BoxDrawBasic draw = new BoxDrawBasic(this.context);
		draw.setTexture(Festivities.ID, "textures/snowglobe/globe.png", 64, 32);
		tess.startDrawingQuads();
		
		draw.cube(0, 0, 0, 16, 3, 16);
		draw.selectUV(16, 0);
		draw.YDown();
		draw.selectUV(0, 0);
		draw.YUp();
		
		int met = tile.getWorldObj().getBlockMetadata(globe.xCoord, globe.yCoord, globe.zCoord);
		
		draw.selectUV((met == 2 ? 0 : 16), 16);
		draw.ZDown();
		draw.selectUV((met == 3 ? 0 : 16), 16);
		draw.ZUp();
		draw.selectUV((met == 4 ? 0 : 16), 16);
		draw.XDown();
		draw.selectUV((met == 5 ? 0 : 16), 16);
		draw.XUp();
		
		draw.cube(1, 2, 1, 14, 13, 14);
		draw.selectUV(46, 0);
		draw.YUp();
		draw.selectUV(32, 0);
		draw.drawSidesSameTexture();
		
		tess.draw();
		GL11.glPopMatrix();
	}
}
