package me.brynblack.primitiveprogression;

import me.brynblack.primitiveprogression.init.BlocksInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class PrimitiveProgressionClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    BlockRenderLayerMap.INSTANCE.putBlock(BlocksInit.STICK_TWIG_BLOCK, RenderLayer.getCutout());
  }
}
