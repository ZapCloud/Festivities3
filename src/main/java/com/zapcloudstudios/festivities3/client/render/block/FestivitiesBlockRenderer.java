package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class FestivitiesBlockRenderer
{
	public static void renderGarland(int id, int meta, int x, int y, int z)
	{
		Tessellator tess = Tessellator.instance;
		
		BoxDrawBasic draw = new BoxDrawBasic();
		// draw.setTexture(Festivities.ID, "textures/tile/garland.png", 16, 16);
		
		tess.startDrawingQuads();
		
		int style = meta / 8;
		meta %= 8;
		
		int thick = 6;
		
		int xpos = 8;
		int ypos = 8;
		int zpos = 8;
		
		int dir;
		
		if (meta < 4)
		{
			int side = meta % 4;
			dir = side / 2;
			switch (side)
			{
				case 0:
					xpos = thick / 2;
					break;
				case 1:
					xpos = 16 - thick / 2;
					break;
				case 2:
					zpos = thick / 2;
					break;
				case 3:
					zpos = 16 - thick / 2;
					break;
			}
		}
		else
		{
			meta = meta % 4;
			dir = meta % 2;
			if (meta < 2)
			{
				ypos = thick / 2;
			}
			else
			{
				ypos = 16 - thick / 2;
			}
		}
		
		int boxx = xpos;
		int boxy = ypos;
		int boxz = zpos;
		
		if (dir == 0)
		{
			boxz = 2;
		}
		if (dir == 1)
		{
			boxx = 2;
		}
		
		int[] offx = new int[] { 0, 1, -1, 0 };
		int[] offy = new int[] { -1, 0, -1, 1 };
		int[] offz = new int[] { 1, -1, 0, 0 };
		for (int i = 0; i < 4; i++)
		{
			if (dir == 0)
			{
				renderGarlandCube(draw, boxx + offx[i], boxy + offy[i], boxz, 4, style);
				boxz += 4;
			}
			if (dir == 1)
			{
				renderGarlandCube(draw, boxx, boxy + offy[i], boxz + offz[i], 4, style);
				boxx += 4;
			}
		}
		
		tess.draw();
	}
	
	public static void renderGarlandCube(BoxDrawBasic draw, int x, int y, int z, int thick, int style)
	{
		Tessellator tess = Tessellator.instance;
		draw.cube(x - thick / 2, y - thick / 2, z - thick / 2, thick, thick, thick);
		
		if (style == 0)
		{
			draw.selectUV(0, 0);
		}
		if (style == 1)
		{
			draw.selectUV(0, 8);
		}
		
		draw.drawAllNormalTextureShape();
	}
	
	public static void renderOrnament(Block id, int meta, int x, int y, int z)
	{
		Tessellator tess = Tessellator.instance;
		BoxDrawBasic draw = new BoxDrawBasic();
		// draw.setTexture(Festivities.ID, "textures/tile/ornament.png", 64,
		// 32);
		tess.startDrawingQuads();
		
		boolean clear = id == Festivities.clearOrnamentBlock && id != Festivities.coloredOrnamentBlock;
		
		long poshash = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
		poshash = poshash * poshash * 42317861L + poshash * 11L;
		int dir = (int) (poshash >> 16 & 3L);
		
		draw.cube(3, 3, 3, 10, 10, 10);
		
		if (clear)
		{
			draw.selectUV(0, 0);
			draw.YUp();
			draw.selectUV(10, 0);
			draw.YDown();
			
			draw.selectUV((dir == 0 ? 10 : 0), 10);
			draw.ZDown();
			draw.selectUV((dir == 1 ? 10 : 0), 10);
			draw.ZUp();
			draw.selectUV((dir == 2 ? 10 : 0), 10);
			draw.XDown();
			draw.selectUV((dir == 3 ? 10 : 0), 10);
			draw.XUp();
		}
		else
		{
			// tess.setColorOpaque_I(ItemOrnament.ornamentColors[meta]);
			
			draw.selectUV(20, 0);
			draw.YUp();
			draw.selectUV(30, 0);
			draw.YDown();
			
			draw.selectUV((dir == 0 ? 30 : 20), 10);
			draw.ZDown();
			draw.selectUV((dir == 1 ? 30 : 20), 10);
			draw.ZUp();
			draw.selectUV((dir == 2 ? 30 : 20), 10);
			draw.XDown();
			draw.selectUV((dir == 3 ? 30 : 20), 10);
			draw.XUp();
		}
		
		tess.draw();
		tess.startDrawingQuads();
		
		tess.setColorOpaque_I(0xFFFFFF);
		
		draw.selectUV(40, 0);
		draw.YUp();
		draw.selectUV(50, 0);
		draw.YDown();
		
		draw.selectUV((dir == 0 ? 50 : 40), 10);
		draw.ZDown();
		draw.selectUV((dir == 1 ? 50 : 40), 10);
		draw.ZUp();
		draw.selectUV((dir == 2 ? 50 : 40), 10);
		draw.XDown();
		draw.selectUV((dir == 3 ? 50 : 40), 10);
		draw.XUp();
		
		tess.draw();
	}
}
