package me.brynblack.primitiveprogression.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CopperArmorBase implements ArmorMaterial {

  private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};

  private static final int[] PROTECTION = new int[] {1, 4, 5, 2};

  @Override
  public int getDurability(EquipmentSlot slot) {
    return BASE_DURABILITY[slot.getEntitySlotId()] * 11;
  }

  @Override
  public int getProtectionAmount(EquipmentSlot slot) {
    return PROTECTION[slot.getEntitySlotId()];
  }

  @Override
  public int getEnchantability() {
    return 12;
  }

  @Override
  public SoundEvent getEquipSound() {

    return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
  }

  @Override
  public Ingredient getRepairIngredient() {

    return Ingredient.ofItems(Items.COPPER_INGOT);
  }

  @Override
  public String getName() {

    return "copper";
  }

  @Override
  public float getToughness() {

    return 0;
  }

  @Override
  public float getKnockbackResistance() {

    return 0;
  }
}
