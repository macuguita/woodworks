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

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

public class GWRecipeProvider extends FabricRecipeProvider {

	public GWRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput recipeExporter) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, GWObjects.SECATEURS.get(), 1)
				.pattern("#$")
				.pattern(" #")
				.define('#', Items.IRON_NUGGET)
				.define('$', Items.SHEARS)
				.unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
				.unlockedBy(getHasName(Items.SHEARS), has(Items.SHEARS))
				.save(recipeExporter);

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
		GWObjects.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
			createSupportRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		GWObjects.SHUTTER_BLOCKS.stream().forEach(regEntry -> {
			createShutterRecipe(recipeExporter, regEntry.get(), GWObjects.WOOD_ASSOCIATIONS.get(regEntry.get()));
		});
		createStumpRecipe(recipeExporter, GWObjects.MUSHROOM_STUMP.get(), Blocks.MUSHROOM_STEM);
		createCarvedLogRecipe(recipeExporter, GWObjects.CARVED_MUSHROOM_STEM.get(), Blocks.MUSHROOM_STEM);
		createBeamRecipe(recipeExporter, GWObjects.MUSHROOM_BEAM.get(), Blocks.MUSHROOM_STEM);
		createHollowLogRecipe(recipeExporter, GWObjects.HOLLOW_MUSHROOM_STEM.get(), Blocks.MUSHROOM_STEM);
	}

	private void createStumpRecipe(RecipeOutput exporter, Block stump, Block log) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, stump, 6)
				.pattern("###")
				.define('#', log)
				.unlockedBy(getHasName(log), has(log))
				.save(exporter);
	}

	private void createCarvedLogRecipe(RecipeOutput exporter, Block carvedLog, Block log) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carvedLog, 6)
				.pattern("#  ")
				.pattern("#  ")
				.pattern("###")
				.define('#', log)
				.unlockedBy(getHasName(log), has(log))
				.save(exporter);
	}

	private void createBeamRecipe(RecipeOutput exporter, Block carvedLog, Block log) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carvedLog, 12)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.define('#', log)
				.unlockedBy(getHasName(log), has(log))
				.save(exporter);
	}

	private void createHollowLogRecipe(RecipeOutput exporter, Block carvedLog, Block log) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carvedLog, 12)
				.pattern("# #")
				.pattern("# #")
				.pattern("# #")
				.define('#', log)
				.unlockedBy(getHasName(log), has(log))
				.save(exporter);
	}

	private void createSupportRecipe(RecipeOutput exporter, Block supportBlock, Block plank) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, supportBlock, 6)
				.pattern("##")
				.pattern("# ")
				.define('#', plank)
				.unlockedBy(getHasName(plank), has(plank))
				.save(exporter);
	}

	private void createShutterRecipe(RecipeOutput exporter, Block supportBlock, Block plank) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, supportBlock, 6)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.define('#', plank)
				.unlockedBy(getHasName(plank), has(plank))
				.save(exporter);
	}
}
