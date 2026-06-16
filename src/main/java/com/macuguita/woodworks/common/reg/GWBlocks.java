package com.macuguita.woodworks.common.reg;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.macuguita.lib.api.reg.GuitaRegistries;
import com.macuguita.lib.api.reg.GuitaRegistry;
import com.macuguita.lib.api.reg.GuitaRegistryEntry;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.Platform;
import com.macuguita.woodworks.common.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.common.block.HollowLogBlock;
import com.macuguita.woodworks.common.block.ResizableBeamBlock;
import com.macuguita.woodworks.common.block.ShutterBlock;
import com.macuguita.woodworks.common.block.StumpSeatBlock;
import com.macuguita.woodworks.common.block.SupportBlock;

public class GWBlocks {

	public static final Map<Block, Block> WOOD_ASSOCIATIONS = new HashMap<>();

	public static final GuitaRegistry<Block> BLOCKS = GuitaRegistries.create(BuiltInRegistries.BLOCK, GuitaWoodworks.MOD_ID);

	public static final GuitaRegistry<Block> STUMP_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_STUMP_BLOCKS = GuitaRegistries.create(BLOCKS);

	public static final GuitaRegistry<Block> CARVED_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_CARVED_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);

	public static final GuitaRegistry<Block> BEAM_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_BEAM_BLOCKS = GuitaRegistries.create(BLOCKS);

	public static final GuitaRegistry<Block> HOLLOW_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);
	public static final GuitaRegistry<Block> STRIPPED_HOLLOW_LOG_BLOCKS = GuitaRegistries.create(BLOCKS);

	public static final GuitaRegistry<Block> SUPPORT_BLOCKS = GuitaRegistries.create(BLOCKS);

	public static final GuitaRegistry<Block> SHUTTER_BLOCKS = GuitaRegistries.create(BLOCKS);

	//formatter:off
	public static @Nullable GuitaRegistryEntry<Block> OAK_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_OAK_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> SPRUCE_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_SPRUCE_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> BIRCH_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_BIRCH_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> JUNGLE_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_JUNGLE_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> ACACIA_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_ACACIA_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> DARK_OAK_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_DARK_OAK_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> MANGROVE_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_MANGROVE_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> CHERRY_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CHERRY_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> BAMBOO_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_BAMBOO_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> PALE_OAK_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_PALE_OAK_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> CRIMSON_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CRIMSON_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> WARPED_STUMP;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_WARPED_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> MUSHROOM_STUMP;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_SPRUCE_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_SPRUCE_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_BIRCH_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_BIRCH_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_JUNGLE_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_JUNGLE_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_ACACIA_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_ACACIA_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_DARK_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_DARK_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_MANGROVE_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_MANGROVE_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_CHERRY_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_CHERRY_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_BAMBOO_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_BAMBOO_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_PALE_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_PALE_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_CRIMSON_STEM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_CRIMSON_STEM;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_WARPED_STEM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CARVED_WARPED_STEM;

	public static @Nullable GuitaRegistryEntry<Block> CARVED_MUSHROOM_STEM;

	public static @Nullable GuitaRegistryEntry<Block> OAK_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_OAK_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> SPRUCE_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_SPRUCE_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> BIRCH_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_BIRCH_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> JUNGLE_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_JUNGLE_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> ACACIA_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_ACACIA_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> DARK_OAK_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_DARK_OAK_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> MANGROVE_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_MANGROVE_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> CHERRY_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CHERRY_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> BAMBOO_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_BAMBOO_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> PALE_OAK_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_PALE_OAK_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> CRIMSON_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_CRIMSON_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> WARPED_BEAM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_WARPED_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> MUSHROOM_BEAM;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_SPRUCE_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_SPRUCE_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_BIRCH_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_BIRCH_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_JUNGLE_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_JUNGLE_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_ACACIA_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_ACACIA_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_DARK_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_DARK_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_MANGROVE_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_MANGROVE_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_CHERRY_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_CHERRY_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_BAMBOO_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_BAMBOO_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_PALE_OAK_LOG;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_PALE_OAK_LOG;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_CRIMSON_STEM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_CRIMSON_STEM;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_WARPED_STEM;
	public static @Nullable GuitaRegistryEntry<Block> STRIPPED_HOLLOW_WARPED_STEM;

	public static @Nullable GuitaRegistryEntry<Block> HOLLOW_MUSHROOM_STEM;

	public static @Nullable GuitaRegistryEntry<Block> OAK_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> SPRUCE_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> BIRCH_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> JUNGLE_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> ACACIA_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> DARK_OAK_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> MANGROVE_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> CHERRY_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> BAMBOO_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> PALE_OAK_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> CRIMSON_SUPPORT;
	public static @Nullable GuitaRegistryEntry<Block> WARPED_SUPPORT;

	public static @Nullable GuitaRegistryEntry<Block> OAK_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> SPRUCE_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> BIRCH_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> JUNGLE_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> ACACIA_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> DARK_OAK_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> MANGROVE_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> CHERRY_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> BAMBOO_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> PALE_OAK_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> CRIMSON_SHUTTER;
	public static @Nullable GuitaRegistryEntry<Block> WARPED_SHUTTER;
	//formatter:on

	public static void init() {
		var config = GuitaWoodworks.CONFIG;

		if (config.enableStumps) {
			OAK_STUMP = createStump(GWBlockItemIds.OAK_STUMP, Blocks.OAK_LOG);
			STRIPPED_OAK_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_OAK_STUMP, Blocks.STRIPPED_OAK_LOG);

			SPRUCE_STUMP = createStump(GWBlockItemIds.SPRUCE_STUMP, Blocks.SPRUCE_LOG);
			STRIPPED_SPRUCE_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_SPRUCE_STUMP, Blocks.STRIPPED_SPRUCE_LOG);

			BIRCH_STUMP = createStump(GWBlockItemIds.BIRCH_STUMP, Blocks.BIRCH_LOG);
			STRIPPED_BIRCH_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_BIRCH_STUMP, Blocks.STRIPPED_BIRCH_LOG);

			JUNGLE_STUMP = createStump(GWBlockItemIds.JUNGLE_STUMP, Blocks.JUNGLE_LOG);
			STRIPPED_JUNGLE_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_JUNGLE_STUMP, Blocks.STRIPPED_JUNGLE_LOG);

			ACACIA_STUMP = createStump(GWBlockItemIds.ACACIA_STUMP, Blocks.ACACIA_LOG);
			STRIPPED_ACACIA_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_ACACIA_STUMP, Blocks.STRIPPED_ACACIA_LOG);

			DARK_OAK_STUMP = createStump(GWBlockItemIds.DARK_OAK_STUMP, Blocks.DARK_OAK_LOG);
			STRIPPED_DARK_OAK_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_DARK_OAK_STUMP, Blocks.STRIPPED_DARK_OAK_LOG);

			MANGROVE_STUMP = createStump(GWBlockItemIds.MANGROVE_STUMP, Blocks.MANGROVE_LOG);
			STRIPPED_MANGROVE_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_MANGROVE_STUMP, Blocks.STRIPPED_MANGROVE_LOG);

			CHERRY_STUMP = createStump(GWBlockItemIds.CHERRY_STUMP, Blocks.CHERRY_LOG);
			STRIPPED_CHERRY_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_CHERRY_STUMP, Blocks.STRIPPED_CHERRY_LOG);

			BAMBOO_STUMP = createStump(GWBlockItemIds.BAMBOO_STUMP, Blocks.BAMBOO_BLOCK);
			STRIPPED_BAMBOO_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_BAMBOO_STUMP, Blocks.STRIPPED_BAMBOO_BLOCK);

			PALE_OAK_STUMP = createStump(GWBlockItemIds.PALE_OAK_STUMP, Blocks.PALE_OAK_LOG);
			STRIPPED_PALE_OAK_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_PALE_OAK_STUMP, Blocks.STRIPPED_PALE_OAK_LOG);

			CRIMSON_STUMP = createStump(GWBlockItemIds.CRIMSON_STUMP, Blocks.CRIMSON_STEM);
			STRIPPED_CRIMSON_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_CRIMSON_STUMP, Blocks.STRIPPED_CRIMSON_STEM);

			WARPED_STUMP = createStump(GWBlockItemIds.WARPED_STUMP, Blocks.WARPED_STEM);
			STRIPPED_WARPED_STUMP = createStrippedStump(GWBlockItemIds.STRIPPED_WARPED_STUMP, Blocks.STRIPPED_WARPED_STEM);

			MUSHROOM_STUMP = register(GWBlockItemIds.MUSHROOM_STUMP, settings -> new StumpSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS);
		}

		if (config.enableCarvedLogs) {
			CARVED_OAK_LOG = createCarvedLog(GWBlockItemIds.CARVED_OAK_LOG, Blocks.OAK_LOG);
			STRIPPED_CARVED_OAK_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_OAK_LOG, Blocks.STRIPPED_OAK_LOG);

			CARVED_SPRUCE_LOG = createCarvedLog(GWBlockItemIds.CARVED_SPRUCE_LOG, Blocks.SPRUCE_LOG);
			STRIPPED_CARVED_SPRUCE_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG);

			CARVED_BIRCH_LOG = createCarvedLog(GWBlockItemIds.CARVED_BIRCH_LOG, Blocks.BIRCH_LOG);
			STRIPPED_CARVED_BIRCH_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG);

			CARVED_JUNGLE_LOG = createCarvedLog(GWBlockItemIds.CARVED_JUNGLE_LOG, Blocks.JUNGLE_LOG);
			STRIPPED_CARVED_JUNGLE_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG);

			CARVED_ACACIA_LOG = createCarvedLog(GWBlockItemIds.CARVED_ACACIA_LOG, Blocks.ACACIA_LOG);
			STRIPPED_CARVED_ACACIA_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG);

			CARVED_DARK_OAK_LOG = createCarvedLog(GWBlockItemIds.CARVED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG);
			STRIPPED_CARVED_DARK_OAK_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG);

			CARVED_MANGROVE_LOG = createCarvedLog(GWBlockItemIds.CARVED_MANGROVE_LOG, Blocks.MANGROVE_LOG);
			STRIPPED_CARVED_MANGROVE_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG);

			CARVED_CHERRY_LOG = createCarvedLog(GWBlockItemIds.CARVED_CHERRY_LOG, Blocks.CHERRY_LOG);
			STRIPPED_CARVED_CHERRY_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG);

			CARVED_BAMBOO_LOG = createCarvedLog(GWBlockItemIds.CARVED_BAMBOO_LOG, Blocks.BAMBOO_BLOCK);
			STRIPPED_CARVED_BAMBOO_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_BAMBOO_LOG, Blocks.STRIPPED_BAMBOO_BLOCK);

			CARVED_PALE_OAK_LOG = createCarvedLog(GWBlockItemIds.CARVED_PALE_OAK_LOG, Blocks.PALE_OAK_LOG);
			STRIPPED_CARVED_PALE_OAK_LOG = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_PALE_OAK_LOG, Blocks.STRIPPED_PALE_OAK_LOG);

			CARVED_CRIMSON_STEM = createCarvedLog(GWBlockItemIds.CARVED_CRIMSON_STEM, Blocks.CRIMSON_STEM);
			STRIPPED_CARVED_CRIMSON_STEM = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM);

			CARVED_WARPED_STEM = createCarvedLog(GWBlockItemIds.CARVED_WARPED_STEM, Blocks.WARPED_STEM);
			STRIPPED_CARVED_WARPED_STEM = createStrippedCarvedLog(GWBlockItemIds.STRIPPED_CARVED_WARPED_STEM, Blocks.STRIPPED_WARPED_STEM);

			CARVED_MUSHROOM_STEM = register(GWBlockItemIds.CARVED_MUSHROOM_STEM, settings -> new CarvedLogSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS);
		}

		if (config.enableBeams) {
			OAK_BEAM = createBeam(GWBlockItemIds.OAK_BEAM, Blocks.OAK_LOG);
			STRIPPED_OAK_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_OAK_BEAM, Blocks.STRIPPED_OAK_LOG);

			SPRUCE_BEAM = createBeam(GWBlockItemIds.SPRUCE_BEAM, Blocks.SPRUCE_LOG);
			STRIPPED_SPRUCE_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_SPRUCE_BEAM, Blocks.STRIPPED_SPRUCE_LOG);

			BIRCH_BEAM = createBeam(GWBlockItemIds.BIRCH_BEAM, Blocks.BIRCH_LOG);
			STRIPPED_BIRCH_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_BIRCH_BEAM, Blocks.STRIPPED_BIRCH_LOG);

			JUNGLE_BEAM = createBeam(GWBlockItemIds.JUNGLE_BEAM, Blocks.JUNGLE_LOG);
			STRIPPED_JUNGLE_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_JUNGLE_BEAM, Blocks.STRIPPED_JUNGLE_LOG);

			ACACIA_BEAM = createBeam(GWBlockItemIds.ACACIA_BEAM, Blocks.ACACIA_LOG);
			STRIPPED_ACACIA_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_ACACIA_BEAM, Blocks.STRIPPED_ACACIA_LOG);

			DARK_OAK_BEAM = createBeam(GWBlockItemIds.DARK_OAK_BEAM, Blocks.DARK_OAK_LOG);
			STRIPPED_DARK_OAK_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_DARK_OAK_BEAM, Blocks.STRIPPED_DARK_OAK_LOG);

			MANGROVE_BEAM = createBeam(GWBlockItemIds.MANGROVE_BEAM, Blocks.MANGROVE_LOG);
			STRIPPED_MANGROVE_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_MANGROVE_BEAM, Blocks.STRIPPED_MANGROVE_LOG);

			CHERRY_BEAM = createBeam(GWBlockItemIds.CHERRY_BEAM, Blocks.CHERRY_LOG);
			STRIPPED_CHERRY_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_CHERRY_BEAM, Blocks.STRIPPED_CHERRY_LOG);

			BAMBOO_BEAM = createBeam(GWBlockItemIds.BAMBOO_BEAM, Blocks.BAMBOO_BLOCK);
			STRIPPED_BAMBOO_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_BAMBOO_BEAM, Blocks.STRIPPED_BAMBOO_BLOCK);

			PALE_OAK_BEAM = createBeam(GWBlockItemIds.PALE_OAK_BEAM, Blocks.PALE_OAK_LOG);
			STRIPPED_PALE_OAK_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_PALE_OAK_BEAM, Blocks.STRIPPED_PALE_OAK_LOG);

			CRIMSON_BEAM = createBeam(GWBlockItemIds.CRIMSON_BEAM, Blocks.CRIMSON_STEM);
			STRIPPED_CRIMSON_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_CRIMSON_BEAM, Blocks.STRIPPED_CRIMSON_STEM);

			WARPED_BEAM = createBeam(GWBlockItemIds.WARPED_BEAM, Blocks.WARPED_STEM);
			STRIPPED_WARPED_BEAM = createStrippedBeam(GWBlockItemIds.STRIPPED_WARPED_BEAM, Blocks.STRIPPED_WARPED_STEM);

			MUSHROOM_BEAM = register(GWBlockItemIds.MUSHROOM_BEAM, settings -> new ResizableBeamBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS);
		}

		if (config.enableHollowLogs) {
			HOLLOW_OAK_LOG = createHollowLog(GWBlockItemIds.HOLLOW_OAK_LOG, Blocks.OAK_LOG);
			STRIPPED_HOLLOW_OAK_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_OAK_LOG, Blocks.STRIPPED_OAK_LOG);

			HOLLOW_SPRUCE_LOG = createHollowLog(GWBlockItemIds.HOLLOW_SPRUCE_LOG, Blocks.SPRUCE_LOG);
			STRIPPED_HOLLOW_SPRUCE_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG);

			HOLLOW_BIRCH_LOG = createHollowLog(GWBlockItemIds.HOLLOW_BIRCH_LOG, Blocks.BIRCH_LOG);
			STRIPPED_HOLLOW_BIRCH_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG);

			HOLLOW_JUNGLE_LOG = createHollowLog(GWBlockItemIds.HOLLOW_JUNGLE_LOG, Blocks.JUNGLE_LOG);
			STRIPPED_HOLLOW_JUNGLE_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG);

			HOLLOW_ACACIA_LOG = createHollowLog(GWBlockItemIds.HOLLOW_ACACIA_LOG, Blocks.ACACIA_LOG);
			STRIPPED_HOLLOW_ACACIA_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG);

			HOLLOW_DARK_OAK_LOG = createHollowLog(GWBlockItemIds.HOLLOW_DARK_OAK_LOG, Blocks.DARK_OAK_LOG);
			STRIPPED_HOLLOW_DARK_OAK_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG);

			HOLLOW_MANGROVE_LOG = createHollowLog(GWBlockItemIds.HOLLOW_MANGROVE_LOG, Blocks.MANGROVE_LOG);
			STRIPPED_HOLLOW_MANGROVE_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG);

			HOLLOW_CHERRY_LOG = createHollowLog(GWBlockItemIds.HOLLOW_CHERRY_LOG, Blocks.CHERRY_LOG);
			STRIPPED_HOLLOW_CHERRY_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG);

			HOLLOW_BAMBOO_LOG = createHollowLog(GWBlockItemIds.HOLLOW_BAMBOO_LOG, Blocks.BAMBOO_BLOCK);
			STRIPPED_HOLLOW_BAMBOO_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_BAMBOO_LOG, Blocks.STRIPPED_BAMBOO_BLOCK);

			HOLLOW_PALE_OAK_LOG = createHollowLog(GWBlockItemIds.HOLLOW_PALE_OAK_LOG, Blocks.PALE_OAK_LOG);
			STRIPPED_HOLLOW_PALE_OAK_LOG = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_PALE_OAK_LOG, Blocks.STRIPPED_PALE_OAK_LOG);

			HOLLOW_CRIMSON_STEM = createHollowLog(GWBlockItemIds.HOLLOW_CRIMSON_STEM, Blocks.CRIMSON_STEM);
			STRIPPED_HOLLOW_CRIMSON_STEM = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM);

			HOLLOW_WARPED_STEM = createHollowLog(GWBlockItemIds.HOLLOW_WARPED_STEM, Blocks.WARPED_STEM);
			STRIPPED_HOLLOW_WARPED_STEM = createStrippedHollowLog(GWBlockItemIds.STRIPPED_HOLLOW_WARPED_STEM, Blocks.STRIPPED_WARPED_STEM);

			HOLLOW_MUSHROOM_STEM = register(GWBlockItemIds.HOLLOW_MUSHROOM_STEM, settings -> new HollowLogBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).mapColor(Blocks.MUSHROOM_STEM.defaultMapColor()), BLOCKS);
		}

		if (config.enableSupports) {
			OAK_SUPPORT = createSupportBlock(GWBlockItemIds.OAK_SUPPORT, Blocks.OAK_PLANKS);
			SPRUCE_SUPPORT = createSupportBlock(GWBlockItemIds.SPRUCE_SUPPORT, Blocks.SPRUCE_PLANKS);
			BIRCH_SUPPORT = createSupportBlock(GWBlockItemIds.BIRCH_SUPPORT, Blocks.BIRCH_PLANKS);
			JUNGLE_SUPPORT = createSupportBlock(GWBlockItemIds.JUNGLE_SUPPORT, Blocks.JUNGLE_PLANKS);
			ACACIA_SUPPORT = createSupportBlock(GWBlockItemIds.ACACIA_SUPPORT, Blocks.ACACIA_PLANKS);
			DARK_OAK_SUPPORT = createSupportBlock(GWBlockItemIds.DARK_OAK_SUPPORT, Blocks.DARK_OAK_PLANKS);
			MANGROVE_SUPPORT = createSupportBlock(GWBlockItemIds.MANGROVE_SUPPORT, Blocks.MANGROVE_PLANKS);
			CHERRY_SUPPORT = createSupportBlock(GWBlockItemIds.CHERRY_SUPPORT, Blocks.CHERRY_PLANKS);
			BAMBOO_SUPPORT = createSupportBlock(GWBlockItemIds.BAMBOO_SUPPORT, Blocks.BAMBOO_PLANKS);
			PALE_OAK_SUPPORT = createSupportBlock(GWBlockItemIds.PALE_OAK_SUPPORT, Blocks.PALE_OAK_PLANKS);
			CRIMSON_SUPPORT = createSupportBlock(GWBlockItemIds.CRIMSON_SUPPORT, Blocks.CRIMSON_PLANKS);
			WARPED_SUPPORT = createSupportBlock(GWBlockItemIds.WARPED_SUPPORT, Blocks.WARPED_PLANKS);
		}

		if (config.enableShutters) {
			OAK_SHUTTER = createShutterBlock(GWBlockItemIds.OAK_SHUTTER, Blocks.OAK_PLANKS);
			SPRUCE_SHUTTER = createShutterBlock(GWBlockItemIds.SPRUCE_SHUTTER, Blocks.SPRUCE_PLANKS);
			BIRCH_SHUTTER = createShutterBlock(GWBlockItemIds.BIRCH_SHUTTER, Blocks.BIRCH_PLANKS);
			JUNGLE_SHUTTER = createShutterBlock(GWBlockItemIds.JUNGLE_SHUTTER, Blocks.JUNGLE_PLANKS);
			ACACIA_SHUTTER = createShutterBlock(GWBlockItemIds.ACACIA_SHUTTER, Blocks.ACACIA_PLANKS);
			DARK_OAK_SHUTTER = createShutterBlock(GWBlockItemIds.DARK_OAK_SHUTTER, Blocks.DARK_OAK_PLANKS);
			MANGROVE_SHUTTER = createShutterBlock(GWBlockItemIds.MANGROVE_SHUTTER, Blocks.MANGROVE_PLANKS);
			CHERRY_SHUTTER = createShutterBlock(GWBlockItemIds.CHERRY_SHUTTER, Blocks.CHERRY_PLANKS);
			BAMBOO_SHUTTER = createShutterBlock(GWBlockItemIds.BAMBOO_SHUTTER, Blocks.BAMBOO_PLANKS);
			PALE_OAK_SHUTTER = createShutterBlock(GWBlockItemIds.PALE_OAK_SHUTTER, Blocks.PALE_OAK_PLANKS);
			CRIMSON_SHUTTER = createShutterBlock(GWBlockItemIds.CRIMSON_SHUTTER, Blocks.CRIMSON_PLANKS);
			WARPED_SHUTTER = createShutterBlock(GWBlockItemIds.WARPED_SHUTTER, Blocks.WARPED_PLANKS);
		}

		BLOCKS.init();
	}

	public static GuitaRegistryEntry<Block> createStump(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, StumpSeatBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STUMP_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedStump(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, settings -> new StumpSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_STUMP_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createCarvedLog(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, CarvedLogSeatBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), CARVED_LOG_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedCarvedLog(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, settings -> new CarvedLogSeatBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_CARVED_LOG_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createBeam(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, settings -> new ResizableBeamBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), BEAM_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedBeam(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, settings -> new ResizableBeamBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_BEAM_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createHollowLog(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), HOLLOW_LOG_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createStrippedHollowLog(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, settings -> new HollowLogBlock(settings, false), BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), STRIPPED_HOLLOW_LOG_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createSupportBlock(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, SupportBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), SUPPORT_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	public static GuitaRegistryEntry<Block> createShutterBlock(BlockItemId res, Block wood) {
		GuitaRegistryEntry<Block> block = register(res, ShutterBlock::new, BlockBehaviour.Properties.ofFullCopy(wood).mapColor(wood.defaultMapColor()), SHUTTER_BLOCKS);
		if (Platform.INSTANCE.loader().equals("fabric")) WOOD_ASSOCIATIONS.put(block.get(), wood);
		return block;
	}

	private static GuitaRegistryEntry<Block> register(BlockItemId res, Function<BlockBehaviour.Properties, ? extends Block> blockFactory, BlockBehaviour.Properties settings, GuitaRegistry<Block> blockReg) {
		return register(res.block(), blockFactory, settings, blockReg);
	}

	private static GuitaRegistryEntry<Block> register(ResourceKey<Block> res, Function<BlockBehaviour.Properties, ? extends Block> blockFactory, BlockBehaviour.Properties settings, GuitaRegistry<Block> blockReg) {
		return blockReg.register(res.identifier().getPath(), () -> blockFactory.apply(settings.setId(res)));
	}
}
