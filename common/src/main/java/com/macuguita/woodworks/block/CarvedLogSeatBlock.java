package com.macuguita.woodworks.block;

import java.util.HashMap;
import java.util.Map;

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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CarvedLogSeatBlock extends NoCornerModularSeatBlock implements SittableBlock {

	public static final Map<Block, Block> STRIPPED_CARVED_LOGS = new HashMap<>();
	public static final Box SEAT = new Box(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	protected static final VoxelShape VOXEL_SHAPE = VoxelShapes.combineAndSimplify(
			VoxelShapes.fullCube(),
			VoxelShapes.union(
					createCuboidShape(2.0, 8.0, 0.0, 14.0, 16.0, 11.0)
			),
			BooleanBiFunction.ONLY_FIRST
	);

	public static final MapCodec<CarvedLogSeatBlock> CODEC = createCodec(CarvedLogSeatBlock::new);

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
		return super.onUse(state, world, pos, player, hit);
	}

	public static VoxelShape rotateVoxelShape(VoxelShape shape, int degrees) {
		int times = ((degrees % 360) + 360) % 360 / 90;

		VoxelShape result = shape;
		for (int i = 0; i < times; i++) {
			VoxelShape rotated = VoxelShapes.empty();
			for (Box box : result.getBoundingBoxes()) {
				rotated = VoxelShapes.union(rotated, VoxelShapes.cuboid(
						1 - box.maxZ, box.minY, box.minX,
						1 - box.minZ, box.maxY, box.maxX
				));
			}
			result = rotated;
		}
		return result;
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
			case SOUTH -> rotateVoxelShape(shape, 180);
			case WEST -> rotateVoxelShape(shape, 270);
			case EAST -> rotateVoxelShape(shape, 90);
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
