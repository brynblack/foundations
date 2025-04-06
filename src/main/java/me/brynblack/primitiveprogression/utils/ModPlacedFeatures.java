package me.brynblack.primitiveprogression.utils;

import java.util.List;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {
  public static final RegistryEntry<PlacedFeature> ROCK_BLOCK;
  public static final RegistryEntry<PlacedFeature> STICK_TWIG;

  private static RegistryEntry<PlacedFeature> register(String id, PlacedFeature placedFeature) {
    return BuiltinRegistries.add(
        BuiltinRegistries.PLACED_FEATURE, Identifier.of("primitiveprogression", id), placedFeature);
  }

  static {
    ROCK_BLOCK =
        register(
            "rock_block_gen",
            new PlacedFeature(
                ModConfiguredFeatures.ROCK_BLOCK,
                List.of(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BiomePlacementModifier.of())));
    STICK_TWIG =
        register(
            "stick_twig_gen",
            new PlacedFeature(
                ModConfiguredFeatures.STICK_TWIG,
                List.of(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of())));
  }
}
