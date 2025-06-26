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

import java.util.function.Supplier;

import com.macuguita.lib.platform.registry.GuitaRegistries;
import com.macuguita.lib.platform.registry.GuitaRegistry;
import com.macuguita.lib.platform.registry.GuitaRegistryEntry;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.StumpBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class GWObjects {

	public static final GuitaRegistry<Block> BLOCKS = GuitaRegistries.create(Registries.BLOCK, GuitaWoodworks.MOD_ID);
	public static final GuitaRegistry<Item> ITEMS = GuitaRegistries.create(Registries.ITEM, GuitaWoodworks.MOD_ID);

	public static final GuitaRegistry<Block> STUMP_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_STUMP_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> STUMP_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_STUMP_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistryEntry<Block> OAK_STUMP = createStump("oak_stump", Blocks.OAK_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_OAK_STUMP = createStrippedStump("stripped_oak_stump", Blocks.STRIPPED_OAK_WOOD);

	public static final GuitaRegistryEntry<Block> SPRUCE_STUMP = createStump("spruce_stump", Blocks.SPRUCE_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_SPRUCE_STUMP = createStrippedStump("stripped_spruce_stump", Blocks.STRIPPED_SPRUCE_WOOD);

	public static final GuitaRegistryEntry<Block> BIRCH_STUMP = createStump("birch_stump", Blocks.BIRCH_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_BIRCH_STUMP = createStrippedStump("stripped_birch_stump", Blocks.STRIPPED_BIRCH_WOOD);

	public static final GuitaRegistryEntry<Block> JUNGLE_STUMP = createStump("jungle_stump", Blocks.JUNGLE_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_JUNGLE_STUMP = createStrippedStump("stripped_jungle_stump", Blocks.STRIPPED_JUNGLE_WOOD);

	public static final GuitaRegistryEntry<Block> ACACIA_STUMP = createStump("acacia_stump", Blocks.ACACIA_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_ACACIA_STUMP = createStrippedStump("stripped_acacia_stump", Blocks.STRIPPED_ACACIA_WOOD);

	public static final GuitaRegistryEntry<Block> DARK_OAK_STUMP = createStump("dark_oak_stump", Blocks.DARK_OAK_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_DARK_OAK_STUMP = createStrippedStump("stripped_dark_oak_stump", Blocks.STRIPPED_DARK_OAK_WOOD);

	public static final GuitaRegistryEntry<Block> MANGROVE_STUMP = createStump("mangrove_stump", Blocks.MANGROVE_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_MANGROVE_STUMP = createStrippedStump("stripped_mangrove_stump", Blocks.STRIPPED_MANGROVE_WOOD);

	public static final GuitaRegistryEntry<Block> CHERRY_STUMP = createStump("cherry_stump", Blocks.CHERRY_WOOD);
	public static final GuitaRegistryEntry<Block> STRIPPED_CHERRY_STUMP = createStrippedStump("stripped_cherry_stump", Blocks.STRIPPED_CHERRY_WOOD);

	public static final GuitaRegistryEntry<Block> CRIMSON_STUMP = createStump("crimson_stump", Blocks.CRIMSON_HYPHAE);
	public static final GuitaRegistryEntry<Block> STRIPPED_CRIMSON_STUMP = createStrippedStump("stripped_crimson_stump", Blocks.STRIPPED_CRIMSON_HYPHAE);

	public static final GuitaRegistryEntry<Block> WARPED_STUMP = createStump("warped_stump", Blocks.WARPED_HYPHAE);
	public static final GuitaRegistryEntry<Block> STRIPPED_WARPED_STUMP = createStrippedStump("stripped_warped_stump", Blocks.STRIPPED_WARPED_HYPHAE);

	public static GuitaRegistryEntry<Block> createStump(String name, Block wood) {
		return registerWithItem(name, () -> new StumpBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STUMP_BLOCKS, STUMP_ITEMS);
	}

	public static GuitaRegistryEntry<Block> createStrippedStump(String name, Block wood) {
		return registerWithItem(name, () -> new StumpBlock(AbstractBlock.Settings.copy(wood).mapColor(wood.getDefaultMapColor())), STRIPPED_STUMP_BLOCKS, STRIPPED_STUMP_ITEMS);
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
