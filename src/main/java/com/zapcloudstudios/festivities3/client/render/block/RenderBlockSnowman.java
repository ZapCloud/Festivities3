package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderBlockSnowman extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		if (world.getBlockMetadata(x, y, z) < 4)
		{
			renderer.setOverrideBlockTexture(Blocks.snow.getIcon(0, 0));
			double offsety = 0;
			for (int width = 11; width >= 7; width -= 2)
			{
				double widthd = width / 16.0D;
				double in = 0.5 - widthd / 2;
				renderer.setRenderBounds(in, offsety, in, 1 - in, offsety + widthd, 1 - in);
				renderer.renderStandardBlock(block, x, y, z);
				offsety += widthd;
			}
			renderer.clearOverrideBlockTexture();

			return true;
		}
		return false;
	}
}
