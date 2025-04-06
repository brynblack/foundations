package me.brynblack.foundations.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import net.minecraft.world.tick.ScheduledTickView;

public class RockBlock extends Block implements Waterloggable {

  protected static final VoxelShape SHAPE = Block.createCuboidShape(6D, 0D, 6D, 10D, 1D, 10D);
  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

  public RockBlock() {
    super(
        AbstractBlock.Settings.create()
            .strength(0.15F, 0.15F)
            .sounds(BlockSoundGroup.STONE)
            .noCollision()
            .noCollision()
            .mapColor(MapColor.LIGHT_GRAY)
            .offset(AbstractBlock.OffsetType.XZ));
    this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return this.getDefaultState()
        .with(
            WATERLOGGED,
            ctx.getWorld().getBlockState(ctx.getBlockPos()).getBlock() == Blocks.WATER);
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

    if (!player.isSneaking()) {
      world.breakBlock(pos, !player.isCreative());
      return ActionResult.SUCCESS;
    }
    return ActionResult.PASS;
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    BlockState stateUnder = world.getBlockState(pos.down());
    return stateUnder.isSideSolidFullSquare(world, pos.down(), Direction.UP);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(WATERLOGGED);
  }

  public FluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
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
    if (!this.canPlaceAt(state, world, pos))
      world.breakBlock(pos, true);
    else if (state.get(WATERLOGGED))
      world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
  }

  public BlockState asWaterlogged() {
    return this.getDefaultState().with(WATERLOGGED, true);
  }

  @Override
  public boolean tryFillWithFluid(
      WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
    return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
  }

  @Override
  public boolean canFillWithFluid(PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
    return Waterloggable.super.canFillWithFluid(player, world, pos, state, fluid);
  }

  @Override
  public BlockState getStateForNeighborUpdate(
      BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction,
      BlockPos neighborPos, BlockState neighborState, Random random) {
    if (state.get(WATERLOGGED)) {
      world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }
    return super.getStateForNeighborUpdate(neighborState, world, tickView, neighborPos, direction, neighborPos, neighborState, random);
  }

  @Override
  public boolean canPathfindThrough(
      BlockState state, BlockView world, BlockPos pos, NavigationType type) {
    if (type == NavigationType.WATER) {
      return world.getFluidState(pos).isIn(FluidTags.WATER);
    }
    return false;
  }

  @Override
  public boolean canMobSpawnInside(BlockState state) {
    return true;
  }
}
