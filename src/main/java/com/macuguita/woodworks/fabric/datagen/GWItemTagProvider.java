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
/*import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import com.macuguita.woodworks.common.reg.GWItemTags;
import com.macuguita.woodworks.common.reg.GWObjects;

public class GWItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public GWItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		valueLookupBuilder(GWItemTags.SECATEURS)
			.add(GWObjects.SECATEURS.get())
			.addOptionalTag(GWItemTags.KNIVES);

		GWObjects.STUMP_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.STUMP).add(regEntry.get());
		});
		GWObjects.STRIPPED_STUMP_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.STUMP).add(regEntry.get());
		});
		GWObjects.CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.CARVED_LOG).add(regEntry.get());
		});
		GWObjects.STRIPPED_CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.CARVED_LOG).add(regEntry.get());
		});
		GWObjects.BEAM_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.BEAM).add(regEntry.get());
		});
		GWObjects.STRIPPED_BEAM_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.BEAM).add(regEntry.get());
		});
		GWObjects.HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.HOLLOW_LOG).add(regEntry.get());
		});
		GWObjects.STRIPPED_HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.HOLLOW_LOG).add(regEntry.get());
		});
		GWObjects.SUPPORT_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.SUPPORT).add(regEntry.get());
		});
		GWObjects.SHUTTER_ITEMS.stream().forEach(regEntry -> {
			valueLookupBuilder(GWItemTags.SHUTTER).add(regEntry.get());
		});
		valueLookupBuilder(GWItemTags.STUMP).add(GWObjects.MUSHROOM_STUMP.get().asItem());
		valueLookupBuilder(GWItemTags.CARVED_LOG).add(GWObjects.CARVED_MUSHROOM_STEM.get().asItem());
		valueLookupBuilder(GWItemTags.BEAM).add(GWObjects.MUSHROOM_BEAM.get().asItem());
		valueLookupBuilder(GWItemTags.HOLLOW_LOG).add(GWObjects.HOLLOW_MUSHROOM_STEM.get().asItem());
	}
}
*///?}
