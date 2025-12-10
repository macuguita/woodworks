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
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.macuguita.woodworks.reg.GWBlockTags;
import com.macuguita.woodworks.reg.GWItemTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ResizableBeamBlock extends Block implements SimpleWaterloggedBlock {

	public static final Map<Block, Block> STRIPPED_BEAM_BLOCKS = new HashMap<>();

	private static final Direction[] FACINGS = Direction.values();

	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

	public static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ImmutableMap.copyOf(
			Util.make(Maps.newEnumMap(Direction.class), directions -> {
				directions.put(Direction.NORTH, NORTH);
				directions.put(Direction.EAST, EAST);
				directions.put(Direction.SOUTH, SOUTH);
				directions.put(Direction.WEST, WEST);
				directions.put(Direction.UP, UP);
				directions.put(Direction.DOWN, DOWN);
			})
	);

	public static final IntegerProperty RADIUS = IntegerProperty.create("radius", 1, 7);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	private final VoxelShape[][] radiusToFacingsShape;

	private final boolean strippable;

	public ResizableBeamBlock(Properties settings) {
		this(settings, true);
	}

	public ResizableBeamBlock(Properties settings, boolean strippable) {
		super(settings);
		this.radiusToFacingsShape = this.generateRadiusToFacingsShapeMap();
		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(RADIUS, 4)
				.setValue(NORTH, false)
				.setValue(EAST, false)
				.setValue(SOUTH, false)
				.setValue(WEST, false)
				.setValue(UP, false)
				.setValue(DOWN, false)
				.setValue(WATERLOGGED, false));
		this.strippable = strippable;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(RADIUS, NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		BlockState rotated = state;
		for (Direction dir : Direction.values()) {
			Direction newDir = rotation.rotate(dir);
			rotated = rotated.setValue(FACING_PROPERTIES.get(newDir), state.getValue(FACING_PROPERTIES.get(dir)));
		}
		return rotated;
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		Rotation rotation = mirror.getRotation(Direction.NORTH);
		if (rotation != Rotation.NONE) {
			return this.rotate(state, rotation);
		}

		BlockState mirrored = state;
		for (Direction dir : Direction.values()) {
			Direction newDir = mirror.mirror(dir);
			mirrored = mirrored.setValue(FACING_PROPERTIES.get(newDir), state.getValue(FACING_PROPERTIES.get(dir)));
		}
		return mirrored;
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return shouldConnectWithNeighbor(super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random), neighborState, direction);
	}

	private BlockState shouldConnectWithNeighbor(BlockState state, BlockState neighborState, Direction dir) {
		if (!neighborState.is(GWBlockTags.BEAM) || !(neighborState.getBlock() instanceof ResizableBeamBlock)) {
			return state;
		}

		if (neighborState.getValue(FACING_PROPERTIES.get(dir.getOpposite()))) {
			return state.setValue(FACING_PROPERTIES.get(dir), true);
		}
		return state;
	}

	private BlockState shouldConnectWithNeighbors(BlockState state, BlockPos pos, Level world) {
		BlockState temp = state;
		for (Direction direction : Direction.values()) {
			temp = shouldConnectWithNeighbor(temp, world.getBlockState(pos.relative(direction)), direction);
		}
		return temp;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction side = ctx.getClickedFace();

		BlockState state = this.defaultBlockState()
				.setValue(FACING_PROPERTIES.get(side.getOpposite()), true)
				.setValue(FACING_PROPERTIES.get(side), ctx.getPlayer() != null && ctx.getPlayer().isShiftKeyDown())
				.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER));

		return shouldConnectWithNeighbors(state, ctx.getClickedPos(), ctx.getLevel());
	}

	private static Optional<Direction> getDirectionByVec(Vec3 hit, BlockPos pos, BlockState state) {
		int radius = state.getValue(RADIUS);
		var relativePos = hit.add(-pos.getX(), -pos.getY(), -pos.getZ()).scale(16);
		if (relativePos.x < (8.0f - radius)) return Optional.of(Direction.WEST);
		else if (relativePos.x > (8.0f + radius)) return Optional.of(Direction.EAST);
		else if (relativePos.z < (8.0f - radius)) return Optional.of(Direction.NORTH);
		else if (relativePos.z > (8.0f + radius)) return Optional.of(Direction.SOUTH);
		else if (relativePos.y < (8.0f - radius)) return Optional.of(Direction.DOWN);
		else if (relativePos.y > (8.0f + radius)) return Optional.of(Direction.UP);
		return Optional.empty();
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
		InteractionHand hand = player.getUsedItemHand();
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() instanceof AxeItem && strippable) {
			Block strippedBlock = STRIPPED_BEAM_BLOCKS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().instabuild) stack.hurtAndBreak(1, player, hand);
				world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);

				BlockState strippedState = strippedBlock.defaultBlockState()
						.setValue(RADIUS, state.getValue(RADIUS))
						.setValue(NORTH, state.getValue(NORTH))
						.setValue(EAST, state.getValue(EAST))
						.setValue(SOUTH, state.getValue(SOUTH))
						.setValue(WEST, state.getValue(WEST))
						.setValue(UP, state.getValue(UP))
						.setValue(DOWN, state.getValue(DOWN))
						.setValue(WATERLOGGED, state.getValue(WATERLOGGED));

				world.setBlockAndUpdate(pos, strippedState);
				return InteractionResult.SUCCESS;
			}
		}
		if (stack.is(GWItemTags.SHEARS)) {
			if (!player.getAbilities().instabuild) stack.hurtAndBreak(1, player, hand);
			world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0f, 1.0f);

			// If I could make it so if the player is sneaking the radius decrements that'd be so much better.
			BlockState newState = shiftRadius(state, 1);

			world.setBlockAndUpdate(pos, newState);
			return InteractionResult.SUCCESS;
		}
		if (stack.is(GWItemTags.SECATEURS)) {
			Optional<Direction> oDir = getDirectionByVec(hit.getLocation(), pos, state);
			if (oDir.isPresent()) {
				Direction dir = oDir.get();
				BlockState newState = state.setValue(FACING_PROPERTIES.get(dir), false);

				BlockPos neighborPos = pos.relative(dir);
				BlockState neighborState = world.getBlockState(neighborPos);

				world.setBlock(pos, newState, UPDATE_ALL);
				if (neighborState.getBlock() instanceof ResizableBeamBlock && neighborState.is(GWBlockTags.BEAM)) {
					BlockState newNeighborState = neighborState.setValue(FACING_PROPERTIES.get(dir.getOpposite()), false);
					world.setBlock(neighborPos, newNeighborState, UPDATE_ALL);
				}

				if (!player.getAbilities().instabuild) stack.hurtAndBreak(1, player, hand);
				world.playSound(player, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
				return InteractionResult.SUCCESS;
			}

			Direction dir = hit.getDirection();

			BlockState newState = state.setValue(FACING_PROPERTIES.get(dir), true);

			world.setBlockAndUpdate(pos, newState);

			if (!player.getAbilities().instabuild) stack.hurtAndBreak(1, player, hand);
			world.playSound(player, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
			return InteractionResult.SUCCESS;
		}
		return super.useWithoutItem(state, world, pos, player, hit);
	}

	private BlockState shiftRadius(BlockState state, int amount) {
		int radius = state.getValue(RADIUS);
		int newRadius = radius + amount;

		if (newRadius > 7) {
			newRadius -= 7;
		} else if (newRadius < 1) {
			newRadius += 7;
		}

		return state.setValue(RADIUS, newRadius);
	}

	private VoxelShape[][] generateRadiusToFacingsShapeMap() {
		VoxelShape[][] shapes = new VoxelShape[8][];

		for (int radius = 1; radius <= 7; radius++) {
			shapes[radius] = this.generateFacingsToShapeMap(radius / 16.0f);
		}

		return shapes;
	}

	private VoxelShape[] generateFacingsToShapeMap(float radius) {
		float f = 0.5f - radius;
		float g = 0.5f + radius;
		VoxelShape centerShape = Block.box(f * 16.0f, f * 16.0f, f * 16.0f, g * 16.0f, g * 16.0f, g * 16.0f);
		VoxelShape[] armShapes = new VoxelShape[FACINGS.length];

		for (int i = 0; i < FACINGS.length; ++i) {
			Direction direction = FACINGS[i];
			armShapes[i] = Shapes.box(
					0.5 + Math.min(-radius, direction.getStepX() * 0.5),
					0.5 + Math.min(-radius, direction.getStepY() * 0.5),
					0.5 + Math.min(-radius, direction.getStepZ() * 0.5),
					0.5 + Math.max(radius, direction.getStepX() * 0.5),
					0.5 + Math.max(radius, direction.getStepY() * 0.5),
					0.5 + Math.max(radius, direction.getStepZ() * 0.5)
			);
		}

		VoxelShape[] facingShapes = new VoxelShape[64];

		for (int mask = 0; mask < 64; mask++) {
			VoxelShape shape = centerShape;

			for (int k = 0; k < FACINGS.length; k++) {
				if ((mask & 1 << k) != 0) {
					shape = Shapes.or(shape, armShapes[k]);
				}
			}

			facingShapes[mask] = shape;
		}

		return facingShapes;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		int radius = state.getValue(RADIUS);
		int mask = this.getConnectionMask(state);
		return this.radiusToFacingsShape[radius][mask];
	}

	protected int getConnectionMask(BlockState state) {
		int mask = 0;

		/*
		 * representation of the int mask where 1 would be connected
		 * 0 0 0 0 0 0
		 * ^ ^ ^ ^ ^ ^
		 * N E S W U D
		 */
		for (int i = 0; i < FACINGS.length; ++i) {
			if (state.getValue(FACING_PROPERTIES.get(FACINGS[i]))) {
				mask |= 1 << i;
			}
		}

		return mask;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}
