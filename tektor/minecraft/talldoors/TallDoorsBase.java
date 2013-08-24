package tektor.minecraft.talldoors;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import tektor.minecraft.chalith.gui.ChalithGuiHandler;
import tektor.minecraft.talldoors.blocks.DrawbridgeWorkbench;
import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import tektor.minecraft.talldoors.entities.drawbridge.EntityConnector;
import tektor.minecraft.talldoors.gui.TallDoorsGuiHandler;
import tektor.minecraft.talldoors.items.Connector;
import tektor.minecraft.talldoors.items.DestructionHammer;
import tektor.minecraft.talldoors.items.DoorPlacer;
import tektor.minecraft.talldoors.items.DrawbridgePlacer;
import tektor.minecraft.talldoors.items.DrawbridgeWorkbenchItemBlock;
import tektor.minecraft.talldoors.items.Key;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "TallDoors", name = "TallDoors", version = "0.2.3")
@NetworkMod(channels = { "TallDoors" }, packetHandler = TallDoorsPacketHandler.class, clientSideRequired = true)
public class TallDoorsBase {

	// instance
	@Instance("TallDoors")
	public static TallDoorsBase instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.talldoors.client.TallDoorsClientProxy", serverSide = "tektor.minecraft.talldoors.TallDoorsCommonProxy")
	public static TallDoorsCommonProxy proxy;

	public static int itemID1, itemID2, itemID3, itemID4, itemID5;
	public static int blockID1;

	public static Item doorPlacer;
	public static Item drawbridge;
	public static Item connector;
	public static Item destructionHammer;
	public static Item key;

	public static Block drawbridgeWorkbench;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		itemID1 = config.get(Configuration.CATEGORY_ITEM, "itemID1", 7100)
				.getInt();
		itemID2 = config.get(Configuration.CATEGORY_ITEM, "itemID2", 7101)
				.getInt();
		itemID3 = config.get(Configuration.CATEGORY_ITEM, "itemID3", 7102)
				.getInt();
		itemID4 = config.get(Configuration.CATEGORY_ITEM, "itemID4", 7103)
				.getInt();
		itemID5 = config.get(Configuration.CATEGORY_ITEM, "itemID5", 7104)
				.getInt();

		blockID1 = config.get(Configuration.CATEGORY_BLOCK, "blockID1", 860)
				.getInt();
		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		initializeIDs();
		registerItems();
		registerBlocks();
		registerEntities();
		registerRecipes();
		registerTileEntities();
		proxy.registerRenderers();

		NetworkRegistry.instance().registerGuiHandler(this,
				new TallDoorsGuiHandler());
	}

	private void registerBlocks() {

		GameRegistry.registerBlock(drawbridgeWorkbench,DrawbridgeWorkbenchItemBlock.class, "drawbridgeWorkbench");
		LanguageRegistry.addName(new ItemStack(drawbridgeWorkbench, 1, 0),
				"Drawbridge Workbench");

	}

	private void registerRecipes() {
		ItemStack door = new ItemStack(Item.doorWood, 1, 0);
		ItemStack fenceGate = new ItemStack(Block.fenceGate, 1);
		ItemStack wood = new ItemStack(Block.planks, 1);
		ItemStack wood2 = new ItemStack(Block.wood, 1);
		ItemStack cobble = new ItemStack(Block.cobblestone, 1);
		ItemStack iron = new ItemStack(Item.ingotIron, 1);
		ItemStack string = new ItemStack(Item.silk, 1);
		ItemStack stick = new ItemStack(Item.stick,1);
		ItemStack redstone = new ItemStack(Item.redstone, 1);
		
		// Destruction Hammer
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.destructionHammer, 1, 0), new Object[] { "YYY",
				"YXY", " X ", 'X', stick, 'Y', iron });
		// Drawbridge Workbench
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.drawbridgeWorkbench, 1, 0), new Object[] { "YYY",
				"XXX", "XXX", 'X', wood, 'Y', cobble });
		
		// Machine Workbench
				GameRegistry.addShapedRecipe(new ItemStack(
						TallDoorsBase.drawbridgeWorkbench, 1, 1), new Object[] { "ZYZ",
						"XXX", "XXX", 'X', wood, 'Y', cobble ,'Z', redstone});

		// Connector
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.connector, 1,
				0), new Object[] { " X ", "XYX", " X ", 'X', string, 'Y',
				cobble });

		// draw base
//		GameRegistry
//				.addShapedRecipe(new ItemStack(TallDoorsBase.drawbridge, 1, 0),
//						new Object[] { "XXX", "XYX", "XXX", 'X', wood, 'Y',
//								wood2 });

		// draw machine
//		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.drawbridge, 1,
//				1),
//				new Object[] { "XXX", "YXY", "XXX", 'X', wood, 'Y', cobble });

		// Dark Metal Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				10),
				new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y', door });
		// Dark Metal left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				11),
				new Object[] { "XXX", "XXX", "YXX", 'X', cobble, 'Y', door });
		// Metal right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				12), new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 10) });
		// Metal left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				13), new Object[] { "XXX", "YXX", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 11) });
		// Metal right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				14), new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 12) });
		// Metal left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				15), new Object[] { "XXX", "YXX", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 13) });

		// Metal Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				8), new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y', door });
		// Metal left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				9), new Object[] { "XXX", "XXX", "YXX", 'X', iron, 'Y', door });
		// Metal right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				16), new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 8) });
		// Metal left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				17), new Object[] { "XXX", "YXX", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 9) });
		// Metal right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				18), new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 16) });
		// Metal left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				19), new Object[] { "XXX", "YXX", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 17) });

		// Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				0), new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y', door });
		// left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				1), new Object[] { "XXX", "XXX", "YXX", 'X', wood, 'Y', door });
		// right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				2), new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 0) });
		// left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				3), new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 1) });
		// right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				4), new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 2) });
		// left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				5), new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 3) });
		// right fence gate
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				6), new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
				fenceGate });
		// left fence gate
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				7), new Object[] { "XXX", "XXX", "YXX", 'X', wood, 'Y',
				fenceGate });

	}

	private void initializeIDs() {
		doorPlacer = new DoorPlacer(itemID1);
		connector = new Connector(itemID2);
		drawbridge = new DrawbridgePlacer(itemID3);
		destructionHammer = new DestructionHammer(itemID4);
		key = new Key(itemID5);

		drawbridgeWorkbench = new DrawbridgeWorkbench(blockID1);

	}

	private void registerItems() {
		GameRegistry.registerItem(doorPlacer, "doorplacer");
		GameRegistry.registerItem(connector, "connector");
		GameRegistry.registerItem(drawbridge, "drawbridge");
		GameRegistry.registerItem(destructionHammer, "destructionHammer");
		GameRegistry.registerItem(key, "key");
	}

	private void registerEntities() {
		EntityRegistry.registerGlobalEntityID(EntranceDoor1.class,
				"EntranceDoor1", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntranceDoor1.class, "EntranceDoor1",
				0, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(EntranceDoor2.class,
				"EntranceDoor2", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntranceDoor2.class, "EntranceDoor2",
				1, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(EntranceDoor3.class,
				"EntranceDoor3", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntranceDoor3.class, "EntranceDoor3",
				2, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(FenceGate1.class, "FenceGate1",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(FenceGate1.class, "FenceGate1", 3,
				TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(EntityConnector.class,
				"EntityConnector", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityConnector.class,
				"EntityConnector", 4, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry
				.registerGlobalEntityID(MetalEntranceDoor1.class,
						"MetalEntranceDoor1",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(MetalEntranceDoor1.class,
				"MetalEntranceDoor1", 5, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(DarkMetalEntranceDoor1.class,
				"DarkMetalEntranceDoor1",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor1.class,
				"DarkMetalEntranceDoor1", 6, TallDoorsBase.instance, 120, 5,
				true);
		EntityRegistry.registerGlobalEntityID(DrawbridgeBase.class,
				"DrawbridgeBase", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DrawbridgeBase.class,
				"DrawbridgeBase", 7, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(DrawbridgeMachine.class,
				"DrawbridgeMachine", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DrawbridgeMachine.class,
				"DrawbridgeMachine", 8, TallDoorsBase.instance, 120, 5, true);

		EntityRegistry.registerGlobalEntityID(DarkMetalEntranceDoor2.class,
				"DarkMetalEntranceDoor2",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor2.class,
				"DarkMetalEntranceDoor2", 9, TallDoorsBase.instance, 120, 5,
				true);

		EntityRegistry.registerGlobalEntityID(DarkMetalEntranceDoor3.class,
				"DarkMetalEntranceDoor3",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor3.class,
				"DarkMetalEntranceDoor3", 10, TallDoorsBase.instance, 120, 5,
				true);
		EntityRegistry
				.registerGlobalEntityID(MetalEntranceDoor2.class,
						"MetalEntranceDoor2",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(MetalEntranceDoor2.class,
				"MetalEntranceDoor2", 11, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry
				.registerGlobalEntityID(MetalEntranceDoor3.class,
						"MetalEntranceDoor3",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(MetalEntranceDoor3.class,
				"MetalEntranceDoor3", 12, TallDoorsBase.instance, 120, 5, true);

	}

	private void registerTileEntities() {
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity.class,
						"Drawbridge_Workbench");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
