package me.brynblack.foundations.armor;

import java.util.Map;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class CopperArmorBase {

  public static final ArmorMaterial COPPER = new ArmorMaterial(
      11,
      Map.of(
          EquipmentType.HELMET, 1,
          EquipmentType.CHESTPLATE, 4,
          EquipmentType.LEGGINGS, 5,
          EquipmentType.BOOTS, 2),
      12,
      SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
      0.0f,
      0.0f,
      TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft", "copper_ingots")),
      RegistryKey.of(RegistryKey.ofRegistry(Identifier.of("minecraft", "equipment_asset")), Identifier.of("foundations", "copper")));

}
