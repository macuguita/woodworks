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

import java.util.HashMap;
import java.util.Map;

import com.macuguita.woodworks.reg.GWItemTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StumpSeatBlock extends Block implements SittableBlock, SimpleWaterloggedBlock {

	public static final Map<Block, Block> STRIPPED_STUMPS = new HashMap<>();
	public static final AABB SEAT = new AABB(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final VoxelShape VOXEL_SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);
	private final boolean strippable;

	public StumpSeatBlock(Properties settings) {
		this(settings, true);
	}

	public StumpSeatBlock(Properties settings, boolean strippable) {
		super(settings);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(WATERLOGGED, false));
		this.strippable = strippable;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState()
				.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER));
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		InteractionHand hand = player.getUsedItemHand();
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() instanceof AxeItem && strippable) {
			Block strippedBlock = STRIPPED_STUMPS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().instabuild) stack.hurtAndBreak(1, player, hand);
				level.playSound(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);

				if (level instanceof ServerLevel serverWorld) {
					BlockState strippedState = strippedBlock.defaultBlockState()
							.setValue(WATERLOGGED, blockState.getValue(WATERLOGGED));

					serverWorld.setBlockAndUpdate(blockPos, strippedState);
				}
				return InteractionResult.SUCCESS;
			}
		}
		if (stack.is(GWItemTags.STUMP)) return InteractionResult.FAIL;
		if (stack.is(GWItemTags.WATER_BUCKETS) || stack.is(GWItemTags.EMPTY_BUCKETS)) return InteractionResult.FAIL;
		return sitOn(level, blockPos, player, null) ? InteractionResult.SUCCESS : super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return VOXEL_SHAPE;
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public AABB getSeatSize(BlockState state) {
		return SEAT;
	}
}
