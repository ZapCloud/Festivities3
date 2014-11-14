package com.zapcloudstudios.festivities3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.lwjgl.input.Keyboard;

import com.zapcloudstudios.festivities3.block.BlockCandyLog;
import com.zapcloudstudios.festivities3.block.BlockFestive;
import com.zapcloudstudios.festivities3.block.BlockFireplace;
import com.zapcloudstudios.festivities3.block.BlockGarland;
import com.zapcloudstudios.festivities3.block.BlockOrnament;
import com.zapcloudstudios.festivities3.block.BlockSnowGlobe;
import com.zapcloudstudios.festivities3.block.BlockSnowMachine;
import com.zapcloudstudios.festivities3.block.BlockTreatPlate;
import com.zapcloudstudios.festivities3.command.CommandHome;
import com.zapcloudstudios.festivities3.command.CommandKringle;
import com.zapcloudstudios.festivities3.debugutils.PerlinTest;
import com.zapcloudstudios.festivities3.events.EventHooks;
import com.zapcloudstudios.festivities3.item.ItemFestive;
import com.zapcloudstudios.festivities3.item.ItemFestiveBlock;
import com.zapcloudstudios.festivities3.item.ItemFoodFestive;
import com.zapcloudstudios.festivities3.item.ItemGarland;
import com.zapcloudstudios.festivities3.item.ItemMoreCookies;
import com.zapcloudstudios.festivities3.item.ItemOrnament;
import com.zapcloudstudios.festivities3.kringle.WorldProviderKringle;
import com.zapcloudstudios.festivities3.kringle.biome.BiomeGenKringle;
import com.zapcloudstudios.festivities3.player.PlayerData;
import com.zapcloudstudios.festivities3.tile.TileEntityGarland;
import com.zapcloudstudios.festivities3.tile.TileEntityPlate;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowMachine;
import com.zapcloudstudios.festivities3.tile.TileEntitySnowglobe;
import com.zapcloudstudios.utils.FestiveUtils;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Festivities.ID, name = Festivities.NAME, version = "3." + Festivities.MAJOR + "." + Festivities.MINOR + "." + Festivities.BUILD)
public class Festivities
{
	public static final String ID = "festivities3";
	public static final String NAME = "Christmas Festivities Mod 2";
	public static final String CHANNEL = "festivities3";
	
	public static final String CHATNAME = "Festivities3";
	
	public static final String PLAYERDATA = "festivities3";
	
	public static final int MAJOR = 0;
	public static final int MINOR = 0;
	public static final int BUILD = 0;
	
	public static final boolean DEBUG = false;
	
	public static final boolean TESTVERSION = false;
	public static final String[] TESTMSG = new String[] { "Christmas Festivities Mod 3", "Version " + "3." + Festivities.MAJOR + "." + Festivities.MINOR + "." + Festivities.BUILD + " is a TEST version!", "You will experience bugs and unfinished features.", "Download a proper release when possible." };
	public static final String[] TESTMSGDATED = new String[] { "This a TEST version of the Christmas Festivities Mod 2!", "You will experience bugs and unfinished features.", "Download a proper release when possible." };
	public static final String[] MSG = new String[] { "Christmas Festivities Mod 3", "Version " + "3." + Festivities.MAJOR + "." + Festivities.MINOR + "." + Festivities.BUILD, "", "Try with \"Not Enough Items\"", "", "Use \"/santa\" to exchange the item you are holding with someone else across the world!" };
	public static final String[] MSGDATED = new String[] {};
	
	public static final int kringleId = 3;
	
	public static final int santacooldowntime = 100;
	
	private int itemId = 7600;
	private int blockId = 2400;
	private int entityId = 0;
	private int globealEntityId = 360;
	
	public Configuration config;
	
	private boolean advancedBlockConfig = false;
	private boolean advancedItemConfig = false;
	
	private List<String> usedversions = new ArrayList<String>();
	
	protected static HashMap<Integer, Integer> oldidsmap = new HashMap<Integer, Integer>();
	protected static HashMap<Integer, Integer> newidsmap = new HashMap<Integer, Integer>();
	
	@Instance("Festivities")
	public static Festivities instance;
	
	public static Item magicCandy;
	public static Item candyCane;
	public static Item moreCookies;
	public static Item berries;
	public static Item holly;
	public static Item bluePie;
	public static Item figgy;
	public static Item coloredOrnament;
	public static Item clearOrnament;
	public static Item flake;
	public static Item peppermintStick;
	public static Item garland;
	public static Item ginger;
	public static Item mintOil;
	public static Item mintLeaf;
	// public static Item WeWishYouAMerryChristmas;
	
	public static Block candyLog;
	public static Block snowglobe;
	public static Block treatplate;
	public static Block coloredOrnamentBlock;
	public static Block clearOrnamentBlock;
	public static Block fireplace;
	public static Block iceBrick;// icebrick
	public static Block iceBrickCarved;// icebrick_carved
	public static Block iceBrickCracked;// icebrick_cracked
	public static Block cobbleIce;// cobbleice
	public static Block snowMachine;
	public static Block candyPlanks;
	public static Block garlandBlock;
	public static Block gingerbreadBlock;
	public static Block greenPresent;
	public static Block redPresent;
	public static Block mintPlant;
	
	public final static int block3dItemRenderId = RenderingRegistry.getNextAvailableRenderId();
	public final static int block2dItemRenderId = RenderingRegistry.getNextAvailableRenderId();
	
	public static FestivitiesTab foodTab = new FestivitiesTab(CreativeTabs.getNextID(), "Festive Foods");
	public static FestivitiesTab decorTab = new FestivitiesTab(CreativeTabs.getNextID(), "Festive Decorations");
	public static FestivitiesTab blockTab = new FestivitiesTab(CreativeTabs.getNextID(), "Festive Blocks");
	public static FestivitiesTab matTab = new FestivitiesTab(CreativeTabs.getNextID(), "Festive Materials");
	public static FestivitiesTab miscTab = new FestivitiesTab(CreativeTabs.getNextID(), "Festive Misc");
	
	public static Class blockItem = ItemFestiveBlock.class;
	
	public static final String shiftInfo = "\u00A7" + "o" + "Hold Shift For More...";
	
	@SidedProxy(modId = Festivities.ID, clientSide = "com.zapcloudstudios.festivities3.client.ClientProxy", serverSide = "com.zapcloudstudios.festivities3.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		
		this.config = new Configuration(event.getSuggestedConfigurationFile());
		this.config.load();
		
		ConfigCategory versions = this.config.getCategory("usedverions");
		String ver = "3." + Festivities.MAJOR + "." + Festivities.MINOR + "." + Festivities.BUILD;
		Iterator<String> verit = versions.keySet().iterator();
		while (verit.hasNext())
		{
			String key = verit.next();
			if (versions.get(key).getBoolean(true))
			{
				this.usedversions.add(key);
			}
		}
		versions.put(ver, new Property(ver, "true", Property.Type.BOOLEAN));
		versions.setComment("For tracking versions used in the past. Do not change.");
		
		this.advancedBlockConfig = this.config.get(Configuration.CATEGORY_GENERAL, "useSpecificBlockIDs", this.advancedBlockConfig, "If this is false the mod will use ids incrementing up from defaultBlockIdStart. If this is true, the mod will use the specified id. If no id is specified, the mod will use the id the block would have had if defaultBlockIdStart was false. Changing this to true and restarting is required to generate the block id configs.").getBoolean(this.advancedBlockConfig);
		this.advancedItemConfig = this.config.get(Configuration.CATEGORY_GENERAL, "useSpecificItemIDs", this.advancedItemConfig, "If this is false the mod will use ids incrementing up from defaultItemIdStart. If this is true, the mod will use the specified id. If no id is specified, the mod will use the id the item would have had if defaultItemIdStart was false. Changing this to true and restarting is required to generate the item id configs.").getBoolean(this.advancedItemConfig);
		
		this.blockId = this.config.get(Configuration.CATEGORY_GENERAL, "defaultBlockIdStart", this.blockId).getInt();
		this.itemId = this.config.get(Configuration.CATEGORY_GENERAL, "defaultItemIdStart", this.itemId).getInt();
		
		this.globealEntityId = this.config.get(Configuration.CATEGORY_GENERAL, "defaultEntityIdStart", this.globealEntityId).getInt();
		
		magicCandy = new ItemFestive().setTip("You probibly shouldn't eat this...").setShiftTip("All purpose test item").setUnlocalizedName("magicCandy").setTextureName(Festivities.ID + ":magicCandy").setCreativeTab(Festivities.miscTab);
		this.registerItem(magicCandy, "magicCandy");
		
		candyCane = new ItemFoodFestive(2, 0.1F, false).setTip("The meaning of Christmas...").setUnlocalizedName("candyCane").setTextureName(Festivities.ID + ":candyCane").setCreativeTab(Festivities.foodTab);
		this.registerItem(candyCane, "candyCane");
		
		candyLog = new BlockCandyLog().setCreativeTab(Festivities.blockTab).setBlockName("candyLog").setBlockTextureName(Festivities.ID + ":candyLog");
		this.registerBlock(candyLog, "candyLog");
		
		snowglobe = new BlockSnowGlobe(Material.glass).setCreativeTab(Festivities.decorTab).setBlockName("snowglobe");
		this.registerBlock(snowglobe, "snowglobe");
		GameRegistry.registerTileEntity(TileEntitySnowglobe.class, "snowglobe");
		
		treatplate = new BlockTreatPlate(Material.glass).setBlockItemTextureName(Festivities.ID + ":treatplate").setCreativeTab(Festivities.decorTab).setBlockName("treatplate");
		this.registerBlock(treatplate, "treatplate");
		GameRegistry.registerTileEntity(TileEntityPlate.class, "treatplate");
		
		moreCookies = new ItemMoreCookies(2, 0.1F).setTip("Everyone likes cookies!").setShiftTip("Can be stacked on a treat plate", "Stacks on plate up to 20 times").setUnlocalizedName("morecookies").setCreativeTab(Festivities.foodTab);
		this.registerItem(moreCookies, "morecookies");
		
		figgy = new ItemFoodFestive(4, 0.6F, false).setTip("Never tried it").setShiftTip("Can be displayed on a treat plate", "Stacks on plate twice").setUnlocalizedName("figgy").setTextureName(Festivities.ID + ":figgy").setCreativeTab(Festivities.foodTab);
		this.registerItem(figgy, "figgy");
		
		holly = new ItemFestive().setTip("Pretty...but spiky too").setShiftTip("Dropped by grass blocks, tall grass, and other plants").setUnlocalizedName("holly").setTextureName(Festivities.ID + ":holly").setCreativeTab(Festivities.matTab);
		this.registerItem(holly, "holly");
		
		berries = new ItemFestive().setTip("Not this season").setShiftTip("Dropped by grass blocks, tall grass, and other plants").setUnlocalizedName("berries").setTextureName(Festivities.ID + ":berries").setCreativeTab(Festivities.matTab);
		this.registerItem(berries, "berries");
		
		bluePie = new ItemFoodFestive(8, 0.3F, false).setTip("Mmmm, sweet").setShiftTip("Can be displayed on a treat plate", "Pumpkin pies also work").setUnlocalizedName("bluPie").setTextureName(Festivities.ID + ":blu_pie").setCreativeTab(Festivities.foodTab);
		this.registerItem(bluePie, "bluPie");
		
		clearOrnamentBlock = new BlockOrnament(true).setBlockName("clearOrnamentBlock").setCreativeTab(Festivities.decorTab);
		this.registerBlockWithoutItem(clearOrnamentBlock, "clearOrnament");
		
		coloredOrnamentBlock = new BlockOrnament(false).setBlockName("coloredOrnamentBlock").setCreativeTab(Festivities.decorTab);
		this.registerBlockWithoutItem(coloredOrnamentBlock, "coloredOrnament");
		
		clearOrnament = new ItemOrnament(clearOrnamentBlock, true).setTip("A glass decoration for your tree!").setShiftTip("Right-Click to place", "Needs a block to sit or hang on").setUnlocalizedName("ornament").setCreativeTab(Festivities.decorTab);
		this.registerItem(clearOrnament, "clearOrnament");
		
		coloredOrnament = new ItemOrnament(coloredOrnamentBlock, false).setTip("A colorful decoration for your tree!").setShiftTip("Right-Click to place", "Needs a block to sit or hang on").setUnlocalizedName("ornament").setCreativeTab(Festivities.decorTab);
		this.registerItem(coloredOrnament, "coloredOrnament");
		
		fireplace = new BlockFireplace(Material.rock).setBlockName("fireplace").setLightLevel(1.0F).setCreativeTab(Festivities.decorTab);
		this.registerBlock(fireplace, "fireplace");
		
		iceBrick = new BlockFestive(Material.rock).setTip("Doesn't shatter!").setBlockName("iceBrick").setBlockTextureName(Festivities.ID + ":icebrick").setCreativeTab(Festivities.blockTab);
		this.registerBlock(iceBrick, "iceBrick");
		
		iceBrickCarved = new BlockFestive(Material.rock).setTip("Oooh, pretty...").setBlockName("iceBrickCarved").setBlockTextureName(Festivities.ID + ":icebrick_carved").setCreativeTab(Festivities.blockTab);
		this.registerBlock(iceBrickCarved, "iceBrickCarved");
		
		iceBrickCracked = new BlockFestive(Material.rock).setTip("Maybe it does shatter...").setBlockName("iceBrickCracked").setBlockTextureName(Festivities.ID + ":icebrick_cracked").setCreativeTab(Festivities.blockTab);
		this.registerBlock(iceBrickCracked, "iceBrickCracked");
		
		cobbleIce = new BlockFestive(Material.rock).setTip("Not as slippery").setBlockName("cobbleIce").setBlockTextureName(Festivities.ID + ":cobbleice").setCreativeTab(Festivities.blockTab);
		this.registerBlock(cobbleIce, "cobbleIce");
		
		snowMachine = new BlockSnowMachine(Material.rock).setBlockItemTextureName(Festivities.ID + ":snowMachine").setBlockName("snowMachine").setCreativeTab(Festivities.decorTab);
		this.registerBlock(snowMachine, "snowMachine");
		GameRegistry.registerTileEntity(TileEntitySnowMachine.class, "snowMachine");
		
		flake = new ItemFestive().setTip("Catch one on your tongue!").setUnlocalizedName("flake").setTextureName(Festivities.ID + ":flake").setCreativeTab(Festivities.matTab);
		this.registerItem(flake, "flake");
		
		peppermintStick = new ItemFoodFestive(1, 0.1F, false).setTip("Not as bendy").setUnlocalizedName("peppermintStick").setTextureName(Festivities.ID + ":peppermintStick").setCreativeTab(Festivities.foodTab);
		this.registerItem(peppermintStick, "peppermintStick");
		
		candyPlanks = new BlockFestive(Material.wood).setTip("Sugary boards").setBlockName("candyPlanks").setBlockTextureName(Festivities.ID + ":candyPlanks").setCreativeTab(Festivities.blockTab);
		this.registerBlock(candyPlanks, "candyPlanks");
		
		garlandBlock = new BlockGarland(Material.circuits).setBlockName("garland").setBlockTextureName(Festivities.ID + ":garland");
		this.registerBlockWithoutItem(garlandBlock, "garland");
		GameRegistry.registerTileEntity(TileEntityGarland.class, "garland");
		
		garland = new ItemGarland(garlandBlock).setTip("Hang it high!").setShiftTip("Right-Click to place").setUnlocalizedName("garland").setCreativeTab(Festivities.decorTab);
		this.registerItem(garland, "garland");
		
		gingerbreadBlock = new BlockFestive(Material.wood).setTip("Perfect for a house!").setBlockName("gingerbreadBlock").setBlockTextureName(Festivities.ID + ":gingerbreadBlock").setCreativeTab(Festivities.blockTab);
		this.registerBlock(gingerbreadBlock, "gingerbreadBlock");
		
		ginger = new ItemFestive().setTip("Don't eat it raw!").setUnlocalizedName("ginger").setTextureName(Festivities.ID + ":ginger").setCreativeTab(Festivities.matTab);
		this.registerItem(ginger, "ginger");
		
		// mintLeaf = new
		// ItemMintPlant().setTip("Green and Minty!").setShiftTip("Right-Click to place").setUnlocalizedName("mintLeaf").setTextureName(Festivities.ID
		// + ":mintLeaf").setCreativeTab(Festivities.miscTab);
		// this.registerItem(mintLeaf, "mintLeaf");
		
		// mintPlant = new
		// BlockMintPlant().setBlockName("mintPlant").setBlockTextureName(Festivities.ID
		// + ":mintPlant");
		// this.registerBlock(mintPlant, "mintPlant");
		
		this.foodTab.setIcon(candyCane);
		this.decorTab.setIcon(coloredOrnament);
		this.blockTab.setIcon(Item.getItemFromBlock(iceBrick));
		this.matTab.setIcon(holly);
		this.miscTab.setIcon(magicCandy);
		
		MinecraftForge.EVENT_BUS.register(new EventHooks());
		
		this.config.save();
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		TextureManager manage = Minecraft.getMinecraft().renderEngine;
		try
		{
			ITextureObject blocks = manage.getTexture(TextureMap.locationBlocksTexture);
			ITextureObject items = manage.getTexture(TextureMap.locationItemsTexture);
			
			ImageIO.write(FestiveUtils.getImageFromGLTexture(blocks.getGlTextureId()), "png", new File("blockMap.png"));
			ImageIO.write(FestiveUtils.getImageFromGLTexture(items.getGlTextureId()), "png", new File("itemMap.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void registerBlock(Block block, String name)
	{
		GameRegistry.registerBlock(block, this.blockItem, name);
	}
	
	protected void registerBlockWithoutItem(Block block, String name)
	{
		GameRegistry.registerBlock(block, null, name);
	}
	
	protected void registerItem(Item item, String name)
	{
		GameRegistry.registerItem(item, name);
	}
	
	protected int nextItemID()
	{
		return ++this.itemId;
	}
	
	protected int nextBlockID()
	{
		return ++this.blockId;
	}
	
	protected int nextEntityID()
	{
		return this.entityId++;
	}
	
	protected int nextGlobalEntityId()
	{
		do
		{
			this.globealEntityId++;
		}
		while (EntityList.getStringFromID(this.globealEntityId) != null);
		
		return this.globealEntityId;
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		if (this.DEBUG)
		{
			PerlinTest perlinTest = new PerlinTest(System.currentTimeMillis(), 8, 0.5F);
			perlinTest.makeWorld();
			perlinTest.saveImg("test.png", 750, 750);
		}
		
		BiomeGenKringle.registerBiomes(130);
		
		this.proxy.registerRenderers();
		
		GameRegistry.addShapelessRecipe(new ItemStack(this.figgy, 1), new Object[] { this.holly, this.berries, this.berries, Items.sugar });
		GameRegistry.addRecipe(new ItemStack(this.moreCookies, 8, 0), new Object[] { "#X#", 'X', Items.sugar, '#', Items.wheat });
		GameRegistry.addRecipe(new ItemStack(this.moreCookies, 8, 1), new Object[] { "X#X", 'X', new ItemStack(Items.dye, 1, 3), '#', Items.wheat });
		for (int i = 0; i < 16; i++)
		{
			if (i != 0 && i != 3 && i != 15)
			{
				GameRegistry.addRecipe(new ItemStack(this.moreCookies, 8, 2), new Object[] { " S ", "#X#", 'X', Items.sugar, '#', Items.wheat, 'S', new ItemStack(Items.dye, 1, i) });
				GameRegistry.addRecipe(new ItemStack(this.moreCookies, 8, 2), new Object[] { "###", "#X#", "###", 'X', new ItemStack(Items.dye, 1, i), '#', new ItemStack(this.moreCookies, 1, 0) });
			}
		}
		GameRegistry.addRecipe(new ItemStack(this.moreCookies, 8, 3), new Object[] { "X#X", 'X', this.candyCane, '#', Items.wheat });
		GameRegistry.addShapelessRecipe(new ItemStack(this.bluePie), new Object[] { this.berries, Items.sugar, Items.egg });
		
		GameRegistry.addRecipe(new ItemStack(this.treatplate, 2), new Object[] { "CCC", 'C', Items.brick });
		GameRegistry.addRecipe(new ItemStack(this.treatplate, 2), new Object[] { "CCC", 'C', Blocks.glass });
		GameRegistry.addRecipe(new ItemStack(this.treatplate, 2), new Object[] { "CCC", 'C', Blocks.stone });
		
		GameRegistry.addRecipe(new ItemStack(this.clearOrnament, 6), new Object[] { " N ", "G G", " G ", 'N', Items.gold_nugget, 'G', Blocks.glass });
		
		for (int i = 0; i < 16; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(this.coloredOrnament, 1, i), new Object[] { new ItemStack(Items.dye, 1, i), this.clearOrnament });
		}
		
		GameRegistry.addRecipe(new ItemStack(this.snowMachine, 2), new Object[] { "I I", " P ", "SDS", 'I', Items.iron_ingot, 'P', Blocks.piston, 'S', new ItemStack(Blocks.stone_slab, 1, 0), 'D', Blocks.dropper });
		
		GameRegistry.addRecipe(new ItemStack(this.fireplace, 1), new Object[] { "   ", "I I", "BWB", 'I', Blocks.iron_bars, 'B', Blocks.stonebrick, 'W', Blocks.coal_block });
		
		GameRegistry.addRecipe(new ItemStack(this.flake, 4), new Object[] { " S ", "S S", " S ", 'S', Items.snowball });
		GameRegistry.addRecipe(new ItemStack(this.snowglobe), new Object[] { "GGG", "GSG", "WIW", 'S', this.flake, 'G', Blocks.glass, 'I', Items.gold_ingot, 'W', Blocks.log });
		GameRegistry.addRecipe(new ItemStack(this.snowglobe), new Object[] { "GGG", "GSG", "WIW", 'S', this.flake, 'G', Blocks.glass, 'I', Items.gold_ingot, 'W', Blocks.log2 });
		
		GameRegistry.addRecipe(new ItemStack(this.iceBrick), new Object[] { "##", "##", '#', Blocks.ice });
		GameRegistry.addRecipe(new ItemStack(this.iceBrickCarved), new Object[] { "##", "##", '#', this.iceBrick });
		GameRegistry.addRecipe(new ItemStack(this.iceBrickCracked), new Object[] { "##", "##", '#', this.cobbleIce });
		GameRegistry.addRecipe(new ItemStack(this.cobbleIce), new Object[] { "#", '#', Blocks.ice });
		
		GameRegistry.addRecipe(new ItemStack(this.candyCane, 4), new Object[] { "#", '#', this.candyLog });
		GameRegistry.addRecipe(new ItemStack(this.candyLog), new Object[] { "##", "##", '#', this.candyCane });
		
		GameRegistry.addRecipe(new ItemStack(this.candyCane), new Object[] { "#", "#", '#', this.peppermintStick });
		GameRegistry.addRecipe(new ItemStack(this.peppermintStick, 2), new Object[] { "#", '#', this.candyCane });
		
		GameRegistry.addRecipe(new ItemStack(this.peppermintStick, 4), new Object[] { "#", "#", '#', this.candyPlanks });
		GameRegistry.addRecipe(new ItemStack(this.candyPlanks, 2), new Object[] { "##", "##", '#', this.peppermintStick });
		
		GameRegistry.addRecipe(new ItemStack(this.garland, 3, 0), new Object[] { "CCC", 'C', Blocks.leaves });
		GameRegistry.addRecipe(new ItemStack(this.garland, 3, 1), new Object[] { "CCC", 'C', Items.gold_nugget });
		
		GameRegistry.addRecipe(new ItemStack(this.greenPresent, 1), new Object[] { "GRG", "GEG", "GRG", 'G', new ItemStack(Blocks.wool, 1, 5), 'R', new ItemStack(Blocks.wool, 1, 14) });
		GameRegistry.addRecipe(new ItemStack(this.redPresent, 1), new Object[] { "RGR", "RER", "RGR", 'G', new ItemStack(Blocks.wool, 1, 5), 'R', new ItemStack(Blocks.wool, 1, 14) });
		
		DimensionManager.registerProviderType(this.kringleId, WorldProviderKringle.class, false);
		DimensionManager.registerDimension(this.kringleId, this.kringleId);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandKringle());
		event.registerServerCommand(new CommandHome());
	}
	
	public static PlayerData getPlayerData(EntityPlayerMP player)
	{
		return (PlayerData) player.getExtendedProperties(PLAYERDATA);
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public static void addInformation(ITipItem tipItem, ItemStack stack, EntityPlayer player, List info, boolean advanced)
	{
		String[] tips;
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			tips = tipItem.getShiftTip(player, stack);
			if (tips == null)
			{
				tips = tipItem.getTip(player, stack);
			}
		}
		else
		{
			tips = tipItem.getTip(player, stack);
			String[] shifttips = tipItem.getShiftTip(player, stack);
			if (shifttips != null && shifttips.length != 0)
			{
				List<String> moretips = new ArrayList<String>();
				for (int i = 0; i < tips.length; i++)
				{
					moretips.add(tips[i]);
				}
				if (!tips[tips.length - 1].isEmpty())
				{
					moretips.add("");
				}
				moretips.add(Festivities.shiftInfo);
				
				tips = moretips.toArray(tips);
			}
		}
		if (tips == null)
		{
			return;
		}
		boolean flag = false;
		for (int i = 0; i < tips.length; i++)
		{
			String tip = tips[i];
			String[] tiplines = FestiveUtils.wrapString(tip, 40);
			if (tiplines.length > 1 && flag)
			{
				info.add("");
			}
			for (int j = 0; j < tiplines.length; j++)
			{
				info.add(tiplines[j]);
			}
			if (tiplines.length > 1 && i < tips.length - 1)
			{
				info.add("");
				flag = false;
			}
			else
			{
				flag = true;
			}
		}
	}
}
