package com.zapcloudstudios.festivities3.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;

public abstract class EntityFestiveFX extends EntityFX
{
	private static final ResourceLocation particles = new ResourceLocation("textures/particle/particles.png");
	
	public abstract ResourceLocation getParticleTexture();
	
	public abstract int getParticleTextureHeight();
	
	public abstract int getParticleTextureWidth();
	
	protected EntityFestiveFX(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}
	
	public EntityFestiveFX(World world, double x, double y, double z, double motionx, double motiony, double motionz)
	{
		super(world, x, y, z, motionx, motiony, motionz);
	}
	
	@Override
	public void renderParticle(Tessellator tessellator, float partialtick, float xrot, float xzrot, float zrot, float yzrot, float xyrot)
	{
		
		tessellator.draw();
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.getParticleTexture());
		
		tessellator.startDrawingQuads();
		
		float lowu = (float) this.particleTextureIndexX / this.getParticleTextureWidth();
		float highu = lowu + 1.0F / this.getParticleTextureWidth();
		float lowv = (float) this.particleTextureIndexY / this.getParticleTextureHeight();
		float highv = lowv + 1.0F / this.getParticleTextureHeight();
		float scalefactor = 0.1F * this.particleScale;
		
		float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * partialtick - interpPosX);
		float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * partialtick - interpPosY);
		float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * partialtick - interpPosZ);
		tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
		tessellator.addVertexWithUV(x - xrot * scalefactor - yzrot * scalefactor, y - xzrot * scalefactor, z - zrot * scalefactor - xyrot * scalefactor, highu, highv);
		tessellator.addVertexWithUV(x - xrot * scalefactor + yzrot * scalefactor, y + xzrot * scalefactor, z - zrot * scalefactor + xyrot * scalefactor, highu, lowv);
		tessellator.addVertexWithUV(x + xrot * scalefactor + yzrot * scalefactor, y + xzrot * scalefactor, z + zrot * scalefactor + xyrot * scalefactor, lowu, lowv);
		tessellator.addVertexWithUV(x + xrot * scalefactor - yzrot * scalefactor, y - xzrot * scalefactor, z + zrot * scalefactor - xyrot * scalefactor, lowu, highv);
		
		tessellator.draw();
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(particles);
		
		tessellator.startDrawingQuads();
	}
	
	@Override
	public void setParticleTextureIndex(int index)
	{
		this.particleTextureIndexX = index % this.getParticleTextureWidth();
		this.particleTextureIndexY = index / this.getParticleTextureHeight();
	}
	
	@Override
	@Deprecated
	public void setParticleIcon(IIcon icon)
	{
		
	}
}
