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

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.reg.GWObjects;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public class GWModelProvider extends FabricModelProvider {

	private static final Model STUMP = new Model(
			Optional.of(GuitaWoodworks.id("block/template_stump")),
			Optional.empty(),
			TextureKey.TOP, TextureKey.SIDE);

	public GWModelProvider(FabricDataOutput output) {
		super(output);
	}

	public static Identifier getWoodTypeId(Block block, String target, String replacement) {
		Identifier original = Registries.BLOCK.getId(block);
		String newPath = "block/" + original.getPath().replace(target, replacement);
		return Identifier.ofVanilla(newPath);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> registerStump(blockStateModelGenerator, regEntry.get()));
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {

	}

	public void registerStump(BlockStateModelGenerator blockStateModelGenerator, Block block) {
		Identifier log = Registries.BLOCK.getId(block);
		boolean isNether = log.getPath().matches(".*(crimson|warped).*");
		TextureMap textureMap = new TextureMap().put(TextureKey.SIDE, isNether ? getWoodTypeId(block, "stump", "stem") : getWoodTypeId(block, "stump", "log")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top"));
		Identifier identifier = STUMP.upload(block, textureMap, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)));
		blockStateModelGenerator.registerParentedItemModel(block, identifier);
	}

}
