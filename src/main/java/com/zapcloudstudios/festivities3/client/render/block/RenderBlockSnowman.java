package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.block.BlockSnowman;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

import org.lwjgl.opengl.GL11;

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

			tessellator.draw();
			tessellator.startDrawingQuads();

			BoxDrawBasic box = new BoxDrawBasic();

			box.setTexture(BlockSnowman.partsIcon);

			box.setDomain(6, 6, 6);
			box.setScale(6 / 16.0F, 6 / 16.0F, 6 / 16.0F);
			box.setOffset(5F / 16.0F, offsety / 16.0F, 5F / 16.0F);

			box.cube(-1, 6, -1, 8, 2, 8);
			box.selectUV(8, 8);
			box.YUp();
			box.YDown();
			box.selectUV(8, 6);
			box.drawSidesSameTexture();

			box.cube(0, 8, 0, 6, 4, 6);
			box.selectUV(9, 9);
			box.drawAll();

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

			GL11.glPushMatrix();
			GL11.glTranslated(-0.5D, 0.0D, -0.5D);
			GL11.glRotatef(meta * 90, 0.0F, 1.0F, 0.0F);
			GL11.glTranslated(x + 0.5D, y, z + 0.5D);

			tessellator.draw();
			GL11.glPopMatrix();

			tessellator.startDrawingQuads();

			return true;
		}
		return false;
	}
}
