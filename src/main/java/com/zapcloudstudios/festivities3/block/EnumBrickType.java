package com.zapcloudstudios.festivities3.block;

public enum EnumBrickType
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
	
	public String getName()
	{
		return this.name;
	}
	
	public static EnumBrickType getStateFromMeta(int meta)
	{
		return EnumBrickType.values()[meta % EnumBrickType.values().length];
	}
	
	public String getUnlocalizedName()
	{
		return this.unlocal;
	}
	
	public static String[] getUnlocalizedNames()
	{
		String[] names = new String[EnumBrickType.values().length];
		for (int i = 0; i < names.length; i++)
		{
			names[i] = EnumBrickType.values()[i].getUnlocalizedName();
		}
		return names;
	}
}
