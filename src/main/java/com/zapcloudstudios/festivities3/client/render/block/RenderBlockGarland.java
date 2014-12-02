package com.zapcloudstudios.festivities3.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.utils.draw.BoxDrawBasic;

public class RenderBlockGarland extends RenderBlockFestivites
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
		int meta = world.getBlockMetadata(x, y, z);
		this.renderGarland(meta, x, y, z);
		Tessellator.instance.addTranslation(-x, -y, -z);
		return true;
	}

	public void renderGarland(int meta, int x, int y, int z)
	{
		BoxDrawBasic draw = new BoxDrawBasic();
		draw.setTexture(Festivities.garlandBlock.getIcon(0, 0));

		int style = meta >> 3;
		meta &= 7;

		int thick = 6;

		int xpos = 8;
		int ypos = 8;
		int zpos = 8;

		int dir = meta & 1;

		if ((meta & 4) == 0)
		{
			switch (meta & 3)
			{
				case 0:
					xpos = 16 - thick / 2;
					break;
				case 1:
					zpos = 16 - thick / 2;
					break;
				case 2:
					xpos = thick / 2;
					break;
				case 3:
					zpos = thick / 2;
					break;
			}
		}
		else
		{
			if ((meta & 2) == 0)
			{
				ypos = 16 - thick / 2;
			}
			else
			{
				ypos = thick / 2;
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
				this.renderGarlandCube(draw, boxx + offx[i], boxy + offy[i], boxz, 4, style);
				boxz += 4;
			}
			if (dir == 1)
			{
				this.renderGarlandCube(draw, boxx, boxy + offy[i], boxz + offz[i], 4, style);
				boxx += 4;
			}
		}
	}

	public void renderGarlandCube(BoxDrawBasic draw, int x, int y, int z, int thick, int style)
	{
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
}
