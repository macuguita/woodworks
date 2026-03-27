/*
 * Copyright (c) 2026 macuguita
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

package com.macuguita.woodworks.common.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.macuguita.woodworks.common.reg.GWItemTags;
import com.macuguita.woodworks.common.utils.GWUtils;

public class HollowLogBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

	public static final Map<Block, Block> STRIPPED_HOLLOW_LOGS = new HashMap<>();
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected static final VoxelShape VOXEL_SHAPE = Shapes.join(
		Shapes.block(),
		Shapes.or(
			box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
		),
		BooleanOp.ONLY_FIRST
	);
	private final boolean strippable;

	public HollowLogBlock(Properties settings) {
		this(settings, true);
	}

	public HollowLogBlock(Properties settings, boolean strippable) {
		super(settings);
		this.strippable = strippable;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(RotatedPillarBlock.AXIS, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState()
			.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER))
			.setValue(AXIS, ctx.getClickedFace().getAxis());
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		Item item = itemStack.getItem();
		if (itemStack.getItem() instanceof AxeItem && strippable) {
			Block strippedBlock = STRIPPED_HOLLOW_LOGS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().instabuild)
					itemStack.hurtAndBreak(1, player, interactionHand);
				if (!level.isClientSide()) player.awardStat(Stats.ITEM_USED.get(item));
				level.playSound(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);

				if (level instanceof ServerLevel serverWorld) {
					BlockState strippedState = strippedBlock.defaultBlockState()
						.setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS))
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
		return switch (state.getValue(RotatedPillarBlock.AXIS)) {
			case X ->
				GWUtils.rotateVoxelShape(GWUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.X, 90), Direction.Axis.Y, 90);
			case Y -> VOXEL_SHAPE;
			case Z -> GWUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.X, 90);
		};
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
}
