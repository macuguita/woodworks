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

package com.macuguita.woodworks;

import java.util.Map;
import java.util.Optional;

import com.macuguita.lib.platform.registry.GuitaRegistry;
import com.macuguita.lib.platform.registry.GuitaRegistryEntry;
import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.StumpSeatBlock;
import com.macuguita.woodworks.mixin.FireBlockAccessor;
import com.macuguita.woodworks.reg.GWEntityTypes;
import com.macuguita.woodworks.reg.GWItemGroups;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public final class GuitaWoodworks {

	public static final String MOD_ID = "gwoodworks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void init() {
		GWObjects.init();
		GWEntityTypes.init();
		GWItemGroups.init();
	}

	public static void commonSetup() {
		registerFuelAndRegisterStripped(GWObjects.STUMP_BLOCKS, GWObjects.STRIPPED_STUMP_BLOCKS, StumpSeatBlock.STRIPPED_STUMPS, 150);
		registerFuelAndRegisterStripped(GWObjects.CARVED_LOG_BLOCKS, GWObjects.STRIPPED_CARVED_LOG_BLOCKS, CarvedLogSeatBlock.STRIPPED_CARVED_LOGS, 250);
		registerFuelAndRegisterStripped(GWObjects.BEAM_BLOCKS, GWObjects.STRIPPED_BEAM_BLOCKS, ResizableBeamBlock.STRIPPED_BEAM_BLOCKS, 75);
		registerFuelAndRegisterStripped(GWObjects.HOLLOW_LOG_BLOCKS, GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS, HollowLogBlock.STRIPPED_HOLLOW_LOGS, 150);
	}

	private static void registerFuelAndRegisterStripped(GuitaRegistry<Block> blockReg, GuitaRegistry<Block> strippedBlockReg, Map<Block, Block> strippedMap, int fuelTime) {
		int index = 0;
		for (GuitaRegistryEntry<Block> regEntry : blockReg.getEntries()) {
			Identifier id = regEntry.getId();
			Block block = regEntry.get();
			Item item = block.asItem();

			Optional<GuitaRegistryEntry<Block>> optionalEntry = strippedBlockReg.stream()
					.skip(index)
					.findFirst();

			Block strippedBlock = null;
			Item strippedItem = null;

			if (optionalEntry.isPresent()) {
				strippedBlock = optionalEntry.get().get();
				strippedItem = strippedBlock.asItem();
			}


			if (!id.getPath().matches(".*(crimson|warped).*")) {
				GWUtils.registerFuel(fuelTime, item);
				((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
				if (strippedItem != null) {
					GWUtils.registerFuel(fuelTime, strippedItem);
					((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(strippedBlock, 5, 5);
				}
			}
			if (strippedBlock != null) {
				strippedMap.put(block, strippedBlock);
			}
			index++;
		}
	}

	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
}
