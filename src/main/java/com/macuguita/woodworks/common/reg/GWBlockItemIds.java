package com.macuguita.woodworks.common.reg;

import com.macuguita.woodworks.GuitaWoodworks;

import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class GWBlockItemIds {

	private GWBlockItemIds() {}

	// Stumps
	public static final BlockItemId OAK_STUMP = create("oak_stump");
	public static final BlockItemId STRIPPED_OAK_STUMP = create("stripped_oak_stump");

	public static final BlockItemId SPRUCE_STUMP = create("spruce_stump");
	public static final BlockItemId STRIPPED_SPRUCE_STUMP = create("stripped_spruce_stump");

	public static final BlockItemId BIRCH_STUMP = create("birch_stump");
	public static final BlockItemId STRIPPED_BIRCH_STUMP = create("stripped_birch_stump");

	public static final BlockItemId JUNGLE_STUMP = create("jungle_stump");
	public static final BlockItemId STRIPPED_JUNGLE_STUMP = create("stripped_jungle_stump");

	public static final BlockItemId ACACIA_STUMP = create("acacia_stump");
	public static final BlockItemId STRIPPED_ACACIA_STUMP = create("stripped_acacia_stump");

	public static final BlockItemId DARK_OAK_STUMP = create("dark_oak_stump");
	public static final BlockItemId STRIPPED_DARK_OAK_STUMP = create("stripped_dark_oak_stump");

	public static final BlockItemId MANGROVE_STUMP = create("mangrove_stump");
	public static final BlockItemId STRIPPED_MANGROVE_STUMP = create("stripped_mangrove_stump");

	public static final BlockItemId CHERRY_STUMP = create("cherry_stump");
	public static final BlockItemId STRIPPED_CHERRY_STUMP = create("stripped_cherry_stump");

	public static final BlockItemId BAMBOO_STUMP = create("bamboo_stump");
	public static final BlockItemId STRIPPED_BAMBOO_STUMP = create("stripped_bamboo_stump");

	public static final BlockItemId PALE_OAK_STUMP = create("pale_oak_stump");
	public static final BlockItemId STRIPPED_PALE_OAK_STUMP = create("stripped_pale_oak_stump");

	public static final BlockItemId CRIMSON_STUMP = create("crimson_stump");
	public static final BlockItemId STRIPPED_CRIMSON_STUMP = create("stripped_crimson_stump");

	public static final BlockItemId WARPED_STUMP = create("warped_stump");
	public static final BlockItemId STRIPPED_WARPED_STUMP = create("stripped_warped_stump");

	public static final BlockItemId MUSHROOM_STUMP = create("mushroom_stump");

	// Carved Logs
	public static final BlockItemId CARVED_OAK_LOG = create("carved_oak_log");
	public static final BlockItemId STRIPPED_CARVED_OAK_LOG = create("stripped_carved_oak_log");

	public static final BlockItemId CARVED_SPRUCE_LOG = create("carved_spruce_log");
	public static final BlockItemId STRIPPED_CARVED_SPRUCE_LOG = create("stripped_carved_spruce_log");

	public static final BlockItemId CARVED_BIRCH_LOG = create("carved_birch_log");
	public static final BlockItemId STRIPPED_CARVED_BIRCH_LOG = create("stripped_carved_birch_log");

	public static final BlockItemId CARVED_JUNGLE_LOG = create("carved_jungle_log");
	public static final BlockItemId STRIPPED_CARVED_JUNGLE_LOG = create("stripped_carved_jungle_log");

	public static final BlockItemId CARVED_ACACIA_LOG = create("carved_acacia_log");
	public static final BlockItemId STRIPPED_CARVED_ACACIA_LOG = create("stripped_carved_acacia_log");

	public static final BlockItemId CARVED_DARK_OAK_LOG = create("carved_dark_oak_log");
	public static final BlockItemId STRIPPED_CARVED_DARK_OAK_LOG = create("stripped_carved_dark_oak_log");

	public static final BlockItemId CARVED_MANGROVE_LOG = create("carved_mangrove_log");
	public static final BlockItemId STRIPPED_CARVED_MANGROVE_LOG = create("stripped_carved_mangrove_log");

	public static final BlockItemId CARVED_CHERRY_LOG = create("carved_cherry_log");
	public static final BlockItemId STRIPPED_CARVED_CHERRY_LOG = create("stripped_carved_cherry_log");

	public static final BlockItemId CARVED_BAMBOO_LOG = create("carved_bamboo_log");
	public static final BlockItemId STRIPPED_CARVED_BAMBOO_LOG = create("stripped_carved_bamboo_log");

	public static final BlockItemId CARVED_PALE_OAK_LOG = create("carved_pale_oak_log");
	public static final BlockItemId STRIPPED_CARVED_PALE_OAK_LOG = create("stripped_carved_pale_oak_log");

	public static final BlockItemId CARVED_CRIMSON_STEM = create("carved_crimson_stem");
	public static final BlockItemId STRIPPED_CARVED_CRIMSON_STEM = create("stripped_carved_crimson_stem");

	public static final BlockItemId CARVED_WARPED_STEM = create("carved_warped_stem");
	public static final BlockItemId STRIPPED_CARVED_WARPED_STEM = create("stripped_carved_warped_stem");

	public static final BlockItemId CARVED_MUSHROOM_STEM = create("carved_mushroom_stem");

	// Beams
	public static final BlockItemId OAK_BEAM = create("oak_beam");
	public static final BlockItemId STRIPPED_OAK_BEAM = create("stripped_oak_beam");

	public static final BlockItemId SPRUCE_BEAM = create("spruce_beam");
	public static final BlockItemId STRIPPED_SPRUCE_BEAM = create("stripped_spruce_beam");

	public static final BlockItemId BIRCH_BEAM = create("birch_beam");
	public static final BlockItemId STRIPPED_BIRCH_BEAM = create("stripped_birch_beam");

	public static final BlockItemId JUNGLE_BEAM = create("jungle_beam");
	public static final BlockItemId STRIPPED_JUNGLE_BEAM = create("stripped_jungle_beam");

	public static final BlockItemId ACACIA_BEAM = create("acacia_beam");
	public static final BlockItemId STRIPPED_ACACIA_BEAM = create("stripped_acacia_beam");

	public static final BlockItemId DARK_OAK_BEAM = create("dark_oak_beam");
	public static final BlockItemId STRIPPED_DARK_OAK_BEAM = create("stripped_dark_oak_beam");

	public static final BlockItemId MANGROVE_BEAM = create("mangrove_beam");
	public static final BlockItemId STRIPPED_MANGROVE_BEAM = create("stripped_mangrove_beam");

	public static final BlockItemId CHERRY_BEAM = create("cherry_beam");
	public static final BlockItemId STRIPPED_CHERRY_BEAM = create("stripped_cherry_beam");

	public static final BlockItemId BAMBOO_BEAM = create("bamboo_beam");
	public static final BlockItemId STRIPPED_BAMBOO_BEAM = create("stripped_bamboo_beam");

	public static final BlockItemId PALE_OAK_BEAM = create("pale_oak_beam");
	public static final BlockItemId STRIPPED_PALE_OAK_BEAM = create("stripped_pale_oak_beam");

	public static final BlockItemId CRIMSON_BEAM = create("crimson_beam");
	public static final BlockItemId STRIPPED_CRIMSON_BEAM = create("stripped_crimson_beam");

	public static final BlockItemId WARPED_BEAM = create("warped_beam");
	public static final BlockItemId STRIPPED_WARPED_BEAM = create("stripped_warped_beam");

	public static final BlockItemId MUSHROOM_BEAM = create("mushroom_beam");

	// Hollow Logs
	public static final BlockItemId HOLLOW_OAK_LOG = create("hollow_oak_log");
	public static final BlockItemId STRIPPED_HOLLOW_OAK_LOG = create("stripped_hollow_oak_log");

	public static final BlockItemId HOLLOW_SPRUCE_LOG = create("hollow_spruce_log");
	public static final BlockItemId STRIPPED_HOLLOW_SPRUCE_LOG = create("stripped_hollow_spruce_log");

	public static final BlockItemId HOLLOW_BIRCH_LOG = create("hollow_birch_log");
	public static final BlockItemId STRIPPED_HOLLOW_BIRCH_LOG = create("stripped_hollow_birch_log");

	public static final BlockItemId HOLLOW_JUNGLE_LOG = create("hollow_jungle_log");
	public static final BlockItemId STRIPPED_HOLLOW_JUNGLE_LOG = create("stripped_hollow_jungle_log");

	public static final BlockItemId HOLLOW_ACACIA_LOG = create("hollow_acacia_log");
	public static final BlockItemId STRIPPED_HOLLOW_ACACIA_LOG = create("stripped_hollow_acacia_log");

	public static final BlockItemId HOLLOW_DARK_OAK_LOG = create("hollow_dark_oak_log");
	public static final BlockItemId STRIPPED_HOLLOW_DARK_OAK_LOG = create("stripped_hollow_dark_oak_log");

	public static final BlockItemId HOLLOW_MANGROVE_LOG = create("hollow_mangrove_log");
	public static final BlockItemId STRIPPED_HOLLOW_MANGROVE_LOG = create("stripped_hollow_mangrove_log");

	public static final BlockItemId HOLLOW_CHERRY_LOG = create("hollow_cherry_log");
	public static final BlockItemId STRIPPED_HOLLOW_CHERRY_LOG = create("stripped_hollow_cherry_log");

	public static final BlockItemId HOLLOW_BAMBOO_LOG = create("hollow_bamboo_log");
	public static final BlockItemId STRIPPED_HOLLOW_BAMBOO_LOG = create("stripped_hollow_bamboo_log");

	public static final BlockItemId HOLLOW_PALE_OAK_LOG = create("hollow_pale_oak_log");
	public static final BlockItemId STRIPPED_HOLLOW_PALE_OAK_LOG = create("stripped_hollow_pale_oak_log");

	public static final BlockItemId HOLLOW_CRIMSON_STEM = create("hollow_crimson_stem");
	public static final BlockItemId STRIPPED_HOLLOW_CRIMSON_STEM = create("stripped_hollow_crimson_stem");

	public static final BlockItemId HOLLOW_WARPED_STEM = create("hollow_warped_stem");
	public static final BlockItemId STRIPPED_HOLLOW_WARPED_STEM = create("stripped_hollow_warped_stem");

	public static final BlockItemId HOLLOW_MUSHROOM_STEM = create("hollow_mushroom_stem");

	// Supports
	public static final BlockItemId OAK_SUPPORT = create("oak_support");
	public static final BlockItemId SPRUCE_SUPPORT = create("spruce_support");
	public static final BlockItemId BIRCH_SUPPORT = create("birch_support");
	public static final BlockItemId JUNGLE_SUPPORT = create("jungle_support");
	public static final BlockItemId ACACIA_SUPPORT = create("acacia_support");
	public static final BlockItemId DARK_OAK_SUPPORT = create("dark_oak_support");
	public static final BlockItemId MANGROVE_SUPPORT = create("mangrove_support");
	public static final BlockItemId CHERRY_SUPPORT = create("cherry_support");
	public static final BlockItemId BAMBOO_SUPPORT = create("bamboo_support");
	public static final BlockItemId PALE_OAK_SUPPORT = create("pale_oak_support");
	public static final BlockItemId CRIMSON_SUPPORT = create("crimson_support");
	public static final BlockItemId WARPED_SUPPORT = create("warped_support");

	// Shutters
	public static final BlockItemId OAK_SHUTTER = create("oak_shutter");
	public static final BlockItemId SPRUCE_SHUTTER = create("spruce_shutter");
	public static final BlockItemId BIRCH_SHUTTER = create("birch_shutter");
	public static final BlockItemId JUNGLE_SHUTTER = create("jungle_shutter");
	public static final BlockItemId ACACIA_SHUTTER = create("acacia_shutter");
	public static final BlockItemId DARK_OAK_SHUTTER = create("dark_oak_shutter");
	public static final BlockItemId MANGROVE_SHUTTER = create("mangrove_shutter");
	public static final BlockItemId CHERRY_SHUTTER = create("cherry_shutter");
	public static final BlockItemId BAMBOO_SHUTTER = create("bamboo_shutter");
	public static final BlockItemId PALE_OAK_SHUTTER = create("pale_oak_shutter");
	public static final BlockItemId CRIMSON_SHUTTER = create("crimson_shutter");
	public static final BlockItemId WARPED_SHUTTER = create("warped_shutter");

	public static BlockItemId create(final String name) {
		Identifier id = GuitaWoodworks.id(name);
		return new BlockItemId(ResourceKey.create(Registries.BLOCK, id), ResourceKey.create(Registries.ITEM, id));
	}
}
