package me.brynblack.foundations.item;

import java.util.function.Predicate;
import me.brynblack.foundations.utils.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public abstract class ModRangedWeaponItem extends Item {
  public static final Predicate<ItemStack> SLINGSHOT_PROJECTILES =
      stack -> stack.isIn(ModItemTags.ROCKS);

  public ModRangedWeaponItem(Item.Settings settings) {
    super(settings);
  }

  public Predicate<ItemStack> getHeldProjectiles() {
    return this.getProjectiles();
  }

  public abstract Predicate<ItemStack> getProjectiles();

  public static ItemStack getHeldProjectile(LivingEntity entity, Predicate<ItemStack> predicate) {
    if (predicate.test(entity.getStackInHand(Hand.OFF_HAND))) {
      return entity.getStackInHand(Hand.OFF_HAND);
    }
    if (predicate.test(entity.getStackInHand(Hand.MAIN_HAND))) {
      return entity.getStackInHand(Hand.MAIN_HAND);
    }
    return ItemStack.EMPTY;
  }

  public abstract int getRange();
}
