package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.block.BlockOrnament;
import com.zapcloudstudios.utils.EnumDirection;
import com.zapcloudstudios.utils.draw.SideDraw;
import com.zapcloudstudios.utils.draw.SideDrawBasic;

public class RenderBlockOrnament extends RenderBlockFestivites
{
	private static class RenderBlocksOrnamentDetail extends RenderBlocks
	{
		public RenderBlocksOrnamentDetail(IBlockAccess world)
		{
			super(world);
		}
		
		@Override
		public boolean renderStandardBlock(Block p_147784_1_, int p_147784_2_, int p_147784_3_, int p_147784_4_)
		{
			float f = 1.0F;
			float f1 = 1.0F;
			float f2 = 1.0F;
			
			if (EntityRenderer.anaglyphEnable)
			{
				float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
				float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
				float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
				f = f3;
				f1 = f4;
				f2 = f5;
			}
			
			return Minecraft.isAmbientOcclusionEnabled() && p_147784_1_.getLightValue() == 0 ? (this.partialRenderBounds ? this.renderStandardBlockWithAmbientOcclusionPartial(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2) : this.renderStandardBlockWithAmbientOcclusion(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2)) : this.renderStandardBlockWithColorMultiplier(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2);
		}
		
		@Override
		public IIcon getBlockIcon(Block block, IBlockAccess world, int x, int y, int z, int side)
		{
			BlockOrnament orn = (BlockOrnament) block;
			return this.getIconSafe(orn.getDetailIcon(world, x, y, z, side));
		}
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer)
	{
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		RenderBlocksOrnamentDetail detailRender = new RenderBlocksOrnamentDetail(world);
		detailRender.renderAllFaces = true;
		renderer.renderAllFaces = true;
		double min = 3 / 16.0F;
		double max = 13 / 16.0F;
		renderer.overrideBlockBounds(min, min, min, max, max, max);
		renderer.renderStandardBlock(block, x, y, z);
		detailRender.overrideBlockBounds(min, min, min, max, max, max);
		detailRender.setRenderFromInside(false);
		detailRender.renderStandardBlock(block, x, y, z);
		detailRender.setRenderFromInside(true);
		detailRender.renderStandardBlock(block, x, y, z);
		renderer.renderAllFaces = false;
		renderer.unlockBlockBounds();
		
		Tessellator.instance.addTranslation(x, y, z);
		this.doWorldBrightness(world, x, y, z, block);
		
		SideDraw draw = new SideDrawBasic();
		draw.setDoubleSided();
		draw.setTexture(BlockOrnament.hookIcon);
		draw.selectUV(0, 0);
		draw.side(EnumDirection.XUp, 6, 6, 8, 13, 5);
		draw.draw();
		draw.selectUV(6, 3);
		draw.side(EnumDirection.ZUp, 6, 3, 5, 13, 8);
		draw.draw();
		
		Tessellator.instance.addTranslation(-x, -y, -z);
		
		return true;
	}
}
