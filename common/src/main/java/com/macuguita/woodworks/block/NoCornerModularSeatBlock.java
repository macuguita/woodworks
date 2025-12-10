/*
 * Copyright (c) 2025 macuguita
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.macuguita.woodworks.block;

import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.reg.GWBlockTags;
import com.macuguita.woodworks.reg.GWItemTags;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

public abstract class NoCornerModularSeatBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, SittableBlock {

	public static final EnumProperty<NoCornerModularSeatProperty> SHAPE = EnumProperty.create("shape", NoCornerModularSeatProperty.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected NoCornerModularSeatBlock(Properties settings) {
		super(settings);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(SHAPE, NoCornerModularSeatProperty.SINGLE)
				.setValue(FACING, Direction.NORTH)
				.setValue(WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(SHAPE, FACING, WATERLOGGED);
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		Direction direction = state.getValue(FACING);
		NoCornerModularSeatProperty couchShape = state.getValue(SHAPE);
		switch (mirror) {
			case LEFT_RIGHT -> {
				if (direction.getAxis() == Direction.Axis.Z) {
					return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, couchShape);
				}
			}
			case FRONT_BACK -> {
				if (direction.getAxis() == Direction.Axis.X) {
					return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, couchShape);
				}
			}
		}
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
		InteractionHand hand = player.getUsedItemHand();
		ItemStack stack = player.getItemInHand(hand);
		if (stack.is(GWItemTags.WATER_BUCKETS)) return InteractionResult.FAIL;
		return this.sitOn(world, pos, player, state.getValue(FACING)) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return direction.getAxis().isHorizontal() ? state.setValue(SHAPE, getShape(state, world, pos)) : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockPos pos = ctx.getClickedPos();
		Direction dir = ctx.getHorizontalDirection().getOpposite();
		BlockState state = this.defaultBlockState()
				.setValue(FACING, dir)
				.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER));
		return state.setValue(SHAPE, getShape(state, ctx.getLevel(), pos));
	}

	private NoCornerModularSeatProperty getShape(BlockState state, LevelReader world, BlockPos pos) {
		Direction dir = state.getValue(FACING);

		Direction left = dir.getCounterClockWise(Direction.Axis.Y);
		Direction right = dir.getClockWise(Direction.Axis.Y);

		boolean hasLeft = world.getBlockState(pos.relative(left)).is(GWBlockTags.CARVED_LOG) && world.getBlockState(pos.relative(left)).getValue(FACING) == dir;
		boolean hasRight = world.getBlockState(pos.relative(right)).is(GWBlockTags.CARVED_LOG) && world.getBlockState(pos.relative(right)).getValue(FACING) == dir;

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
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}
}
