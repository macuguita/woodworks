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

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class GWBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public GWBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.STUMP).add(getRes(block, wrapperLookup));
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.STUMP).add(getRes(block, wrapperLookup));
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.CARVED_LOG).add(getRes(block, wrapperLookup));
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.CARVED_LOG).add(getRes(block, wrapperLookup));
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.BEAM).add(getRes(block, wrapperLookup));
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.BEAM).add(getRes(block, wrapperLookup));
		});
		GWObjects.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.HOLLOW_LOG).add(getRes(block, wrapperLookup));
		});
		GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.HOLLOW_LOG).add(getRes(block, wrapperLookup));
		});
		GWObjects.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.SUPPORT).add(getRes(block, wrapperLookup));
		});
		GWObjects.SHUTTER_BLOCKS.stream().forEach(regEntry -> {
			Block block = regEntry.get();
			tag(GWBlockTags.SHUTTER).add(getRes(block, wrapperLookup));
		});
		tag(GWBlockTags.STUMP).add(getRes(GWObjects.MUSHROOM_STUMP.get(), wrapperLookup));
		tag(GWBlockTags.CARVED_LOG).add(getRes(GWObjects.CARVED_MUSHROOM_STEM.get(), wrapperLookup));
		tag(GWBlockTags.BEAM).add(getRes(GWObjects.MUSHROOM_BEAM.get(), wrapperLookup));
		tag(GWBlockTags.HOLLOW_LOG).add(getRes(GWObjects.HOLLOW_MUSHROOM_STEM.get(), wrapperLookup));
		tag(GWBlockTags.CONNECTING_MUSHROOM)
				.add(getRes(GWObjects.CARVED_MUSHROOM_STEM.get(), wrapperLookup))
				.add(getRes(GWObjects.HOLLOW_MUSHROOM_STEM.get(), wrapperLookup));
		tag(BlockTags.MINEABLE_WITH_AXE)
				.add(getRes(GWObjects.MUSHROOM_STUMP.get(), wrapperLookup))
				.add(getRes(GWObjects.CARVED_MUSHROOM_STEM.get(), wrapperLookup))
				.add(getRes(GWObjects.MUSHROOM_BEAM.get(), wrapperLookup))
				.add(getRes(GWObjects.HOLLOW_MUSHROOM_STEM.get(), wrapperLookup))
				.addTag(GWBlockTags.STUMP)
				.addTag(GWBlockTags.CARVED_LOG)
				.addTag(GWBlockTags.BEAM)
				.addTag(GWBlockTags.HOLLOW_LOG)
				.addTag(GWBlockTags.SUPPORT)
				.addTag(GWBlockTags.SHUTTER);
	}

	private ResourceKey<Block> getRes(Block block, HolderLookup.Provider wrapperLookup) {
		var lookup = wrapperLookup.lookupOrThrow(Registries.BLOCK);
		return lookup.get(block.builtInRegistryHolder().key()).orElseThrow().key();
	}
}
