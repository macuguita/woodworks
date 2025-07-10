/*
 * Copyright (c) 2025 macuguita.
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.macuguita.woodworks.block;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import com.macuguita.woodworks.reg.GWBlockTags;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ResizableBeamBlock extends Block implements Waterloggable {
	//TODO: when finished remove all debug assets for this block
	//TODO: add datagen
	//TODO: make textures and models

	public static final Map<Block, Block> STRIPPED_BEAM_BLOCKS = new HashMap<>();

	private static final Direction[] FACINGS = Direction.values();

	public static final BooleanProperty NORTH = Properties.NORTH;
	public static final BooleanProperty EAST = Properties.EAST;
	public static final BooleanProperty SOUTH = Properties.SOUTH;
	public static final BooleanProperty WEST = Properties.WEST;
	public static final BooleanProperty UP = Properties.UP;
	public static final BooleanProperty DOWN = Properties.DOWN;

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

	public static final IntProperty RADIUS = IntProperty.of("radius", 1, 7);
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	private final VoxelShape[][] radiusToFacingsShape;

	public ResizableBeamBlock(Settings settings) {
		super(settings);
		this.radiusToFacingsShape = this.generateRadiusToFacingsShapeMap();
		this.setDefaultState(this.getStateManager().getDefaultState()
				.with(RADIUS, 1)
				.with(NORTH, false)
				.with(EAST, false)
				.with(SOUTH, false)
				.with(WEST, false)
				.with(UP, false)
				.with(DOWN, false)
				.with(WATERLOGGED, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(RADIUS, NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction side = ctx.getSide();
		PlayerEntity player = ctx.getPlayer();

		BlockState state = this.getDefaultState()
				.with(FACING_PROPERTIES.get(side.getOpposite()), true)
				.with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));

		if (player != null) state.with(FACING_PROPERTIES.get(side), player.isSneaking());

		return shouldConnectWithNeighbors(state, ctx.getBlockPos(), ctx.getWorld());
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return shouldConnectWithNeighbor(super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos), neighborState, direction);
	}

	private BlockState shouldConnectWithNeighbor(BlockState state, BlockState neighborState, Direction dir) {
		if (!neighborState.isIn(GWBlockTags.BEAM) || !(neighborState.getBlock() instanceof ResizableBeamBlock)) return state;

		if (neighborState.get(FACING_PROPERTIES.get(dir.getOpposite()))) return state.with(FACING_PROPERTIES.get(dir), true);
		return state;
	}

	private BlockState shouldConnectWithNeighbors(BlockState state, BlockPos pos, World world) {
		BlockState temp = state;
		for (Direction direction: Direction.values()) temp = shouldConnectWithNeighbor(temp, world.getBlockState(pos.offset(direction)), direction);
		return temp;
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
		VoxelShape centerShape = Block.createCuboidShape(f * 16.0f, f * 16.0f, f * 16.0f, g * 16.0f, g * 16.0f, g * 16.0f);
		VoxelShape[] armShapes = new VoxelShape[FACINGS.length];

		for (int i = 0; i < FACINGS.length; i++) {
			Direction direction = FACINGS[i];
			armShapes[i] = VoxelShapes.cuboid(
					0.5 + Math.min(-radius, direction.getOffsetX() * 0.5),
					0.5 + Math.min(-radius, direction.getOffsetY() * 0.5),
					0.5 + Math.min(-radius, direction.getOffsetZ() * 0.5),
					0.5 + Math.max(radius, direction.getOffsetX() * 0.5),
					0.5 + Math.max(radius, direction.getOffsetY() * 0.5),
					0.5 + Math.max(radius, direction.getOffsetZ() * 0.5)
			);
		}

		VoxelShape[] facingShapes = new VoxelShape[64];

		for (int mask = 0; mask < 64; mask++) {
			VoxelShape shape = centerShape;

			for (int k = 0; k < FACINGS.length; k++) {
				if ((mask & 1 << k) != 0) {
					shape = VoxelShapes.union(shape, armShapes[k]);
				}
			}

			facingShapes[mask] = shape;
		}

		return facingShapes;
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		int radius = state.get(RADIUS);
		int mask = this.getConnectionMask(state);
		return this.radiusToFacingsShape[radius][mask];
	}

	protected int getConnectionMask(BlockState state) {
		int mask = 0;

		for (int i = 0; i < FACINGS.length; i++) {
			if (state.get(FACING_PROPERTIES.get(FACINGS[i]))) {
				mask |= 1 << i;
			}
		}

		return mask;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}
}
