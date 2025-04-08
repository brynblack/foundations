package me.brynblack.foundations.item;

import java.util.function.Predicate;
import me.brynblack.foundations.Foundations;
import me.brynblack.foundations.entity.RockEntity;
import me.brynblack.foundations.init.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SlingshotItem extends ModRangedWeaponItem {

  public SlingshotItem(Item.Settings settings) {
    super(settings);
  }

  @Override
  public boolean onStoppedUsing(
      ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
    int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
    float f = getPullProgress(i);

    if (!(user instanceof PlayerEntity playerEntity)) return false;

    boolean bl =
        playerEntity.getAbilities().creativeMode
            || EnchantmentHelper.getLevel(
                    world
                        .getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(Enchantments.INFINITY),
                    stack)
                > 0;
    ItemStack itemStack = getRockType(playerEntity, stack);

    if (itemStack.isEmpty() && !bl) return false;

    if (itemStack.isEmpty()) {
      if (!bl) return false;
      itemStack = new ItemStack(ItemsInit.STONE_ROCK);
    }

    if ((double) f < 0.1D) {
      return false;
    }

    boolean bl2 = bl && itemStack.isOf(ItemsInit.STONE_ROCK);

    if (!world.isClient) {
      Foundations.LOGGER.info("Use Tick: " + f);

      int j =
          EnchantmentHelper.getLevel(
              world
                  .getRegistryManager()
                  .getOrThrow(RegistryKeys.ENCHANTMENT)
                  .getOrThrow(Enchantments.POWER),
              stack);
      int k =
          EnchantmentHelper.getLevel(
              world
                  .getRegistryManager()
                  .getOrThrow(RegistryKeys.ENCHANTMENT)
                  .getOrThrow(Enchantments.PUNCH),
              stack);

      RockEntity rockEntity = new RockEntity(world, user, stack);
      rockEntity.setItem(itemStack);
      rockEntity.setVelocity(
          playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 1.5f, 1.0f);
      if (j > 0) {
        rockEntity.setDamage(rockEntity.getDamage() + (double) j * 0.5D + 0.5D);
      }
      if (k > 0) {
        rockEntity.setPunch(k);
      }
      if (EnchantmentHelper.getLevel(
              world
                  .getRegistryManager()
                  .getOrThrow(RegistryKeys.ENCHANTMENT)
                  .getOrThrow(Enchantments.FLAME),
              stack)
          > 0) {
        rockEntity.setOnFireFor(100);
      }
      stack.damage(1, playerEntity, EquipmentSlot.MAINHAND);

      world.spawnEntity(rockEntity);
    }

    if (!bl2 && !playerEntity.getAbilities().creativeMode) {
      itemStack.decrement(1);
      if (itemStack.isEmpty()) {
        playerEntity.getInventory().removeOne(itemStack);
      }
    }

    world.playSound(
        null,
        playerEntity.getX(),
        playerEntity.getY(),
        playerEntity.getZ(),
        SoundEvents.ENTITY_ARROW_SHOOT,
        SoundCategory.PLAYERS,
        1.0F,
        1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    return bl2;
  }

  public static float getPullProgress(int useTicks) {
    float f = (float) useTicks / 20.0f;
    if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
      f = 1.0f;
    }
    return f;
  }

  @Override
  public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
    return 72000;
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.BOW;
  }

  @Override
  public ActionResult use(World world, PlayerEntity user, Hand hand) {
    ItemStack itemStack = user.getStackInHand(hand);
    boolean bl = !getRockType(user, itemStack).isEmpty();
    if (user.getAbilities().creativeMode || bl) {
      user.setCurrentHand(hand);
      return ActionResult.CONSUME;
    }
    return ActionResult.FAIL;
  }

  public ItemStack getRockType(PlayerEntity player, ItemStack stack) {
    if (!(stack.getItem() instanceof ModRangedWeaponItem)) {
      return ItemStack.EMPTY;
    }
    Predicate<ItemStack> predicate = ((ModRangedWeaponItem) stack.getItem()).getHeldProjectiles();
    ItemStack itemStack = ModRangedWeaponItem.getHeldProjectile(player, predicate);
    if (!itemStack.isEmpty()) {
      return itemStack;
    }
    predicate = ((ModRangedWeaponItem) stack.getItem()).getProjectiles();
    for (int i = 0; i < player.getInventory().size(); ++i) {
      ItemStack itemStack2 = player.getInventory().getStack(i);
      if (predicate.test(itemStack2)) {
        return itemStack2;
      }
    }
    return player.getAbilities().creativeMode
        ? new ItemStack(ItemsInit.STONE_ROCK)
        : ItemStack.EMPTY;
  }

  @Override
  public Predicate<ItemStack> getProjectiles() {
    return ModRangedWeaponItem.SLINGSHOT_PROJECTILES;
  }

  @Override
  public int getRange() {
    return 15;
  }
}
