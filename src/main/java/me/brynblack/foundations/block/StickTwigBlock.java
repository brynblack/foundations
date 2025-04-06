package me.brynblack.foundations.block;

import me.brynblack.foundations.init.BlocksInit;
import me.brynblack.foundations.utils.ModBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;

public class StickTwigBlock extends Block {

  protected static final VoxelShape SHAPE = Block.createCuboidShape(4D, 0.01D, 4D, 12D, 0.02D, 12D);

  public StickTwigBlock() {
    super(
        AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.WOOD)
            .strength(0.15F, 0.15F)
            .noCollision()
            .noCollision()
            .offset(AbstractBlock.OffsetType.XZ));
  }

  protected boolean canPlaceOnTop(BlockState floor, BlockView world, BlockPos pos) {
    return !(floor.isAir()
        || floor.isOf(Blocks.TALL_GRASS)
        || floor.isIn(BlockTags.SAND)
        || floor.isOf(Blocks.WATER)
        || floor.isOf(Blocks.LAVA)
        || floor.isIn(BlockTags.LEAVES)
        || floor.isIn(BlockTags.FLOWERS)
        || floor.isIn(BlockTags.FLOWER_POTS)
        || floor.isIn(ModBlockTags.ROCKS)
        || floor.isOf(BlocksInit.STICK_TWIG_BLOCK));
  }

  @Override
  public VoxelShape getOutlineShape(
      BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Vec3d vec3d = state.getModelOffset(pos);
    return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
  }

  @Override
  public ActionResult onUse(
      BlockState state,
      World world,
      BlockPos pos,
      PlayerEntity player,
      BlockHitResult hit) {
    world.breakBlock(pos, !player.isCreative());
    return ActionResult.SUCCESS;
  }

  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    BlockPos blockPos = pos.down();
    return this.canPlaceOnTop(world.getBlockState(blockPos), world, blockPos);
  }

  @Override
  public void neighborUpdate(
    BlockState state,
    World world,
    BlockPos pos,
    Block sourceBlock,
    WireOrientation wireOrientation,
    boolean notify) {
    super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
    if (!this.canPlaceAt(state, world, pos)) {
      world.breakBlock(pos, true);
    }
  }
}
