// Thanks to ! !#6008 from the Fabric Discord for the help!

package me.brynblack.primitiveprogression.mixin;

import me.brynblack.primitiveprogression.PrimitiveProgression;
import me.brynblack.primitiveprogression.init.BlocksInit;
import me.brynblack.primitiveprogression.utils.MiningDamageSource;
import me.brynblack.primitiveprogression.utils.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class BlockMiningMixin extends LivingEntity {

  protected BlockMiningMixin(EntityType<? extends LivingEntity> entityType, World world) {
    super(entityType, world);
  }

  @Inject(
      method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
      at = @At("RETURN"),
      cancellable = true)
  private void blockBreak(BlockState state, CallbackInfoReturnable<Float> cir) {

    PlayerEntity player = (PlayerEntity) (Object) this;
    double rand = Math.random();

    if (PrimitiveProgression.CONFIG.harderGroundBlocks) {
      if (!player.getInventory().getMainHandStack().isIn(ItemTags.SHOVELS)
          && state.isIn(ModBlockTags.SLOW_DIGGING)) cir.setReturnValue(cir.getReturnValue() / 3.8F);
    }

    if (!player.isCreative()) {
      boolean isStone =
          (state.getMaterial().equals(Material.STONE)
                  || state.getMaterial().equals(Material.REPAIR_STATION)
                  || state.getMaterial().equals(Material.METAL))
              && !state.isIn(ModBlockTags.ROCKS);
      boolean isWood =
          (state.getMaterial().equals(Material.WOOD)
                  || state.getMaterial().equals(Material.NETHER_WOOD)
                  || state.getMaterial().equals(Material.BAMBOO))
              && !state.equals(BlocksInit.STICK_TWIG_BLOCK.getDefaultState());
      boolean isBlacklisted = state.isIn(ModBlockTags.BLACKLISTED_BLOCKS);
      if (isStone)
        if (!isBlacklisted
            ? !player.getInventory().getMainHandStack().isIn(ItemTags.PICKAXES)
            : !player.getInventory().getMainHandStack().isIn(ItemTags.AXES)) {
          if (PrimitiveProgression.CONFIG.warningMessage)
            player.sendMessage(
                Text.translatable(
                    !isBlacklisted
                        ? "primitiveprogression.pick_required"
                        : "primitiveprogression.axe_required"),
                true);
          cir.setReturnValue(0.0F);
          if (player.getInventory().getMainHandStack().isEmpty()
              && rand <= PrimitiveProgression.CONFIG.damageProbability * .01)
            if (!isBlacklisted) player.damage(new MiningDamageSource.BrokenHandDamage(), 2.0F);
            else player.damage(new MiningDamageSource.SplinterDamage(), 1.0F);
        }

      if (isWood)
        if (!isBlacklisted
            ? !player.getInventory().getMainHandStack().isIn(ItemTags.AXES)
            : !player.getInventory().getMainHandStack().isIn(ItemTags.PICKAXES)) {
          if (PrimitiveProgression.CONFIG.warningMessage)
            player.sendMessage(
                Text.translatable(
                    !isBlacklisted
                        ? "primitiveprogression.axe_required"
                        : "primitiveprogression.pick_required"),
                true);
          cir.setReturnValue(0.0F);
          if (player.getInventory().getMainHandStack().isEmpty()
              && rand <= PrimitiveProgression.CONFIG.damageProbability * .01)
            if (!isBlacklisted) player.damage(new MiningDamageSource.SplinterDamage(), 1.0F);
            else player.damage(new MiningDamageSource.BrokenHandDamage(), 2.0F);
        }
    }
  }
}
