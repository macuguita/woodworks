package com.macuguita.woodworks.block;

import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.reg.GWBlockTags;

import com.macuguita.woodworks.reg.GWItemTags;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public abstract class NoCornerModularSeatBlock extends HorizontalFacingBlock implements Waterloggable, SittableBlock {

	public static final EnumProperty<NoCornerModularSeatProperty> SHAPE = EnumProperty.of("shape", NoCornerModularSeatProperty.class);
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	protected NoCornerModularSeatBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
				.with(SHAPE, NoCornerModularSeatProperty.SINGLE)
				.with(FACING, Direction.NORTH)
				.with(WATERLOGGED, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(SHAPE, FACING, WATERLOGGED);
	}

	@Override
	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		Direction direction = state.get(FACING);
		NoCornerModularSeatProperty couchShape = state.get(SHAPE);
		switch (mirror) {
			case LEFT_RIGHT -> {
				if (direction.getAxis() == Direction.Axis.Z) {
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, couchShape);
				}
			}
			case FRONT_BACK -> {
				if (direction.getAxis() == Direction.Axis.X) {
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, couchShape);
				}
			}
		}
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		Hand hand = player.getActiveHand();
		ItemStack stack = player.getStackInHand(hand);
		if (stack.isIn(GWItemTags.WATER_BUCKETS)) return ActionResult.FAIL;
		if (stack.isIn(GWItemTags.CONNECTING)) return ActionResult.FAIL;
		return this.sitOn(world, pos, player, state.get(FACING)) ? ActionResult.SUCCESS : ActionResult.FAIL;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return direction.getAxis().isHorizontal() ? state.with(SHAPE, getShape(state, world, pos)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos pos = ctx.getBlockPos();
		Direction dir = ctx.getHorizontalPlayerFacing().getOpposite();
		BlockState state = this.getDefaultState()
				.with(FACING, dir)
				.with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
		return state.with(SHAPE, getShape(state, ctx.getWorld(), pos));
	}

	public NoCornerModularSeatProperty getShape(BlockState state, WorldAccess world, BlockPos pos) {
		Direction dir = state.get(FACING);

		Direction left = dir.rotateCounterclockwise(Direction.Axis.Y);
		Direction right = dir.rotateClockwise(Direction.Axis.Y);

		boolean hasLeft = world.getBlockState(pos.offset(left)).isIn(GWBlockTags.CONNECTING) && world.getBlockState(pos.offset(left)).get(FACING) == dir;
		boolean hasRight = world.getBlockState(pos.offset(right)).isIn(GWBlockTags.CONNECTING) && world.getBlockState(pos.offset(right)).get(FACING) == dir;

		if (hasLeft && hasRight) {
			return NoCornerModularSeatProperty.MIDDLE;
		} else if (hasLeft) {
			return NoCornerModularSeatProperty.RIGHT;
		} else if (hasRight) {
			return NoCornerModularSeatProperty.LEFT;
		} else {
			return NoCornerModularSeatProperty.SINGLE;
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}
}
