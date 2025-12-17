package com.macuguita.woodworks.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class SwitchableBlockItem<T extends Property<U>, U extends Comparable<U>> extends BlockItem {
	private final BooleanProperty switching;
	private final boolean defaultState;

	public SwitchableBlockItem(Block blockIn, Properties builder, BooleanProperty switching, boolean defaultState) {
		super(blockIn, builder);
		this.switching = switching;
		this.defaultState = defaultState;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
		if (player.isShiftKeyDown()) {
			if (!level.isClientSide()) {
				cycleValueTag(player.getItemInHand(interactionHand));
			}
			return InteractionResult.SUCCESS;
		}
		return super.use(level, player, interactionHand);
	}


	public BlockState getSwitchedState(BlockState state, ItemStack stack) {
		if (state.hasProperty(switching)) {
			state = state.setValue(switching, getValue(stack));
		}
		return state;
	}

	private boolean getValue(ItemStack stack) {
		BlockItemStateProperties properties = stack.get(DataComponents.BLOCK_STATE);
		if (properties == null) {
			return defaultState;
		}

		Boolean val = properties.get(switching);
		return val != null ? val : defaultState;
	}

	public void cycleValueTag(ItemStack stack) {
		BlockItemStateProperties properties = stack.get(DataComponents.BLOCK_STATE);
		if (properties == null) {
			properties = BlockItemStateProperties.EMPTY;
		}

		Boolean val = properties.get(switching);
		boolean next = val != null ? !val : !defaultState;

		if (next == defaultState) {
			stack.remove(DataComponents.BLOCK_STATE);
		} else {
			stack.set(DataComponents.BLOCK_STATE, properties.with(switching, next));
		}
	}

	public static boolean getValueForStack(ItemStack stack) {
		Item item = stack.getItem();
		return item instanceof SwitchableBlockItem && ((SwitchableBlockItem<?, ?>) item).getValue(stack);
	}
}
