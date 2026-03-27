/*
 * Copyright (c) 2026 macuguita
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

package com.macuguita.woodworks.common.item;

import java.util.function.Consumer;

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

import com.macuguita.woodworks.GuitaWoodworks;

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
