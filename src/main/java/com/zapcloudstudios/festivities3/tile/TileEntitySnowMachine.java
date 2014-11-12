package com.zapcloudstudios.festivities3.tile;

import java.util.Random;

import com.zapcloudstudios.festivities3.client.particle.EntitySnowFX;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySnowMachine extends TileEntityFestive
{
	private boolean powered;
	
	public int snowCount;
	
	private long tickCount;
	private float snowDensity;
	
	public int snowConsumption = 30;
	public static final int iceSnow = 6;
	public static final int ballSnow = 1;
	public static final int blockSnow = 4;
	
	private static final int snowDistance = 16;
	private static final float jetangle = 0.15F;
	private static final float jetvel = 0.2F;
	
	private boolean isnew = true;
	
	private float jetx;
	private float jetz;
	private float jetrad;
	private float jetrads;
	private float jetang;
	
	public TileEntitySnowMachine()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void readFromNBT(NBTTagCompound myTag)
	{
		super.readFromNBT(myTag);
		this.snowCount = myTag.getInteger("iceCount");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound myTag)
	{
		super.writeToNBT(myTag);
		myTag.setInteger("iceCount", this.snowCount);
	}
	
	public void onClick(EntityPlayer player, World world)
	{
		if (!world.isRemote)
		{
			ItemStack myStack = player.inventory.getCurrentItem();
			if (myStack != null)
			{
				if (this.snowCount == 0)
				{
					this.tickCount = 0;
				}

				boolean flag = false;

				if (myStack.getItem() == Item.getItemFromBlock(Blocks.ice))
				{
					this.snowCount += myStack.stackSize * iceSnow;
					flag = true;
				}
				else if (myStack.getItem() == Items.snowball)
				{
					this.snowCount += myStack.stackSize * ballSnow;
					flag = true;
				}
				else if (myStack.getItem() == Item.getItemFromBlock(Blocks.snow))
				{
					this.snowCount += myStack.stackSize * blockSnow;
					flag = true;
				}

				if (flag && !player.capabilities.isCreativeMode)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				}
			}
			this.markDirty();
		}
	}
	
	public boolean isPowered()
	{
		return this.powered;
	}
	
	@Override
	public void updateEntity()
	{
		World myWorld = this.worldObj;
		
		this.snowDensity = 0;
		
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
		{
			this.powered = true;
			if (this.snowCount > 0 && this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
			{
				if (this.isnew)
				{
					this.isnew = false;
					this.jetang = myWorld.rand.nextFloat();
				}
				
				this.snowDensity = (float) ++this.tickCount / (12 * this.snowConsumption);
				
				this.jetx = MathHelper.sin(this.jetang * 6.28F) * this.jetrads;
				this.jetz = MathHelper.cos(this.jetang * 6.28F) * this.jetrads;
				
				this.jetrad += myWorld.rand.nextFloat() * 0.02F - 0.01F;
				
				this.jetang += ((myWorld.rand.nextFloat() * 0.004F + this.snowDensity * 0.0002F) - 0.0002F);
				
				if (this.jetrad < 0)
				{
					this.jetrad = 0;
				}
				
				if (this.jetrad > 1)
				{
					this.jetrad = 1;
				}
				
				this.jetrads = MathHelper.sqrt_float(this.jetrad);
				
				this.jetrads *= 0.65F;
				this.jetrads += 0.35F;
				
				if (this.snowDensity > 1)
				{
					this.snowDensity = 1;
				}
				
				if (!this.worldObj.isRemote)
				{
					float sp = this.snowDensity / 4;
					
					if (sp > 0.1F)
					{
						sp = 0.1F;
					}
					if (myWorld.rand.nextFloat() < sp)
					{
						this.letItSnow();
					}
					if (this.tickCount % this.snowConsumption == 0)
					{
						this.snowCount--;
					}
				}
			}
		}
		else
		{
			this.powered = false;
			this.tickCount = 0;
		}
	}
	
	public float getSnowDensity()
	{
		return this.snowDensity;
	}
	
	private void letItSnow()
	{
		int x;
		int y;
		int z;
		Random randy = this.worldObj.rand;
		for (int i = 0; i < 20; i++)
		{
			x = (int) ((randy.nextFloat() + randy.nextFloat() - 1) * snowDistance) + this.xCoord;
			z = (int) ((randy.nextFloat() + randy.nextFloat() - 1) * snowDistance) + this.zCoord;
			y = this.getFirstUncoveredBlockHeight(x, z);
			if (y >= 0)
			{
				Block id = this.worldObj.getBlock(x, y, z);
				if (id == Blocks.snow_layer && randy.nextFloat() < 0.1F)
				{
					int meta = this.worldObj.getBlockMetadata(x, y, z);
					if (meta < 7)
					{
						float m = meta / 8.0F;
						m = MathHelper.sqrt_float(m);
						if (randy.nextFloat() > m)
						{
							if (meta + 1 == 7)
							{
								this.worldObj.setBlock(x, y, z, Blocks.snow_layer);
							}
							else
							{
								this.worldObj.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
							}
							break;
						}
					}
				}
				else
				{
					y++;
					if (Blocks.snow_layer.canPlaceBlockAt(this.worldObj, x, y, z))
					{
						this.worldObj.setBlock(x, y, z, Blocks.snow_layer);
						break;
					}
				}
			}
		}
	}
	
	public int getFirstUncoveredBlockHeight(int x, int z)
	{
		int y = 128;
		while (this.worldObj.isAirBlock(x, y, z))
		{
			y--;
			if (y < 0)
			{
				return -1;
			}
		}
		return y;
	}
	
	@SideOnly(Side.CLIENT)
	public void spawnFX(Random rand)
	{
		float den = this.getSnowDensity();
		int runs = 0;
		if (den != 0.0F)
		{
			runs = (int) den * 8 + 2;
		}
		for (int i = 0; i < runs; i++)
		{
			double X = this.xCoord + rand.nextFloat() * (8 / 16.0F) + (4 / 16.0F);
			double Z = this.zCoord + rand.nextFloat() * (8 / 16.0F) + (4 / 16.0F);
			double Y = this.yCoord + 0.6F + rand.nextFloat() * 0.4F;
			float xvel = this.jetx * jetangle;
			float zvel = this.jetz * jetangle;
			float yvel = jetvel * (rand.nextFloat() * 0.1F + 0.95F);
			EntitySnowFX.spawn(new EntitySnowFX(this.worldObj, X, Y, Z, xvel, yvel, zvel).setSize(0.01F).setMult(0.985F).setGrav(0.002F));
		}
	}
}
