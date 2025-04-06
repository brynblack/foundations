package me.brynblack.foundations.utils;

import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModConfiguredFeatures {
  public static final RegistryEntry<ConfiguredFeature<?, ?>> ROCK_BLOCK;
  public static final RegistryEntry<ConfiguredFeature<?, ?>> STICK_TWIG;

  private static RegistryEntry<ConfiguredFeature<?, ?>> register(
      String id, ConfiguredFeature<?, ?> configuredFeature) {
    return BuiltinRegistries.add(
        BuiltinRegistries.CONFIGURED_FEATURE,
        Identifier.of("foundations", id),
        configuredFeature);
  }

  static {
    ROCK_BLOCK =
        register(
            "rock_block_gen",
            new ConfiguredFeature<>(ModFeature.ROCK_BLOCK_FEATURE, FeatureConfig.DEFAULT));
    STICK_TWIG =
        register(
            "stick_twig_gen",
            new ConfiguredFeature<>(ModFeature.STICK_TWIG_FEATURE, FeatureConfig.DEFAULT));
  }
}
