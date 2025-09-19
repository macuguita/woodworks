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

import com.macuguita.woodworks.reg.GWBlockTags;
import com.macuguita.woodworks.reg.GWObjects;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.registry.tag.BlockTags;

public class GWBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public GWBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.STUMP).add(block);
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.STUMP).add(block);
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.CARVED_LOG).add(block);
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.CARVED_LOG).add(block);
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.BEAM).add(block);
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.BEAM).add(block);
		});
		GWObjects.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.HOLLOW_LOG).add(block);
		});
		GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
			getOrCreateTagBuilder(GWBlockTags.HOLLOW_LOG).add(block);
		});
		getOrCreateTagBuilder(GWBlockTags.STUMP).add(GWObjects.MUSHROOM_STUMP.get());
		getOrCreateTagBuilder(GWBlockTags.CARVED_LOG).add(GWObjects.CARVED_MUSHROOM_STEM.get());
		getOrCreateTagBuilder(GWBlockTags.BEAM).add(GWObjects.MUSHROOM_BEAM.get());
		getOrCreateTagBuilder(GWBlockTags.HOLLOW_LOG).add(GWObjects.HOLLOW_MUSHROOM_STEM.get());
		getOrCreateTagBuilder(GWBlockTags.CONNECTING_MUSHROOM)
				.add(GWObjects.CARVED_MUSHROOM_STEM.get())
				.add(GWObjects.HOLLOW_MUSHROOM_STEM.get());
		getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
				.add(GWObjects.MUSHROOM_STUMP.get())
				.add(GWObjects.CARVED_MUSHROOM_STEM.get())
				.add(GWObjects.MUSHROOM_BEAM.get())
				.add(GWObjects.HOLLOW_MUSHROOM_STEM.get());
	}
}
