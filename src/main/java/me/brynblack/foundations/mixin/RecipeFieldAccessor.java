// package me.brynblack.foundations.mixin;

// import java.util.Map;
// import net.minecraft.recipe.Recipe;
// import net.minecraft.recipe.RecipeManager;
// import net.minecraft.recipe.RecipeType;
// import net.minecraft.util.Identifier;
// import org.spongepowered.asm.mixin.Mixin;
// import org.spongepowered.asm.mixin.gen.Accessor;

// @Mixin(RecipeManager.class)
// public interface RecipeFieldAccessor {
//   @Accessor("recipes")
//   Map<RecipeType<?>, Map<Identifier, Recipe<?>>> getRecipeField();

//   @Accessor("recipes")
//   void setRecipeField(Map<RecipeType<?>, Map<Identifier, Recipe<?>>> recipe);
// }
