package me.brynblack.primitiveprogression.utils;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import java.util.function.Predicate;
import me.brynblack.primitiveprogression.PrimitiveProgression;
import me.brynblack.primitiveprogression.mixin.MatchingStackAccessor;
import me.brynblack.primitiveprogression.mixin.RecipeFieldAccessor;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class RecipeRemover {

  public static void removeRecipe() {
    ServerLifecycleEvents.SERVER_STARTED.register(
        server -> {
          final RecipeManager mgr = server.getRecipeManager();

          if (!PrimitiveProgression.CONFIG.crafting.enableWoodenTools)
            removeRecipes(mgr, ModItemTags.WOODEN_TOOLS);
          if (!PrimitiveProgression.CONFIG.crafting.enableStoneTools)
            removeRecipes(mgr, ModItemTags.STONE_TOOLS);
          if (!PrimitiveProgression.CONFIG.crafting.enablePlanksAndSticks) {
            removePlankRecipes(mgr);
            removeRecipes(mgr, new ItemStack(Items.STICK, 4));
          }
        });
  }

  /**
   * Removes all crafting recipes with an output item contained in the specified tag.
   *
   * @param recipeManager The recipe manager
   * @param stack The ItemStack output of the recipe to remove
   */
  private static void removeRecipes(final RecipeManager recipeManager, final ItemStack stack) {
    final int recipesRemoved =
        removeRecipes(
            recipeManager,
            recipe -> {
              final ItemStack recipeOutput = recipe.getOutput();
              return !recipeOutput.isEmpty()
                  && (!stack.isEmpty()
                      && recipeOutput.getCount() == stack.getCount()
                      && recipeOutput.getItem() == stack.getItem()
                      && ItemStack.areNbtEqual(recipeOutput, stack));
            });

    PrimitiveProgression.LOGGER.info("Removed {} recipe(s)", recipesRemoved);
  }

  /**
   * Removes all crafting recipes with an output item contained in the specified tag.
   *
   * @param recipeManager The recipe manager
   * @param tag The tag
   */
  private static void removeRecipes(final RecipeManager recipeManager, final TagKey<Item> tag) {
    final int recipesRemoved =
        removeRecipes(
            recipeManager,
            recipe -> {
              final ItemStack recipeOutput = recipe.getOutput();
              return !recipeOutput.isEmpty() && recipeOutput.isIn(tag);
            });

    PrimitiveProgression.LOGGER.info("Removed {} recipe(s)", recipesRemoved);
  }

  /**
   * Removes all plank recipes if an ingredient is a log.
   *
   * @param recipeManager The recipe manager
   */
  private static void removePlankRecipes(final RecipeManager recipeManager) {
    final int recipesRemoved =
        removeRecipes(
            recipeManager,
            recipe -> {
              final ItemStack recipeOutput = recipe.getOutput();
              for (Ingredient ing : recipe.getIngredients()) {
                for (ItemStack stack : ((MatchingStackAccessor) (Object) ing).getMatchingStacks()) {
                  if (stack.isIn(ItemTags.LOGS)) {
                    return !recipeOutput.isEmpty() && recipeOutput.isIn(ItemTags.PLANKS);
                  }
                }
              }
              return false;
            });

    PrimitiveProgression.LOGGER.info("Removed {} plank recipe(s)", recipesRemoved);
  }

  /**
   * Remove all crafting recipes that match the specified predicate.
   *
   * @param recipeManager The recipe manager
   * @param predicate The predicate
   * @return The number of recipes removed
   */
  private static int removeRecipes(
      final RecipeManager recipeManager, final Predicate<Recipe<?>> predicate) {

    final Map<RecipeType<?>, Map<Identifier, Recipe<?>>> existingRecipes =
        ((RecipeFieldAccessor) recipeManager).getRecipeField();

    final Object2IntMap<RecipeType<?>> removedCounts = new Object2IntOpenHashMap<>();
    final ImmutableMap.Builder<RecipeType<?>, Map<Identifier, Recipe<?>>> newRecipes =
        ImmutableMap.builder();

    // For each recipe type, create a new map that doesn't contain the recipes to be removed
    existingRecipes.forEach(
        (recipeType, existingRecipesForType) -> {
          // noinspection UnstableApiUsage

          final ImmutableMap<Identifier, Recipe<?>> newRecipesForType =
              existingRecipesForType.entrySet().stream()
                  .filter(entry -> !predicate.test(entry.getValue()))
                  .collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));

          removedCounts.put(recipeType, existingRecipesForType.size() - newRecipesForType.size());
          newRecipes.put(recipeType, newRecipesForType);
        });

    final int removedCount = removedCounts.values().intStream().sum();

    ((RecipeFieldAccessor) recipeManager).setRecipeField(newRecipes.build());

    return removedCount;
  }
}
