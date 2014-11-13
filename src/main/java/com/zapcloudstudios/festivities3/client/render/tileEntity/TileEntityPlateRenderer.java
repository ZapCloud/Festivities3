package com.zapcloudstudios.festivities3.client.render.tileEntity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.tile.TileEntityPlate;
import com.zapcloudstudios.festivities3.tile.TileEntityPlate.PlateFoods;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class TileEntityPlateRenderer extends TileEntityFestivitiesRenderer
{
	public static final short[] cookieXPos = new short[] { 6, 7, 2, 4, 10, 5, 8, 2, 3, 5, 3, 10, 9, 5, 10, 10, 7, 7, 1, 3 };
	public static final short[] cookieYPos = new short[] { 0, 0, 0, 1, 0, 0, 1, 0, 1, 2, 0, 0, 1, 1, 2, 0, 2, 3, 1, 2 };
	public static final short[] cookieZPos = new short[] { 4, 8, 7, 6, 3, 1, 3, 3, 3, 5, 11, 7, 7, 10, 5, 10, 8, 6, 9, 8 };
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.render((TileEntityPlate) tileentity);
		GL11.glPopMatrix();
	}
	
	public void render(TileEntityPlate tile)
	{
		Tessellator t = Tessellator.instance;
		int cookieAt = 0;
		int figgyAt = 0;
		int pieAt = 0;
		for (PlateFoods item : tile.onPlate)
		{
			if (item.isCookie)
			{
				this.renderCookie(1 + this.cookieXPos[cookieAt], 1 + this.cookieYPos[cookieAt], 1 + this.cookieZPos[cookieAt], item, 0);
				cookieAt++;
			}
			if (item == PlateFoods.Figgy)
			{
				if (figgyAt == 0)
				{
					this.renderFiggy(1, 1, 1);
				}
				if (figgyAt == 1)
				{
					this.renderFiggy(8, 1, 8);
				}
				figgyAt++;
			}
			if (item == PlateFoods.BluPie)
			{
				this.renderPie(4, 1, 4, "blu");
				pieAt++;
			}
			if (item == PlateFoods.PmkPie)
			{
				this.renderPie(4, 1, 4, "ppk");
				pieAt++;
			}
		}
	}
	
	public void renderFiggy(int x, int y, int z)
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		Tessellator t = Tessellator.instance;
		draw.setTexture(this, Festivities.ID, "textures/tile/plate_figgy.png", 24, 11);
		t.startDrawingQuads();
		
		draw.setPos(x, y, z);
		draw.cube(6, 5, 6);
		draw.selectUV(0, 0);
		draw.drawAllLeftJustTextureShape(true);
		
		draw.setPos(x + 3, y + 6, z + 3);
		draw.cube(1, 1, 1);
		draw.selectUV(12, 4);
		draw.drawAllNormalTextureShape();
		
		draw.setPos(x + 3, y + 5, z + 2);
		draw.drawAllNormalTextureShape();
		
		draw.setPos(x + 2, y + 5, z + 3);
		draw.drawAllNormalTextureShape();
		
		float leafx = 3 + x;
		float leafy = 5.5F + y;
		float leafz = 3 + z;
		float X = 3;
		float Z = 3;
		
		draw.addVertexWithUV(leafx, leafy, leafz, 16, 6);
		draw.addVertexWithUV(leafx + X, leafy, leafz, 16, 3);
		draw.addVertexWithUV(leafx + X, leafy, leafz + Z, 19, 3);
		draw.addVertexWithUV(leafx, leafy, leafz + Z, 19, 6);
		
		t.draw();
	}
	
	public void renderPie(int x, int y, int z, String type)
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		Tessellator t = Tessellator.instance;
		draw.setTexture(this, Festivities.ID, "textures/tile/plate_pie_" + type + ".png", 32, 16);
		t.startDrawingQuads();
		
		draw.setPos(x, y, z);
		draw.cube(8, 4, 8);
		draw.selectUV(0, 4);
		draw.drawAllLeftJustTextureShape(false);
		
		draw.setPos(x + 1, y + 3, z + 1);
		draw.cube(6, 1, 6);
		draw.faceIn();
		draw.selectUV(0, 3);
		draw.drawSidesGroupedTexture();
		draw.selectUV(16, 6);
		draw.YDown();
		
		t.draw();
	}
	
	public void renderCookie(int x, int y, int z, PlateFoods type, int texture)
	{
		int t = -1;
		switch (type)
		{
			case ChipCookie:
				t = 0;
				break;
			case SugarCookie:
				t = 1;
				break;
			case ChocCookie:
				t = 2;
				break;
			case SprinkCookie:
				t = 3;
				break;
			case CandyCookie:
				t = 4;
				break;
			default:
				break;
		}
		this.renderCookie(x, y, z, t, texture);
	}
	
	public void renderCookie(int x, int y, int z, int type, int texture)
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		Tessellator t = Tessellator.instance;
		draw.setTexture(this, Festivities.ID, "textures/tile/plate_cookie.png", 16, 16);
		t.startDrawingQuads();
		draw.setPos(x, y, z);
		draw.selectUV(type * 3, texture * 3);
		draw.cube(3, 1, 3);
		draw.YUp();
		draw.selectV(13);
		draw.YDown();
		draw.selectV(12);
		draw.drawSidesSameTexture();
		t.draw();
	}
}
