package net.arathain.aot.world;

import net.arathain.aot.TemerantBlocks;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;


public class TemerantFeatureConfigs {
    public static final TreeFeatureConfig DENNER_TREE_CONFIG = new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(TemerantBlocks.DENNER_LOG.getDefaultState()),
            new SimpleBlockStateProvider(TemerantBlocks.DENNER_LEAVES.getDefaultState()),
            new BlobFoliagePlacer(UniformIntDistribution.of(3, 1), UniformIntDistribution.of(2, 0), 2),
            new StraightTrunkPlacer(4, 2, 0),
				new TwoLayersFeatureSize(1, 0, 1)
		).ignoreVines().build();
}
