package me.brynblack.primitiveprogression.utils;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModBlockTags {
  public static final TagKey<Block> ROCK_PLACEABLE_ON = register("rock_placeable_on");
  public static final TagKey<Block> STONE_LIKE = register("stone_like");
  public static final TagKey<Block> ROCKS = register("rocks");
  public static final TagKey<Block> SLOW_DIGGING = register("slow_digging");
  public static final TagKey<Block> BLACKLISTED_BLOCKS = register("blacklisted_blocks");

  private ModBlockTags() {}

  private static TagKey<Block> register(String id) {
    return TagKey.of(RegistryKeys.BLOCK, Identifier.of("primitiveprogression", id));
  }
}
