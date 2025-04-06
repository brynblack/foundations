package me.brynblack.foundations.item;

import me.brynblack.foundations.Foundations;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItemGroup {
  public static final RegistryKey<ItemGroup> FOUNDATIONS_KEY =
      RegistryKey.of(
          Registries.ITEM_GROUP.getKey(), Identifier.of(Foundations.MOD_ID, "eggroup"));
  public static final ItemGroup FOUNDATIONS =
      FabricItemGroup.builder().icon(() -> new ItemStack(Items.FLINT)).build();
}
