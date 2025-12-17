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

import java.util.concurrent.CompletableFuture;

import com.macuguita.woodworks.reg.GWItemTags;
import com.macuguita.woodworks.reg.GWObjects;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class GWItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public GWItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		tag(GWItemTags.SECATEURS)
				.add(getRes(GWObjects.SECATEURS.get(), wrapperLookup))
				.addOptionalTag(GWItemTags.KNIVES.location());

		GWObjects.STUMP_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.STUMP).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.STRIPPED_STUMP_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.STUMP).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.CARVED_LOG).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.STRIPPED_CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.CARVED_LOG).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.BEAM_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.BEAM).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.STRIPPED_BEAM_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.BEAM).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.HOLLOW_LOG).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.STRIPPED_HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.HOLLOW_LOG).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.SUPPORT_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.SUPPORT).add(getRes(regEntry.get(), wrapperLookup));
		});
		GWObjects.SHUTTER_ITEMS.stream().forEach(regEntry -> {
			tag(GWItemTags.SHUTTER).add(getRes(regEntry.get(), wrapperLookup));
		});
		tag(GWItemTags.STUMP).add(getRes(GWObjects.MUSHROOM_STUMP.get().asItem(), wrapperLookup));
		tag(GWItemTags.CARVED_LOG).add(getRes(GWObjects.CARVED_MUSHROOM_STEM.get().asItem(), wrapperLookup));
		tag(GWItemTags.BEAM).add(getRes(GWObjects.MUSHROOM_BEAM.get().asItem(), wrapperLookup));
		tag(GWItemTags.HOLLOW_LOG).add(getRes(GWObjects.HOLLOW_MUSHROOM_STEM.get().asItem(), wrapperLookup));
	}

	private ResourceKey<Item> getRes(Item item, HolderLookup.Provider wrapperLookup) {
		var lookup = wrapperLookup.lookupOrThrow(Registries.ITEM);
		return lookup.get(item.builtInRegistryHolder().key()).orElseThrow().key();
	}
}
