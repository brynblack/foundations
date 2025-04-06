package me.brynblack.foundations.init;

import me.brynblack.foundations.utils.DisableWoodStoneTools;
import me.brynblack.foundations.utils.FlintKnapEvent;
import me.brynblack.foundations.utils.FlintKnifeKnapEvent;
import me.brynblack.foundations.utils.RecipeRemover;
import me.brynblack.foundations.utils.WoodCuttingEvent;

public class EventsInit {
  public static void init() {
    DisableWoodStoneTools.noStoneWoodTier();
    FlintKnapEvent.knapEvent();
    FlintKnifeKnapEvent.knifeKnapEvent();
    WoodCuttingEvent.chopEvent();
    RecipeRemover.removeRecipe();
  }
}
