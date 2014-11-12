package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCandyLog extends BlockFestive
{
	@SideOnly(Side.CLIENT)
	private IIcon dlSide;
	@SideOnly(Side.CLIENT)
	private IIcon urSide;
	@SideOnly(Side.CLIENT)
	private IIcon drSide;
	@SideOnly(Side.CLIENT)
	private IIcon ulSide;
	@SideOnly(Side.CLIENT)
	private IIcon rEnd;
	@SideOnly(Side.CLIENT)
	private IIcon lEnd;

	public BlockCandyLog()
	{
		super(Material.wood);
	}

	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int l, int d)
	{
		if (d == -2)
		{
			return this.urSide;
		}
		if (d == -1)
		{
			return this.drSide;
		}
		if (d == 1)
		{
			return this.dlSide;
		}
		if (d == 2)
		{
			return this.ulSide;
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	protected IIcon getEndIcon(int l, int d)
	{
		if (d == -1)
		{
			return this.rEnd;
		}
		if (d == 1)
		{
			return this.lEnd;
		}
		return null;
	}

	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
	 * side, hitX, hitY, hitZ, block metadata
	 */
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		int j1 = par9 & 3;
		byte b0 = 0;

		switch (par5)
		{
			case 0:
			case 1:
				b0 = 0;
				break;
			case 2:
			case 3:
				b0 = 8;
				break;
			case 4:
			case 5:
				b0 = 4;
		}

		return j1 | b0;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int s, int m)
	{
		int k = m & 12;
		int l = m & 3;

		if (k == 0)
		{
			if (s == 0 || s == 1)
			{
				return this.getEndIcon(l, 1);
			}
			return this.getSideIcon(l, 1);
		}
		if (k == 4)
		{
			if (s == 4)
			{
				return this.getEndIcon(l, -1);
			}
			if (s == 5)
			{
				return this.getEndIcon(l, 1);
			}
			if (s == 0)
			{
				return this.getSideIcon(l, -2);
			}
			if (s == 1)
			{
				return this.getSideIcon(l, 1);
			}
			if (s == 2)
			{
				return this.getSideIcon(l, 2);
			}
			if (s == 3)
			{
				return this.getSideIcon(l, 1);
			}
		}
		if (k == 8)
		{
			if (s == 2)
			{
				return this.getEndIcon(l, 1);
			}
			if (s == 3)
			{
				return this.getEndIcon(l, -1);
			}
			if (s == 0)
			{
				return this.getSideIcon(l, 2);
			}
			if (s == 1)
			{
				return this.getSideIcon(l, -2);
			}
			if (s == 4)
			{
				return this.getSideIcon(l, 2);
			}
			if (s == 5)
			{
				return this.getSideIcon(l, 1);
			}
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.dlSide = reg.registerIcon(this.getTextureName() + "_DLside");
		this.ulSide = reg.registerIcon(this.getTextureName() + "_ULside");
		this.drSide = reg.registerIcon(this.getTextureName() + "_DRside");
		this.urSide = reg.registerIcon(this.getTextureName() + "_URside");
		this.rEnd = reg.registerIcon(this.getTextureName() + "_Rend");
		this.lEnd = reg.registerIcon(this.getTextureName() + "_Lend");
	}

	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Strong and delicious!" };
	}

	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return null;
	}
}
