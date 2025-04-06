package me.brynblack.foundations.utils;

import me.brynblack.foundations.world.StickTwigGen;
import me.brynblack.foundations.world.StoneRockGen;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public abstract class ModFeature {

  public static final Feature<DefaultFeatureConfig> ROCK_BLOCK_FEATURE =
      Registry.register(
          Registries.FEATURE,
          Identifier.of("foundations", "rock_block_gen"),
          new StoneRockGen(DefaultFeatureConfig.CODEC));

  public static final Feature<DefaultFeatureConfig> STICK_TWIG_FEATURE =
      Registry.register(
          Registries.FEATURE,
          Identifier.of("foundations", "stick_twig_gen"),
          new StickTwigGen(DefaultFeatureConfig.CODEC));
}
