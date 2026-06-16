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
import java.util.concurrent.CompletableFuture;

import com.macuguita.lib.api.reg.GuitaRegistryEntry;

import com.macuguita.woodworks.common.reg.GWItemIds;
import com.macuguita.woodworks.common.reg.GWItems;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import com.macuguita.woodworks.common.reg.GWItemTags;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class GWItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public GWItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	//~ if >26.1 'valueLookupBuilder' -> 'tag' {
	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		tag(GWItemTags.SECATEURS)
			.add(/*?>26.1{*/GWItemIds/*?}else{*//*GWItems*//*?}*/.SECATEURS/*?<=26.1{*//*.get()*//*?}*/)
			.addOptionalTag(GWItemTags.KNIVES);

		GWItems.STUMP_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.STUMP).add(res(regEntry));
		});

		GWItems.STRIPPED_STUMP_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.STUMP).add(res(regEntry));
		});

		GWItems.CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.CARVED_LOG).add(res(regEntry));
		});

		GWItems.STRIPPED_CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.CARVED_LOG).add(res(regEntry));
		});

		GWItems.BEAM_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.BEAM).add(res(regEntry));
		});

		GWItems.STRIPPED_BEAM_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.BEAM).add(res(regEntry));
		});

		GWItems.HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.HOLLOW_LOG).add(res(regEntry));
		});

		GWItems.STRIPPED_HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.HOLLOW_LOG).add(res(regEntry));
		});

		GWItems.SUPPORT_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.SUPPORT).add(res(regEntry));
		});

		GWItems.SHUTTER_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.SHUTTER).add(res(regEntry));
		});

		tag(GWItemTags.STUMP)
			.add(res(GWItems.MUSHROOM_STUMP));

		tag(GWItemTags.CARVED_LOG)
			.add(res(GWItems.CARVED_MUSHROOM_STEM));

		tag(GWItemTags.BEAM)
			.add(res(GWItems.MUSHROOM_BEAM));

		tag(GWItemTags.HOLLOW_LOG)
			.add(res(GWItems.HOLLOW_MUSHROOM_STEM));
	}
	//~}

	//? if >26.1 {
	private static ResourceKey<Item> res(GuitaRegistryEntry<Item> entry) {
		return ResourceKey.create(BuiltInRegistries.ITEM.key(), entry.getId());
	}
	//?} else {
	/*private static Item res(GuitaRegistryEntry<Item> entry) {
		return entry.get();
	}
	*///?}
}
//?}
