package com.zapcloudstudios.festivities3.client.particle;

import com.zapcloudstudios.festivities3.Festivities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntitySnowFX extends EntityFX
{
	public float velmult = 0.98F;
	
	public EntitySnowFX(World world, double x, double y, double z, float velx, float vely, float velz)
	{
		this(world, x, y, z);
		this.motionX = velx;
		this.motionY = vely;
		this.motionZ = velz;
	}
	
	public EntitySnowFX setSize(float size)
	{
		this.setSize(size, size);
		return this;
	}
	
	public EntitySnowFX setGrav(float grav)
	{
		this.particleGravity = grav;
		return this;
	}
	
	public EntitySnowFX setMult(float velmult)
	{
		this.velmult = velmult;
		return this;
	}
	
	public EntitySnowFX(World world, double x, double y, double z)
	{
		super(world, x, y, z);
		this.motionX *= 0.3D;
		this.motionY = 0.0F;
		this.motionZ *= 0.3D;
		this.particleRed = 1.0F;
		this.particleGreen = 1.0F;
		this.particleBlue = 1.0F;
		this.setParticleTextureIndex(176 + this.rand.nextInt(8));
		this.setSize(0.01F, 0.01F);
		this.particleGravity = 0.001F;
		this.particleMaxAge = 180;
		this.noClip = true;
	}
	
	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= (double) this.particleGravity;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= this.velmult;
		this.motionY *= this.velmult;
		this.motionZ *= this.velmult;
		
		if (this.particleMaxAge-- <= 0)
		{
			this.setDead();
		}
		
		Block id = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
		
		if (id == Festivities.snowglobe || id == Festivities.snowMachine)
		{
			return;
		}
		
		Material material = id.getMaterial();
		
		if (material.isLiquid() || material.isSolid())
		{
			this.setDead();
		}
	}
	
	public static void spawn(World world, double x, double y, double z)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySnowFX(world, x, y, z));
	}
	
	public static void spawn(EntitySnowFX snow)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(snow);
	}
}
