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

import java.util.Map;
import java.util.Optional;

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.NoCornerModularSeatBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public class GWModelProvider extends FabricModelProvider {

	public GWModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerItemModel(GWObjects.SECATEURS.get());
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
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {

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

	private void registerStump(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top"));
		Identifier identifier = STUMP.upload(block, textureMap, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)));
		blockStateModelGenerator.registerParentedItemModel(block, identifier);
	}

	private void registerCarvedLog(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMapMiddle = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside"));
		TextureMap textureMapCommon = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(log, "_top")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside"));
		Identifier middleModel = CARVED_LOG_MIDDLE.upload(block, textureMapMiddle, blockStateModelGenerator.modelCollector);
		Identifier leftModel = CARVED_LOG_LEFT.upload(block, textureMapCommon, blockStateModelGenerator.modelCollector);
		Identifier rightModel = CARVED_LOG_RIGHT.upload(block, textureMapCommon, blockStateModelGenerator.modelCollector);
		Identifier singleModel = CARVED_LOG_SINGLE.upload(block, textureMapCommon, blockStateModelGenerator.modelCollector);
		Map<NoCornerModularSeatProperty, Identifier> modelMap = Map.of(
				NoCornerModularSeatProperty.SINGLE, singleModel,
				NoCornerModularSeatProperty.LEFT, leftModel,
				NoCornerModularSeatProperty.MIDDLE, middleModel,
				NoCornerModularSeatProperty.RIGHT, rightModel
		);

		BlockStateVariantMap.DoubleProperty<NoCornerModularSeatProperty, Direction> map = BlockStateVariantMap.create(NoCornerModularSeatBlock.SHAPE, NoCornerModularSeatBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.register(shape, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, model))
					.register(shape, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, model).put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.register(shape, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, model).put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.register(shape, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, model).put(VariantSettings.Y, VariantSettings.Rotation.R270));
		}
		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(map));
		blockStateModelGenerator.registerParentedItemModel(block, singleModel);
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
		Identifier coreModel2 = BEAM_CORE_2X2.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Identifier coreModel4 = BEAM_CORE_4X4.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Identifier coreModel6 = BEAM_CORE_6X6.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Identifier coreModel8 = BEAM_CORE_8X8.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Identifier coreModel10 = BEAM_CORE_10X10.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Identifier coreModel12 = BEAM_CORE_12X12.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Identifier coreModel14 = BEAM_CORE_14X14.upload(block, textureMapCore, blockStateModelGenerator.modelCollector);
		Map<Integer, Identifier> coreModelMap = Map.of(
				1, coreModel2,
				2, coreModel4,
				3, coreModel6,
				4, coreModel8,
				5, coreModel10,
				6, coreModel12,
				7, coreModel14
		);
		Identifier sideUpModel2 = BEAM_SIDE_UP_2X2.upload(block, "_up", textureMapSideMap.get(1), blockStateModelGenerator.modelCollector);
		Identifier sideUpModel4 = BEAM_SIDE_UP_4X4.upload(block, "_up", textureMapSideMap.get(2), blockStateModelGenerator.modelCollector);
		Identifier sideUpModel6 = BEAM_SIDE_UP_6X6.upload(block, "_up", textureMapSideMap.get(3), blockStateModelGenerator.modelCollector);
		Identifier sideUpModel8 = BEAM_SIDE_UP_8X8.upload(block, "_up", textureMapSideMap.get(4), blockStateModelGenerator.modelCollector);
		Identifier sideUpModel10 = BEAM_SIDE_UP_10X10.upload(block, "_up", textureMapSideMap.get(5), blockStateModelGenerator.modelCollector);
		Identifier sideUpModel12 = BEAM_SIDE_UP_12X12.upload(block, "_up", textureMapSideMap.get(6), blockStateModelGenerator.modelCollector);
		Identifier sideUpModel14 = BEAM_SIDE_UP_14X14.upload(block, "_up", textureMapSideMap.get(7), blockStateModelGenerator.modelCollector);
		Map<Integer, Identifier> sideUpModelMap = Map.of(
				1, sideUpModel2,
				2, sideUpModel4,
				3, sideUpModel6,
				4, sideUpModel8,
				5, sideUpModel10,
				6, sideUpModel12,
				7, sideUpModel14
		);

		Identifier sideDownModel2 = BEAM_SIDE_DOWN_2X2.upload(block, "_down", textureMapSideMap.get(1), blockStateModelGenerator.modelCollector);
		Identifier sideDownModel4 = BEAM_SIDE_DOWN_4X4.upload(block, "_down", textureMapSideMap.get(2), blockStateModelGenerator.modelCollector);
		Identifier sideDownModel6 = BEAM_SIDE_DOWN_6X6.upload(block, "_down", textureMapSideMap.get(3), blockStateModelGenerator.modelCollector);
		Identifier sideDownModel8 = BEAM_SIDE_DOWN_8X8.upload(block, "_down", textureMapSideMap.get(4), blockStateModelGenerator.modelCollector);
		Identifier sideDownModel10 = BEAM_SIDE_DOWN_10X10.upload(block, "_down", textureMapSideMap.get(5), blockStateModelGenerator.modelCollector);
		Identifier sideDownModel12 = BEAM_SIDE_DOWN_12X12.upload(block, "_down", textureMapSideMap.get(6), blockStateModelGenerator.modelCollector);
		Identifier sideDownModel14 = BEAM_SIDE_DOWN_14X14.upload(block, "_down", textureMapSideMap.get(7), blockStateModelGenerator.modelCollector);
		Map<Integer, Identifier> sideDownModelMap = Map.of(
				1, sideDownModel2,
				2, sideDownModel4,
				3, sideDownModel6,
				4, sideDownModel8,
				5, sideDownModel10,
				6, sideDownModel12,
				7, sideDownModel14
		);

		MultipartBlockStateSupplier blockStateSupplier = MultipartBlockStateSupplier.create(block);

		IntProperty radiusProp = ResizableBeamBlock.RADIUS;
		for (int size : radiusProp.getValues()) {
			blockStateSupplier.with(When.create().set(radiusProp, size), BlockStateVariant.create().put(VariantSettings.MODEL, coreModelMap.get(size)));
			for (Direction dir : Direction.values()) {
				BooleanProperty sideProp = ResizableBeamBlock.FACING_PROPERTIES.get(dir);
				blockStateSupplier.with(When.create().set(radiusProp, size).set(sideProp, true), rotateBeamModel(BlockStateVariant.create().put(VariantSettings.MODEL, getSidedModel(sideUpModelMap.get(size), sideDownModelMap.get(size), dir)), dir));
			}
		}

		blockStateModelGenerator.blockStateCollector.accept(blockStateSupplier);

		Identifier inventoryModel = BEAM_SIDE_INVENTORY.upload(block, textureMapSide8x8, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.registerParentedItemModel(block, inventoryModel);
	}

	private Identifier getSidedModel(Identifier sideUp, Identifier sideDown, Direction dir) {
		return switch (dir) {
			case UP, NORTH, EAST -> sideUp;
			case DOWN, SOUTH, WEST -> sideDown;
		};
	}

	private BlockStateVariant rotateBeamModel(BlockStateVariant blockStateVariant, Direction dir) {
		switch (dir) {
			case EAST -> blockStateVariant.put(VariantSettings.Y, VariantSettings.Rotation.R90);
			case SOUTH -> blockStateVariant.put(VariantSettings.Y, VariantSettings.Rotation.R180);
			case WEST -> blockStateVariant.put(VariantSettings.Y, VariantSettings.Rotation.R270);
			case UP -> blockStateVariant.put(VariantSettings.X, VariantSettings.Rotation.R270);
			case DOWN -> blockStateVariant.put(VariantSettings.X, VariantSettings.Rotation.R90);
		}

		return blockStateVariant;
	}
}
