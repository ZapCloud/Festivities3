package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.block.BlockFireplace;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class RenderBlockFireplace extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
		this.renderFireplace(0, 0, 0);
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		this.renderFireplace(x, y, z);
		return true;
	}
	
	private static void renderFireplace(int x, int y, int z)
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		
		draw.setTexture(BlockFireplace.floor);
		
		draw.cube(1, 0, 1, 14, 1, 14);
		draw.selectUV(0, 13);
		draw.YDown();
		draw.YUp();
		
		draw.setTexture(BlockFireplace.detail);
		
		int a = (int) (Minecraft.getSystemTime() / 200);
		
		drawLog(draw, 5, 2, 4, 2, (a + 1) % 4);
		drawLog(draw, 9, 2, 4, 2, (a + 2) % 4);
		drawLog(draw, 4, 4, 7, 0, (a + 0) % 4);
		
		draw.cube(1, 0, 1, 14, 5, 14);
		draw.selectUV(0, 8);
		draw.faceIn();
		draw.drawSidesSameTexture();
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
