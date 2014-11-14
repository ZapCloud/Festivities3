package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.block.BlockTreatPlate;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class RenderBlockPlate extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
		
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		Tessellator.instance.addTranslation(x, y, z);
		this.doWorldBrightness(world, x, y, z, block);
		this.drawPlate();
		Tessellator.instance.addTranslation(-x, -y, -z);
		return true;
	}
	
	public void drawPlate()
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		
		draw.cube(0, 0, 0, 16, 3, 16);
		
		draw.setTexture(BlockTreatPlate.topIcon);
		draw.selectUV(0, 0);
		draw.YUp();
		
		draw.setTexture(BlockTreatPlate.bottomIcon);
		draw.selectUV(0, 0);
		draw.YDown();
		
		draw.setTexture(BlockTreatPlate.sideIcon);
		draw.selectUV(0, 0);
		draw.XUp();
		draw.selectUV(0, 3);
		draw.ZUp();
		draw.selectUV(0, 6);
		draw.XDown();
		draw.selectUV(0, 9);
		draw.ZDown();
		
		draw.setTexture(BlockTreatPlate.insideIcon);
		draw.cube(1, 1, 1, 14, 2, 14);
		draw.faceIn();
		draw.selectUV(0, 0);
		draw.YDown();
		draw.selectUV(0, 14);
		draw.XUp();
		draw.selectUV(0, 14);
		draw.ZUp();
		draw.selectUV(0, 14);
		draw.XDown();
		draw.selectUV(0, 14);
		draw.ZDown();
	}
}
