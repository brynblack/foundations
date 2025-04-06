package me.brynblack.primitiveprogression;

import me.brynblack.primitiveprogression.config.PrimitiveProgressionConfig;
import me.brynblack.primitiveprogression.init.*;
import me.brynblack.primitiveprogression.utils.ModPlacedFeatures;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimitiveProgression implements ModInitializer {

  public static final String MOD_ID = "primitiveprogression";

  public static PrimitiveProgressionConfig CONFIG;

  public static final Logger LOGGER = LogManager.getLogger("PrimitiveProgression");

  @Override
  public void onInitialize() {

    // config
    AutoConfig.register(PrimitiveProgressionConfig.class, GsonConfigSerializer::new);
    CONFIG = AutoConfig.getConfigHolder(PrimitiveProgressionConfig.class).getConfig();

    // init
    BlocksInit.init();
    ItemsInit.init();
    EventsInit.init();

    // world gen

    if (CONFIG.generateStones) {
      BiomeModifications.addFeature(
          BiomeSelectors.foundInOverworld(),
          GenerationStep.Feature.VEGETAL_DECORATION,
          ModPlacedFeatures.ROCK_BLOCK.getKey().get());
    }
    if (CONFIG.generateSticks) {
      BiomeModifications.addFeature(
          BiomeSelectors.foundInOverworld(),
          GenerationStep.Feature.VEGETAL_DECORATION,
          ModPlacedFeatures.STICK_TWIG.getKey().get());
    }
  }
}
