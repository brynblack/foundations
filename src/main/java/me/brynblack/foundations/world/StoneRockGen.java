package me.brynblack.foundations.world;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import java.util.Map;
import me.brynblack.foundations.init.BlocksInit;
import me.brynblack.foundations.utils.ModBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class StoneRockGen extends Feature<DefaultFeatureConfig> {

  private static final Supplier<Map<Block, Block>> A =
      Suppliers.memoize(
          () ->
              new ImmutableMap.Builder<Block, Block>()
                  .put(Blocks.STONE, BlocksInit.STONE_ROCK_BLOCK)
                  .put(Blocks.ANDESITE, BlocksInit.ANDESITE_ROCK_BLOCK)
                  .put(Blocks.DIORITE, BlocksInit.DIORITE_ROCK_BLOCK)
                  .put(Blocks.GRANITE, BlocksInit.GRANITE_ROCK_BLOCK)
                  .put(Blocks.SAND, BlocksInit.SANDSTONE_ROCK_BLOCK)
                  .put(Blocks.SANDSTONE, BlocksInit.SANDSTONE_ROCK_BLOCK)
                  .put(Blocks.RED_SAND, BlocksInit.RED_SANDSTONE_ROCK_BLOCK)
                  .put(Blocks.RED_SANDSTONE, BlocksInit.RED_SANDSTONE_ROCK_BLOCK)
                  .put(Blocks.TERRACOTTA, BlocksInit.RED_SANDSTONE_ROCK_BLOCK)
                  .build());

  public StoneRockGen(Codec<DefaultFeatureConfig> configCodec) {
    super(configCodec);
  }

  @Override
  public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
    StructureWorldAccess world = context.getWorld();
    BlockPos pos = context.getOrigin();

    BlockState stateAt = world.getBlockState(pos);
    BlockState stateDown = world.getBlockState(pos.down());

    if (stateAt.isAir() && stateDown.isIn(ModBlockTags.ROCK_PLACEABLE_ON)) {
      for (int y = 1; y <= 8; y++) {
        BlockPos stonePos = pos.down(y);
        BlockState stoneState = world.getBlockState(stonePos);
        if (A.get().containsKey(stoneState.getBlock())) {
          Block looseRockBlock = A.get().get(stoneState.getBlock());
          world.setBlockState(pos, looseRockBlock.getDefaultState(), 3);
          return true;
        }
      }
    }
    return true;
  }
}
