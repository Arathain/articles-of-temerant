package net.arathain.aot.world;

import net.arathain.aot.ArticlesOfTemerant;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.impl.biome.modification.BiomeModificationImpl;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class TemerantFeatures {
    private static final Map<ConfiguredFeature<?, ?>, Identifier> CONFIGURED_STUFF = new LinkedHashMap<>();

    public static final ConfiguredFeature<TreeFeatureConfig, ?> DENNER_TREE = register("denner_tree", Feature.TREE.configure(TemerantFeatureConfigs.DENNER_TREE_CONFIG));


    public static <BEE extends FeatureConfig> ConfiguredFeature<BEE, ?> register(String id, ConfiguredFeature<BEE, ?> feature) {
        CONFIGURED_STUFF.put(feature, new Identifier(ArticlesOfTemerant.MOD_ID, id));
        return feature;
    }
    public static void init() {
        ConfiguredFeature<?, ?> DENNER_TREE_CHAOS = register("denner_tree_chaos", DENNER_TREE.decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(100) )));
        CONFIGURED_STUFF.keySet().forEach(configuredFeature -> Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, CONFIGURED_STUFF.get(configuredFeature), configuredFeature));
        BiomeModificationImpl.INSTANCE.addModifier(CONFIGURED_STUFF.get(DENNER_TREE), ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld().and(context -> context.getBiome().getCategory() == Biome.Category.FOREST), context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, DENNER_TREE_CHAOS));
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            Identifier bastion_treasure = new Identifier(ArticlesOfTemerant.MOD_ID, "inject/bastion_treasure");
            if (LootTables.BASTION_TREASURE_CHEST.equals(identifier)) {
                fabricLootSupplierBuilder.withPool(LootPool.builder().with(LootTableEntry.builder(bastion_treasure).weight(1)).build());
            }
        });
    }

}

