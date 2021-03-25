package net.arathain.aot.block;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

public class DennerSaplingGenerator extends SaplingGenerator {
    public final Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> tree;

    public DennerSaplingGenerator(Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> tree) {
        this.tree = tree;
    }

    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return this.tree.get();
    }
}
