package me.brynblack.primitiveprogression.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FlintToolBase implements ToolMaterial {

  @Override
  public int getDurability() {
    return 20;
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return 2.0f;
  }

  @Override
  public float getAttackDamage() {
    return 0f;
  }

  @Override
  public int getMiningLevel() {
    return 0;
  }

  @Override
  public int getEnchantability() {
    return 0;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return null;
  }
}
