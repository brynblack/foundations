package me.brynblack.foundations.utils;

import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> ROCK_BLOCK_KEY =
        RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of("foundations", "rock_block_gen"));

    public static final RegistryKey<ConfiguredFeature<?, ?>> STICK_TWIG_KEY =
        RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of("foundations", "stick_twig_gen"));

    public static Feature<DefaultFeatureConfig> ROCK_BLOCK_FEATURE;
    public static Feature<DefaultFeatureConfig> STICK_TWIG_FEATURE;

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        context.register(
            ROCK_BLOCK_KEY,
            new ConfiguredFeature<>(ROCK_BLOCK_FEATURE, FeatureConfig.DEFAULT)
        );

        context.register(
            STICK_TWIG_KEY,
            new ConfiguredFeature<>(STICK_TWIG_FEATURE, FeatureConfig.DEFAULT)
        );
    }
}