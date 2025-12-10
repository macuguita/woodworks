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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.NoCornerModularSeatBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.client.renderer.block.model.multipart.CombinedCondition;
import net.minecraft.client.renderer.block.model.multipart.Condition;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public class GWModelProvider extends FabricModelProvider {

	public GWModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
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
		itemModelGenerator.generateFlatItem(GWObjects.SECATEURS.get(), ModelTemplates.FLAT_ITEM);
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

	private void registerStump(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tm) {
		Identifier id = STUMP.create(block, tm, blockStateModelGenerator.modelOutput);
		MultiVariant weightedVariant = BlockModelGenerators.plainVariant(id);
		blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, weightedVariant));
		blockStateModelGenerator.registerSimpleItemModel(block, id);
	}

	private void registerCarvedLog(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tmMiddle, TextureMapping tmCommon) {
		Identifier singleModelId = CARVED_LOG_SINGLE.create(block, tmCommon, blockStateModelGenerator.modelOutput);
		MultiVariant singleWeightedVariant = BlockModelGenerators.plainVariant(singleModelId);
		MultiVariant middleWeightedVariant = BlockModelGenerators.plainVariant(CARVED_LOG_MIDDLE.create(block, tmMiddle, blockStateModelGenerator.modelOutput));
		MultiVariant leftWeightedVariant = BlockModelGenerators.plainVariant(CARVED_LOG_LEFT.create(block, tmCommon, blockStateModelGenerator.modelOutput));
		MultiVariant rightWeightedVariant = BlockModelGenerators.plainVariant(CARVED_LOG_RIGHT.create(block, tmCommon, blockStateModelGenerator.modelOutput));
		PropertyDispatch<VariantMutator> rotationOperations = PropertyDispatch.modify(
						NoCornerModularSeatBlock.FACING
				)
				.select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
				.select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
				.select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
				.select(Direction.NORTH, BlockModelGenerators.NOP);

		Map<NoCornerModularSeatProperty, MultiVariant> modelMap = Map.of(
				NoCornerModularSeatProperty.SINGLE, singleWeightedVariant,
				NoCornerModularSeatProperty.LEFT, leftWeightedVariant,
				NoCornerModularSeatProperty.MIDDLE, middleWeightedVariant,
				NoCornerModularSeatProperty.RIGHT, rightWeightedVariant
		);

		var blockStateVariantMap = PropertyDispatch.initial(NoCornerModularSeatBlock.SHAPE);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			blockStateVariantMap.select(shape, modelMap.get(shape));
		}

		blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(blockStateVariantMap).with(rotationOperations));
		blockStateModelGenerator.registerSimpleItemModel(block, singleModelId);
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
		MultiVariant weightedVariantCore2 = BlockModelGenerators.plainVariant(BEAM_CORE_2X2.create(block, tmCore, blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantCore4 = BlockModelGenerators.plainVariant(BEAM_CORE_4X4.create(block, tmCore, blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantCore6 = BlockModelGenerators.plainVariant(BEAM_CORE_6X6.create(block, tmCore, blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantCore8 = BlockModelGenerators.plainVariant(BEAM_CORE_8X8.create(block, tmCore, blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantCore10 = BlockModelGenerators.plainVariant(BEAM_CORE_10X10.create(block, tmCore, blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantCore12 = BlockModelGenerators.plainVariant(BEAM_CORE_12X12.create(block, tmCore, blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantCore14 = BlockModelGenerators.plainVariant(BEAM_CORE_14X14.create(block, tmCore, blockStateModelGenerator.modelOutput));
		Map<Integer, MultiVariant> coreWeightedVariantMap = Map.of(
				1, weightedVariantCore2,
				2, weightedVariantCore4,
				3, weightedVariantCore6,
				4, weightedVariantCore8,
				5, weightedVariantCore10,
				6, weightedVariantCore12,
				7, weightedVariantCore14
		);
		MultiVariant weightedVariantSideUp2 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_2X2.createWithSuffix(block, "_up", textureMapSideMap.get(1), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideUp4 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_4X4.createWithSuffix(block, "_up", textureMapSideMap.get(2), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideUp6 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_6X6.createWithSuffix(block, "_up", textureMapSideMap.get(3), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideUp8 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_8X8.createWithSuffix(block, "_up", textureMapSideMap.get(4), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideUp10 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_10X10.createWithSuffix(block, "_up", textureMapSideMap.get(5), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideUp12 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_12X12.createWithSuffix(block, "_up", textureMapSideMap.get(6), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideUp14 = BlockModelGenerators.plainVariant(BEAM_SIDE_UP_14X14.createWithSuffix(block, "_up", textureMapSideMap.get(7), blockStateModelGenerator.modelOutput));
		Map<Integer, MultiVariant> sideUpWeightedVariantMap = Map.of(
				1, weightedVariantSideUp2,
				2, weightedVariantSideUp4,
				3, weightedVariantSideUp6,
				4, weightedVariantSideUp8,
				5, weightedVariantSideUp10,
				6, weightedVariantSideUp12,
				7, weightedVariantSideUp14
		);

		MultiVariant weightedVariantSideDown2 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_2X2.createWithSuffix(block, "_down", textureMapSideMap.get(1), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideDown4 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_4X4.createWithSuffix(block, "_down", textureMapSideMap.get(2), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideDown6 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_6X6.createWithSuffix(block, "_down", textureMapSideMap.get(3), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideDown8 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_8X8.createWithSuffix(block, "_down", textureMapSideMap.get(4), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideDown10 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_10X10.createWithSuffix(block, "_down", textureMapSideMap.get(5), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideDown12 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_12X12.createWithSuffix(block, "_down", textureMapSideMap.get(6), blockStateModelGenerator.modelOutput));
		MultiVariant weightedVariantSideDown14 = BlockModelGenerators.plainVariant(BEAM_SIDE_DOWN_14X14.createWithSuffix(block, "_down", textureMapSideMap.get(7), blockStateModelGenerator.modelOutput));
		Map<Integer, MultiVariant> sideDownWeightedVariantMap = Map.of(
				1, weightedVariantSideDown2,
				2, weightedVariantSideDown4,
				3, weightedVariantSideDown6,
				4, weightedVariantSideDown8,
				5, weightedVariantSideDown10,
				6, weightedVariantSideDown12,
				7, weightedVariantSideDown14
		);

		MultiPartGenerator multipartBlockModelDefinitionCreator = MultiPartGenerator.multiPart(block);

		IntegerProperty radiusProp = ResizableBeamBlock.RADIUS;
		Map<Direction, BooleanProperty> facingProperties = ResizableBeamBlock.FACING_PROPERTIES;
		for (int size : radiusProp.getPossibleValues()) {
			generateRotatedCoreModels(multipartBlockModelDefinitionCreator, coreWeightedVariantMap, size);
			for (Direction dir : Direction.values()) {
				BooleanProperty sideProp = facingProperties.get(dir);
				multipartBlockModelDefinitionCreator.with(BlockModelGenerators.condition()
						.term(radiusProp, size)
						.term(sideProp, true), getSidedModel(sideUpWeightedVariantMap.get(size), sideDownWeightedVariantMap.get(size), dir).with(getBeamRotation(dir)));
			}
		}

		blockStateModelGenerator.blockStateOutput.accept(multipartBlockModelDefinitionCreator);

		Identifier inventoryModel = BEAM_SIDE_INVENTORY.create(block, tmSide8x8, blockStateModelGenerator.modelOutput);
		blockStateModelGenerator.registerSimpleItemModel(block, inventoryModel);
	}

	private void registerHollowLog(BlockModelGenerators blockStateModelGenerator, Block block, TextureMapping tm) {
		Identifier model = HOLLOW_LOG.create(block, tm, blockStateModelGenerator.modelOutput);
		MultiVariant weightedVariant = BlockModelGenerators.plainVariant(model);

		PropertyDispatch<VariantMutator> rotationOperations = PropertyDispatch.modify(
						HollowLogBlock.AXIS
				)
				.select(Direction.Axis.X, BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_90))
				.select(Direction.Axis.Y, BlockModelGenerators.NOP)
				.select(Direction.Axis.Z, BlockModelGenerators.X_ROT_90);

		blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, weightedVariant).with(rotationOperations));
		blockStateModelGenerator.registerSimpleItemModel(block, model);
	}

	private void generateRotatedCoreModels(MultiPartGenerator multipartBlockModelDefinitionCreator, Map<Integer, MultiVariant> coreWeightedVariantMap, int size) {
		multipartBlockModelDefinitionCreator.with(and(
				BlockModelGenerators.condition().term(ResizableBeamBlock.RADIUS, size),
				or(
						BlockModelGenerators.condition().term(ResizableBeamBlock.UP, false),
						BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, false),
						BlockModelGenerators.condition().term(ResizableBeamBlock.NORTH, false),
						BlockModelGenerators.condition().term(ResizableBeamBlock.SOUTH, false),
						BlockModelGenerators.condition().term(ResizableBeamBlock.EAST, false),
						BlockModelGenerators.condition().term(ResizableBeamBlock.WEST, false)
				),
				or(
						BlockModelGenerators.condition().term(ResizableBeamBlock.UP, true),
						BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, true),
						and(
								BlockModelGenerators.condition().term(ResizableBeamBlock.UP, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.NORTH, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.SOUTH, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.EAST, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.WEST, false)
						),
						and(
								BlockModelGenerators.condition().term(ResizableBeamBlock.UP, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, false),
								or(
										BlockModelGenerators.condition().term(ResizableBeamBlock.NORTH, false),
										BlockModelGenerators.condition().term(ResizableBeamBlock.SOUTH, false)
								),
								or(
										BlockModelGenerators.condition().term(ResizableBeamBlock.WEST, false),
										BlockModelGenerators.condition().term(ResizableBeamBlock.EAST, false)
								)
						),
						and(
								BlockModelGenerators.condition().term(ResizableBeamBlock.UP, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.NORTH, true),
								BlockModelGenerators.condition().term(ResizableBeamBlock.SOUTH, true),
								BlockModelGenerators.condition().term(ResizableBeamBlock.EAST, true),
								BlockModelGenerators.condition().term(ResizableBeamBlock.WEST, true)
						)
				)
		), coreWeightedVariantMap.get(size));

		multipartBlockModelDefinitionCreator.with(and(
				BlockModelGenerators.condition().term(ResizableBeamBlock.RADIUS, size),
				BlockModelGenerators.condition().term(ResizableBeamBlock.UP, false),
				BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, false),
				and(
						and(
								BlockModelGenerators.condition().term(ResizableBeamBlock.NORTH, true),
								BlockModelGenerators.condition().term(ResizableBeamBlock.SOUTH, true)
						),
						or(
								BlockModelGenerators.condition().term(ResizableBeamBlock.EAST, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.WEST, false)
						)
				)
		), coreWeightedVariantMap.get(size).with(BlockModelGenerators.X_ROT_90));

		multipartBlockModelDefinitionCreator.with(and(
				BlockModelGenerators.condition().term(ResizableBeamBlock.RADIUS, size),
				BlockModelGenerators.condition().term(ResizableBeamBlock.UP, false),
				BlockModelGenerators.condition().term(ResizableBeamBlock.DOWN, false),
				and(
						and(
								BlockModelGenerators.condition().term(ResizableBeamBlock.EAST, true),
								BlockModelGenerators.condition().term(ResizableBeamBlock.WEST, true)
						),
						or(
								BlockModelGenerators.condition().term(ResizableBeamBlock.NORTH, false),
								BlockModelGenerators.condition().term(ResizableBeamBlock.SOUTH, false)
						)
				)
		), coreWeightedVariantMap.get(size).with(BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_90)));
	}

	public static Condition and(Object... conditions) {
		return combine(CombinedCondition.Operation.AND, conditions);
	}

	public static Condition or(Object... conditions) {
		return combine(CombinedCondition.Operation.OR, conditions);
	}

	private static Condition combine(CombinedCondition.Operation op, Object... conditions) {
		List<Condition> built = Stream.of(conditions)
				.map(condition -> {
					if (condition instanceof ConditionBuilder builder) {
						return builder.build();
					} else if (condition instanceof Condition mc) {
						return mc;
					} else {
						throw new IllegalArgumentException("Unsupported condition type: " + condition.getClass());
					}
				})
				.toList();
		return new CombinedCondition(op, built);
	}

	private MultiVariant getSidedModel(MultiVariant sideUp, MultiVariant sideDown, Direction dir) {
		return switch (dir) {
			case UP, NORTH, EAST -> sideUp;
			case DOWN, SOUTH, WEST -> sideDown;
		};
	}

	private VariantMutator getBeamRotation(Direction dir) {
		return switch (dir) {
			case EAST -> BlockModelGenerators.Y_ROT_90;
			case SOUTH -> BlockModelGenerators.Y_ROT_180;
			case WEST -> BlockModelGenerators.Y_ROT_270;
			case UP -> BlockModelGenerators.X_ROT_270;
			case DOWN -> BlockModelGenerators.X_ROT_90;
			default -> BlockModelGenerators.NOP;
		};
	}
}
