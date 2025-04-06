package me.brynblack.primitiveprogression.init;

import me.brynblack.primitiveprogression.utils.DisableWoodStoneTools;
import me.brynblack.primitiveprogression.utils.FlintKnapEvent;
import me.brynblack.primitiveprogression.utils.FlintKnifeKnapEvent;
import me.brynblack.primitiveprogression.utils.RecipeRemover;
import me.brynblack.primitiveprogression.utils.WoodCuttingEvent;

public class EventsInit {
  public static void init() {
    DisableWoodStoneTools.noStoneWoodTier();
    FlintKnapEvent.knapEvent();
    FlintKnifeKnapEvent.knifeKnapEvent();
    WoodCuttingEvent.chopEvent();
    RecipeRemover.removeRecipe();
  }
}
