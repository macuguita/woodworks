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

import com.macuguita.woodworks.utils.GWUtils;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jspecify.annotations.Nullable;

public class ShutterBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	public static final EnumProperty<DoorHingeSide> SIDE = BlockStateProperties.DOOR_HINGE;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final VoxelShape VOXEL_SHAPE = Block.box(0.0, 0.0, 13.0, 8.0, 16.0, 16.0);
	public static final MapCodec<ShutterBlock> CODEC = simpleCodec(ShutterBlock::new);

	public ShutterBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(SIDE, DoorHingeSide.LEFT));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction facing = ctx.getHorizontalDirection().getOpposite();

		Vec3 hit = ctx.getClickLocation();
		BlockPos pos = ctx.getClickedPos();
		Vec3 local = hit.subtract(pos.getX(), pos.getY(), pos.getZ()).scale(16.0);

		DoorHingeSide side = isRightSide(local, facing) ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;

		return this.defaultBlockState().setValue(FACING, facing).setValue(SIDE, side).setValue(WATERLOGGED, ctx.getLevel().getFluidState(pos).isSource());
	}

	@Override
	protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bl) {
		boolean isPowered = level.hasNeighborSignal(blockPos);
		boolean powered = blockState.getValue(POWERED);

		if (powered != isPowered) {
			if (blockState.getValue(OPEN) != isPowered) {
				level.playSound(null, blockPos,
						isPowered ? SoundEvents.WOODEN_TRAPDOOR_OPEN : SoundEvents.WOODEN_TRAPDOOR_CLOSE,
						SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
			}
			if (!level.isClientSide()) {
				blockState = blockState.setValue(POWERED, isPowered);
				blockState = blockState.setValue(OPEN, isPowered);
				level.setBlock(blockPos, blockState, Block.UPDATE_ALL);
				level.gameEvent(null, isPowered ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
			}
		}
		super.neighborChanged(blockState, level, blockPos, block, orientation, bl);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		boolean wasOpen = state.getValue(OPEN);
		BlockState newState = state.setValue(OPEN, !wasOpen);

		level.playSound(player, pos, wasOpen ? SoundEvents.WOODEN_TRAPDOOR_CLOSE : SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		if (level.isClientSide()) return InteractionResult.SUCCESS;

		level.setBlock(pos, newState, Block.UPDATE_CLIENTS);
		level.gameEvent(player, wasOpen ? GameEvent.BLOCK_CLOSE : GameEvent.BLOCK_OPEN, pos);
		return InteractionResult.CONSUME;
	}

	private static boolean isRightSide(Vec3 local, Direction facing) {
		return switch (facing) {
			case NORTH -> local.x <= 8.0;
			case SOUTH -> local.x > 8.0;
			case WEST -> local.z >= 8.0;
			case EAST -> local.z < 8.0;
			default -> false;
		};
	}

	public static GWUtils.BlockCorner getHingeCorner(Direction facing, DoorHingeSide side) {
		return switch (facing) {
			case SOUTH -> side == DoorHingeSide.LEFT ? GWUtils.BlockCorner.NORTH_WEST : GWUtils.BlockCorner.NORTH_EAST;
			case NORTH -> side == DoorHingeSide.LEFT ? GWUtils.BlockCorner.SOUTH_EAST : GWUtils.BlockCorner.SOUTH_WEST;
			case EAST -> side == DoorHingeSide.LEFT ? GWUtils.BlockCorner.SOUTH_WEST : GWUtils.BlockCorner.NORTH_WEST;
			case WEST -> side == DoorHingeSide.LEFT ? GWUtils.BlockCorner.NORTH_EAST : GWUtils.BlockCorner.SOUTH_EAST;
			default -> GWUtils.BlockCorner.NORTH_WEST;
		};
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction facing = state.getValue(FACING);
		DoorHingeSide side = state.getValue(SIDE);
		boolean open = state.getValue(OPEN);

		VoxelShape shape = VOXEL_SHAPE;

		if (side == DoorHingeSide.LEFT) {
			shape = GWUtils.mirrorVoxelShape(shape, Direction.Axis.X);
		}
		if (open) {
			GWUtils.BlockCorner hinge = getHingeCorner(Direction.NORTH, side);
			shape = GWUtils.rotateVoxelShapeFromCornerFixed(shape, Direction.Axis.Y, 90, hinge);
		}
		shape = GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, yRotationFromNorth(facing));

		return shape;
	}

	private static int yRotationFromNorth(Direction facing) {
		return switch (facing) {
			case EAST -> 90;
			case SOUTH -> 180;
			case WEST -> 270;
			default -> 0;
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, OPEN, SIDE, POWERED);
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
