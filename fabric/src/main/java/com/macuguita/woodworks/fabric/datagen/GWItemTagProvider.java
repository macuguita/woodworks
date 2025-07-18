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

import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class GWItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public GWItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		getOrCreateTagBuilder(GWItemTags.SECATEURS)
				.add(GWObjects.SECATEURS.get())
				.addOptionalTag(GWItemTags.KNIVES);

		GWObjects.STUMP_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.STUMP).add(regEntry.get());
		});
		GWObjects.STRIPPED_STUMP_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.STUMP).add(regEntry.get());
		});
		GWObjects.CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.CARVED_LOG).add(regEntry.get());
		});
		GWObjects.STRIPPED_CARVED_LOG_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.CARVED_LOG).add(regEntry.get());
		});
		GWObjects.BEAM_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.BEAM).add(regEntry.get());
		});
		GWObjects.STRIPPED_BEAM_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.BEAM).add(regEntry.get());
		});
		GWObjects.HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.HOLLOW_LOG).add(regEntry.get());
		});
		GWObjects.STRIPPED_HOLLOW_LOG_ITEMS.stream().forEach(regEntry -> {
			getOrCreateTagBuilder(GWItemTags.HOLLOW_LOG).add(regEntry.get());
		});
	}
}
