package me.brynblack.foundations.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;

public class BaseArmor extends ArmorItem {

  public BaseArmor(ArmorMaterial material, EquipmentType type, Settings settings) {
    super(material, type, settings);
  }
}
