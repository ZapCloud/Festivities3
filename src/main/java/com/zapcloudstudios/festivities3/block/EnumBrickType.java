package com.zapcloudstudios.festivities3.block;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public enum EnumBrickType implements IStringSerializable
{
	COBBLE("cobble", "cobble"),
	BRICK("brick", "brick"),
	CRACKED("brick_cracked", "cracked"),
	CARVED("brick_carved", "carved");

	private final String name;
	private final String unlocal;

	EnumBrickType(String name, String unlocal)
	{
		this.name = name;
		this.unlocal = unlocal;
	}

	public int getMetaFromState()
	{
		return this.ordinal();
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public static PropertyEnum makeProperty()
	{
		return PropertyEnum.create("variant", EnumBrickType.class);
	}

	public static EnumBrickType getStateFromMeta(int meta)
	{
		return EnumBrickType.values()[meta % EnumBrickType.values().length];
	}

	public String getUnlocalizedName()
	{
		return this.unlocal;
	}
}
