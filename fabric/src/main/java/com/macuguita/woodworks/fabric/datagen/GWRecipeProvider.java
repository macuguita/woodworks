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

import java.util.concurrent.CompletableFuture;

import com.macuguita.woodworks.reg.GWObjects;

import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

public class GWRecipeProvider extends FabricRecipeProvider {

	public GWRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate(RecipeExporter recipeExporter) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, GWObjects.SECATEURS.get(), 1)
				.pattern("#$")
				.pattern(" #")
				.input('#', Items.IRON_NUGGET)
				.input('$', Items.SHEARS)
				.criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
				.criterion(hasItem(Items.SHEARS), conditionsFromItem(Items.SHEARS))
				.offerTo(recipeExporter);

		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			createStumpRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			createStumpRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			createCarvedLogRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			createCarvedLogRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			createBeamRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			createBeamRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			createHollowLogRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
			createHollowLogRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
	}

	private void createStumpRecipe(RecipeExporter exporter, Block stump, Block log) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, stump, 6)
				.pattern("###")
				.input('#', log)
				.criterion(hasItem(log), conditionsFromItem(log))
				.offerTo(exporter);
	}

	private void createCarvedLogRecipe(RecipeExporter exporter, Block carvedLog, Block log) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, carvedLog, 6)
				.pattern("#  ")
				.pattern("#  ")
				.pattern("###")
				.input('#', log)
				.criterion(hasItem(log), conditionsFromItem(log))
				.offerTo(exporter);
	}

	private void createBeamRecipe(RecipeExporter exporter, Block carvedLog, Block log) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, carvedLog, 12)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.input('#', log)
				.criterion(hasItem(log), conditionsFromItem(log))
				.offerTo(exporter);
	}

	private void createHollowLogRecipe(RecipeExporter exporter, Block carvedLog, Block log) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, carvedLog, 12)
				.pattern("# #")
				.pattern("# #")
				.pattern("# #")
				.input('#', log)
				.criterion(hasItem(log), conditionsFromItem(log))
				.offerTo(exporter);
	}
}
