package net.arathain.aot;


import com.terraformersmc.terraform.tree.block.TerraformSaplingBlock;
import com.terraformersmc.terraform.wood.block.*;

import net.arathain.aot.block.DennerLogBlock;
import net.arathain.aot.block.DennerSaplingGenerator;
import net.arathain.aot.world.TemerantFeatures;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.HashMap;
import java.util.Map;

public class TemerantBlocks {
    private static final Map<Identifier, BlockItem> ITEMS = new HashMap<>();
    private static final Map<Identifier, Block> BLOCKS = new HashMap<>();

    public static final Block DENNER_PLANKS = add("denner_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block STRIPPED_DENNER_LOG = add("stripped_denner_log", createLog(MaterialColor.BROWN, MaterialColor.WOOD), ItemGroup.BUILDING_BLOCKS);
    public static final Block STRIPPED_DENNER_WOOD = add("stripped_denner_wood", createLog(MaterialColor.BROWN, MaterialColor.WOOD), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_LOG = add("denner_log", new DennerLogBlock(() -> STRIPPED_DENNER_LOG, MaterialColor.BROWN, FabricBlockSettings.copyOf(Blocks.DARK_OAK_LOG).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_WOOD = add("denner_wood", new StrippableLogBlock(() -> STRIPPED_DENNER_WOOD, MaterialColor.BROWN, FabricBlockSettings.copyOf(Blocks.DARK_OAK_LOG).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_LEAVES = add("denner_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).breakByTool(FabricToolTags.HOES).allowsSpawning(TemerantBlocks::canSpawnOnLeaves).suffocates(TemerantBlocks::never).blockVision(TemerantBlocks::never)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_SAPLING = add("denner_sapling", new TerraformSaplingBlock(new DennerSaplingGenerator(() -> TemerantFeatures.DENNER_TREE)), ItemGroup.DECORATIONS);
    public static final Block DENNER_TRAPDOOR = add("denner_trapdoor", new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).breakByTool(FabricToolTags.AXES)), ItemGroup.REDSTONE);
    public static final Block DENNER_SLAB = add("denner_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_PRESSURE_PLATE = add("denner_pressure_plate", new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE).breakByTool(FabricToolTags.AXES)), ItemGroup.REDSTONE);
    public static final Block DENNER_FENCE = add("denner_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE).breakByTool(FabricToolTags.AXES)), ItemGroup.DECORATIONS);
    public static final Block DENNER_DOOR = add("denner_door", new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).breakByTool(FabricToolTags.AXES)), ItemGroup.REDSTONE);
    public static final Block DENNER_FENCE_GATE = add("denner_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE).breakByTool(FabricToolTags.AXES)), ItemGroup.REDSTONE);
    public static final Block DENNER_STAIRS = add("denner_stairs", new TerraformStairsBlock(DENNER_PLANKS, FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_BUTTON = add("denner_button", new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).breakByTool(FabricToolTags.AXES)), ItemGroup.REDSTONE);


    public static void register() {
        for (Identifier id : ITEMS.keySet()) {
            Registry.register(Registry.ITEM, id, ITEMS.get(id));
        }
        for (Identifier id : BLOCKS.keySet()) {
            Registry.register(Registry.BLOCK, id, BLOCKS.get(id));
        }
        addFlammables();
    }
    private static <B extends Block> B add(String name, B block, ItemGroup tab) {
        return add(name, block, new BlockItem(block, new Item.Settings().group(tab)));
    }
    private static <B extends Block> B add(String name, B block, BlockItem item) {
        add(name, block);
        if (item != null) {
            item.appendBlocks(Item.BLOCK_ITEMS, item);
            ITEMS.put(new Identifier(ArticlesOfTemerant.MOD_ID, name), item);
        }
        return block;
    }

    private static <B extends Block> B add(String name, B block) {
        BLOCKS.put(new Identifier(ArticlesOfTemerant.MOD_ID, name), block);
        return block;
    }

    private static <I extends BlockItem> I add(String name, I item) {
        item.appendBlocks(Item.BLOCK_ITEMS, item);
        ITEMS.put(new Identifier(ArticlesOfTemerant.MOD_ID, name), item);
        return item;
    }

    private static void addFlammables() {
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(DENNER_PLANKS, 3, 4);
        flammableRegistry.add(DENNER_SLAB, 3, 4);
        flammableRegistry.add(DENNER_STAIRS, 3, 4);
        flammableRegistry.add(DENNER_BUTTON, 3, 4);
        flammableRegistry.add(DENNER_FENCE, 3, 4);
        flammableRegistry.add(DENNER_FENCE_GATE, 3, 4);
        flammableRegistry.add(DENNER_LOG, 3, 4);
        flammableRegistry.add(DENNER_TRAPDOOR, 3, 4);
        flammableRegistry.add(DENNER_DOOR, 3, 4);
        flammableRegistry.add(DENNER_PRESSURE_PLATE, 3, 4);
        flammableRegistry.add(DENNER_WOOD, 3, 4);
        flammableRegistry.add(STRIPPED_DENNER_LOG, 3, 4);
        flammableRegistry.add(STRIPPED_DENNER_WOOD, 3, 4);
        flammableRegistry.add(DENNER_LEAVES, 18, 36);
    }
    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
    public static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
    private static PillarBlock createLog(MaterialColor topColor, MaterialColor sideColor) {
        return new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));
    }


}
