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

import com.google.common.collect.ImmutableMap;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.macuguita.woodworks.common.block.property.SupportFaceShapeProperty;
import com.macuguita.woodworks.common.item.SwitchableBlockItem;

public class SupportBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	private static final double BOTTOM_HEIGHT = 3D;
	private static final double TOP_HEIGHT = 13D;
	private static final double SMALL_START = 4D;
	private static final double SMALL_END = 12D;

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final EnumProperty<SupportFaceShapeProperty> HORIZONTAL_SHAPE = EnumProperty.create("horizontal", SupportFaceShapeProperty.class);
	public static final EnumProperty<SupportFaceShapeProperty> VERTICAL_SHAPE = EnumProperty.create("vertical", SupportFaceShapeProperty.class);
	private static final VoxelShape TOP_LARGE =
		Block.box(0, TOP_HEIGHT, 0, 16, 16, 16);
	private static final VoxelShape BOTTOM_LARGE =
		Block.box(0, 0, 0, 16, BOTTOM_HEIGHT, 16);

	private static final VoxelShape TOP_SMALL_NS =
		Block.box(SMALL_START, TOP_HEIGHT, 0, SMALL_END, 16, 16);
	private static final VoxelShape TOP_SMALL_EW =
		Block.box(0, TOP_HEIGHT, SMALL_START, 16, 16, SMALL_END);

	private static final VoxelShape BOTTOM_SMALL_NS =
		Block.box(SMALL_START, 0, 0, SMALL_END, BOTTOM_HEIGHT, 16);
	private static final VoxelShape BOTTOM_SMALL_EW =
		Block.box(0, 0, SMALL_START, 16, BOTTOM_HEIGHT, SMALL_END);

	private static final Map<Direction, VoxelShape> VERTICAL_LARGE = new EnumMap<>(Direction.class);
	private static final Map<Direction, VoxelShape> VERTICAL_SMALL = new EnumMap<>(Direction.class);
	public static final MapCodec<SupportBlock> CODEC = simpleCodec(SupportBlock::new);
	private final ImmutableMap<BlockState, VoxelShape> shapeCache;

	public SupportBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(
			this.stateDefinition.any()
				.setValue(WATERLOGGED, false)
				.setValue(UP, true)
				.setValue(HORIZONTAL_SHAPE, SupportFaceShapeProperty.BIG)
				.setValue(VERTICAL_SHAPE, SupportFaceShapeProperty.SMALL)
		);

		this.shapeCache = buildShapeCache(this.getStateDefinition());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return shapeCache.get(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		FluidState fluid = ctx.getLevel().getFluidState(ctx.getClickedPos());
		boolean waterlogged = fluid.is(FluidTags.WATER) && fluid.getAmount() == 8;

		BlockState state = defaultBlockState()
			.setValue(FACING, ctx.getHorizontalDirection().getOpposite())
			.setValue(WATERLOGGED, waterlogged);

		ItemStack stack = ctx.getItemInHand();
		if (stack.getItem() instanceof SwitchableBlockItem<?, ?> switchable) {
			state = switchable.getSwitchedState(state, stack);
		}

		if (!state.getValue(UP)) {
			state = state.setValue(HORIZONTAL_SHAPE, SupportFaceShapeProperty.SMALL);
		}

		return state;
	}

	public static void onSupportActivation(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, Vec3 hit) {
		boolean hitVertical = shouldHitVertical(state, hit.y() - pos.getY());
		if (!level.isClientSide()) player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));

		if (player.isShiftKeyDown()) {
			toggleHidden(player, state, level, pos, hitVertical);
		} else {
			toggleSize(player, state, level, pos, hitVertical);
		}
	}

	private static boolean shouldHitVertical(BlockState state, double hitHeight) {
		if (state.getValue(HORIZONTAL_SHAPE).isHidden()) return true;
		if (state.getValue(VERTICAL_SHAPE).isHidden()) return false;

		return state.getValue(UP)
			? hitHeight < TOP_HEIGHT / 16
			: hitHeight > BOTTOM_HEIGHT / 16;
	}

	private static void toggleHidden(Player player, BlockState state, Level level, BlockPos blockPos, boolean vertical) {
		if (vertical) {
			level.setBlockAndUpdate(
				blockPos,
				state.setValue(
					state.getValue(HORIZONTAL_SHAPE).isHidden()
						? HORIZONTAL_SHAPE
						: VERTICAL_SHAPE,
					state.getValue(HORIZONTAL_SHAPE).isHidden()
						? SupportFaceShapeProperty.BIG
						: SupportFaceShapeProperty.HIDDEN
				)
			);
			level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
		} else {
			level.setBlockAndUpdate(
				blockPos,
				state.setValue(
					state.getValue(VERTICAL_SHAPE).isHidden()
						? VERTICAL_SHAPE
						: HORIZONTAL_SHAPE,
					state.getValue(VERTICAL_SHAPE).isHidden()
						? SupportFaceShapeProperty.SMALL
						: SupportFaceShapeProperty.HIDDEN
				)
			);
			level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
		}
	}

	private static void toggleSize(Player player, BlockState state, Level level, BlockPos blockPos, boolean vertical) {
		level.setBlockAndUpdate(
			blockPos,
			state.setValue(
				vertical ? VERTICAL_SHAPE : HORIZONTAL_SHAPE,
				state.getValue(vertical ? VERTICAL_SHAPE : HORIZONTAL_SHAPE).getSwitched()
			)
		);
		level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
	}

	private static ImmutableMap<BlockState, VoxelShape> buildShapeCache(StateDefinition<Block, BlockState> def) {
		return ImmutableMap.copyOf(
			def.getPossibleStates().stream()
				.collect(Collectors.toMap(Function.identity(), SupportBlock::shapeForState))
		);
	}

	private static VoxelShape shapeForState(BlockState state) {
		VoxelShape horizontal = horizontalShape(state);
		VoxelShape vertical = verticalShape(state);

		if (horizontal.isEmpty() && vertical.isEmpty()) {
			return Shapes.block();
		}
		return Shapes.or(horizontal, vertical);
	}

	private static VoxelShape horizontalShape(BlockState state) {
		boolean up = state.getValue(UP);
		boolean ns = state.getValue(FACING).getAxis() == Direction.Axis.Z;

		return switch (state.getValue(HORIZONTAL_SHAPE)) {
			case BIG -> up ? TOP_LARGE : BOTTOM_LARGE;
			case SMALL -> up
				? (ns ? TOP_SMALL_NS : TOP_SMALL_EW)
				: (ns ? BOTTOM_SMALL_NS : BOTTOM_SMALL_EW);
			default -> Shapes.empty();
		};
	}

	private static VoxelShape verticalShape(BlockState state) {
		return switch (state.getValue(VERTICAL_SHAPE)) {
			case BIG -> VERTICAL_LARGE.get(state.getValue(FACING));
			case SMALL -> VERTICAL_SMALL.get(state.getValue(FACING));
			default -> Shapes.empty();
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, UP, HORIZONTAL_SHAPE, VERTICAL_SHAPE);
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState blockState) {
		return !blockState.getValue(WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED)
			? Fluids.WATER.getSource(false)
			: super.getFluidState(state);
	}

	@Override
	protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
		return false;
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	static {
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			int x = dir.getStepX();
			int z = dir.getStepZ();

			VERTICAL_LARGE.put(dir,
				Block.box(
					x * x * (TOP_HEIGHT / 2 - TOP_HEIGHT / 2 * x),
					0,
					z * z * (TOP_HEIGHT / 2 - TOP_HEIGHT / 2 * z),
					(1 - x * x) * 16 + x * x * ((BOTTOM_HEIGHT + 16) / 2 + (BOTTOM_HEIGHT - 16) / 2 * x),
					16,
					(1 - z * z) * 16 + z * z * ((BOTTOM_HEIGHT + 16) / 2 + (BOTTOM_HEIGHT - 16) / 2 * z)
				)
			);

			VERTICAL_SMALL.put(dir,
				Block.box(
					(1 - x * x) * SMALL_START + x * x * (TOP_HEIGHT / 2 - TOP_HEIGHT / 2 * x),
					0,
					(1 - z * z) * SMALL_START + z * z * (TOP_HEIGHT / 2 - TOP_HEIGHT / 2 * z),
					(1 - x * x) * SMALL_END + x * x * ((BOTTOM_HEIGHT + 16) / 2 + (BOTTOM_HEIGHT - 16) / 2 * x),
					16,
					(1 - z * z) * SMALL_END + z * z * ((BOTTOM_HEIGHT + 16) / 2 + (BOTTOM_HEIGHT - 16) / 2 * z)
				)
			);
		}
	}
}
