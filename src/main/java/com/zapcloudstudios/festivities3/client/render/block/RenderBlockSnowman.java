package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.block.BlockSnowman;
import com.zapcloudstudios.utils.EnumDirection;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class RenderBlockSnowman extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 4)
		{
			renderer.setOverrideBlockTexture(Blocks.snow.getIcon(0, 0));
			int offsety = 0;
			for (int width = 10; width >= 6; width -= 2)
			{
				double widthd = width / 16.0D;
				double in = 0.5 - widthd / 2;
				double offsetyd = offsety / 16.0D;
				renderer.setRenderBounds(in, offsetyd, in, 1 - in, offsetyd + widthd, 1 - in);
				renderer.renderStandardBlock(block, x, y, z);
				offsety += width;
			}
			offsety -= 6;
			renderer.clearOverrideBlockTexture();
			
			BoxDrawBasic box = new BoxDrawBasic();
			box.setGridSnapExp(10);
			
			box.setTexture(BlockSnowman.partsIcon);
			
			box.setDomain(6, 6, 6);
			box.setOffsetMatrixPre(-0.5F, 0.0F, -0.5F);
			switch (meta)
			{
				case 0:
					box.setMatrix(new float[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } });
					break;
				case 1:
					box.setMatrix(new float[][] { { 0, 0, -1 }, { 0, 1, 0 }, { 1, 0, 0 } });
					break;
				case 2:
					box.setMatrix(new float[][] { { -1, 0, 0 }, { 0, 1, 0 }, { 0, 0, -1 } });
					break;
				case 3:
					box.setMatrix(new float[][] { { 0, 0, 1 }, { 0, 1, 0 }, { -1, 0, 0 } });
					break;
			}
			box.scaleMatrix(6 / 16.0F);
			box.setOffsetMatrixPost(0.5F + x, offsety / 16.0F + y, 0.5F + z);
			
			box.cube(-1, 6, -1, 8, 2, 8);
			box.selectUV(8, 8);
			box.YUp();
			box.YDown();
			box.selectUV(8, 6);
			box.drawSidesSameTexture();
			
			box.cube(0, 8, 0, 6, 4, 6);
			box.selectUV(9, 9);
			box.drawAllBut(EnumDirection.YDown);
			
			box.cube(4, 2, 8, 2, 2, 2);
			box.selectUV(2, 12);
			box.drawAll();
			
			box.cube(4.5F, 2, 6, 1, 1, 2);
			box.selectUV(0, 13);
			box.setRotUVWorldMapping(true);
			box.YUp();
			box.YDown();
			box.setRotUVWorldMapping(false);
			box.XUp();
			box.XDown();
			
			box.selectUV(0, 10);
			box.cube(0.5F, 3, 5.2F, 1, 1, 1);
			box.drawAllBut(EnumDirection.ZDown);
			box.cube(1.5F, 2, 5.2F, 1, 1, 1);
			box.drawAllBut(EnumDirection.ZDown);
			box.cube(3.5F, 2, 5.2F, 1, 1, 1);
			box.drawAllBut(EnumDirection.ZDown);
			box.cube(4.5F, 3, 5.2F, 1, 1, 1);
			box.drawAllBut(EnumDirection.ZDown);
			
			box.cube(0.5F, 5, 5.2F, 1, 1, 1);
			box.drawAllBut(EnumDirection.ZDown);
			box.cube(4.5F, 5, 5.2F, 1, 1, 1);
			box.drawAllBut(EnumDirection.ZDown);
			
			box.selectUV(0, 11);
			box.cube(2.5F, 3.5F, 6, 1, 1, 3);
			box.setRotUVWorldMapping(true);
			box.YUp();
			box.YDown();
			box.setRotUVWorldMapping(false);
			box.XUp();
			box.XDown();
			box.selectUV(2, 11);
			box.ZUp();
			
			box.setDomain(8, 8, 8);
			box.scaleMatrix(8.0F / 6.0F);
			offsety -= 8;
			box.setOffsetMatrixPost(0.5F + x, offsety / 16.0F + y, 0.5F + z);
			
			box.cube(-0.5F, 7, -0.5F, 9, 2, 9);
			box.selectUV(0, 0);
			box.faceIn();
			box.drawSidesSameTexture();
			box.faceOut();
			box.drawSidesSameTexture();
			
			box.cube(-0.5F, 2, 8.5F, 2, 5, 0);
			box.setRotUVWorldMapping(true);
			box.faceIn();
			box.ZUp();
			box.faceOut();
			box.ZUp();
			box.setRotUVWorldMapping(false);
			
			box.selectUV(0, 14);
			box.cube(3, 1, 7.4F, 2, 2, 1);
			box.drawAllBut(EnumDirection.ZDown);
			box.cube(3, 5, 7.4F, 2, 2, 1);
			box.drawAllBut(EnumDirection.ZDown);
			
			box.setDomain(10, 10, 10);
			box.scaleMatrix(10.0F / 8.0F);
			offsety -= 10;
			box.setOffsetMatrixPost(0.5F + x, offsety / 16.0F + y, 0.5F + z);
			
			box.cube(4, 7, 9.4F, 2, 2, 1);
			box.drawAllBut(EnumDirection.ZDown);
			box.cube(4, 3, 9.4F, 2, 2, 1);
			box.drawAllBut(EnumDirection.ZDown);
			
			return true;
		}
		return false;
	}
}
