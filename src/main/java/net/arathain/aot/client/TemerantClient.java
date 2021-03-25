package net.arathain.aot.client;

import net.arathain.aot.TemerantBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;


@Environment(EnvType.CLIENT)
public class TemerantClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRender();
    }
    private static void registerRender() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TemerantBlocks.DENNER_SAPLING, TemerantBlocks.DENNER_TRAPDOOR);
    }
}
