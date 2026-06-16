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

package com.macuguita.woodworks.fabric.datagen;

//? fabric {
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import com.macuguita.woodworks.common.reg.GWBlocks;
import com.macuguita.woodworks.common.reg.GWItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class GWLangProvider extends FabricLanguageProvider {

	public GWLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider wrapperLookup, TranslationBuilder translationBuilder) {
		generateItemTranslations(translationBuilder, GWItems.SECATEURS.get());
		GWBlocks.STUMP_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.BEAM_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		GWBlocks.SHUTTER_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
			generateItemTranslations(translationBuilder, regEntry.get().asItem());
		});
		generateBlockTranslations(translationBuilder, GWBlocks.MUSHROOM_STUMP.get());
		generateItemTranslations(translationBuilder, GWBlocks.MUSHROOM_STUMP.get().asItem());
		generateBlockTranslations(translationBuilder, GWBlocks.CARVED_MUSHROOM_STEM.get());
		generateItemTranslations(translationBuilder, GWBlocks.CARVED_MUSHROOM_STEM.get().asItem());
		generateBlockTranslations(translationBuilder, GWBlocks.MUSHROOM_BEAM.get());
		generateItemTranslations(translationBuilder, GWBlocks.MUSHROOM_BEAM.get().asItem());
		generateBlockTranslations(translationBuilder, GWBlocks.HOLLOW_MUSHROOM_STEM.get());
		generateItemTranslations(translationBuilder, GWBlocks.HOLLOW_MUSHROOM_STEM.get().asItem());

		translationBuilder.add("block_type.gwoodworks.stump", "%s Stump");
		translationBuilder.add("block_type.gwoodworks.stripped_stump", "Stripped %s Stump");
		translationBuilder.add("block_type.gwoodworks.carved_log", "Carved %s Log");
		translationBuilder.add("block_type.gwoodworks.stripped_carved_log", "Stripped Carved %s Log");
		translationBuilder.add("block_type.gwoodworks.beam", "%s Beam");
		translationBuilder.add("block_type.gwoodworks.stripped_beam", "Stripped %s Beam");
		translationBuilder.add("block_type.gwoodworks.hollow_log", "Hollow %s Log");
		translationBuilder.add("block_type.gwoodworks.stripped_hollow_log", "Stripped Hollow %s Log");
		translationBuilder.add("block_type.gwoodworks.support", "%s Support");
		translationBuilder.add("block_type.gwoodworks.shutter", "%s Shutter");
		translationBuilder.add("itemGroup.gwoodworks.gwoodworks", "guita's Woodworks");
		translationBuilder.add("tag.item.gwoodworks.stump", "Stump");
		translationBuilder.add("tag.block.gwoodworks.stump", "Stump");
		translationBuilder.add("tag.item.gwoodworks.carved_log", "Carved Log");
		translationBuilder.add("tag.block.gwoodworks.carved_log", "Carved Log");
		translationBuilder.add("tag.item.gwoodworks.beam", "Beam");
		translationBuilder.add("tag.block.gwoodworks.beam", "Beam");
		translationBuilder.add("tag.item.gwoodworks.hollow_log", "Hollow Log");
		translationBuilder.add("tag.block.gwoodworks.hollow_log", "Hollow Log");
		translationBuilder.add("tag.item.gwoodworks.secateurs", "Secateurs");
		translationBuilder.add("tooltip.gwoodworks.beam_block", "Strip with axe, resize with shears, link with secateurs.");
		translationBuilder.add("tooltip.gwoodworks.support.condition1", "Sneak right-click in air:");
		translationBuilder.add("tooltip.gwoodworks.support.behavior1", "Flip block item upside down");
		translationBuilder.add("tooltip.gwoodworks.support.condition2", "Right-click with an axe:");
		translationBuilder.add("tooltip.gwoodworks.support.behavior2", "Toggle a side between big and small");
		translationBuilder.add("tooltip.gwoodworks.support.condition3", "Sneak right-click with an axe:");
		translationBuilder.add("tooltip.gwoodworks.support.behavior3", "Hide/unhide a side");
		translationBuilder.add("tooltip.gwoodworks.beam.condition1", "Right-click with an axe:");
		translationBuilder.add("tooltip.gwoodworks.beam.behavior1", "Strip the block");
		translationBuilder.add("tooltip.gwoodworks.beam.condition2", "Right-click with shears:");
		translationBuilder.add("tooltip.gwoodworks.beam.behavior2", "Increase/decrease the radius");
		translationBuilder.add("tooltip.gwoodworks.beam.condition3", "Right-click with secateurs:");
		translationBuilder.add("tooltip.gwoodworks.beam.behavior3", "Hide/unhide a connection");
		translationBuilder.add("tooltip.gwoodworks.beam.condition4", "Place while sneaking:");
		translationBuilder.add("tooltip.gwoodworks.beam.behavior4", "On placed connect to opposite side");
	}

	private String capitalizeString(String string) {
		char[] chars = string.toLowerCase(Locale.getDefault()).toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; ++i) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
				found = false;
			}
		}
		return new String(chars);
	}

	private void generateBlockTranslations(TranslationBuilder translationBuilder, Block block) {
		String temp = capitalizeString(BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_", " "));
		translationBuilder.add(block, temp);
	}

	private void generateItemTranslations(TranslationBuilder translationBuilder, Item item) {
		String temp = capitalizeString(BuiltInRegistries.ITEM.getKey(item).getPath().replace("_", " "));
		translationBuilder.add(item, temp);
	}
}
//?}
