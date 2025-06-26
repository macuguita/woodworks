package com.macuguita.woodworks.block;

import java.util.HashMap;
import java.util.Map;

import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.reg.GWItemTags;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CarvedLogSeatBlock extends NoCornerModularSeatBlock implements SittableBlock {

	public static final Map<Block, Block> STRIPPED_CARVED_LOGS = new HashMap<>();
	public static final Box SEAT = new Box(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	protected static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);

	public static final MapCodec<CarvedLogSeatBlock> CODEC = createCodec(CarvedLogSeatBlock::new);

	public CarvedLogSeatBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		Hand hand = player.getActiveHand();
		ItemStack stack = player.getStackInHand(hand);
		if (stack.isIn(GWItemTags.SHEARS)) {
			Vec3d hitPos = hit.getPos().subtract(Vec3d.of(pos));
			Direction facing = state.get(FACING);
			BlockPos neighborPos = null;
			boolean success = false;

			if (facing != null) {
				if (facing.getAxis() == Direction.Axis.Z) {
					neighborPos = pos.offset(hitPos.x < 0.5 ? Direction.WEST : Direction.EAST);
				} else if (facing.getAxis() == Direction.Axis.X) {
					neighborPos = pos.offset(hitPos.z < 0.5 ? Direction.NORTH : Direction.SOUTH);
				}

				if (neighborPos != null) {
					BlockState neighborState = world.getBlockState(neighborPos);

					if (neighborState.getBlock() instanceof NoCornerModularSeatBlock) {
						Direction neighborFacing = neighborState.get(FACING);
						if (neighborFacing != null) {
							if (facing == neighborFacing) {
								boolean originIsRightOfNeighbor;
								if (facing.getAxis() == Direction.Axis.X) {
									originIsRightOfNeighbor = pos.getZ() > neighborPos.getZ();
								} else {
									originIsRightOfNeighbor = pos.getX() > neighborPos.getX();
								}

								boolean isPair = (originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT)
										|| (!originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT);
								boolean isMiddle = state.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE;
								boolean isConnected = (originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE)
										|| (!originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT)
										|| (originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT)
										|| (!originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE);
								boolean isSeparated = (originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT)
										|| (!originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT)
										|| (originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.SINGLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT)
										|| (!originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.SINGLE)
										|| (originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.SINGLE)
										|| (!originIsRightOfNeighbor && state.get(SHAPE) == NoCornerModularSeatProperty.SINGLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT);

								if (!world.isClient) {
									if (isPair) {
										world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.SINGLE), Block.NOTIFY_ALL);
										world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.SINGLE), Block.NOTIFY_ALL);
									}

									if (isMiddle) {
										if (originIsRightOfNeighbor) {
											world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.LEFT), Block.NOTIFY_ALL);
											world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.RIGHT), Block.NOTIFY_ALL);
										} else {
											world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.RIGHT), Block.NOTIFY_ALL);
											world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.LEFT), Block.NOTIFY_ALL);
										}
									}

									if (isConnected) {
										if (originIsRightOfNeighbor) {
											if (state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.SINGLE), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.RIGHT), Block.NOTIFY_ALL);
											}
											if (state.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.LEFT), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.SINGLE), Block.NOTIFY_ALL);
											}
										} else {
											if (state.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.RIGHT), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.SINGLE), Block.NOTIFY_ALL);
											}
											if (state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.MIDDLE) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.SINGLE), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.LEFT), Block.NOTIFY_ALL);
											}
										}
									}

									if (isSeparated) {
										if (originIsRightOfNeighbor) {
											if (state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
											}
											if (state.get(SHAPE) == NoCornerModularSeatProperty.SINGLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.RIGHT) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.RIGHT), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
											}
											if (state.get(SHAPE) == NoCornerModularSeatProperty.LEFT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.SINGLE) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.LEFT), Block.NOTIFY_ALL);
											}
										} else {
											if (state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
											}
											if (state.get(SHAPE) == NoCornerModularSeatProperty.RIGHT && neighborState.get(SHAPE) == NoCornerModularSeatProperty.SINGLE) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.RIGHT), Block.NOTIFY_ALL);
											}
											if (state.get(SHAPE) == NoCornerModularSeatProperty.SINGLE && neighborState.get(SHAPE) == NoCornerModularSeatProperty.LEFT) {
												world.setBlockState(pos, state.with(SHAPE, NoCornerModularSeatProperty.LEFT), Block.NOTIFY_ALL);
												world.setBlockState(neighborPos, neighborState.with(SHAPE, NoCornerModularSeatProperty.MIDDLE), Block.NOTIFY_ALL);
											}
										}
									}
								}
								success = isConnected || isMiddle || isPair || isSeparated;
							}
						}
					}
				}
			}
			if (success) {
				world.playSound(player, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!player.getAbilities().creativeMode) stack.damage(1, player, LivingEntity.getSlotForHand(hand));
				return ActionResult.SUCCESS;
			}
		}
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

	private double distanceSqFromOrigin(BlockPos p) {
		return p.getX() * p.getX() + p.getY() * p.getY() + p.getZ() * p.getZ();
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VOXEL_SHAPE;
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
