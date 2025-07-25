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

import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.BlockStateVariantMap;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.MultipartBlockModelDefinitionCreator;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.MultipartModelCombinedCondition;
import net.minecraft.client.render.model.json.MultipartModelCondition;
import net.minecraft.client.render.model.json.MultipartModelConditionBuilder;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public class GWModelProvider extends FabricModelProvider {

	public GWModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			registerStump(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			registerStump(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			registerCarvedLog(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			registerCarvedLog(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			registerBeamBlock(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			registerBeamBlock(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			registerHollowLog(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			registerHollowLog(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(GWObjects.SECATEURS.get(), Models.GENERATED);
	}

	private static final Model STUMP = new Model(
			Optional.of(GuitaWoodworks.id("block/template_stump")),
			Optional.empty(),
			TextureKey.TOP, TextureKey.SIDE);

	private static final Model CARVED_LOG_MIDDLE = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_middle")),
			Optional.of("_middle"),
			TextureKey.SIDE, TextureKey.INSIDE);

	private static final Model CARVED_LOG_LEFT = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_left")),
			Optional.of("_left"),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private static final Model CARVED_LOG_RIGHT = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_right")),
			Optional.of("_right"),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private static final Model CARVED_LOG_SINGLE = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_single")),
			Optional.of("_single"),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private static final Model BEAM_SIDE_INVENTORY = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/template_beam_inventory")),
			Optional.empty(),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_CORE_2X2 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_2x2")),
			Optional.of("_core_2x2"),
			TextureKey.SIDE);

	private static final Model BEAM_CORE_4X4 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_4x4")),
			Optional.of("_core_4x4"),
			TextureKey.SIDE);

	private static final Model BEAM_CORE_6X6 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_6x6")),
			Optional.of("_core_6x6"),
			TextureKey.SIDE);

	private static final Model BEAM_CORE_8X8 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_8x8")),
			Optional.of("_core_8x8"),
			TextureKey.SIDE);

	private static final Model BEAM_CORE_10X10 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_10x10")),
			Optional.of("_core_10x10"),
			TextureKey.SIDE);

	private static final Model BEAM_CORE_12X12 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_12x12")),
			Optional.of("_core_12x12"),
			TextureKey.SIDE);

	private static final Model BEAM_CORE_14X14 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/cores/template_beam_core_14x14")),
			Optional.of("_core_14x14"),
			TextureKey.SIDE);

	private static final Model BEAM_SIDE_UP_2X2 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_2x2")),
			Optional.of("_side_2x2"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_UP_4X4 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_4x4")),
			Optional.of("_side_4x4"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_UP_6X6 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_6x6")),
			Optional.of("_side_6x6"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_UP_8X8 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_8x8")),
			Optional.of("_side_8x8"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_UP_10X10 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_10x10")),
			Optional.of("_side_10x10"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_UP_12X12 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_12x12")),
			Optional.of("_side_12x12"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_UP_14X14 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_up/template_beam_side_14x14")),
			Optional.of("_side_14x14"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_2X2 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_2x2")),
			Optional.of("_side_2x2"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_4X4 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_4x4")),
			Optional.of("_side_4x4"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_6X6 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_6x6")),
			Optional.of("_side_6x6"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_8X8 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_8x8")),
			Optional.of("_side_8x8"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_10X10 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_10x10")),
			Optional.of("_side_10x10"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_12X12 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_12x12")),
			Optional.of("_side_12x12"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model BEAM_SIDE_DOWN_14X14 = new Model(
			Optional.of(GuitaWoodworks.id("block/beams/sides_down/template_beam_side_14x14")),
			Optional.of("_side_14x14"),
			TextureKey.SIDE, TextureKey.TOP);

	private static final Model HOLLOW_LOG = new Model(
			Optional.of(GuitaWoodworks.id("block/template_hollow_log")),
			Optional.empty(),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private void registerStump(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top"));
		Identifier id = STUMP.upload(block, textureMap, blockStateModelGenerator.modelCollector);
		WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(id);
		blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, weightedVariant));
		blockStateModelGenerator.registerParentedItemModel(block, id);
	}

	private void registerCarvedLog(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMapMiddle = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside"));
		TextureMap textureMapCommon = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(log, "_top")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside"));
		Identifier singleModelId = CARVED_LOG_SINGLE.upload(block, textureMapCommon, blockStateModelGenerator.modelCollector);
		WeightedVariant singleWeightedVariant = BlockStateModelGenerator.createWeightedVariant(singleModelId);
		WeightedVariant middleWeightedVariant = BlockStateModelGenerator.createWeightedVariant(CARVED_LOG_MIDDLE.upload(block, textureMapMiddle, blockStateModelGenerator.modelCollector));
		WeightedVariant leftWeightedVariant = BlockStateModelGenerator.createWeightedVariant(CARVED_LOG_LEFT.upload(block, textureMapCommon, blockStateModelGenerator.modelCollector));
		WeightedVariant rightWeightedVariant = BlockStateModelGenerator.createWeightedVariant(CARVED_LOG_RIGHT.upload(block, textureMapCommon, blockStateModelGenerator.modelCollector));
		BlockStateVariantMap<ModelVariantOperator> rotationOperations = BlockStateVariantMap.operations(
						NoCornerModularSeatBlock.FACING
				)
				.register(Direction.EAST, BlockStateModelGenerator.ROTATE_Y_90)
				.register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_Y_180)
				.register(Direction.WEST, BlockStateModelGenerator.ROTATE_Y_270)
				.register(Direction.NORTH, BlockStateModelGenerator.NO_OP);

		Map<NoCornerModularSeatProperty, WeightedVariant> modelMap = Map.of(
				NoCornerModularSeatProperty.SINGLE, singleWeightedVariant,
				NoCornerModularSeatProperty.LEFT, leftWeightedVariant,
				NoCornerModularSeatProperty.MIDDLE, middleWeightedVariant,
				NoCornerModularSeatProperty.RIGHT, rightWeightedVariant
		);

		var blockStateVariantMap = BlockStateVariantMap.models(NoCornerModularSeatBlock.SHAPE);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			blockStateVariantMap.register(shape, modelMap.get(shape));
		}

		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block).with(blockStateVariantMap).coordinate(rotationOperations));
		blockStateModelGenerator.registerParentedItemModel(block, singleModelId);
	}

	private void registerBeamBlock(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMapCore = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log));
		TextureMap textureMapSide2x2 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_2x2"));
		TextureMap textureMapSide4x4 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_4x4"));
		TextureMap textureMapSide6x6 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_6x6"));
		TextureMap textureMapSide8x8 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_8x8"));
		TextureMap textureMapSide10x10 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_10x10"));
		TextureMap textureMapSide12x12 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_12x12"));
		TextureMap textureMapSide14x14 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top_14x14"));
		Map<Integer, TextureMap> textureMapSideMap = Map.of(
				1, textureMapSide2x2,
				2, textureMapSide4x4,
				3, textureMapSide6x6,
				4, textureMapSide8x8,
				5, textureMapSide10x10,
				6, textureMapSide12x12,
				7, textureMapSide14x14
		);
		WeightedVariant weightedVariantCore2 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_2X2.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantCore4 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_4X4.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantCore6 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_6X6.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantCore8 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_8X8.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantCore10 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_10X10.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantCore12 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_12X12.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantCore14 = BlockStateModelGenerator.createWeightedVariant(BEAM_CORE_14X14.upload(block, textureMapCore, blockStateModelGenerator.modelCollector));
		Map<Integer, WeightedVariant> coreWeightedVariantMap = Map.of(
				1, weightedVariantCore2,
				2, weightedVariantCore4,
				3, weightedVariantCore6,
				4, weightedVariantCore8,
				5, weightedVariantCore10,
				6, weightedVariantCore12,
				7, weightedVariantCore14
		);
		WeightedVariant weightedVariantSideUp2 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_2X2.upload(block, "_up", textureMapSideMap.get(1), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideUp4 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_4X4.upload(block, "_up", textureMapSideMap.get(2), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideUp6 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_6X6.upload(block, "_up", textureMapSideMap.get(3), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideUp8 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_8X8.upload(block, "_up", textureMapSideMap.get(4), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideUp10 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_10X10.upload(block, "_up", textureMapSideMap.get(5), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideUp12 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_12X12.upload(block, "_up", textureMapSideMap.get(6), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideUp14 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_UP_14X14.upload(block, "_up", textureMapSideMap.get(7), blockStateModelGenerator.modelCollector));
		Map<Integer, WeightedVariant> sideUpWeightedVariantMap = Map.of(
				1, weightedVariantSideUp2,
				2, weightedVariantSideUp4,
				3, weightedVariantSideUp6,
				4, weightedVariantSideUp8,
				5, weightedVariantSideUp10,
				6, weightedVariantSideUp12,
				7, weightedVariantSideUp14
		);

		WeightedVariant weightedVariantSideDown2 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_2X2.upload(block, "_down", textureMapSideMap.get(1), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideDown4 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_4X4.upload(block, "_down", textureMapSideMap.get(2), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideDown6 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_6X6.upload(block, "_down", textureMapSideMap.get(3), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideDown8 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_8X8.upload(block, "_down", textureMapSideMap.get(4), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideDown10 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_10X10.upload(block, "_down", textureMapSideMap.get(5), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideDown12 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_12X12.upload(block, "_down", textureMapSideMap.get(6), blockStateModelGenerator.modelCollector));
		WeightedVariant weightedVariantSideDown14 = BlockStateModelGenerator.createWeightedVariant(BEAM_SIDE_DOWN_14X14.upload(block, "_down", textureMapSideMap.get(7), blockStateModelGenerator.modelCollector));
		Map<Integer, WeightedVariant> sideDownWeightedVariantMap = Map.of(
				1, weightedVariantSideDown2,
				2, weightedVariantSideDown4,
				3, weightedVariantSideDown6,
				4, weightedVariantSideDown8,
				5, weightedVariantSideDown10,
				6, weightedVariantSideDown12,
				7, weightedVariantSideDown14
		);

		MultipartBlockModelDefinitionCreator multipartBlockModelDefinitionCreator = MultipartBlockModelDefinitionCreator.create(block);

		IntProperty radiusProp = ResizableBeamBlock.RADIUS;
		Map<Direction, BooleanProperty> facingProperties = ResizableBeamBlock.FACING_PROPERTIES;
		for (int size : radiusProp.getValues()) {
			generateRotatedCoreModels(multipartBlockModelDefinitionCreator, coreWeightedVariantMap, size);
			for (Direction dir : Direction.values()) {
				BooleanProperty sideProp = facingProperties.get(dir);
				multipartBlockModelDefinitionCreator.with(BlockStateModelGenerator.createMultipartConditionBuilder()
						.put(radiusProp, size)
						.put(sideProp, true), getSidedModel(sideUpWeightedVariantMap.get(size), sideDownWeightedVariantMap.get(size), dir).apply(getBeamRotation(dir)));
			}
		}

		blockStateModelGenerator.blockStateCollector.accept(multipartBlockModelDefinitionCreator);

		Identifier inventoryModel = BEAM_SIDE_INVENTORY.upload(block, textureMapSide8x8, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.registerParentedItemModel(block, inventoryModel);
	}

	private void registerHollowLog(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(log, "_top")).put(TextureKey.INSIDE, TextureMap.getId(GWUtils.getStrippedBlockOrSelf(log)));
		Identifier model = HOLLOW_LOG.upload(block, textureMap, blockStateModelGenerator.modelCollector);
		WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(model);

		BlockStateVariantMap<ModelVariantOperator> rotationOperations = BlockStateVariantMap.operations(
						HollowLogBlock.AXIS
				)
				.register(Direction.Axis.X, BlockStateModelGenerator.ROTATE_X_90.then(BlockStateModelGenerator.ROTATE_Y_90))
				.register(Direction.Axis.Y, BlockStateModelGenerator.NO_OP)
				.register(Direction.Axis.Z, BlockStateModelGenerator.ROTATE_X_90);

		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block, weightedVariant).coordinate(rotationOperations));
		blockStateModelGenerator.registerParentedItemModel(block, model);
	}

	private void generateRotatedCoreModels(MultipartBlockModelDefinitionCreator multipartBlockModelDefinitionCreator, Map<Integer, WeightedVariant> coreWeightedVariantMap, int size) {
		multipartBlockModelDefinitionCreator.with(and(
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.RADIUS, size),
				or(
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, false),
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, false),
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.NORTH, false),
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.SOUTH, false),
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.EAST, false),
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.WEST, false)
				),
				or(
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, true),
						BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, true),
						and(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.NORTH, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.SOUTH, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.EAST, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.WEST, false)
						),
						and(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, false),
								or(
										BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.NORTH, false),
										BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.SOUTH, false)
								),
								or(
										BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.WEST, false),
										BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.EAST, false)
								)
						),
						and(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.NORTH, true),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.SOUTH, true),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.EAST, true),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.WEST, true)
						)
				)
		), coreWeightedVariantMap.get(size));

		multipartBlockModelDefinitionCreator.with(and(
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.RADIUS, size),
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, false),
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, false),
				and(
						and(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.NORTH, true),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.SOUTH, true)
						),
						or(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.EAST, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.WEST, false)
						)
				)
		), coreWeightedVariantMap.get(size).apply(BlockStateModelGenerator.ROTATE_X_90));

		multipartBlockModelDefinitionCreator.with(and(
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.RADIUS, size),
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.UP, false),
				BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.DOWN, false),
				and(
						and(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.EAST, true),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.WEST, true)
						),
						or(
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.NORTH, false),
								BlockStateModelGenerator.createMultipartConditionBuilder().put(ResizableBeamBlock.SOUTH, false)
						)
				)
		), coreWeightedVariantMap.get(size).apply(BlockStateModelGenerator.ROTATE_X_90.then(BlockStateModelGenerator.ROTATE_Y_90)));
	}

	public static MultipartModelCondition and(Object... conditions) {
		return combine(MultipartModelCombinedCondition.LogicalOperator.AND, conditions);
	}

	public static MultipartModelCondition or(Object... conditions) {
		return combine(MultipartModelCombinedCondition.LogicalOperator.OR, conditions);
	}

	private static MultipartModelCondition combine(MultipartModelCombinedCondition.LogicalOperator op, Object... conditions) {
		List<MultipartModelCondition> built = Stream.of(conditions)
				.map(condition -> {
					if (condition instanceof MultipartModelConditionBuilder builder) {
						return builder.build();
					} else if (condition instanceof MultipartModelCondition mc) {
						return mc;
					} else {
						throw new IllegalArgumentException("Unsupported condition type: " + condition.getClass());
					}
				})
				.toList();
		return new MultipartModelCombinedCondition(op, built);
	}

	private WeightedVariant getSidedModel(WeightedVariant sideUp, WeightedVariant sideDown, Direction dir) {
		return switch (dir) {
			case UP, NORTH, EAST -> sideUp;
			case DOWN, SOUTH, WEST -> sideDown;
		};
	}

	private ModelVariantOperator getBeamRotation(Direction dir) {
		return switch (dir) {
			case EAST -> BlockStateModelGenerator.ROTATE_Y_90;
			case SOUTH -> BlockStateModelGenerator.ROTATE_Y_180;
			case WEST -> BlockStateModelGenerator.ROTATE_Y_270;
			case UP -> BlockStateModelGenerator.ROTATE_X_270;
			case DOWN -> BlockStateModelGenerator.ROTATE_X_90;
			default -> BlockStateModelGenerator.NO_OP;
		};
	}
}
