package com.zapcloudstudios.festivities3.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.festivities3.client.particle.EntitySnowFX;
import com.zapcloudstudios.festivities3.client.render.block.RenderBlockSnowglobe;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSnowglobe extends BlockFestiveContainer
{
	public static IIcon sideIcon;
	public static IIcon topIcon;
	public static IIcon bottomIcon;
	public static IIcon baseIcon;
	public static IIcon glassTopIcon;
	public static IIcon glassSideIcon;

	public BlockSnowglobe(Material par2Material)
	{
		super(par2Material);
		this.setBlockTextureName(Festivities.ID + ":snowglobe");
		this.setShouldRender3D(true);
		this.setRenderer(RenderBlockSnowglobe.class);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntitySnowglobe();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int s, int m)
	{
		return bottomIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		sideIcon = reg.registerIcon(this.getTextureName() + "_side");
		topIcon = reg.registerIcon(this.getTextureName() + "_top");
		bottomIcon = reg.registerIcon(this.getTextureName() + "_bottom");
		baseIcon = reg.registerIcon(this.getTextureName() + "_base");
		glassTopIcon = reg.registerIcon(this.getTextureName() + "_glass_top");
		glassSideIcon = reg.registerIcon(this.getTextureName() + "_glass_side");
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntitySnowglobe t = (TileEntitySnowglobe) world.getTileEntity(par2, par3, par4);
		ItemStack held = player.inventory.getCurrentItem();
		ItemStack pop = t.popSceneItem();

		if (held != null)
		{
			if (t.giveSceneItem(held))
			{
				if (!player.capabilities.isCreativeMode && --held.stackSize <= 0)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				}
			}
		}

		if (pop != null)
		{
			player.dropPlayerItemWithRandomChoice(pop, false);
		}

		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
	{
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item)
	{
		int l = determineOrientation(world, x, y, z, entity);
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

	/**
	 * A randomly called display update to be able to add particles or other
	 * items for display
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		for (int i = 0; i < 4; i++)
		{
			double X = x + (random.nextFloat() * 12 + 2) / 16.0F;
			double Z = z + (random.nextFloat() * 12 + 2) / 16.0F;
			double Y = y + (15 / 16.0F);
			EntitySnowFX.spawn(new EntitySnowFX(world, X, Y, Z));
		}
	}

	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Right-Click with certain items to change the interior", "Use magic candy to create the Kringle inside" };
	}

	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Shake it up!" };
	}
}
