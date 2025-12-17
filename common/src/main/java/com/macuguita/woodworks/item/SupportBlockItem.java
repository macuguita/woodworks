package com.macuguita.woodworks.item;

import java.util.function.Consumer;

import com.macuguita.woodworks.GuitaWoodworks;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("rawtypes")
public class SupportBlockItem extends SwitchableBlockItem {
	public static final Identifier OVERRIDE_TAG = GuitaWoodworks.id("up");

	public SupportBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder, BlockStateProperties.UP, true);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
		if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), InputConstants.KEY_LSHIFT)) {
			consumer.accept(Component.translatable("tooltip.gwoodworks.support.condition1").withStyle(ChatFormatting.GRAY));
			consumer.accept(Component.translatable("tooltip.gwoodworks.support.behavior1").withStyle(ChatFormatting.DARK_AQUA));
			consumer.accept(Component.literal(""));
			consumer.accept(Component.translatable("tooltip.gwoodworks.support.condition2").withStyle(ChatFormatting.GRAY));
			consumer.accept(Component.translatable("tooltip.gwoodworks.support.behavior2").withStyle(ChatFormatting.DARK_AQUA));
			consumer.accept(Component.literal(""));
			consumer.accept(Component.translatable("tooltip.gwoodworks.support.condition3").withStyle(ChatFormatting.GRAY));
			consumer.accept(Component.translatable("tooltip.gwoodworks.support.behavior3").withStyle(ChatFormatting.DARK_AQUA));
		}
		super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
	}
}
