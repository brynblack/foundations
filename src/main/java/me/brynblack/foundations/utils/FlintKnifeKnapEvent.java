package me.brynblack.foundations.utils;

import java.util.Random;
import me.brynblack.foundations.Foundations;
import me.brynblack.foundations.init.ItemsInit;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class FlintKnifeKnapEvent {

  private static final Random RANDOM = new Random();

  public static void knifeKnapEvent() {

    UseBlockCallback.EVENT.register(
        (player, world, hand, block) -> {
          ItemStack stack = player.getStackInHand(hand);
          BlockPos pos = block.getBlockPos();
          BlockState state = world.getBlockState(pos);

          double r1 = RANDOM.nextDouble();
          double r2 = RANDOM.nextDouble();

          boolean
              isKnife = player.getInventory().getMainHandStack().isIn(ModItemTags.KNIVES),
              isRock = state.isIn(ModBlockTags.ROCKS);

          if (state == null || player == null) return ActionResult.PASS;

          if (isKnife && isRock && player.isSneaking()) {
            if (!world.isClient) {
              if (r1 <= Foundations.CONFIG.flintConsumeProb) {
                world.breakBlock(pos, false);
                if (r2 <= Foundations.CONFIG.flintSuccessProb && !player.isCreative()) {
                  ItemEntity itemEntity =
                      new ItemEntity(
                          player.getWorld(),
                          block.getPos().x,
                          block.getPos().y - 0.5,
                          block.getPos().z,
                          new ItemStack(ItemsInit.FLINT_SHARD, 1));
                  player.getWorld().spawnEntity(itemEntity);
                }
              } else
                world.playSound(
                    null, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);

              if (stack.getItem().getDefaultStack().isDamageable()) {

                ItemStack savedStack = stack.copy();
                boolean shouldAttemptDmg = true;
                Random random = new Random();
                int unbreakingLvl =
                    EnchantmentHelper.getLevel(
                        world
                            .getRegistryManager()
                            .getOrThrow(RegistryKeys.ENCHANTMENT)
                            .getOrThrow(Enchantments.UNBREAKING),
                        savedStack);

                if (unbreakingLvl > 0) shouldAttemptDmg = (1 + random.nextInt(5)) <= unbreakingLvl;

                if (savedStack.getDamage() < savedStack.getMaxDamage()) {

                  if (shouldAttemptDmg) savedStack.setDamage(savedStack.getDamage() + 1);

                  player.setStackInHand(hand, savedStack);

                } else player.setStackInHand(hand, ItemStack.EMPTY);
              }
            }
            return ActionResult.SUCCESS;
          }

          return ActionResult.PASS;
        });
  }
}
