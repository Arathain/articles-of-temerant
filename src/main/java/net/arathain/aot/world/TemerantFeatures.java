package net.arathain.aot.world;

import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class TemerantFeatures {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> DENNER_TREE = register("denner_tree", Feature.TREE.configure(TemerantFeatureConfigs.DENNER_TREE_CONFIG));




    private static <BEE extends FeatureConfig> ConfiguredFeature<BEE, ?> register(String id, ConfiguredFeature<BEE, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ArticlesOfTemerant.MOD_ID, id), feature);
    }
}
