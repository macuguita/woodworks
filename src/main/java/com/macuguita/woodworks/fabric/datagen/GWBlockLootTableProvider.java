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

import com.macuguita.woodworks.common.reg.GWBlocks;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;

public class GWBlockLootTableProvider extends FabricBlockLootSubProvider {

	protected GWBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		GWBlocks.STUMP_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.BEAM_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		GWBlocks.SHUTTER_BLOCKS.stream().forEach(regEntry -> {
			dropSelf(regEntry.get());
		});
		dropSelf(GWBlocks.MUSHROOM_STUMP.get());
		dropSelf(GWBlocks.CARVED_MUSHROOM_STEM.get());
		dropSelf(GWBlocks.MUSHROOM_BEAM.get());
		dropSelf(GWBlocks.HOLLOW_MUSHROOM_STEM.get());
	}
}
//?}
