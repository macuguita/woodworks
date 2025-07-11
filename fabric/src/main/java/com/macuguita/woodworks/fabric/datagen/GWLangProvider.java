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

package com.macuguita.woodworks.fabric.datagen;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class GWLangProvider extends FabricLanguageProvider {

	public GWLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
		generateItemTranslations(translationBuilder, GWObjects.SECATEURS.get());
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			generateBlockTranslations(translationBuilder, regEntry.get());
		});

		translationBuilder.add("block_type.gwoodworks.stump", "%s Stump");
		translationBuilder.add("block_type.gwoodworks.stripped_stump", "Stripped %s Stump");
		translationBuilder.add("block_type.gwoodworks.carved_log", "Carved %s Log");
		translationBuilder.add("block_type.gwoodworks.stripped_carved_log", "Stripped Carved %s Log");
		translationBuilder.add("itemGroup.gwoodworks.gwoodworks", "guita's Woodworks");
		translationBuilder.add("tag.item.gwoodworks.stump", "Stump");
		translationBuilder.add("tag.block.gwoodworks.stump", "Stump");
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
		String temp = capitalizeString(Registries.BLOCK.getId(block).getPath().replace("_", " "));
		translationBuilder.add(block, temp);
	}

	private void generateItemTranslations(TranslationBuilder translationBuilder, Item item) {
		String temp = capitalizeString(Registries.ITEM.getId(item).getPath().replace("_", " "));
		translationBuilder.add(item, temp);
	}
}
