package com.macuguita.woodworks.block;

import java.util.HashMap;
import java.util.Map;

import com.macuguita.woodworks.reg.GWItemTags;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
		super.createBlockStateDefinition(builder);
		builder.add(WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState()
				.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER))
				.setValue(AXIS, ctx.getClickedFace().getAxis());
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		Item item = itemStack.getItem();
		if (itemStack.getItem() instanceof AxeItem && strippable) {
			Block strippedBlock = STRIPPED_HOLLOW_LOGS.get(this);
			if (strippedBlock != null) {
				if (!player.getAbilities().instabuild)
					itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(interactionHand));
				if (!level.isClientSide()) player.awardStat(Stats.ITEM_USED.get(item));
				level.playSound(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);

				if (level instanceof ServerLevel serverWorld) {
					BlockState strippedState = strippedBlock.defaultBlockState()
							.setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS))
							.setValue(WATERLOGGED, blockState.getValue(WATERLOGGED));

					serverWorld.setBlockAndUpdate(blockPos, strippedState);
					level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
				}
				return ItemInteractionResult.SUCCESS;
			}
		}
		if (itemStack.is(GWItemTags.WATER_BUCKETS) || itemStack.is(GWItemTags.EMPTY_BUCKETS))
			return ItemInteractionResult.FAIL;
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
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}
