package eekysam.festivities.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import eekysam.festivities.Festivities;
import eekysam.festivities.block.BlockMintPlant;

public class ItemMintPlant extends ItemFestiveBlock
{
	public ItemMintPlant()
	{
		super(Festivities.mintPlant);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		BlockMintPlant block = (BlockMintPlant) Festivities.mintPlant;
		if (block.canPlaceBlockAt(world, x, y, z))
		{
			world.setBlock(x, y, z, block, 0, 3);
			world.setBlock(x, y + 1, z, block, 1, 3);

			--stack.stackSize;

			return true;
		}
		return false;
	}
}
