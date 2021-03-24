package net.arathain.aot;

import com.terraformersmc.terraform.leaves.ComposterRecipes;
import com.terraformersmc.terraform.wood.block.StrippableLogBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
    public static final Block DENNER_LOG = add("denner_log", new StrippableLogBlock(() -> STRIPPED_DENNER_LOG, MaterialColor.BROWN, FabricBlockSettings.copyOf(Blocks.DARK_OAK_LOG).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DENNER_LEAVES = add("denner_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).breakByTool(FabricToolTags.HOES).allowsSpawning(TemerantBlocks::canSpawnOnLeaves).suffocates(TemerantBlocks::never).blockVision(TemerantBlocks::never)), ItemGroup.BUILDING_BLOCKS);

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
        flammableRegistry.add(DENNER_PLANKS, 3, 12);
        flammableRegistry.add(DENNER_LOG, 3, 3);
        flammableRegistry.add(STRIPPED_DENNER_LOG, 3, 3);
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
