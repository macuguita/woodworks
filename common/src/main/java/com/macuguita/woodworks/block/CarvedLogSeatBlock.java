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
import com.macuguita.woodworks.utils.GWUtils;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CarvedLogSeatBlock extends NoCornerModularSeatBlock implements SittableBlock {

	public static final Map<Block, Block> STRIPPED_CARVED_LOGS = new HashMap<>();
	public static final AABB SEAT = new AABB(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	public static final MapCodec<CarvedLogSeatBlock> CODEC = simpleCodec(CarvedLogSeatBlock::new);
	protected static final VoxelShape VOXEL_SHAPE = Shapes.join(
			Shapes.block(),
			Shapes.or(
					box(2.0, 8.0, 0.0, 14.0, 16.0, 11.0)
			),
			BooleanOp.ONLY_FIRST
	);
	private final boolean strippable;

	public CarvedLogSeatBlock(Properties settings) {
		this(settings, true);
	}

	public CarvedLogSeatBlock(Properties settings, boolean strippable) {
		super(settings);
		this.strippable = strippable;
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		Item item = itemStack.getItem();
		if (itemStack.getItem() instanceof AxeItem && strippable) {
			Block strippedBlock = STRIPPED_CARVED_LOGS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().instabuild)
					itemStack.hurtAndBreak(1, player, interactionHand);
				if (!level.isClientSide()) player.awardStat(Stats.ITEM_USED.get(item));
				level.playSound(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);

				if (level instanceof ServerLevel serverWorld) {
					BlockState strippedState = strippedBlock.defaultBlockState()
							.setValue(SHAPE, blockState.getValue(SHAPE))
							.setValue(FACING, blockState.getValue(FACING))
							.setValue(WATERLOGGED, blockState.getValue(WATERLOGGED));

					serverWorld.setBlockAndUpdate(blockPos, strippedState);
					level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
				}
				return InteractionResult.SUCCESS;
			}
		}
		if (itemStack.is(GWItemTags.WATER_BUCKETS) || itemStack.is(GWItemTags.EMPTY_BUCKETS))
			return InteractionResult.FAIL;
		return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		VoxelShape shape = switch (state.getValue(SHAPE)) {
			case SINGLE -> VOXEL_SHAPE;
			case LEFT -> Shapes.join(
					VOXEL_SHAPE,
					Shapes.or(
							box(14.0, 8.0, 0.0, 16.0, 16.0, 11.0)

					),
					BooleanOp.ONLY_FIRST
			);
			case MIDDLE -> Shapes.join(
					VOXEL_SHAPE,
					Shapes.or(
							box(14.0, 8.0, 0.0, 16.0, 16.0, 11.0),
							box(0.0, 8.0, 0.0, 2.0, 16.0, 11.0)
					),
					BooleanOp.ONLY_FIRST
			);
			case RIGHT -> Shapes.join(
					VOXEL_SHAPE,
					Shapes.or(
							box(0.0, 8.0, 0.0, 2.0, 16.0, 11.0)
					),
					BooleanOp.ONLY_FIRST
			);
		};
		return switch (state.getValue(FACING)) {
			case DOWN, UP -> Shapes.empty();
			case NORTH -> shape;
			case SOUTH -> GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, 180);
			case WEST -> GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, 270);
			case EAST -> GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, 90);
		};
	}

	@Override
	public AABB getSeatSize(BlockState state) {
		return SEAT;
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
