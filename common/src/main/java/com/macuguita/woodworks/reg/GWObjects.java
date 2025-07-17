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

package com.macuguita.woodworks.reg;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.macuguita.lib.platform.registry.GuitaRegistries;
import com.macuguita.lib.platform.registry.GuitaRegistry;
import com.macuguita.lib.platform.registry.GuitaRegistryEntry;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.StumpSeatBlock;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.Registries;

public class GWObjects {

	public static final Map<Block, Block> WOOD_ASSOCIATIONS = new HashMap<>();

	public static final GuitaRegistry<Block> BLOCKS = GuitaRegistries.create(Registries.BLOCK, GuitaWoodworks.MOD_ID);
	public static final GuitaRegistry<Item> ITEMS = GuitaRegistries.create(Registries.ITEM, GuitaWoodworks.MOD_ID);

	public static final GuitaRegistry<Block> STUMP_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_STUMP_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> STUMP_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_STUMP_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Block> CARVED_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_CARVED_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> CARVED_LOG_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_CARVED_LOG_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Block> BEAM_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_BEAM_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> BEAM_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_BEAM_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Block> HOLLOW_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_HOLLOW_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> HOLLOW_LOG_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_HOLLOW_LOG_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistryEntry<Item> SECATEURS = ITEMS.register("secateurs", () -> new ShearsItem(new Item.Settings().maxDamage(476)));

	public static final GuitaRegistryEntry<Block> OAK_STUMP = createStump("oak_stump", Blocks.OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_OAK_STUMP = createStrippedStump("stripped_oak_stump", Blocks.STRIPPED_OAK_LOG);

	public static final GuitaRegistryEntry<Block> SPRUCE_STUMP = createStump("spruce_stump", Blocks.SPRUCE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_SPRUCE_STUMP = createStrippedStump("stripped_spruce_stump", Blocks.STRIPPED_SPRUCE_LOG);

	public static final GuitaRegistryEntry<Block> BIRCH_STUMP = createStump("birch_stump", Blocks.BIRCH_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_BIRCH_STUMP = createStrippedStump("stripped_birch_stump", Blocks.STRIPPED_BIRCH_LOG);

	public static final GuitaRegistryEntry<Block> JUNGLE_STUMP = createStump("jungle_stump", Blocks.JUNGLE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_JUNGLE_STUMP = createStrippedStump("stripped_jungle_stump", Blocks.STRIPPED_JUNGLE_LOG);

	public static final GuitaRegistryEntry<Block> ACACIA_STUMP = createStump("acacia_stump", Blocks.ACACIA_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_ACACIA_STUMP = createStrippedStump("stripped_acacia_stump", Blocks.STRIPPED_ACACIA_LOG);

	public static final GuitaRegistryEntry<Block> DARK_OAK_STUMP = createStump("dark_oak_stump", Blocks.DARK_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_DARK_OAK_STUMP = createStrippedStump("stripped_dark_oak_stump", Blocks.STRIPPED_DARK_OAK_LOG);

	public static final GuitaRegistryEntry<Block> MANGROVE_STUMP = createStump("mangrove_stump", Blocks.MANGROVE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_MANGROVE_STUMP = createStrippedStump("stripped_mangrove_stump", Blocks.STRIPPED_MANGROVE_LOG);

	public static final GuitaRegistryEntry<Block> CHERRY_STUMP = createStump("cherry_stump", Blocks.CHERRY_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CHERRY_STUMP = createStrippedStump("stripped_cherry_stump", Blocks.STRIPPED_CHERRY_LOG);

	public static final GuitaRegistryEntry<Block> CRIMSON_STUMP = createStump("crimson_stump", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CRIMSON_STUMP = createStrippedStump("stripped_crimson_stump", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> WARPED_STUMP = createStump("warped_stump", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_WARPED_STUMP = createStrippedStump("stripped_warped_stump", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> CARVED_OAK_LOG = createCarvedLog("carved_oak_log", Blocks.OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_OAK_LOG = createStrippedCarvedLog("stripped_carved_oak_log", Blocks.STRIPPED_OAK_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_SPRUCE_LOG = createCarvedLog("carved_spruce_log", Blocks.SPRUCE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_SPRUCE_LOG = createStrippedCarvedLog("stripped_carved_spruce_log", Blocks.STRIPPED_SPRUCE_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_BIRCH_LOG = createCarvedLog("carved_birch_log", Blocks.BIRCH_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_BIRCH_LOG = createStrippedCarvedLog("stripped_carved_birch_log", Blocks.STRIPPED_BIRCH_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_JUNGLE_LOG = createCarvedLog("carved_jungle_log", Blocks.JUNGLE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_JUNGLE_LOG = createStrippedCarvedLog("stripped_carved_jungle_log", Blocks.STRIPPED_JUNGLE_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_ACACIA_LOG = createCarvedLog("carved_acacia_log", Blocks.ACACIA_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_ACACIA_LOG = createStrippedCarvedLog("stripped_carved_acacia_log", Blocks.STRIPPED_ACACIA_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_DARK_OAK_LOG = createCarvedLog("carved_dark_oak_log", Blocks.DARK_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_DARK_OAK_LOG = createStrippedCarvedLog("stripped_carved_dark_oak_log", Blocks.STRIPPED_DARK_OAK_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_MANGROVE_LOG = createCarvedLog("carved_mangrove_log", Blocks.MANGROVE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_MANGROVE_LOG = createStrippedCarvedLog("stripped_carved_mangrove_log", Blocks.STRIPPED_MANGROVE_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_CHERRY_LOG = createCarvedLog("carved_cherry_log", Blocks.CHERRY_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_CHERRY_LOG = createStrippedCarvedLog("stripped_carved_cherry_log", Blocks.STRIPPED_CHERRY_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_CRIMSON_STEM = createCarvedLog("carved_crimson_stem", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_CRIMSON_STEM = createStrippedCarvedLog("stripped_carved_crimson_stem", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> CARVED_WARPED_STEM = createCarvedLog("carved_warped_stem", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_WARPED_STEM = createStrippedCarvedLog("stripped_carved_warped_stem", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> OAK_BEAM = createBeam("oak_beam", Blocks.OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_OAK_BEAM = createStrippedBeam("stripped_oak_beam", Blocks.STRIPPED_OAK_LOG);

	public static final GuitaRegistryEntry<Block> SPRUCE_BEAM = createBeam("spruce_beam", Blocks.SPRUCE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_SPRUCE_BEAM = createStrippedBeam("stripped_spruce_beam", Blocks.STRIPPED_SPRUCE_LOG);

	public static final GuitaRegistryEntry<Block> BIRCH_BEAM = createBeam("birch_beam", Blocks.BIRCH_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_BIRCH_BEAM = createStrippedBeam("stripped_birch_beam", Blocks.STRIPPED_BIRCH_LOG);

	public static final GuitaRegistryEntry<Block> JUNGLE_BEAM = createBeam("jungle_beam", Blocks.JUNGLE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_JUNGLE_BEAM = createStrippedBeam("stripped_jungle_beam", Blocks.STRIPPED_JUNGLE_LOG);

	public static final GuitaRegistryEntry<Block> ACACIA_BEAM = createBeam("acacia_beam", Blocks.ACACIA_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_ACACIA_BEAM = createStrippedBeam("stripped_acacia_beam", Blocks.STRIPPED_ACACIA_LOG);

	public static final GuitaRegistryEntry<Block> DARK_OAK_BEAM = createBeam("dark_oak_beam", Blocks.DARK_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_DARK_OAK_BEAM = createStrippedBeam("stripped_dark_oak_beam", Blocks.STRIPPED_DARK_OAK_LOG);

	public static final GuitaRegistryEntry<Block> MANGROVE_BEAM = createBeam("mangrove_beam", Blocks.MANGROVE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_MANGROVE_BEAM = createStrippedBeam("stripped_mangrove_beam", Blocks.STRIPPED_MANGROVE_LOG);

	public static final GuitaRegistryEntry<Block> CHERRY_BEAM = createBeam("cherry_beam", Blocks.CHERRY_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CHERRY_BEAM = createStrippedBeam("stripped_cherry_beam", Blocks.STRIPPED_CHERRY_LOG);

	public static final GuitaRegistryEntry<Block> CRIMSON_BEAM = createBeam("crimson_beam", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CRIMSON_BEAM = createStrippedBeam("stripped_crimson_beam", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> WARPED_BEAM = createBeam("warped_beam", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_WARPED_BEAM = createStrippedBeam("stripped_warped_beam", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> HOLLOW_OAK_LOG = createHollowLog("hollow_oak_log", Blocks.OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_OAK_LOG = createStrippedHollowLog("stripped_hollow_oak_log", Blocks.STRIPPED_OAK_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_SPRUCE_LOG = createHollowLog("hollow_spruce_log", Blocks.SPRUCE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_SPRUCE_LOG = createStrippedHollowLog("stripped_hollow_spruce_log", Blocks.STRIPPED_SPRUCE_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_BIRCH_LOG = createHollowLog("hollow_birch_log", Blocks.BIRCH_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_BIRCH_LOG = createStrippedHollowLog("stripped_hollow_birch_log", Blocks.STRIPPED_BIRCH_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_JUNGLE_LOG = createHollowLog("hollow_jungle_log", Blocks.JUNGLE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_JUNGLE_LOG = createStrippedHollowLog("stripped_hollow_jungle_log", Blocks.STRIPPED_JUNGLE_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_ACACIA_LOG = createHollowLog("hollow_acacia_log", Blocks.ACACIA_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_ACACIA_LOG = createStrippedHollowLog("stripped_hollow_acacia_log", Blocks.STRIPPED_ACACIA_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_DARK_OAK_LOG = createHollowLog("hollow_dark_oak_log", Blocks.DARK_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_DARK_OAK_LOG = createStrippedHollowLog("stripped_hollow_dark_oak_log", Blocks.STRIPPED_DARK_OAK_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_MANGROVE_LOG = createHollowLog("hollow_mangrove_log", Blocks.MANGROVE_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_MANGROVE_LOG = createStrippedHollowLog("stripped_hollow_mangrove_log", Blocks.STRIPPED_MANGROVE_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_CHERRY_LOG = createHollowLog("hollow_cherry_log", Blocks.CHERRY_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_CHERRY_LOG = createStrippedHollowLog("stripped_hollow_cherry_log", Blocks.STRIPPED_CHERRY_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_CRIMSON_STEM = createHollowLog("hollow_crimson_stem", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_CRIMSON_STEM = createStrippedHollowLog("stripped_hollow_crimson_stem", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> HOLLOW_WARPED_STEM = createHollowLog("hollow_warped_stem", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_WARPED_STEM = createStrippedHollowLog("stripped_hollow_warped_stem", Blocks.STRIPPED_WARPED_STEM);

	public static GuitaRegistryEntry<Block> createStump(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new StumpSeatBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STUMP_BLOCKS, STUMP_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedStump(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new StumpSeatBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STRIPPED_STUMP_BLOCKS, STRIPPED_STUMP_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createCarvedLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new CarvedLogSeatBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), CARVED_LOG_BLOCKS, CARVED_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedCarvedLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new CarvedLogSeatBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STRIPPED_CARVED_LOG_BLOCKS, STRIPPED_CARVED_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createBeam(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new ResizableBeamBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), BEAM_BLOCKS, BEAM_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedBeam(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new ResizableBeamBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STRIPPED_BEAM_BLOCKS, STRIPPED_BEAM_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createHollowLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new HollowLogBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), HOLLOW_LOG_BLOCKS, HOLLOW_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedHollowLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, () -> new HollowLogBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STRIPPED_HOLLOW_LOG_BLOCKS, STRIPPED_HOLLOW_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static <T extends Block> GuitaRegistryEntry<T> registerWithItem(String name, Supplier<T> block, GuitaRegistry<Block> blockReg, GuitaRegistry<Item> itemReg) {
		GuitaRegistryEntry<T> toReturn = blockReg.register(name, block);
		itemReg.register(name, () -> new BlockItem(toReturn.get(), new Item.Settings()));
		return toReturn;
	}

	public static void init() {
		BLOCKS.init();
		ITEMS.init();
	}
}
