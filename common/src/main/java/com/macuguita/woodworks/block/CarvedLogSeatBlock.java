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

import com.macuguita.woodworks.reg.GWItemTags;
import com.macuguita.woodworks.utils.GWUtils;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CarvedLogSeatBlock extends NoCornerModularSeatBlock implements SittableBlock {

	public static final Map<Block, Block> STRIPPED_CARVED_LOGS = new HashMap<>();
	public static final Box SEAT = new Box(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	public static final MapCodec<CarvedLogSeatBlock> CODEC = createCodec(CarvedLogSeatBlock::new);
	protected static final VoxelShape VOXEL_SHAPE = VoxelShapes.combineAndSimplify(
			VoxelShapes.fullCube(),
			VoxelShapes.union(
					createCuboidShape(2.0, 8.0, 0.0, 14.0, 16.0, 11.0)
			),
			BooleanBiFunction.ONLY_FIRST
	);

	public CarvedLogSeatBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		Hand hand = player.getActiveHand();
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() instanceof AxeItem) {
			Block strippedBlock = STRIPPED_CARVED_LOGS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().creativeMode) stack.damage(1, player, LivingEntity.getSlotForHand(hand));
				world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);

				if (world instanceof ServerWorld serverWorld) {
					BlockState strippedState = strippedBlock.getDefaultState()
							.with(SHAPE, state.get(SHAPE))
							.with(FACING, state.get(FACING))
							.with(WATERLOGGED, state.get(WATERLOGGED));

					serverWorld.setBlockState(pos, strippedState);
				}
				return ActionResult.SUCCESS;
			}
		}
		if (stack.isIn(GWItemTags.WATER_BUCKETS) || stack.isIn(GWItemTags.EMPTY_BUCKETS)) return ActionResult.FAIL;
		return super.onUse(state, world, pos, player, hit);
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		VoxelShape shape = switch (state.get(SHAPE)) {
			case SINGLE -> VOXEL_SHAPE;
			case LEFT -> VoxelShapes.combineAndSimplify(
					VOXEL_SHAPE,
					VoxelShapes.union(
							createCuboidShape(14.0, 8.0, 0.0, 16.0, 16.0, 11.0)

					),
					BooleanBiFunction.ONLY_FIRST
			);
			case MIDDLE -> VoxelShapes.combineAndSimplify(
					VOXEL_SHAPE,
					VoxelShapes.union(
							createCuboidShape(14.0, 8.0, 0.0, 16.0, 16.0, 11.0),
							createCuboidShape(0.0, 8.0, 0.0, 2.0, 16.0, 11.0)
					),
					BooleanBiFunction.ONLY_FIRST
			);
			case RIGHT -> VoxelShapes.combineAndSimplify(
					VOXEL_SHAPE,
					VoxelShapes.union(
							createCuboidShape(0.0, 8.0, 0.0, 2.0, 16.0, 11.0)
					),
					BooleanBiFunction.ONLY_FIRST
			);
		};
		return switch (state.get(FACING)) {
			case DOWN, UP -> VoxelShapes.empty();
			case NORTH -> shape;
			case SOUTH -> GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, 180);
			case WEST -> GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, 270);
			case EAST -> GWUtils.rotateVoxelShape(shape, Direction.Axis.Y, 90);
		};
	}

	@Override
	public Box getSeatSize(BlockState state) {
		return SEAT;
	}

	@Override
	protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
		return CODEC;
	}
}
