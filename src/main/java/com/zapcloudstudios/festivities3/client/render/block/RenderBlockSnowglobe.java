package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.block.BlockSnowglobe;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class RenderBlockSnowglobe extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
		BoxDrawBasic draw = this.renderSnowglobe(0, 0, 0, 0);
		draw.setTexture(BlockSnowglobe.baseIcon);
		draw.YUp();
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		Tessellator.instance.addTranslation(x, y, z);
		this.doWorldBrightness(world, x, y, z, block);
		int meta = world.getBlockMetadata(x, y, z);
		this.renderSnowglobe(meta, x, y, z);
		Tessellator.instance.addTranslation(-x, -y, -z);
		return true;
	}
	
	private static BoxDrawBasic renderSnowglobe(int meta, int x, int y, int z)
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		
		draw.cube(1, 2, 1, 14, 13, 14);
		draw.setTexture(BlockSnowglobe.glassTopIcon);
		draw.YUp();
		draw.setTexture(BlockSnowglobe.glassSideIcon);
		draw.drawSidesSameTexture();
		
		draw.cube(0, 0, 0, 16, 3, 16);
		draw.selectUV(0, 0);
		draw.setTexture(BlockSnowglobe.bottomIcon);
		draw.YDown();
		draw.setTexture(BlockSnowglobe.topIcon);
		draw.YUp();
		
		draw.setTexture(BlockSnowglobe.sideIcon);
		draw.selectV(meta == 2 ? 0 : 3);
		draw.ZDown();
		draw.selectV(meta == 3 ? 0 : 3);
		draw.ZUp();
		draw.selectV(meta == 4 ? 0 : 3);
		draw.XDown();
		draw.selectV(meta == 5 ? 0 : 3);
		draw.XUp();
		
		return draw;
	}
}
