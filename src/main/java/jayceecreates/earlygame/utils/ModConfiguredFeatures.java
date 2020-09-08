package jayceecreates.earlygame.utils;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModConfiguredFeatures {
   public static final ConfiguredFeature<?, ?> ROCK_BLOCK = Registry.register(
      BuiltinRegistries.CONFIGURED_FEATURE,
      new Identifier("earlygame", "rock_block_gen"),
      ModFeature.ROCK_BLOCK_FEATURE
         .configure(FeatureConfig.DEFAULT)
         .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
         .repeatRandomly(5)
   );
}
