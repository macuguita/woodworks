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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.macuguita.woodworks.reg.GWBlockTags;
import com.macuguita.woodworks.reg.GWItemTags;

import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

@SuppressWarnings("deprecation")
public class ResizableBeamBlock extends Block implements Waterloggable {

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
				.with(RADIUS, 4)
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
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.translatable("tooltip.gwoodworks.beam_block").formatted(Formatting.DARK_GRAY));
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		BlockState rotated = state;
		for (Direction dir : Direction.values()) {
			Direction newDir = rotation.rotate(dir);
			rotated = rotated.with(FACING_PROPERTIES.get(newDir), state.get(FACING_PROPERTIES.get(dir)));
		}
		return rotated;
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		BlockRotation rotation = mirror.getRotation(Direction.NORTH);
		if (rotation != BlockRotation.NONE) {
			return this.rotate(state, rotation);
		}

		BlockState mirrored = state;
		for (Direction dir : Direction.values()) {
			Direction newDir = mirror.apply(dir);
			mirrored = mirrored.with(FACING_PROPERTIES.get(newDir), state.get(FACING_PROPERTIES.get(dir)));
		}
		return mirrored;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return shouldConnectWithNeighbor(super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos), neighborState, direction);
	}

	private BlockState shouldConnectWithNeighbor(BlockState state, BlockState neighborState, Direction dir) {
		if (!neighborState.isIn(GWBlockTags.BEAM) || !(neighborState.getBlock() instanceof ResizableBeamBlock)) {
			return state;
		}

		if (neighborState.get(FACING_PROPERTIES.get(dir.getOpposite()))) {
			return state.with(FACING_PROPERTIES.get(dir), true);
		}
		return state;
	}

	private BlockState shouldConnectWithNeighbors(BlockState state, BlockPos pos, World world) {
		BlockState temp = state;
		for (Direction direction : Direction.values()) {
			temp = shouldConnectWithNeighbor(temp, world.getBlockState(pos.offset(direction)), direction);
		}
		return temp;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction side = ctx.getSide();

		BlockState state = this.getDefaultState()
				.with(FACING_PROPERTIES.get(side.getOpposite()), true)
				.with(FACING_PROPERTIES.get(side), ctx.getPlayer() != null && ctx.getPlayer().isSneaking())
				.with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));

		return shouldConnectWithNeighbors(state, ctx.getBlockPos(), ctx.getWorld());
	}

	private static Optional<Direction> getDirectionByVec(Vec3d hit, BlockPos pos, BlockState state) {
		int radius = state.get(RADIUS);
		var relativePos = hit.add(-pos.getX(), -pos.getY(), -pos.getZ()).multiply(16);
		if (relativePos.x < (8.0f - radius)) return Optional.of(Direction.WEST);
		else if (relativePos.x > (8.0f + radius)) return Optional.of(Direction.EAST);
		else if (relativePos.z < (8.0f - radius)) return Optional.of(Direction.NORTH);
		else if (relativePos.z > (8.0f + radius)) return Optional.of(Direction.SOUTH);
		else if (relativePos.y < (8.0f - radius)) return Optional.of(Direction.DOWN);
		else if (relativePos.y > (8.0f + radius)) return Optional.of(Direction.UP);
		return Optional.empty();
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() instanceof AxeItem) {
			Block strippedBlock = STRIPPED_BEAM_BLOCKS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().creativeMode) stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
				world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);

				BlockState strippedState = strippedBlock.getDefaultState()
						.with(RADIUS, state.get(RADIUS))
						.with(NORTH, state.get(NORTH))
						.with(EAST, state.get(EAST))
						.with(SOUTH, state.get(SOUTH))
						.with(WEST, state.get(WEST))
						.with(UP, state.get(UP))
						.with(DOWN, state.get(DOWN))
						.with(WATERLOGGED, state.get(WATERLOGGED));

				world.setBlockState(pos, strippedState);
				return ActionResult.SUCCESS;
			}
		}
		if (stack.isIn(GWItemTags.SHEARS)) {
			if (!player.getAbilities().creativeMode) stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
			world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 1.0f, 1.0f);

			// If I could make it so if the player is sneaking the radius decrements that'd be so much better.
			BlockState newState = shiftRadius(state, 1);

			world.setBlockState(pos, newState);
			return ActionResult.SUCCESS;
		}
		if (stack.isIn(GWItemTags.SECATEURS)) {
			Optional<Direction> oDir = getDirectionByVec(hit.getPos(), pos, state);
			if (oDir.isPresent()) {
				Direction dir = oDir.get();
				BlockState newState = state.with(FACING_PROPERTIES.get(dir), false);

				BlockPos neighborPos = pos.offset(dir);
				BlockState neighborState = world.getBlockState(neighborPos);

				world.setBlockState(pos, newState, NOTIFY_ALL);
				if (neighborState.getBlock() instanceof ResizableBeamBlock && neighborState.isIn(GWBlockTags.BEAM)) {
					BlockState newNeighborState = neighborState.with(FACING_PROPERTIES.get(dir.getOpposite()), false);
					world.setBlockState(neighborPos, newNeighborState, NOTIFY_ALL);
				}

				if (!player.getAbilities().creativeMode) stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
				world.playSound(player, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				return ActionResult.SUCCESS;
			}

			Direction dir = hit.getSide();

			BlockState newState = state.with(FACING_PROPERTIES.get(dir), true);

			world.setBlockState(pos, newState);

			if (!player.getAbilities().creativeMode) stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
			world.playSound(player, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return ActionResult.SUCCESS;
		}
		return super.onUse(state, world, pos, player, hand, hit);
	}

	private BlockState shiftRadius(BlockState state, int amount) {
		int radius = state.get(RADIUS);
		int newRadius = radius + amount;

		if (newRadius > 7) {
			newRadius -= 7;
		} else if (newRadius < 1) {
			newRadius += 7;
		}

		return state.with(RADIUS, newRadius);
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

		for (int i = 0; i < FACINGS.length; ++i) {
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
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		int radius = state.get(RADIUS);
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
