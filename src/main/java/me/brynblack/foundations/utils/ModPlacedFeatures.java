package me.brynblack.foundations.utils;

import java.util.List;

import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> ROCK_BLOCK_PLACED_KEY =
        RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of("foundations", "rock_block_gen"));

    public static final RegistryKey<PlacedFeature> STICK_TWIG_PLACED_KEY =
        RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of("foundations", "stick_twig_gen"));

    public static void bootstrap(Registerable<PlacedFeature> context) {
        context.register(
            ROCK_BLOCK_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ROCK_BLOCK_KEY),
                List.of(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BiomePlacementModifier.of()
                )
            )
        );

        context.register(
            STICK_TWIG_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.STICK_TWIG_KEY),
                List.of(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of()
                )
            )
        );
    }
}