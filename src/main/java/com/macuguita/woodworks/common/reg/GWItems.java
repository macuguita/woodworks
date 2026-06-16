package com.macuguita.woodworks.common.reg;

import java.util.function.Function;
import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Block;

import com.macuguita.lib.api.reg.GuitaRegistries;
import com.macuguita.lib.api.reg.GuitaRegistry;
import com.macuguita.lib.api.reg.GuitaRegistryEntry;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.common.item.TooltippedBlockItem;

public class GWItems {

	public static final GuitaRegistry<Item> ITEMS = GuitaRegistries.create(BuiltInRegistries.ITEM, GuitaWoodworks.MOD_ID);

	public static final GuitaRegistry<Item> TOOL_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Item> STUMP_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_STUMP_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Item> CARVED_LOG_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_CARVED_LOG_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Item> BEAM_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_BEAM_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Item> HOLLOW_LOG_ITEMS = GuitaRegistries.create(ITEMS);
	public static final GuitaRegistry<Item> STRIPPED_HOLLOW_LOG_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Item> SUPPORT_ITEMS = GuitaRegistries.create(ITEMS);

	public static final GuitaRegistry<Item> SHUTTER_ITEMS = GuitaRegistries.create(ITEMS);

	//formatter:off
	public static @Nullable GuitaRegistryEntry<Item> SECATEURS;

	public static @Nullable GuitaRegistryEntry<Item> OAK_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_OAK_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> SPRUCE_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_SPRUCE_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> BIRCH_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_BIRCH_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> JUNGLE_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_JUNGLE_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> ACACIA_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_ACACIA_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> DARK_OAK_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_DARK_OAK_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> MANGROVE_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_MANGROVE_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> CHERRY_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CHERRY_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> BAMBOO_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_BAMBOO_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> PALE_OAK_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_PALE_OAK_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> CRIMSON_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CRIMSON_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> WARPED_STUMP;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_WARPED_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> MUSHROOM_STUMP;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_SPRUCE_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_SPRUCE_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_BIRCH_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_BIRCH_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_JUNGLE_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_JUNGLE_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_ACACIA_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_ACACIA_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_DARK_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_DARK_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_MANGROVE_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_MANGROVE_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_CHERRY_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_CHERRY_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_BAMBOO_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_BAMBOO_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_PALE_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_PALE_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_CRIMSON_STEM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_CRIMSON_STEM;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_WARPED_STEM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CARVED_WARPED_STEM;

	public static @Nullable GuitaRegistryEntry<Item> CARVED_MUSHROOM_STEM;

	public static @Nullable GuitaRegistryEntry<Item> OAK_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_OAK_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> SPRUCE_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_SPRUCE_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> BIRCH_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_BIRCH_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> JUNGLE_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_JUNGLE_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> ACACIA_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_ACACIA_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> DARK_OAK_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_DARK_OAK_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> MANGROVE_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_MANGROVE_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> CHERRY_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CHERRY_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> BAMBOO_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_BAMBOO_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> PALE_OAK_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_PALE_OAK_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> CRIMSON_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_CRIMSON_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> WARPED_BEAM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_WARPED_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> MUSHROOM_BEAM;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_SPRUCE_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_SPRUCE_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_BIRCH_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_BIRCH_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_JUNGLE_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_JUNGLE_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_ACACIA_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_ACACIA_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_DARK_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_DARK_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_MANGROVE_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_MANGROVE_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_CHERRY_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_CHERRY_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_BAMBOO_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_BAMBOO_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_PALE_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_PALE_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_CRIMSON_STEM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_CRIMSON_STEM;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_WARPED_STEM;
	public static @Nullable GuitaRegistryEntry<Item> STRIPPED_HOLLOW_WARPED_STEM;

	public static @Nullable GuitaRegistryEntry<Item> HOLLOW_MUSHROOM_STEM;

	public static @Nullable GuitaRegistryEntry<Item> OAK_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> SPRUCE_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> BIRCH_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> JUNGLE_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> ACACIA_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> DARK_OAK_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> MANGROVE_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> CHERRY_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> BAMBOO_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> PALE_OAK_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> CRIMSON_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Item> WARPED_SUPPORT;

	public static @Nullable GuitaRegistryEntry<Item> OAK_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> SPRUCE_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> BIRCH_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> JUNGLE_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> ACACIA_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> DARK_OAK_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> MANGROVE_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> CHERRY_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> BAMBOO_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> PALE_OAK_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> CRIMSON_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Item> WARPED_SHUTTER;
	//formatter:on

	@SuppressWarnings("DataFlowIssue")
	public static void init() {
		var config = GuitaWoodworks.CONFIG;
		if (config.enableBeams) {
			// Relying on neoforge deferred registries makes me do this kind of stuff VVVV
			SECATEURS = register(GWItemIds.SECATEURS, props -> new Item(props.component(DataComponents.TOOL, ShearsItem.createToolProperties())), new Item.Properties().durability(476), TOOL_ITEMS);
		}

		if (config.enableStumps) {
			OAK_STUMP = createStump(GWBlockItemIds.OAK_STUMP, GWBlocks.OAK_STUMP);
			STRIPPED_OAK_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_OAK_STUMP, GWBlocks.STRIPPED_OAK_STUMP);

			SPRUCE_STUMP = createStump(GWBlockItemIds.SPRUCE_STUMP, GWBlocks.SPRUCE_STUMP);
			STRIPPED_SPRUCE_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_SPRUCE_STUMP, GWBlocks.STRIPPED_SPRUCE_STUMP);

			BIRCH_STUMP = createStump(GWBlockItemIds.BIRCH_STUMP, GWBlocks.BIRCH_STUMP);
			STRIPPED_BIRCH_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_BIRCH_STUMP, GWBlocks.STRIPPED_BIRCH_STUMP);

			JUNGLE_STUMP = createStump(GWBlockItemIds.JUNGLE_STUMP, GWBlocks.JUNGLE_STUMP);
			STRIPPED_JUNGLE_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_JUNGLE_STUMP, GWBlocks.STRIPPED_JUNGLE_STUMP);

			ACACIA_STUMP = createStump(GWBlockItemIds.ACACIA_STUMP, GWBlocks.ACACIA_STUMP);
			STRIPPED_ACACIA_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_ACACIA_STUMP, GWBlocks.STRIPPED_ACACIA_STUMP);

			DARK_OAK_STUMP = createStump(GWBlockItemIds.DARK_OAK_STUMP, GWBlocks.DARK_OAK_STUMP);
			STRIPPED_DARK_OAK_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_DARK_OAK_STUMP, GWBlocks.STRIPPED_DARK_OAK_STUMP);

			MANGROVE_STUMP = createStump(GWBlockItemIds.MANGROVE_STUMP, GWBlocks.MANGROVE_STUMP);
			STRIPPED_MANGROVE_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_MANGROVE_STUMP, GWBlocks.STRIPPED_MANGROVE_STUMP);

			CHERRY_STUMP = createStump(GWBlockItemIds.CHERRY_STUMP, GWBlocks.CHERRY_STUMP);
			STRIPPED_CHERRY_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_CHERRY_STUMP, GWBlocks.STRIPPED_CHERRY_STUMP);

			BAMBOO_STUMP = createStump(GWBlockItemIds.BAMBOO_STUMP, GWBlocks.BAMBOO_STUMP);
			STRIPPED_BAMBOO_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_BAMBOO_STUMP, GWBlocks.STRIPPED_BAMBOO_STUMP);

			PALE_OAK_STUMP = createStump(GWBlockItemIds.PALE_OAK_STUMP, GWBlocks.PALE_OAK_STUMP);
			STRIPPED_PALE_OAK_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_PALE_OAK_STUMP, GWBlocks.STRIPPED_PALE_OAK_STUMP);

			CRIMSON_STUMP = createStump(GWBlockItemIds.CRIMSON_STUMP, GWBlocks.CRIMSON_STUMP);
			STRIPPED_CRIMSON_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_CRIMSON_STUMP, GWBlocks.STRIPPED_CRIMSON_STUMP);

			WARPED_STUMP = createStump(GWBlockItemIds.WARPED_STUMP, GWBlocks.WARPED_STUMP);
			STRIPPED_WARPED_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_WARPED_STUMP, GWBlocks.STRIPPED_WARPED_STUMP);

			MUSHROOM_STUMP = createStump(GWBlockItemIds.MUSHROOM_STUMP, GWBlocks.MUSHROOM_STUMP);
		}

		if (config.enableCarvedLogs) {
			CARVED_OAK_LOG = createCarvedLog(GWBlockItemIds.CARVED_OAK_LOG, GWBlocks.CARVED_OAK_LOG);
			STRIPPED_CARVED_OAK_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_OAK_LOG, GWBlocks.STRIPPED_CARVED_OAK_LOG);

			CARVED_SPRUCE_LOG = createCarvedLog(GWBlockItemIds.CARVED_SPRUCE_LOG, GWBlocks.CARVED_SPRUCE_LOG);
			STRIPPED_CARVED_SPRUCE_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_SPRUCE_LOG, GWBlocks.STRIPPED_CARVED_SPRUCE_LOG);

			CARVED_BIRCH_LOG = createCarvedLog(GWBlockItemIds.CARVED_BIRCH_LOG, GWBlocks.CARVED_BIRCH_LOG);
			STRIPPED_CARVED_BIRCH_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_BIRCH_LOG, GWBlocks.STRIPPED_CARVED_BIRCH_LOG);

			CARVED_JUNGLE_LOG = createCarvedLog(GWBlockItemIds.CARVED_JUNGLE_LOG, GWBlocks.CARVED_JUNGLE_LOG);
			STRIPPED_CARVED_JUNGLE_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_JUNGLE_LOG, GWBlocks.STRIPPED_CARVED_JUNGLE_LOG);

			CARVED_ACACIA_LOG = createCarvedLog(GWBlockItemIds.CARVED_ACACIA_LOG, GWBlocks.CARVED_ACACIA_LOG);
			STRIPPED_CARVED_ACACIA_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_ACACIA_LOG, GWBlocks.STRIPPED_CARVED_ACACIA_LOG);

			CARVED_DARK_OAK_LOG = createCarvedLog(GWBlockItemIds.CARVED_DARK_OAK_LOG, GWBlocks.CARVED_DARK_OAK_LOG);
			STRIPPED_CARVED_DARK_OAK_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_DARK_OAK_LOG, GWBlocks.STRIPPED_CARVED_DARK_OAK_LOG);

			CARVED_MANGROVE_LOG = createCarvedLog(GWBlockItemIds.CARVED_MANGROVE_LOG, GWBlocks.CARVED_MANGROVE_LOG);
			STRIPPED_CARVED_MANGROVE_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_MANGROVE_LOG, GWBlocks.STRIPPED_CARVED_MANGROVE_LOG);

			CARVED_CHERRY_LOG = createCarvedLog(GWBlockItemIds.CARVED_CHERRY_LOG, GWBlocks.CARVED_CHERRY_LOG);
			STRIPPED_CARVED_CHERRY_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_CHERRY_LOG, GWBlocks.STRIPPED_CARVED_CHERRY_LOG);

			CARVED_BAMBOO_LOG = createCarvedLog(GWBlockItemIds.CARVED_BAMBOO_LOG, GWBlocks.CARVED_BAMBOO_LOG);
			STRIPPED_CARVED_BAMBOO_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_BAMBOO_LOG, GWBlocks.STRIPPED_CARVED_BAMBOO_LOG);

			CARVED_PALE_OAK_LOG = createCarvedLog(GWBlockItemIds.CARVED_PALE_OAK_LOG, GWBlocks.CARVED_PALE_OAK_LOG);
			STRIPPED_CARVED_PALE_OAK_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_PALE_OAK_LOG, GWBlocks.STRIPPED_CARVED_PALE_OAK_LOG);

			CARVED_CRIMSON_STEM = createCarvedLog(GWBlockItemIds.CARVED_CRIMSON_STEM, GWBlocks.CARVED_CRIMSON_STEM);
			STRIPPED_CARVED_CRIMSON_STEM = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_CRIMSON_STEM, GWBlocks.STRIPPED_CARVED_CRIMSON_STEM);

			CARVED_WARPED_STEM = createCarvedLog(GWBlockItemIds.CARVED_WARPED_STEM, GWBlocks.CARVED_WARPED_STEM);
			STRIPPED_CARVED_WARPED_STEM = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_WARPED_STEM, GWBlocks.STRIPPED_CARVED_WARPED_STEM);

			CARVED_MUSHROOM_STEM = createStrippedCarvedLog(GWBlockItemIds.CARVED_MUSHROOM_STEM, GWBlocks.CARVED_MUSHROOM_STEM);
		}

		if (config.enableBeams) {
			OAK_BEAM = createBeam(GWBlockItemIds.OAK_BEAM, GWBlocks.OAK_BEAM);
			STRIPPED_OAK_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_OAK_BEAM, GWBlocks.STRIPPED_OAK_BEAM);

			SPRUCE_BEAM = createBeam(GWBlockItemIds.SPRUCE_BEAM, GWBlocks.SPRUCE_BEAM);
			STRIPPED_SPRUCE_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_SPRUCE_BEAM, GWBlocks.STRIPPED_SPRUCE_BEAM);

			BIRCH_BEAM = createBeam(GWBlockItemIds.BIRCH_BEAM, GWBlocks.BIRCH_BEAM);
			STRIPPED_BIRCH_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_BIRCH_BEAM, GWBlocks.STRIPPED_BIRCH_BEAM);

			JUNGLE_BEAM = createBeam(GWBlockItemIds.JUNGLE_BEAM, GWBlocks.JUNGLE_BEAM);
			STRIPPED_JUNGLE_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_JUNGLE_BEAM, GWBlocks.STRIPPED_JUNGLE_BEAM);

			ACACIA_BEAM = createBeam(GWBlockItemIds.ACACIA_BEAM, GWBlocks.ACACIA_BEAM);
			STRIPPED_ACACIA_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_ACACIA_BEAM, GWBlocks.STRIPPED_ACACIA_BEAM);

			DARK_OAK_BEAM = createBeam(GWBlockItemIds.DARK_OAK_BEAM, GWBlocks.DARK_OAK_BEAM);
			STRIPPED_DARK_OAK_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_DARK_OAK_BEAM, GWBlocks.STRIPPED_DARK_OAK_BEAM);

			MANGROVE_BEAM = createBeam(GWBlockItemIds.MANGROVE_BEAM, GWBlocks.MANGROVE_BEAM);
			STRIPPED_MANGROVE_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_MANGROVE_BEAM, GWBlocks.STRIPPED_MANGROVE_BEAM);

			CHERRY_BEAM = createBeam(GWBlockItemIds.CHERRY_BEAM, GWBlocks.CHERRY_BEAM);
			STRIPPED_CHERRY_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_CHERRY_BEAM, GWBlocks.STRIPPED_CHERRY_BEAM);

			BAMBOO_BEAM = createBeam(GWBlockItemIds.BAMBOO_BEAM, GWBlocks.BAMBOO_BEAM);
			STRIPPED_BAMBOO_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_BAMBOO_BEAM, GWBlocks.STRIPPED_BAMBOO_BEAM);

			PALE_OAK_BEAM = createBeam(GWBlockItemIds.PALE_OAK_BEAM, GWBlocks.PALE_OAK_BEAM);
			STRIPPED_PALE_OAK_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_PALE_OAK_BEAM, GWBlocks.STRIPPED_PALE_OAK_BEAM);

			CRIMSON_BEAM = createBeam(GWBlockItemIds.CRIMSON_BEAM, GWBlocks.CRIMSON_BEAM);
			STRIPPED_CRIMSON_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_CRIMSON_BEAM, GWBlocks.STRIPPED_CRIMSON_BEAM);

			WARPED_BEAM = createBeam(GWBlockItemIds.WARPED_BEAM, GWBlocks.WARPED_BEAM);
			STRIPPED_WARPED_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_WARPED_BEAM, GWBlocks.STRIPPED_WARPED_BEAM);

			MUSHROOM_BEAM = createBeam(GWBlockItemIds.MUSHROOM_BEAM, GWBlocks.MUSHROOM_BEAM);
		}

		if (config.enableHollowLogs) {
			HOLLOW_OAK_LOG = createHollowLog(GWBlockItemIds.HOLLOW_OAK_LOG, GWBlocks.HOLLOW_OAK_LOG);
			STRIPPED_HOLLOW_OAK_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_OAK_LOG, GWBlocks.STRIPPED_HOLLOW_OAK_LOG);

			HOLLOW_SPRUCE_LOG = createHollowLog(GWBlockItemIds.HOLLOW_SPRUCE_LOG, GWBlocks.HOLLOW_SPRUCE_LOG);
			STRIPPED_HOLLOW_SPRUCE_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_SPRUCE_LOG, GWBlocks.STRIPPED_HOLLOW_SPRUCE_LOG);

			HOLLOW_BIRCH_LOG = createHollowLog(GWBlockItemIds.HOLLOW_BIRCH_LOG, GWBlocks.HOLLOW_BIRCH_LOG);
			STRIPPED_HOLLOW_BIRCH_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_BIRCH_LOG, GWBlocks.STRIPPED_HOLLOW_BIRCH_LOG);

			HOLLOW_JUNGLE_LOG = createHollowLog(GWBlockItemIds.HOLLOW_JUNGLE_LOG, GWBlocks.HOLLOW_JUNGLE_LOG);
			STRIPPED_HOLLOW_JUNGLE_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_JUNGLE_LOG, GWBlocks.STRIPPED_HOLLOW_JUNGLE_LOG);

			HOLLOW_ACACIA_LOG = createHollowLog(GWBlockItemIds.HOLLOW_ACACIA_LOG, GWBlocks.HOLLOW_ACACIA_LOG);
			STRIPPED_HOLLOW_ACACIA_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_ACACIA_LOG, GWBlocks.STRIPPED_HOLLOW_ACACIA_LOG);

			HOLLOW_DARK_OAK_LOG = createHollowLog(GWBlockItemIds.HOLLOW_DARK_OAK_LOG, GWBlocks.HOLLOW_DARK_OAK_LOG);
			STRIPPED_HOLLOW_DARK_OAK_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_DARK_OAK_LOG, GWBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG);

			HOLLOW_MANGROVE_LOG = createHollowLog(GWBlockItemIds.HOLLOW_MANGROVE_LOG, GWBlocks.HOLLOW_MANGROVE_LOG);
			STRIPPED_HOLLOW_MANGROVE_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_MANGROVE_LOG, GWBlocks.STRIPPED_HOLLOW_MANGROVE_LOG);

			HOLLOW_CHERRY_LOG = createHollowLog(GWBlockItemIds.HOLLOW_CHERRY_LOG, GWBlocks.HOLLOW_CHERRY_LOG);
			STRIPPED_HOLLOW_CHERRY_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_CHERRY_LOG, GWBlocks.STRIPPED_HOLLOW_CHERRY_LOG);

			HOLLOW_BAMBOO_LOG = createHollowLog(GWBlockItemIds.HOLLOW_BAMBOO_LOG, GWBlocks.HOLLOW_BAMBOO_LOG);
			STRIPPED_HOLLOW_BAMBOO_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_BAMBOO_LOG, GWBlocks.STRIPPED_HOLLOW_BAMBOO_LOG);

			HOLLOW_PALE_OAK_LOG = createHollowLog(GWBlockItemIds.HOLLOW_PALE_OAK_LOG, GWBlocks.HOLLOW_PALE_OAK_LOG);
			STRIPPED_HOLLOW_PALE_OAK_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_PALE_OAK_LOG, GWBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG);

			HOLLOW_CRIMSON_STEM = createHollowLog(GWBlockItemIds.HOLLOW_CRIMSON_STEM, GWBlocks.HOLLOW_CRIMSON_STEM);
			STRIPPED_HOLLOW_CRIMSON_STEM = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_CRIMSON_STEM, GWBlocks.STRIPPED_HOLLOW_CRIMSON_STEM);

			HOLLOW_WARPED_STEM = createHollowLog(GWBlockItemIds.HOLLOW_WARPED_STEM, GWBlocks.HOLLOW_WARPED_STEM);
			STRIPPED_HOLLOW_WARPED_STEM = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_WARPED_STEM, GWBlocks.STRIPPED_HOLLOW_WARPED_STEM);

			HOLLOW_MUSHROOM_STEM = createHollowLog(GWBlockItemIds.HOLLOW_MUSHROOM_STEM, GWBlocks.HOLLOW_MUSHROOM_STEM);
		}

		if (config.enableSupports) {
			OAK_SUPPORT = createSupportBlock(GWBlockItemIds.OAK_SUPPORT, GWBlocks.OAK_SUPPORT);
			SPRUCE_SUPPORT = createSupportBlock(GWBlockItemIds.SPRUCE_SUPPORT, GWBlocks.SPRUCE_SUPPORT);
			BIRCH_SUPPORT = createSupportBlock(GWBlockItemIds.BIRCH_SUPPORT, GWBlocks.BIRCH_SUPPORT);
			JUNGLE_SUPPORT = createSupportBlock(GWBlockItemIds.JUNGLE_SUPPORT, GWBlocks.JUNGLE_SUPPORT);
			ACACIA_SUPPORT = createSupportBlock(GWBlockItemIds.ACACIA_SUPPORT, GWBlocks.ACACIA_SUPPORT);
			DARK_OAK_SUPPORT = createSupportBlock(GWBlockItemIds.DARK_OAK_SUPPORT, GWBlocks.DARK_OAK_SUPPORT);
			MANGROVE_SUPPORT = createSupportBlock(GWBlockItemIds.MANGROVE_SUPPORT, GWBlocks.MANGROVE_SUPPORT);
			CHERRY_SUPPORT = createSupportBlock(GWBlockItemIds.CHERRY_SUPPORT, GWBlocks.CHERRY_SUPPORT);
			BAMBOO_SUPPORT = createSupportBlock(GWBlockItemIds.BAMBOO_SUPPORT, GWBlocks.BAMBOO_SUPPORT);
			PALE_OAK_SUPPORT = createSupportBlock(GWBlockItemIds.PALE_OAK_SUPPORT, GWBlocks.PALE_OAK_SUPPORT);
			CRIMSON_SUPPORT = createSupportBlock(GWBlockItemIds.CRIMSON_SUPPORT, GWBlocks.CRIMSON_SUPPORT);
			WARPED_SUPPORT = createSupportBlock(GWBlockItemIds.WARPED_SUPPORT, GWBlocks.WARPED_SUPPORT);
		}

		if (config.enableShutters) {
			OAK_SHUTTER = createShutterBlock(GWBlockItemIds.OAK_SHUTTER, GWBlocks.OAK_SHUTTER);
			SPRUCE_SHUTTER = createShutterBlock(GWBlockItemIds.SPRUCE_SHUTTER, GWBlocks.SPRUCE_SHUTTER);
			BIRCH_SHUTTER = createShutterBlock(GWBlockItemIds.BIRCH_SHUTTER, GWBlocks.BIRCH_SHUTTER);
			JUNGLE_SHUTTER = createShutterBlock(GWBlockItemIds.JUNGLE_SHUTTER, GWBlocks.JUNGLE_SHUTTER);
			ACACIA_SHUTTER = createShutterBlock(GWBlockItemIds.ACACIA_SHUTTER, GWBlocks.ACACIA_SHUTTER);
			DARK_OAK_SHUTTER = createShutterBlock(GWBlockItemIds.DARK_OAK_SHUTTER, GWBlocks.DARK_OAK_SHUTTER);
			MANGROVE_SHUTTER = createShutterBlock(GWBlockItemIds.MANGROVE_SHUTTER, GWBlocks.MANGROVE_SHUTTER);
			CHERRY_SHUTTER = createShutterBlock(GWBlockItemIds.CHERRY_SHUTTER, GWBlocks.CHERRY_SHUTTER);
			BAMBOO_SHUTTER = createShutterBlock(GWBlockItemIds.BAMBOO_SHUTTER, GWBlocks.BAMBOO_SHUTTER);
			PALE_OAK_SHUTTER = createShutterBlock(GWBlockItemIds.PALE_OAK_SHUTTER, GWBlocks.PALE_OAK_SHUTTER);
			CRIMSON_SHUTTER = createShutterBlock(GWBlockItemIds.CRIMSON_SHUTTER, GWBlocks.CRIMSON_SHUTTER);
			WARPED_SHUTTER = createShutterBlock(GWBlockItemIds.WARPED_SHUTTER, GWBlocks.WARPED_SHUTTER);
		}

		ITEMS.init();
	}

	public static GuitaRegistryEntry<Item> createStump(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), STUMP_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createStrippedStump(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), STRIPPED_STUMP_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createCarvedLog(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), CARVED_LOG_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createStrippedCarvedLog(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), STRIPPED_CARVED_LOG_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createBeam(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return register(res, prop -> new TooltippedBlockItem(blockEntry.get(), prop, (itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag) -> {
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
		}), new Item.Properties(), BEAM_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createStrippedBeam(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return register(res, prop -> new TooltippedBlockItem(blockEntry.get(), prop, (itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag) -> {
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
		}), new Item.Properties(), STRIPPED_BEAM_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createHollowLog(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), HOLLOW_LOG_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createStrippedHollowLog(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), STRIPPED_HOLLOW_LOG_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createSupportBlock(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), SUPPORT_ITEMS);
	}

	public static GuitaRegistryEntry<Item> createShutterBlock(BlockItemId res, GuitaRegistryEntry<Block> blockEntry) {
		return registerBlockItem(res, blockEntry, new Item.Properties(), SHUTTER_ITEMS);
	}

	private static GuitaRegistryEntry<Item> registerBlockItem(BlockItemId res, GuitaRegistryEntry<Block> blockEntry, Item.Properties properties, GuitaRegistry<Item> itemReg) {
		return register(res, (props) -> new BlockItem(blockEntry.get(), props), properties, itemReg);
	}

	private static GuitaRegistryEntry<Item> register(BlockItemId res, Function<Item.Properties, ? extends Item> itemFactory, Item.Properties properties, GuitaRegistry<Item> itemReg) {
		return register(res.item(), itemFactory, properties, itemReg);
	}

	private static GuitaRegistryEntry<Item> register(ResourceKey<Item> res, Function<Item.Properties, ? extends Item> itemFactory, Item.Properties properties, GuitaRegistry<Item> itemReg) {
		return itemReg.register(
			res.identifier().getPath(),
			() -> itemFactory.apply(properties.setId(res))
		);
	}
}
