package me.brynblack.foundations.item;

import me.brynblack.foundations.Foundations;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireStarterItem extends Item {

  public FireStarterItem(Settings settings) {
    super(settings);
  }

  public ActionResult useOnBlock(ItemUsageContext context) {
    PlayerEntity playerEntity = context.getPlayer();
    World world = context.getWorld();
    BlockPos blockPos = context.getBlockPos();
    BlockState blockState = world.getBlockState(blockPos);

    double rand = Math.random();
    if (CampfireBlock.canBeLit(blockState)) {
      world.playSound(
          playerEntity,
          blockPos,
          SoundEvents.ITEM_FLINTANDSTEEL_USE,
          SoundCategory.BLOCKS,
          1.0F,
          world.getRandom().nextFloat() * 0.4F + 0.8F);
      if (rand <= Foundations.CONFIG.fireStartProb && !world.isClient)
        world.setBlockState(blockPos, blockState.with(Properties.LIT, true), 11);
      if (playerEntity != null) {
        context
            .getStack()
            .damage(
                1, (LivingEntity) playerEntity, EquipmentSlot.MAINHAND);
      }
      return ActionResult.SUCCESS;
    } else {
      BlockPos blockPos2 = blockPos.offset(context.getSide());
      if (AbstractFireBlock.canPlaceAt(world, blockPos2, context.getHorizontalPlayerFacing())) {
        world.playSound(
            playerEntity,
            blockPos2,
            SoundEvents.ITEM_FLINTANDSTEEL_USE,
            SoundCategory.BLOCKS,
            1.0F,
            world.getRandom().nextFloat() * 0.4F + 0.8F);
        if (rand <= Foundations.CONFIG.fireStartProb && !world.isClient) {
          BlockState blockState2 = AbstractFireBlock.getState(world, blockPos2);
          world.setBlockState(blockPos2, blockState2, 11);
        }
        ItemStack itemStack = context.getStack();
        if (playerEntity instanceof ServerPlayerEntity) {
          Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) playerEntity, blockPos2, itemStack);
          itemStack.damage(
              1, (LivingEntity) playerEntity, EquipmentSlot.MAINHAND);
        }
        return ActionResult.SUCCESS;
      } else return ActionResult.FAIL;
    }
  }
}
