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

import com.macuguita.lib.reg.GuitaRegistry;
import com.macuguita.lib.reg.GuitaRegistryEntry;
import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.StumpSeatBlock;
import com.macuguita.woodworks.mixin.FireBlockAccessor;
import com.macuguita.woodworks.reg.GWEntityTypes;
import com.macuguita.woodworks.reg.GWItemGroups;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;
import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class GuitaWoodworks {

	public static final String MOD_ID = "gwoodworks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Config CONFIG = WrappedConfig.createToml(GWUtils.getConfigDir(), "", MOD_ID, Config.class);

	public static final class Config extends WrappedConfig {

		@Comment("!!! DISCLAIMER !!!")
		@Comment("If you disable blocks after loading a world")
		@Comment("the disabled blocks will be removed")
		@Comment("with no way to bring them back if you've not done a previous backup.")
		@Comment("")
		@Comment("For the best experience make sure that the configs are the same in the client and server")
		@Comment("so clients don't hallucinate blocks that are not available in recipe viewers or creative menus")
		@Comment("")
		@Comment("If false, hides/disables Stump blocks for all wood types.")
		public boolean enableStumps = true;
		@Comment("If false, hides/disables Carved Log blocks for all wood types.")
		public boolean enableCarvedLogs = true;
		@Comment("If false, hides/disables Beam blocks for all wood types and the Secateurs item.")
		public boolean enableBeams = true;
		@Comment("Allow resizing resizable beams using shears.")
		public boolean enableResizingBeams = true;
		@Comment("If false, hides/disables Hollow Log blocks for all wood types.")
		public boolean enableHollowLogs = true;
		@Comment("If false, hides/disables Support blocks for all wood types.")
		public boolean enableSupports = true;
		@Comment("If false, hides/disables Shutter blocks for all wood types.")
		public boolean enableShutters = true;
	}

	public static void init() {
		GWObjects.init();
		GWEntityTypes.init();
		GWItemGroups.init();
	}

	public static void commonSetup() {
		if (CONFIG.enableStumps) {
			registerFuelAndRegisterStripped(GWObjects.STUMP_BLOCKS, GWObjects.STRIPPED_STUMP_BLOCKS, StumpSeatBlock.STRIPPED_STUMPS, 150);
		}
		if (CONFIG.enableCarvedLogs) {
			registerFuelAndRegisterStripped(GWObjects.CARVED_LOG_BLOCKS, GWObjects.STRIPPED_CARVED_LOG_BLOCKS, CarvedLogSeatBlock.STRIPPED_CARVED_LOGS, 250);
		}
		if (CONFIG.enableBeams) {
			registerFuelAndRegisterStripped(GWObjects.BEAM_BLOCKS, GWObjects.STRIPPED_BEAM_BLOCKS, ResizableBeamBlock.STRIPPED_BEAM_BLOCKS, 75);
		}
		if (CONFIG.enableHollowLogs) {
			registerFuelAndRegisterStripped(GWObjects.HOLLOW_LOG_BLOCKS, GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS, HollowLogBlock.STRIPPED_HOLLOW_LOGS, 150);
		}
		if (CONFIG.enableSupports) {
			registerFuel(GWObjects.SUPPORT_BLOCKS, null, 150);
		}
		if (CONFIG.enableShutters) {
			registerFuel(GWObjects.SHUTTER_BLOCKS, null, 150);
		}
	}

	private static void registerFuelAndRegisterStripped(GuitaRegistry<Block> blockReg, GuitaRegistry<Block> strippedBlockReg, Map<Block, Block> strippedMap, int fuelTime) {
		registerFuel(blockReg, strippedBlockReg, fuelTime);
		registerStripped(blockReg, strippedBlockReg, strippedMap);
	}

	private static void registerFuel(GuitaRegistry<Block> blockReg, @Nullable GuitaRegistry<Block> strippedBlockReg, int fuelTime) {
		int index = 0;
		for (GuitaRegistryEntry<Block> regEntry : blockReg.getEntries()) {
			Identifier id = regEntry.getId();
			Block block = regEntry.get();
			Item item = block.asItem();

			Optional<GuitaRegistryEntry<Block>> optionalEntry = strippedBlockReg != null
					? strippedBlockReg.stream().skip(index).findFirst()
					: Optional.empty();

			if (!id.getPath().matches(".*(crimson|warped).*")) {
				GWUtils.registerFuel(fuelTime, item);
				((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);

				if (optionalEntry.isPresent()) {
					Block strippedBlock = optionalEntry.get().get();
					Item strippedItem = strippedBlock.asItem();
					GWUtils.registerFuel(fuelTime, strippedItem);
					((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(strippedBlock, 5, 5);
				}
			}
			index++;
		}
	}

	private static void registerStripped(GuitaRegistry<Block> blockReg, GuitaRegistry<Block> strippedBlockReg, Map<Block, Block> strippedMap) {
		int index = 0;
		for (GuitaRegistryEntry<Block> regEntry : blockReg.getEntries()) {
			Block block = regEntry.get();

			Optional<GuitaRegistryEntry<Block>> optionalEntry = strippedBlockReg.stream()
					.skip(index)
					.findFirst();

			if (optionalEntry.isPresent()) {
				Block strippedBlock = optionalEntry.get().get();
				strippedMap.put(block, strippedBlock);
			}
			index++;
		}
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}
