package me.brynblack.primitiveprogression.utils;

import java.util.Random;
import me.brynblack.primitiveprogression.PrimitiveProgression;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class WoodCuttingEvent {

  private static final Random RANDOM = new Random();

  public static void chopEvent() {

    UseBlockCallback.EVENT.register(
        (player, world, hand, block) -> {
          ItemStack stack = player.getStackInHand(hand);
          BlockPos pos = block.getBlockPos();
          BlockState state = world.getBlockState(pos);

          double r1 = RANDOM.nextDouble();
          int r2 = 0;

          boolean isAxe = player.getInventory().getMainHandStack().isIn(ItemTags.AXES),
              isSaw = player.getInventory().getMainHandStack().isIn(ModItemTags.SAWS),
              isLog = state.isIn(BlockTags.LOGS),
              isPlank = state.isIn(BlockTags.PLANKS);

          if (state == null || player == null) return ActionResult.PASS;

          if (isAxe
              && (isLog || isPlank)
              && block.getSide() == Direction.UP
              && player.isSneaking()) {
            if (!world.isClient) {
              if (r1 <= PrimitiveProgression.CONFIG.woodChoppingProb) {
                world.breakBlock(pos, false);
                ItemEntity itemEntity = null;
                if (isLog) {
                  r2 = isSaw ? 4 : RANDOM.nextInt(3) + 2;

                  for (RegistryEntry<Block> obj : Registries.BLOCK.iterateEntries(BlockTags.LOGS)) {
                    if (state.getBlock() == obj.value()) {
                      String planksString =
                          obj.value()
                              .toString()
                              .replace("stripped_", "")
                              .replaceAll("_[^_]+$", "_planks}");
                      for (RegistryEntry<Block> planks :
                          Registries.BLOCK.iterateEntries(BlockTags.PLANKS)) {
                        if (planks.value().toString().equals(planksString)) {
                          itemEntity =
                              new ItemEntity(
                                  player.getWorld(),
                                  block.getPos().x,
                                  block.getPos().y - 0.5,
                                  block.getPos().z,
                                  new ItemStack(planks.value(), r2));
                          break;
                        }
                      }
                    }
                  }
                }
                if (isPlank) {
                  r2 = isSaw ? 2 : RANDOM.nextInt(2) + 1;
                  itemEntity =
                      new ItemEntity(
                          player.getWorld(),
                          block.getPos().x,
                          block.getPos().y - 0.5,
                          block.getPos().z,
                          new ItemStack(Items.STICK, r2));
                }
                player.getWorld().spawnEntity(itemEntity);
              } else
                world.playSound(
                    null, pos, SoundEvents.BLOCK_WOOD_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);

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
