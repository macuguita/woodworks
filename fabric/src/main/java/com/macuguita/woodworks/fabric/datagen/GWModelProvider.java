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

package com.macuguita.woodworks.fabric.datagen;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.NoCornerModularSeatBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.ShutterBlock;
import com.macuguita.woodworks.block.SupportBlock;
import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.block.property.SupportFaceShapeProperty;
import com.macuguita.woodworks.item.SupportBlockItem;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

@SuppressWarnings("deprecation")
public class GWModelProvider extends FabricModelProvider {

	public GWModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		blockStateModelGenerator.createSimpleFlatItemModel(GWObjects.SECATEURS.get());
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerStump(blockStateModelGenerator, block,
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")));
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerStump(blockStateModelGenerator, block,
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")));
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerCarvedLog(blockStateModelGenerator, block,
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(block, "_inside")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(log, "_top"))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(block, "_inside")));
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerCarvedLog(blockStateModelGenerator, block,
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(block, "_inside")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(log, "_top"))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(block, "_inside")));
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerBeamBlock(blockStateModelGenerator, block,
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_2x2")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_4x4")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_6x6")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_8x8")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_10x10")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_12x12")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_14x14")));
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerBeamBlock(blockStateModelGenerator, block,
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_2x2")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_4x4")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_6x6")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_8x8")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_10x10")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_12x12")),
					new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_14x14")));
		});
		GWObjects.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerHollowLog(blockStateModelGenerator, block,
					new TextureMapping()
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(log, "_top"))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(GWUtils.getStrippedBlockOrSelf(log))));
		});
		GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			var log = GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get());
			var block = regEntry.get();
			registerHollowLog(blockStateModelGenerator, block,
					new TextureMapping()
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(log))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(log, "_top"))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(GWUtils.getStrippedBlockOrSelf(log))));
		});
		GWObjects.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
			var block = regEntry.get();
			registerSupportBlock(blockStateModelGenerator, block,
					new TextureMapping()
							.put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_end"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side")));
		});
		GWObjects.SHUTTER_BLOCKS.stream().forEach(regEntry -> {
			var block = regEntry.get();
			ResourceLocation id = regEntry.getId();
			String basePath = id.getPath().replace("_shutter", "_support");
			ResourceLocation endId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + basePath + "_end");
			ResourceLocation sideId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + basePath + "_side");
			registerShutterBlock(blockStateModelGenerator, block,
					new TextureMapping()
							.put(TextureSlot.END, endId)
							.put(TextureSlot.SIDE, sideId));
		});

		registerStump(blockStateModelGenerator, GWObjects.MUSHROOM_STUMP.get(),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM))
						.put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")));

		registerCarvedLog(blockStateModelGenerator, GWObjects.CARVED_MUSHROOM_STEM.get(),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM))
						.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(GWObjects.CARVED_MUSHROOM_STEM.get(), "_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM))
						.put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside"))
						.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(GWObjects.CARVED_MUSHROOM_STEM.get(), "_inside")));

		registerBeamBlock(blockStateModelGenerator, GWObjects.MUSHROOM_BEAM.get(),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")),
				new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)).put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")));

		registerHollowLog(blockStateModelGenerator, GWObjects.HOLLOW_MUSHROOM_STEM.get(),
				new TextureMapping()
						.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM))
						.put(TextureSlot.TOP, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside"))
						.put(TextureSlot.INSIDE, ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside")));
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		GWObjects.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
			var block = regEntry.get();
			var tm = new TextureMapping()
					.put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_end"))
					.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
			ResourceLocation inventory = SUPPORT_INVENTORY.createWithSuffix(block, "_inventory", tm, itemModelGenerator.output);
			ResourceLocation uInventory = SUPPORT_INVENTORY_UPSIDE_DOWN.createWithSuffix(block, "_inventory_upside_down", tm, itemModelGenerator.output);
			generateSupportItem(itemModelGenerator, block, inventory, uInventory);
		});
	}

	private static final ModelTemplate STUMP = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_stump")),
			Optional.empty(),
			TextureSlot.TOP, TextureSlot.SIDE);

	private static final ModelTemplate CARVED_LOG_MIDDLE = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_middle")),
			Optional.of("_middle"),
			TextureSlot.SIDE, TextureSlot.INSIDE);

	private static final ModelTemplate CARVED_LOG_LEFT = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_left")),
			Optional.of("_left"),
			TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.INSIDE);

	private static final ModelTemplate CARVED_LOG_RIGHT = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_right")),
			Optional.of("_right"),
			TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.INSIDE);

	private static final ModelTemplate CARVED_LOG_SINGLE = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_single")),
			Optional.of("_single"),
			TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.INSIDE);

	private static final ModelTemplate BEAM_SIDE_INVENTORY = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/template_beam_inventory")),
			Optional.empty(),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_CORE_2X2 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_2x2")),
			Optional.of("_core_2x2"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_CORE_4X4 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_4x4")),
			Optional.of("_core_4x4"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_CORE_6X6 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_6x6")),
			Optional.of("_core_6x6"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_CORE_8X8 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_8x8")),
			Optional.of("_core_8x8"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_CORE_10X10 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_10x10")),
			Optional.of("_core_10x10"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_CORE_12X12 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_12x12")),
			Optional.of("_core_12x12"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_CORE_14X14 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_14x14")),
			Optional.of("_core_14x14"),
			TextureSlot.SIDE);

	private static final ModelTemplate BEAM_SIDE_UP_2X2 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_2x2")),
			Optional.of("_side_2x2"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_UP_4X4 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_4x4")),
			Optional.of("_side_4x4"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_UP_6X6 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_6x6")),
			Optional.of("_side_6x6"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_UP_8X8 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_8x8")),
			Optional.of("_side_8x8"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_UP_10X10 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_10x10")),
			Optional.of("_side_10x10"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_UP_12X12 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_12x12")),
			Optional.of("_side_12x12"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_UP_14X14 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_14x14")),
			Optional.of("_side_14x14"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_2X2 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_2x2")),
			Optional.of("_side_2x2"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_4X4 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_4x4")),
			Optional.of("_side_4x4"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_6X6 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_6x6")),
			Optional.of("_side_6x6"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_8X8 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_8x8")),
			Optional.of("_side_8x8"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_10X10 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_10x10")),
			Optional.of("_side_10x10"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_12X12 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_12x12")),
			Optional.of("_side_12x12"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate BEAM_SIDE_DOWN_14X14 = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_14x14")),
			Optional.of("_side_14x14"),
			TextureSlot.SIDE, TextureSlot.TOP);

	private static final ModelTemplate HOLLOW_LOG = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_hollow_log")),
			Optional.empty(),
			TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.INSIDE);

	private static final ModelTemplate SUPPORT_HORIZONTAL_BIG = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_horizontal_big")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_HORIZONTAL_SMALL = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_horizontal_small")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_INVENTORY = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_inventory")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_POST = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_post")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_VERTICAL_BIG = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_vertical_big")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_VERTICAL_SMALL = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_vertical_small")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_HORIZONTAL_BIG_UPSIDE_DOWN = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_horizontal_big_upside_down")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_HORIZONTAL_SMALL_UPSIDE_DOWN = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_horizontal_small_upside_down")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_INVENTORY_UPSIDE_DOWN = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_inventory_upside_down")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_POST_UPSIDE_DOWN = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_post_upside_down")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_VERTICAL_BIG_UPSIDE_DOWN = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_vertical_big_upside_down")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SUPPORT_VERTICAL_SMALL_UPSIDE_DOWN = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_support_vertical_small_upside_down")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SHUTTER_LEFT = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_shutter_left")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SHUTTER_RIGHT = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_shutter_right")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private static final ModelTemplate SHUTTER_INVENTORY = new ModelTemplate(
			Optional.of(GuitaWoodworks.id("block/template_shutter_inventory")),
			Optional.empty(),
			TextureSlot.END, TextureSlot.SIDE);

	private void registerStump(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tm) {
		ResourceLocation identifier = STUMP.create(block, tm, blockStateModelGenerator.modelOutput);
		blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)));
		blockStateModelGenerator.delegateItemModel(block, identifier);
	}

	private void registerCarvedLog(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tmMiddle, TextureMapping tmCommon) {
		ResourceLocation middleModel = CARVED_LOG_MIDDLE.create(block, tmMiddle, blockStateModelGenerator.modelOutput);
		ResourceLocation leftModel = CARVED_LOG_LEFT.create(block, tmCommon, blockStateModelGenerator.modelOutput);
		ResourceLocation rightModel = CARVED_LOG_RIGHT.create(block, tmCommon, blockStateModelGenerator.modelOutput);
		ResourceLocation singleModel = CARVED_LOG_SINGLE.create(block, tmCommon, blockStateModelGenerator.modelOutput);
		Map<NoCornerModularSeatProperty, ResourceLocation> modelMap = Map.of(
				NoCornerModularSeatProperty.SINGLE, singleModel,
				NoCornerModularSeatProperty.LEFT, leftModel,
				NoCornerModularSeatProperty.MIDDLE, middleModel,
				NoCornerModularSeatProperty.RIGHT, rightModel
		);

		PropertyDispatch.C2<NoCornerModularSeatProperty, Direction> map = PropertyDispatch.properties(NoCornerModularSeatBlock.SHAPE, NoCornerModularSeatBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(shape, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(shape, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
		}
		blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockStateModelGenerator.delegateItemModel(block, singleModel);
	}

	private void registerBeamBlock(BlockModelGenerators blockStateModelGenerator, Block block,
								   TextureMapping tmCore, TextureMapping tmSide2x2, TextureMapping tmSide4x4, TextureMapping tmSide6x6,
								   TextureMapping tmSide8x8, TextureMapping tmSide10x10, TextureMapping tmSide12x12, TextureMapping tmSide14x14) {
		Map<Integer, TextureMapping> textureMapSideMap = Map.of(
				1, tmSide2x2,
				2, tmSide4x4,
				3, tmSide6x6,
				4, tmSide8x8,
				5, tmSide10x10,
				6, tmSide12x12,
				7, tmSide14x14
		);
		ResourceLocation coreModel2 = BEAM_CORE_2X2.create(block, tmCore, blockStateModelGenerator.modelOutput);
		ResourceLocation coreModel4 = BEAM_CORE_4X4.create(block, tmCore, blockStateModelGenerator.modelOutput);
		ResourceLocation coreModel6 = BEAM_CORE_6X6.create(block, tmCore, blockStateModelGenerator.modelOutput);
		ResourceLocation coreModel8 = BEAM_CORE_8X8.create(block, tmCore, blockStateModelGenerator.modelOutput);
		ResourceLocation coreModel10 = BEAM_CORE_10X10.create(block, tmCore, blockStateModelGenerator.modelOutput);
		ResourceLocation coreModel12 = BEAM_CORE_12X12.create(block, tmCore, blockStateModelGenerator.modelOutput);
		ResourceLocation coreModel14 = BEAM_CORE_14X14.create(block, tmCore, blockStateModelGenerator.modelOutput);
		Map<Integer, ResourceLocation> coreModelMap = Map.of(
				1, coreModel2,
				2, coreModel4,
				3, coreModel6,
				4, coreModel8,
				5, coreModel10,
				6, coreModel12,
				7, coreModel14
		);
		ResourceLocation sideUpModel2 = BEAM_SIDE_UP_2X2.createWithSuffix(block, "_up", textureMapSideMap.get(1), blockStateModelGenerator.modelOutput);
		ResourceLocation sideUpModel4 = BEAM_SIDE_UP_4X4.createWithSuffix(block, "_up", textureMapSideMap.get(2), blockStateModelGenerator.modelOutput);
		ResourceLocation sideUpModel6 = BEAM_SIDE_UP_6X6.createWithSuffix(block, "_up", textureMapSideMap.get(3), blockStateModelGenerator.modelOutput);
		ResourceLocation sideUpModel8 = BEAM_SIDE_UP_8X8.createWithSuffix(block, "_up", textureMapSideMap.get(4), blockStateModelGenerator.modelOutput);
		ResourceLocation sideUpModel10 = BEAM_SIDE_UP_10X10.createWithSuffix(block, "_up", textureMapSideMap.get(5), blockStateModelGenerator.modelOutput);
		ResourceLocation sideUpModel12 = BEAM_SIDE_UP_12X12.createWithSuffix(block, "_up", textureMapSideMap.get(6), blockStateModelGenerator.modelOutput);
		ResourceLocation sideUpModel14 = BEAM_SIDE_UP_14X14.createWithSuffix(block, "_up", textureMapSideMap.get(7), blockStateModelGenerator.modelOutput);
		Map<Integer, ResourceLocation> sideUpModelMap = Map.of(
				1, sideUpModel2,
				2, sideUpModel4,
				3, sideUpModel6,
				4, sideUpModel8,
				5, sideUpModel10,
				6, sideUpModel12,
				7, sideUpModel14
		);

		ResourceLocation sideDownModel2 = BEAM_SIDE_DOWN_2X2.createWithSuffix(block, "_down", textureMapSideMap.get(1), blockStateModelGenerator.modelOutput);
		ResourceLocation sideDownModel4 = BEAM_SIDE_DOWN_4X4.createWithSuffix(block, "_down", textureMapSideMap.get(2), blockStateModelGenerator.modelOutput);
		ResourceLocation sideDownModel6 = BEAM_SIDE_DOWN_6X6.createWithSuffix(block, "_down", textureMapSideMap.get(3), blockStateModelGenerator.modelOutput);
		ResourceLocation sideDownModel8 = BEAM_SIDE_DOWN_8X8.createWithSuffix(block, "_down", textureMapSideMap.get(4), blockStateModelGenerator.modelOutput);
		ResourceLocation sideDownModel10 = BEAM_SIDE_DOWN_10X10.createWithSuffix(block, "_down", textureMapSideMap.get(5), blockStateModelGenerator.modelOutput);
		ResourceLocation sideDownModel12 = BEAM_SIDE_DOWN_12X12.createWithSuffix(block, "_down", textureMapSideMap.get(6), blockStateModelGenerator.modelOutput);
		ResourceLocation sideDownModel14 = BEAM_SIDE_DOWN_14X14.createWithSuffix(block, "_down", textureMapSideMap.get(7), blockStateModelGenerator.modelOutput);
		Map<Integer, ResourceLocation> sideDownModelMap = Map.of(
				1, sideDownModel2,
				2, sideDownModel4,
				3, sideDownModel6,
				4, sideDownModel8,
				5, sideDownModel10,
				6, sideDownModel12,
				7, sideDownModel14
		);

		MultiPartGenerator blockStateSupplier = MultiPartGenerator.multiPart(block);

		IntegerProperty radiusProp = ResizableBeamBlock.RADIUS;
		Map<Direction, BooleanProperty> facingProperties = ResizableBeamBlock.PROPERTY_BY_DIRECTION;
		for (int size : radiusProp.getPossibleValues()) {
			generateRotatedCoreModels(blockStateSupplier, coreModelMap, size);
			for (Direction dir : Direction.values()) {
				BooleanProperty sideProp = facingProperties.get(dir);
				blockStateSupplier.with(Condition.condition().term(radiusProp, size).term(sideProp, true), rotateBeamModel(Variant.variant().with(VariantProperties.MODEL, getSidedModel(sideUpModelMap.get(size), sideDownModelMap.get(size), dir)), dir));
			}
		}

		blockStateModelGenerator.blockStateOutput.accept(blockStateSupplier);

		ResourceLocation inventoryModel = BEAM_SIDE_INVENTORY.create(block, tmSide8x8, blockStateModelGenerator.modelOutput);
		blockStateModelGenerator.delegateItemModel(block, inventoryModel);
	}

	private void registerHollowLog(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tm) {
		ResourceLocation model = HOLLOW_LOG.create(block, tm, blockStateModelGenerator.modelOutput);

		blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(
				PropertyDispatch.property(HollowLogBlock.AXIS)
						.select(Direction.Axis.X, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
						.select(Direction.Axis.Y, Variant.variant().with(VariantProperties.MODEL, model))
						.select(Direction.Axis.Z, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
		));
		blockStateModelGenerator.delegateItemModel(block, model);
	}

	private void registerSupportBlock(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tm) {
		Map<SupportType, Pair<ResourceLocation, ResourceLocation>> supportModels = new EnumMap<>(SupportType.class);

		supportModels.put(SupportType.HORIZONTAL_BIG, Pair.of(
				SUPPORT_HORIZONTAL_BIG.createWithSuffix(block, "_horizontal_big", tm, blockStateModelGenerator.modelOutput),
				SUPPORT_HORIZONTAL_BIG_UPSIDE_DOWN.createWithSuffix(block, "_horizontal_big_upside_down", tm, blockStateModelGenerator.modelOutput)
		));
		supportModels.put(SupportType.HORIZONTAL_SMALL, Pair.of(
				SUPPORT_HORIZONTAL_SMALL.createWithSuffix(block, "_horizontal_small", tm, blockStateModelGenerator.modelOutput),
				SUPPORT_HORIZONTAL_SMALL_UPSIDE_DOWN.createWithSuffix(block, "_horizontal_small_upside_down", tm, blockStateModelGenerator.modelOutput)
		));
		supportModels.put(SupportType.POST, Pair.of(
				SUPPORT_POST.createWithSuffix(block, "_post", tm, blockStateModelGenerator.modelOutput),
				SUPPORT_POST_UPSIDE_DOWN.createWithSuffix(block, "_post_upside_down", tm, blockStateModelGenerator.modelOutput)
		));
		supportModels.put(SupportType.VERTICAL_BIG, Pair.of(
				SUPPORT_VERTICAL_BIG.createWithSuffix(block, "_vertical_big", tm, blockStateModelGenerator.modelOutput),
				SUPPORT_VERTICAL_BIG_UPSIDE_DOWN.createWithSuffix(block, "_vertical_big_upside_down", tm, blockStateModelGenerator.modelOutput)
		));
		supportModels.put(SupportType.VERTICAL_SMALL, Pair.of(
				SUPPORT_VERTICAL_SMALL.createWithSuffix(block, "_vertical_small", tm, blockStateModelGenerator.modelOutput),
				SUPPORT_VERTICAL_SMALL_UPSIDE_DOWN.createWithSuffix(block, "_vertical_small_upside_down", tm, blockStateModelGenerator.modelOutput)
		));

		MultiPartGenerator blockStateSupplier = MultiPartGenerator.multiPart(block);

		for (Direction dir : Direction.Plane.HORIZONTAL) {
			for (SupportFaceShapeProperty hProperty : SupportFaceShapeProperty.values()) {
				for (SupportFaceShapeProperty vProperty : SupportFaceShapeProperty.values()) {
					boolean hVisible = hProperty != SupportFaceShapeProperty.HIDDEN;
					boolean vVisible = vProperty != SupportFaceShapeProperty.HIDDEN;

					if (hVisible && vVisible) {
						for (boolean isUp : new boolean[]{true, false}) {
							blockStateSupplier.with(
									Condition.condition()
											.term(SupportBlock.UP, isUp)
											.term(SupportBlock.FACING, dir)
											.term(SupportBlock.HORIZONTAL_SHAPE, hProperty)
											.term(SupportBlock.VERTICAL_SHAPE, vProperty),
									rotateSupportModelVariant(
											Variant.variant().with(VariantProperties.MODEL,
													isUp ? supportModels.get(SupportType.POST).getFirst()
															: supportModels.get(SupportType.POST).getSecond()),
											dir
									)
							);
						}
					}
				}
			}

			for (SupportFaceShapeProperty property : SupportFaceShapeProperty.values()) {
				processShapeVariant(blockStateSupplier, supportModels, dir, property,
						SupportBlock.HORIZONTAL_SHAPE, this::horizontalSupportType);

				processShapeVariant(blockStateSupplier, supportModels, dir, property,
						SupportBlock.VERTICAL_SHAPE, this::verticalSupportType);
			}
		}

		blockStateModelGenerator.blockStateOutput.accept(blockStateSupplier);
	}

	private void registerShutterBlock(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tm) {
		ResourceLocation leftModel = SHUTTER_LEFT.createWithSuffix(block, "_left", tm, blockStateModelGenerator.modelOutput);
		ResourceLocation rightModel = SHUTTER_RIGHT.createWithSuffix(block, "_right", tm, blockStateModelGenerator.modelOutput);

		PropertyDispatch.C3<Boolean, Direction, DoorHingeSide> dispatch = PropertyDispatch.properties(
				ShutterBlock.OPEN,
				ShutterBlock.FACING,
				ShutterBlock.SIDE
		);

		for (Boolean open : new Boolean[]{false, true}) {
			for (DoorHingeSide side : DoorHingeSide.values()) {
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					dispatch.select(open, dir, side, rotateShutterBlock(dir, open, side, leftModel, rightModel));
				}
			}
		}

		ResourceLocation inventory = SHUTTER_INVENTORY.createWithSuffix(block, "_inventory", tm, blockStateModelGenerator.modelOutput);
		blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
		blockStateModelGenerator.delegateItemModel(block, inventory);
	}

	private enum SupportType {
		HORIZONTAL_BIG,
		HORIZONTAL_SMALL,
		POST,
		VERTICAL_BIG,
		VERTICAL_SMALL,
		NONE
	}

	private void generateRotatedCoreModels(MultiPartGenerator blockStateSupplier, Map<Integer, ResourceLocation> coreModelMap, int size) {
		blockStateSupplier.with(Condition.and(
				Condition.condition().term(ResizableBeamBlock.RADIUS, size),
				Condition.or(
						Condition.condition().negatedTerm(ResizableBeamBlock.UP, true),
						Condition.condition().negatedTerm(ResizableBeamBlock.DOWN, true),
						Condition.condition().negatedTerm(ResizableBeamBlock.NORTH, true),
						Condition.condition().negatedTerm(ResizableBeamBlock.SOUTH, true),
						Condition.condition().negatedTerm(ResizableBeamBlock.EAST, true),
						Condition.condition().negatedTerm(ResizableBeamBlock.WEST, true)
				),
				Condition.or(
						Condition.condition().term(ResizableBeamBlock.UP, true),
						Condition.condition().term(ResizableBeamBlock.DOWN, true),
						Condition.and(
								Condition.condition().term(ResizableBeamBlock.UP, false),
								Condition.condition().term(ResizableBeamBlock.DOWN, false),
								Condition.condition().term(ResizableBeamBlock.NORTH, false),
								Condition.condition().term(ResizableBeamBlock.SOUTH, false),
								Condition.condition().term(ResizableBeamBlock.EAST, false),
								Condition.condition().term(ResizableBeamBlock.WEST, false)
						),
						Condition.and(
								Condition.condition().term(ResizableBeamBlock.UP, false),
								Condition.condition().term(ResizableBeamBlock.DOWN, false),
								Condition.or(
										Condition.condition().term(ResizableBeamBlock.NORTH, false),
										Condition.condition().term(ResizableBeamBlock.SOUTH, false)
								),
								Condition.or(
										Condition.condition().term(ResizableBeamBlock.WEST, false),
										Condition.condition().term(ResizableBeamBlock.EAST, false)
								)
						),
						Condition.and(
								Condition.condition().term(ResizableBeamBlock.UP, false),
								Condition.condition().term(ResizableBeamBlock.DOWN, false),
								Condition.condition().term(ResizableBeamBlock.NORTH, true),
								Condition.condition().term(ResizableBeamBlock.SOUTH, true),
								Condition.condition().term(ResizableBeamBlock.EAST, true),
								Condition.condition().term(ResizableBeamBlock.WEST, true)
						)
				)
		), Variant.variant().with(VariantProperties.MODEL, coreModelMap.get(size)));

		blockStateSupplier.with(Condition.and(
				Condition.condition().term(ResizableBeamBlock.RADIUS, size),
				Condition.condition().term(ResizableBeamBlock.UP, false),
				Condition.condition().term(ResizableBeamBlock.DOWN, false),
				Condition.and(
						Condition.and(
								Condition.condition().term(ResizableBeamBlock.NORTH, true),
								Condition.condition().term(ResizableBeamBlock.SOUTH, true)
						),
						Condition.or(
								Condition.condition().term(ResizableBeamBlock.EAST, false),
								Condition.condition().term(ResizableBeamBlock.WEST, false)
						)
				)
		), Variant.variant().with(VariantProperties.MODEL, coreModelMap.get(size)).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90));

		blockStateSupplier.with(Condition.and(
				Condition.condition().term(ResizableBeamBlock.RADIUS, size),
				Condition.condition().term(ResizableBeamBlock.UP, false),
				Condition.condition().term(ResizableBeamBlock.DOWN, false),
				Condition.and(
						Condition.and(
								Condition.condition().term(ResizableBeamBlock.EAST, true),
								Condition.condition().term(ResizableBeamBlock.WEST, true)
						),
						Condition.or(
								Condition.condition().term(ResizableBeamBlock.NORTH, false),
								Condition.condition().term(ResizableBeamBlock.SOUTH, false)
						)
				)
		), Variant.variant().with(VariantProperties.MODEL, coreModelMap.get(size)).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
	}

	private ResourceLocation getSidedModel(ResourceLocation sideUp, ResourceLocation sideDown, Direction dir) {
		return switch (dir) {
			case UP, NORTH, EAST -> sideUp;
			case DOWN, SOUTH, WEST -> sideDown;
		};
	}

	private Variant rotateBeamModel(Variant blockStateVariant, Direction dir) {
		switch (dir) {
			case EAST -> blockStateVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
			case SOUTH -> blockStateVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
			case WEST -> blockStateVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
			case UP -> blockStateVariant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R270);
			case DOWN -> blockStateVariant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90);
		}

		return blockStateVariant;
	}

	private void processShapeVariant(
			MultiPartGenerator blockStateSupplier,
			Map<SupportType, Pair<ResourceLocation, ResourceLocation>> supportModels,
			Direction dir,
			SupportFaceShapeProperty property,
			Property<SupportFaceShapeProperty> shapeProperty,
			BiFunction<SupportFaceShapeProperty, Boolean, SupportType> supportTypeFunc
	) {

		for (boolean isUp : new boolean[]{true, false}) {
			SupportType type = supportTypeFunc.apply(property, isUp);

			if (type != SupportType.NONE && property != SupportFaceShapeProperty.HIDDEN) {
				blockStateSupplier.with(
						Condition.condition()
								.term(SupportBlock.UP, isUp)
								.term(SupportBlock.FACING, dir)
								.term(shapeProperty, property),
						rotateSupportModelVariant(
								Variant.variant().with(VariantProperties.MODEL,
										isUp ? supportModels.get(type).getFirst()
												: supportModels.get(type).getSecond()),
								dir
						)
				);
			}
		}
	}

	private Variant rotateSupportModelVariant(Variant blockStateVariant, Direction dir) {
		switch (dir) {
			case SOUTH -> blockStateVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
			case EAST -> blockStateVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
			case WEST -> blockStateVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
		}

		return blockStateVariant;
	}

	private SupportType horizontalSupportType(SupportFaceShapeProperty prop, boolean up) {
		return switch (prop) {
			case BIG -> SupportType.HORIZONTAL_BIG;
			case SMALL -> SupportType.HORIZONTAL_SMALL;
			case HIDDEN -> SupportType.NONE;
		};
	}

	private SupportType verticalSupportType(SupportFaceShapeProperty prop, boolean up) {
		return switch (prop) {
			case BIG -> SupportType.VERTICAL_BIG;
			case SMALL -> SupportType.VERTICAL_SMALL;
			case HIDDEN -> SupportType.NONE;
		};
	}

	private void generateSupportItem(ItemModelGenerators itemModelGenerator, Block block, ResourceLocation blockModelParent, ResourceLocation upsideDownBlockModel) {
		itemModelGenerator.output.accept(
				ModelLocationUtils.getModelLocation(block.asItem()),
				() -> {
					JsonObject json = new JsonObject();
					json.addProperty("parent", blockModelParent.toString());

					JsonArray overrides = new JsonArray();
					JsonObject override = new JsonObject();

					JsonObject predicate = new JsonObject();
					predicate.addProperty(SupportBlockItem.OVERRIDE_TAG.toString(), 1.0);
					override.add("predicate", predicate);
					override.addProperty("model", upsideDownBlockModel.toString());

					overrides.add(override);
					json.add("overrides", overrides);

					return json;
				}
		);
	}

	private Variant rotateShutterBlock(Direction dir, boolean open, DoorHingeSide side, ResourceLocation leftModel, ResourceLocation rightModel) {
		Variant variant = Variant.variant();
		VariantProperties.Rotation baseRotation = switch (dir) {
			case NORTH -> VariantProperties.Rotation.R0;
			case EAST -> VariantProperties.Rotation.R90;
			case SOUTH -> VariantProperties.Rotation.R180;
			case WEST -> VariantProperties.Rotation.R270;
			default -> throw new IllegalStateException("Unexpected direction: " + dir);
		};

		VariantProperties.Rotation finalRotation = open
				? switch (side) {
			case LEFT -> switch (baseRotation) {
				case R0 -> VariantProperties.Rotation.R270;
				case R90 -> VariantProperties.Rotation.R0;
				case R180 -> VariantProperties.Rotation.R90;
				case R270 -> VariantProperties.Rotation.R180;
			};
			case RIGHT -> switch (baseRotation) {
				case R0 -> VariantProperties.Rotation.R90;
				case R90 -> VariantProperties.Rotation.R180;
				case R180 -> VariantProperties.Rotation.R270;
				case R270 -> VariantProperties.Rotation.R0;
			};
		}
				: baseRotation;

		ResourceLocation model = open
				? (side == DoorHingeSide.LEFT ? rightModel : leftModel)
				: (side == DoorHingeSide.LEFT ? leftModel : rightModel);

		return variant
				.with(VariantProperties.Y_ROT, finalRotation)
				.with(VariantProperties.MODEL, model);
	}

}
