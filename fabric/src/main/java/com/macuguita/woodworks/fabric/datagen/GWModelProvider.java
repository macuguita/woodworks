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

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.NoCornerModularSeatBlock;
import com.macuguita.woodworks.block.property.NoCornerModularSeatProperty;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
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
		AtomicInteger iterator = new AtomicInteger();
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			registerStump(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_LOGS[iterator.get()]);
			registerStump(blockStateModelGenerator, GWUtils.getStrippedStump(regEntry.get()), GWObjects.STRIPPED_WOOD_LOGS[iterator.get()]);
			iterator.getAndIncrement();
		});
		iterator.set(0);
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			registerCarvedLog(blockStateModelGenerator, regEntry.get(), GWObjects.WOOD_LOGS[iterator.get()]);
			registerCarvedLog(blockStateModelGenerator, GWUtils.getStrippedCarvedLog(regEntry.get()), GWObjects.STRIPPED_WOOD_LOGS[iterator.get()]);
			iterator.getAndIncrement();
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
			Optional.empty(),
			TextureKey.SIDE, TextureKey.INSIDE);

	private static final Model CARVED_LOG_LEFT = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_left")),
			Optional.empty(),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private static final Model CARVED_LOG_RIGHT = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_right")),
			Optional.empty(),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private static final Model CARVED_LOG_SINGLE = new Model(
			Optional.of(GuitaWoodworks.id("block/template_carved_log_single")),
			Optional.empty(),
			TextureKey.SIDE, TextureKey.TOP, TextureKey.INSIDE);

	private void registerStump(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top"));
		Identifier identifier = STUMP.upload(block, textureMap, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)));
		blockStateModelGenerator.registerParentedItemModel(block, identifier);
	}

	private void registerCarvedLog(BlockStateModelGenerator blockStateModelGenerator, Block block, Block log) {
		TextureMap textureMapMiddle = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside"));
		TextureMap textureMapCommon = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(log)).put(TextureKey.TOP, TextureMap.getSubId(log, "_top")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside"));
		Identifier middleModel = CARVED_LOG_MIDDLE.upload(block, "_middle", textureMapMiddle, blockStateModelGenerator.modelCollector);
		Identifier leftModel = CARVED_LOG_LEFT.upload(block, "_left", textureMapCommon, blockStateModelGenerator.modelCollector);
		Identifier rightModel = CARVED_LOG_RIGHT.upload(block, "_right", textureMapCommon, blockStateModelGenerator.modelCollector);
		Identifier singleModel = CARVED_LOG_SINGLE.upload(block, "_single", textureMapCommon, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.blockStateCollector.accept(
				VariantsBlockStateSupplier.create(block)
						.coordinate(BlockStateVariantMap.create(NoCornerModularSeatBlock.SHAPE, NoCornerModularSeatBlock.FACING)
								.register(NoCornerModularSeatProperty.MIDDLE, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, middleModel))
								.register(NoCornerModularSeatProperty.MIDDLE, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, middleModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
								.register(NoCornerModularSeatProperty.MIDDLE, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, middleModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
								.register(NoCornerModularSeatProperty.MIDDLE, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, middleModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
								.register(NoCornerModularSeatProperty.LEFT, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, leftModel))
								.register(NoCornerModularSeatProperty.LEFT, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, leftModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
								.register(NoCornerModularSeatProperty.LEFT, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, leftModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
								.register(NoCornerModularSeatProperty.LEFT, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, leftModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
								.register(NoCornerModularSeatProperty.RIGHT, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, rightModel))
								.register(NoCornerModularSeatProperty.RIGHT, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, rightModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
								.register(NoCornerModularSeatProperty.RIGHT, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, rightModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
								.register(NoCornerModularSeatProperty.RIGHT, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, rightModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
								.register(NoCornerModularSeatProperty.SINGLE, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, singleModel))
								.register(NoCornerModularSeatProperty.SINGLE, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, singleModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
								.register(NoCornerModularSeatProperty.SINGLE, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, singleModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
								.register(NoCornerModularSeatProperty.SINGLE, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, singleModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						)
		);
		blockStateModelGenerator.registerParentedItemModel(block, singleModel);
	}
}
