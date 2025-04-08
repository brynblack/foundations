package me.brynblack.foundations.utils;

import me.brynblack.foundations.Foundations;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;

public class DisableWoodStoneTools {
  public static void noStoneWoodTier() {

    UseBlockCallback.EVENT.register(
        (player, _, _, _) -> {
          if (player == null) return ActionResult.PASS;

          ItemStack itemHeld = player.getInventory().getMainHandStack();
          ActionResult a = ActionResult.PASS;
          // if player is not in creative
          if (!player.isCreative()) {
          if (itemHeld.getItem() instanceof AxeItem
              || itemHeld.getItem() instanceof HoeItem
              || itemHeld.getItem() instanceof PickaxeItem
              || itemHeld.getItem() instanceof ShovelItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            } else if (itemHeld.getItem() instanceof SwordItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            }
          } else {
            a = ActionResult.PASS;
          }
          return a;
        });

    UseEntityCallback.EVENT.register(
        (player, _, _, _, _) -> {
          if (player == null) return ActionResult.PASS;

          ItemStack itemHeld = player.getInventory().getMainHandStack();
          ActionResult a = ActionResult.PASS;
          // if player is not in creative
          if (!player.isCreative()) {
          if (itemHeld.getItem() instanceof AxeItem
              || itemHeld.getItem() instanceof HoeItem
              || itemHeld.getItem() instanceof PickaxeItem
              || itemHeld.getItem() instanceof ShovelItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            } else if (itemHeld.getItem() instanceof SwordItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            }
          } else {
            a = ActionResult.PASS;
          }
          return a;
        });

    AttackBlockCallback.EVENT.register(
        (player, world, _, pos, _) -> {
          if (world.getBlockState(pos) == null || player == null) {
            return ActionResult.PASS;
          }

          ItemStack itemHeld = player.getInventory().getMainHandStack();
          ActionResult a = ActionResult.PASS;
          // if player is not in creative
          if (!player.isCreative()) {
          if (itemHeld.getItem() instanceof AxeItem
              || itemHeld.getItem() instanceof HoeItem
              || itemHeld.getItem() instanceof PickaxeItem
              || itemHeld.getItem() instanceof ShovelItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            } else if (itemHeld.getItem() instanceof SwordItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            }
          } else {
            a = ActionResult.PASS;
          }
          return a;
        });

    AttackEntityCallback.EVENT.register(
        (player, _, _, _, _) -> {
          if (player == null) return ActionResult.PASS;

          ItemStack itemHeld = player.getInventory().getMainHandStack();
          ActionResult a = ActionResult.PASS;
          // if player is not in creative
          if (!player.isCreative()) {
          if (itemHeld.getItem() instanceof AxeItem
              || itemHeld.getItem() instanceof HoeItem
              || itemHeld.getItem() instanceof PickaxeItem
              || itemHeld.getItem() instanceof ShovelItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            } else if (itemHeld.getItem() instanceof SwordItem) {
              if (!Foundations.CONFIG.crafting.enableWoodenTools) {
                if (itemHeld.isIn(ModItemTags.WOODEN_TOOLS)) a = ActionResult.FAIL;
              }
              if (!Foundations.CONFIG.crafting.enableStoneTools) {
                if (itemHeld.isIn(ModItemTags.STONE_TOOLS)) a = ActionResult.FAIL;
              }
            }
          } else {
            a = ActionResult.PASS;
          }
          return a;
        });
  }
}
