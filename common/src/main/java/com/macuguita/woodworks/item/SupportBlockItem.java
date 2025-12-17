package com.macuguita.woodworks.item;

import java.util.List;

import com.macuguita.woodworks.GuitaWoodworks;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("rawtypes")
public class SupportBlockItem extends SwitchableBlockItem {
	public static final ResourceLocation OVERRIDE_TAG = GuitaWoodworks.id("up");

	public SupportBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder, BlockStateProperties.UP, true);
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (Screen.hasShiftDown()) {
			tooltip.add(Component.translatable("tooltip.gwoodworks.support.condition1").withStyle(ChatFormatting.GRAY));
			tooltip.add(Component.translatable("tooltip.gwoodworks.support.behavior1").withStyle(ChatFormatting.DARK_AQUA));
			tooltip.add(Component.literal(""));
			tooltip.add(Component.translatable("tooltip.gwoodworks.support.condition2").withStyle(ChatFormatting.GRAY));
			tooltip.add(Component.translatable("tooltip.gwoodworks.support.behavior2").withStyle(ChatFormatting.DARK_AQUA));
			tooltip.add(Component.literal(""));
			tooltip.add(Component.translatable("tooltip.gwoodworks.support.condition3").withStyle(ChatFormatting.GRAY));
			tooltip.add(Component.translatable("tooltip.gwoodworks.support.behavior3").withStyle(ChatFormatting.DARK_AQUA));
		}
		super.appendHoverText(stack, ctx, tooltip, tooltipFlag);
	}
}
