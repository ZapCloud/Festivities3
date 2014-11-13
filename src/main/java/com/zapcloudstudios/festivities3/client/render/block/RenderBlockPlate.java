package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import com.zapcloudstudios.festivities3.block.BlockTreatPlate;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class RenderBlockPlate extends RenderBlockFestivites
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		this.drawPlate();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		this.drawPlate();
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
		draw.selectUV(0, 16);
		draw.YDown();
		draw.selectUV(14, 16);
		draw.XUp();
		draw.selectUV(14, 18);
		draw.ZUp();
		draw.selectUV(14, 20);
		draw.XDown();
		draw.selectUV(14, 22);
		draw.ZDown();
	}
}
