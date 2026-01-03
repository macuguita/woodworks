/*
 * Copyright (c) 2025 macuguita
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

package com.macuguita.woodworks.reg;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.macuguita.lib.platform.registry.GuitaRegistries;
import com.macuguita.lib.platform.registry.GuitaRegistry;
import com.macuguita.lib.platform.registry.GuitaRegistryEntry;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.ShutterBlock;
import com.macuguita.woodworks.block.StumpSeatBlock;
import com.macuguita.woodworks.block.SupportBlock;
import com.macuguita.woodworks.item.SupportBlockItem;
import com.macuguita.woodworks.item.TooltippedBlockItem;
import com.macuguita.woodworks.utils.GWUtils;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class GWObjects {

	public static final Map<Block, Block> WOOD_ASSOCIATIONS = new HashMap<>();

	public static final GuitaRegistry<Block> BLOCKS = GuitaRegistries.create(BuiltInRegistries.BLOCK, GuitaWoodworks.MOD_ID);
	public static final GuitaRegistry<Item> ITEMS = GuitaRegistries.create(BuiltInRegistries.ITEM, GuitaWoodworks.MOD_ID);

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

	public static final GuitaRegistry<Block> SUPPORT_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> SUPPORT_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Block> SHUTTER_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Item> SHUTTER_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistryEntry<Item> SECATEURS = ITEMS.register("secateurs", () -> new Item(new Item.Properties().durability(476).component(DataComponents.TOOL, ShearsItem.createToolProperties()).setId(keyOfItem("secateurs"))));

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

	public static final GuitaRegistryEntry<Block> BAMBOO_STUMP = createStump("bamboo_stump", Blocks.BAMBOO_BLOCK);
	public static final GuitaRegistryEntry<Block> STRIPPED_BAMBOO_STUMP = createStrippedStump("stripped_bamboo_stump", Blocks.STRIPPED_BAMBOO_BLOCK);

	public static final GuitaRegistryEntry<Block> PALE_OAK_STUMP = createStump("pale_oak_stump", Blocks.PALE_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_PALE_OAK_STUMP = createStrippedStump("stripped_pale_oak_stump", Blocks.STRIPPED_PALE_OAK_LOG);

	public static final GuitaRegistryEntry<Block> CRIMSON_STUMP = createStump("crimson_stump", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CRIMSON_STUMP = createStrippedStump("stripped_crimson_stump", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> WARPED_STUMP = createStump("warped_stump", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_WARPED_STUMP = createStrippedStump("stripped_warped_stump", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> MUSHROOM_STUMP = registerWithItem("mushroom_stump", settings -> new StumpSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS, ITEMS);

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

	public static final GuitaRegistryEntry<Block> CARVED_BAMBOO_LOG = createCarvedLog("carved_bamboo_log", Blocks.BAMBOO_BLOCK);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_BAMBOO_LOG = createStrippedCarvedLog("stripped_carved_bamboo_log", Blocks.STRIPPED_BAMBOO_BLOCK);

	public static final GuitaRegistryEntry<Block> CARVED_PALE_OAK_LOG = createCarvedLog("carved_pale_oak_log", Blocks.PALE_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_PALE_OAK_LOG = createStrippedCarvedLog("stripped_carved_pale_oak_log", Blocks.STRIPPED_PALE_OAK_LOG);

	public static final GuitaRegistryEntry<Block> CARVED_CRIMSON_STEM = createCarvedLog("carved_crimson_stem", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_CRIMSON_STEM = createStrippedCarvedLog("stripped_carved_crimson_stem", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> CARVED_WARPED_STEM = createCarvedLog("carved_warped_stem", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CARVED_WARPED_STEM = createStrippedCarvedLog("stripped_carved_warped_stem", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> CARVED_MUSHROOM_STEM = registerWithItem("carved_mushroom_stem", settings -> new CarvedLogSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS, ITEMS);

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

	public static final GuitaRegistryEntry<Block> BAMBOO_BEAM = createBeam("bamboo_beam", Blocks.BAMBOO_BLOCK);
	public static final GuitaRegistryEntry<Block> STRIPPED_BAMBOO_BEAM = createStrippedBeam("stripped_bamboo_beam", Blocks.STRIPPED_BAMBOO_BLOCK);

	public static final GuitaRegistryEntry<Block> PALE_OAK_BEAM = createBeam("pale_oak_beam", Blocks.PALE_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_PALE_OAK_BEAM = createStrippedBeam("stripped_pale_oak_beam", Blocks.STRIPPED_PALE_OAK_LOG);

	public static final GuitaRegistryEntry<Block> CRIMSON_BEAM = createBeam("crimson_beam", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_CRIMSON_BEAM = createStrippedBeam("stripped_crimson_beam", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> WARPED_BEAM = createBeam("warped_beam", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_WARPED_BEAM = createStrippedBeam("stripped_warped_beam", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> MUSHROOM_BEAM = registerWithItem("mushroom_beam", settings -> new ResizableBeamBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS, ITEMS);

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

	public static final GuitaRegistryEntry<Block> HOLLOW_BAMBOO_LOG = createHollowLog("hollow_bamboo_log", Blocks.BAMBOO_BLOCK);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_BAMBOO_LOG = createStrippedHollowLog("stripped_hollow_bamboo_log", Blocks.STRIPPED_BAMBOO_BLOCK);

	public static final GuitaRegistryEntry<Block> HOLLOW_PALE_OAK_LOG = createHollowLog("hollow_pale_oak_log", Blocks.PALE_OAK_LOG);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_PALE_OAK_LOG = createStrippedHollowLog("stripped_hollow_pale_oak_log", Blocks.STRIPPED_PALE_OAK_LOG);

	public static final GuitaRegistryEntry<Block> HOLLOW_CRIMSON_STEM = createHollowLog("hollow_crimson_stem", Blocks.CRIMSON_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_CRIMSON_STEM = createStrippedHollowLog("stripped_hollow_crimson_stem", Blocks.STRIPPED_CRIMSON_STEM);

	public static final GuitaRegistryEntry<Block> HOLLOW_WARPED_STEM = createHollowLog("hollow_warped_stem", Blocks.WARPED_STEM);
	public static final GuitaRegistryEntry<Block> STRIPPED_HOLLOW_WARPED_STEM = createStrippedHollowLog("stripped_hollow_warped_stem", Blocks.STRIPPED_WARPED_STEM);

	public static final GuitaRegistryEntry<Block> HOLLOW_MUSHROOM_STEM = registerWithItem("hollow_mushroom_stem", settings -> new HollowLogBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS, ITEMS);

	public static final GuitaRegistryEntry<Block> OAK_SUPPORT = createSupportBlock("oak_support", Blocks.OAK_PLANKS);

	public static final GuitaRegistryEntry<Block> SPRUCE_SUPPORT = createSupportBlock("spruce_support", Blocks.SPRUCE_PLANKS);

	public static final GuitaRegistryEntry<Block> BIRCH_SUPPORT = createSupportBlock("birch_support", Blocks.BIRCH_PLANKS);

	public static final GuitaRegistryEntry<Block> JUNGLE_SUPPORT = createSupportBlock("jungle_support", Blocks.JUNGLE_PLANKS);

	public static final GuitaRegistryEntry<Block> ACACIA_SUPPORT = createSupportBlock("acacia_support", Blocks.ACACIA_PLANKS);

	public static final GuitaRegistryEntry<Block> DARK_OAK_SUPPORT = createSupportBlock("dark_oak_support", Blocks.DARK_OAK_PLANKS);

	public static final GuitaRegistryEntry<Block> MANGROVE_SUPPORT = createSupportBlock("mangrove_support", Blocks.MANGROVE_PLANKS);

	public static final GuitaRegistryEntry<Block> CHERRY_SUPPORT = createSupportBlock("cherry_support", Blocks.CHERRY_PLANKS);

	public static final GuitaRegistryEntry<Block> BAMBOO_SUPPORT = createSupportBlock("bamboo_support", Blocks.BAMBOO_PLANKS);

	public static final GuitaRegistryEntry<Block> PALE_OAK_SUPPORT = createSupportBlock("pale_oak_support", Blocks.PALE_OAK_PLANKS);

	public static final GuitaRegistryEntry<Block> CRIMSON_SUPPORT = createSupportBlock("crimson_support", Blocks.CRIMSON_PLANKS);

	public static final GuitaRegistryEntry<Block> WARPED_SUPPORT = createSupportBlock("warped_support", Blocks.WARPED_PLANKS);

	public static final GuitaRegistryEntry<Block> OAK_SHUTTER = createShutterBlock("oak_shutter", Blocks.OAK_PLANKS);

	public static final GuitaRegistryEntry<Block> SPRUCE_SHUTTER = createShutterBlock("spruce_shutter", Blocks.SPRUCE_PLANKS);

	public static final GuitaRegistryEntry<Block> BIRCH_SHUTTER = createShutterBlock("birch_shutter", Blocks.BIRCH_PLANKS);

	public static final GuitaRegistryEntry<Block> JUNGLE_SHUTTER = createShutterBlock("jungle_shutter", Blocks.JUNGLE_PLANKS);

	public static final GuitaRegistryEntry<Block> ACACIA_SHUTTER = createShutterBlock("acacia_shutter", Blocks.ACACIA_PLANKS);

	public static final GuitaRegistryEntry<Block> DARK_OAK_SHUTTER = createShutterBlock("dark_oak_shutter", Blocks.DARK_OAK_PLANKS);

	public static final GuitaRegistryEntry<Block> MANGROVE_SHUTTER = createShutterBlock("mangrove_shutter", Blocks.MANGROVE_PLANKS);

	public static final GuitaRegistryEntry<Block> CHERRY_SHUTTER = createShutterBlock("cherry_shutter", Blocks.CHERRY_PLANKS);

	public static final GuitaRegistryEntry<Block> BAMBOO_SHUTTER = createShutterBlock("bamboo_shutter", Blocks.BAMBOO_PLANKS);

	public static final GuitaRegistryEntry<Block> PALE_OAK_SHUTTER = createShutterBlock("pale_oak_shutter", Blocks.PALE_OAK_PLANKS);

	public static final GuitaRegistryEntry<Block> CRIMSON_SHUTTER = createShutterBlock("crimson_shutter", Blocks.CRIMSON_PLANKS);

	public static final GuitaRegistryEntry<Block> WARPED_SHUTTER = createShutterBlock("warped_shutter", Blocks.WARPED_PLANKS);

	public static GuitaRegistryEntry<Block> createStump(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, StumpSeatBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STUMP_BLOCKS, STUMP_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedStump(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, settings -> new StumpSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_STUMP_BLOCKS, STRIPPED_STUMP_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createCarvedLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, CarvedLogSeatBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), CARVED_LOG_BLOCKS, CARVED_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedCarvedLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, settings -> new CarvedLogSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_CARVED_LOG_BLOCKS, STRIPPED_CARVED_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createBeam(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, settings -> new ResizableBeamBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), (beamBlock, prop) -> new TooltippedBlockItem(beamBlock, prop, (itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag) -> {
			if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), InputConstants.KEY_LSHIFT)) {
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition1").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior1").withStyle(ChatFormatting.DARK_AQUA));
				consumer.accept(Component.literal(""));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition2").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior2").withStyle(ChatFormatting.DARK_AQUA));
				consumer.accept(Component.literal(""));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition3").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior3").withStyle(ChatFormatting.DARK_AQUA));
				consumer.accept(Component.literal(""));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition4").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior4").withStyle(ChatFormatting.DARK_AQUA));
			}
		}), new Item.Properties().setId(keyOfItem(name)), BEAM_BLOCKS, BEAM_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedBeam(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, settings -> new ResizableBeamBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), (beamBlock, prop) -> new TooltippedBlockItem(beamBlock, prop, (itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag) -> {
			if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), InputConstants.KEY_LSHIFT)) {
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition1").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior1").withStyle(ChatFormatting.DARK_AQUA));
				consumer.accept(Component.literal(""));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition2").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior2").withStyle(ChatFormatting.DARK_AQUA));
				consumer.accept(Component.literal(""));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition3").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior3").withStyle(ChatFormatting.DARK_AQUA));
				consumer.accept(Component.literal(""));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.condition4").withStyle(ChatFormatting.GRAY));
				consumer.accept(Component.translatable("tooltip.gwoodworks.beam.behavior4").withStyle(ChatFormatting.DARK_AQUA));
			}
		}), new Item.Properties().setId(keyOfItem(name)), STRIPPED_BEAM_BLOCKS, STRIPPED_BEAM_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createHollowLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), HOLLOW_LOG_BLOCKS, HOLLOW_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedHollowLog(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, settings -> new HollowLogBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_HOLLOW_LOG_BLOCKS, STRIPPED_HOLLOW_LOG_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createSupportBlock(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, SupportBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), SupportBlockItem::new, new Item.Properties().setId(keyOfItem(name)), SUPPORT_BLOCKS, SUPPORT_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createShutterBlock(String name, Block wood) {
		GuitaRegistryEntry<Block> block = registerWithItem(name, ShutterBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), SHUTTER_BLOCKS, SHUTTER_ITEMS);
		if (GWUtils.isFabric()) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	private static <T extends Block> GuitaRegistryEntry<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings, GuitaRegistry<Block> blockReg, GuitaRegistry<Item> itemReg) {
		return registerWithItem(name, blockFactory, settings, BlockItem::new, new Item.Properties().setId(keyOfItem(name)),blockReg,itemReg);
	}

	private static <T extends Block, B extends BlockItem> GuitaRegistryEntry<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings, BiFunction<T, Item.Properties, B> itemFunc, Item.Properties itemProperties, GuitaRegistry<Block> blockReg, GuitaRegistry<Item> itemReg) {
		GuitaRegistryEntry<T> toReturn = blockReg.register(name, () -> blockFactory.apply(settings.setId(keyOfBlock(name))));
		itemReg.register(name, () -> itemFunc.apply(toReturn.get(),
				new Item.Properties().setId(keyOfItem(name))));
		return toReturn;
	}

	private static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, GuitaWoodworks.id(name));
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, GuitaWoodworks.id(name));
	}

	public static void init() {
		BLOCKS.init();
		ITEMS.init();
	}
}
