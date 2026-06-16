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

import com.macuguita.woodworks.common.reg.GWBlockItemIds;
import com.macuguita.woodworks.common.reg.GWBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import com.macuguita.woodworks.common.reg.GWBlockTags;

public class GWBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public GWBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	//~ if >26.1 'valueLookupBuilder' -> 'tag' {
	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		GWBlocks.STUMP_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.STUMP).add(res(regEntry));
		});

		GWBlocks.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.STUMP).add(res(regEntry));
		});

		GWBlocks.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.CARVED_LOG).add(res(regEntry));
		});

		GWBlocks.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.CARVED_LOG).add(res(regEntry));
		});

		GWBlocks.BEAM_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.BEAM).add(res(regEntry));
		});

		GWBlocks.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.BEAM).add(res(regEntry));
		});

		GWBlocks.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.SUPPORT).add(res(regEntry));
		});

		GWBlocks.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			tag(GWBlockTags.SHUTTER).add(res(regEntry));
		});

		//~ if >26.1 'GWBlocks' -> 'GWBlockItemIds' {
		tag(GWBlockTags.STUMP)
			.add(GWBlockItemIds.MUSHROOM_STUMP/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/);

		tag(GWBlockTags.CARVED_LOG)
			.add(GWBlockItemIds.CARVED_MUSHROOM_STEM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/);

		tag(GWBlockTags.BEAM)
			.add(GWBlockItemIds.MUSHROOM_BEAM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/);

		tag(GWBlockTags.HOLLOW_LOG)
			.add(GWBlockItemIds.HOLLOW_MUSHROOM_STEM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/);

		tag(GWBlockTags.CONNECTING_MUSHROOM)
			.add(GWBlockItemIds.CARVED_MUSHROOM_STEM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/)
			.add(GWBlockItemIds.HOLLOW_MUSHROOM_STEM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/);

		tag(BlockTags.MINEABLE_WITH_AXE)
			.add(GWBlockItemIds.MUSHROOM_STUMP/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/)
			.add(GWBlockItemIds.CARVED_MUSHROOM_STEM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/)
			.add(GWBlockItemIds.MUSHROOM_BEAM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/)
			.add(GWBlockItemIds.HOLLOW_MUSHROOM_STEM/*?<=26.1{*//*.get()*//*?}else{*/.block()/*?}*/)
			.addTag(GWBlockTags.STUMP)
			.addTag(GWBlockTags.CARVED_LOG)
			.addTag(GWBlockTags.BEAM)
			.addTag(GWBlockTags.HOLLOW_LOG)
			.addTag(GWBlockTags.SUPPORT)
			.addTag(GWBlockTags.SHUTTER);
	}
	//~}
	//~}

	//? if >26.1 {
	private static ResourceKey<Block> res(GuitaRegistryEntry<Block> entry) {
		return ResourceKey.create(BuiltInRegistries.BLOCK.key(), entry.getId());
	}
	//?} else {
	/*private static Block res(GuitaRegistryEntry<Block> entry) {
		return entry.get();
	}
	*///?}
}
//?}
