package eekysam.festivities.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import eekysam.festivities.Festivities;
import eekysam.utils.draw.BoxDrawBasic;
import eekysam.utils.draw.FestivitiesRenderContext;

public class RenderBlockFireplace extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		this.renderFireplace(this.context, 0, 0, 0, 0);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		return false;
	}

	private static void renderFireplace(FestivitiesRenderContext render, int meta, int x, int y, int z)
	{
		Tessellator tess = Tessellator.instance;
		BoxDrawBasic draw = new BoxDrawBasic(render);
		draw.setTexture(Festivities.ID, "textures/tile/fireplace.png", 16, 32);
		tess.startDrawingQuads();
		
		draw.cube(1, 0, 1, 14, 5, 14);
		draw.selectUV(0, 8);
		draw.faceIn();
		draw.drawSidesSameTexture();
		
		tess.draw();
		tess.startDrawingQuads();
		
		draw.faceOut();
		
		draw.cube(1, 0, 1, 14, 1, 14);
		draw.selectUV(0, 13);
		draw.YDown();
		draw.YUp();
		
		tess.draw();
		tess.startDrawingQuads();
		
		draw.setTexture(Festivities.ID, "textures/tile/burnlog.png", 8, 14);
		
		int a = render.getTime() / 200;
		
		drawLog(draw, 5, 2, 4, 2, a);// (a + 1) % 4);
		drawLog(draw, 9, 2, 4, 2, a);// (a + 2) % 4);
		drawLog(draw, 4, 4, 7, 0, a);// (a + 0) % 4);
		
		tess.draw();
	}

	private static void drawLog(BoxDrawBasic draw, int x, int y, int z, int dir, int anim)
	{
		dir = dir % 3;
		int v = anim * 2;
		if (dir == 0)
		{
			draw.cube(x, y, z, 8, 2, 2);
			draw.selectUV(0, v);
			draw.YUp();
			draw.YDown();
			draw.ZUp();
			draw.ZDown();
			draw.selectUV(8, v);
			draw.XUp();
			draw.XDown();
		}
		if (dir == 1)
		{
			draw.cube(x, y, z, 2, 8, 2);
			draw.selectUV(0, v);
			draw.XUp();
			draw.XDown();
			draw.ZUp();
			draw.ZDown();
			draw.selectUV(8, v);
			draw.YUp();
			draw.YDown();
		}
		if (dir == 2)
		{
			draw.cube(x, y, z, 2, 2, 8);
			draw.selectUV(0, v);
			draw.setRotUVWorldMapping(true);
			draw.YUp();
			draw.YDown();
			draw.setRotUVWorldMapping(false);
			draw.XUp();
			draw.XDown();
			draw.selectUV(8, v);
			draw.ZUp();
			draw.ZDown();
		}
	}
}
