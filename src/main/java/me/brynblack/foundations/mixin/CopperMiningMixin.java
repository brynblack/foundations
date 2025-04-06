package me.brynblack.foundations.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class CopperMiningMixin extends LivingEntity {

  @Shadow @Final private PlayerInventory inventory;

  @Inject(
      method = "canHarvest(Lnet/minecraft/block/BlockState;)Z",
      at = @At("HEAD"),
      cancellable = true)
  private void canHarvest(BlockState state, CallbackInfoReturnable<Boolean> cir) {
    Item heldItem = this.inventory.getMainHandStack().getItem();

    if (heldItem instanceof PickaxeItem) {
      int miningLevel = ((PickaxeItem) heldItem).getMaterial().getMiningLevel();
      String blockName = Registries.BLOCK.getId(state.getBlock()).toString();
      if (miningLevel == 0 && blockName.equals("minecraft:copper_ore")) cir.setReturnValue(true);
    }
  }

  protected CopperMiningMixin(EntityType<? extends LivingEntity> entityType, World world) {
    super(entityType, world);
  }
}
