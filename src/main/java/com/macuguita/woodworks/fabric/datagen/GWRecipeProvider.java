/*
 * Copyright (c) 2026 macuguita
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.macuguita.woodworks.fabric.datagen;

//? fabric {
import java.util.concurrent.CompletableFuture;

import com.macuguita.woodworks.common.reg.GWBlocks;
import com.macuguita.woodworks.common.reg.GWItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

public class GWRecipeProvider extends FabricRecipeProvider {

	public GWRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
		return new RecipeProvider(wrapperLookup, recipeExporter) {
			@Override
			public void buildRecipes() {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.TOOLS, GWItems.SECATEURS.get(), 1)
					.pattern("#$")
					.pattern(" #")
					.define('#', Items.IRON_NUGGET)
					.define('$', Items.SHEARS)
					.unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
					.unlockedBy(getHasName(Items.SHEARS), has(Items.SHEARS))
					.save(recipeExporter);

				GWBlocks.STUMP_BLOCKS.stream().forEach(regEntry -> {
					createStumpRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
					createStumpRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
					createCarvedLogRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
					createCarvedLogRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.BEAM_BLOCKS.stream().forEach(regEntry -> {
					createBeamRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
					createBeamRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
					createHollowLogRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.STRIPPED_HOLLOW_LOG_BLOCKS.stream().forEach(regEntry -> {
					createHollowLogRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.SUPPORT_BLOCKS.stream().forEach(regEntry -> {
					createSupportRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				GWBlocks.SHUTTER_BLOCKS.stream().forEach(regEntry -> {
					createShutterRecipe(recipeExporter, regEntry.get(), GWBlocks.WOOD_ASSOCIATIONS.get(regEntry.get()));
				});
				createStumpRecipe(recipeExporter, GWBlocks.MUSHROOM_STUMP.get(), Blocks.MUSHROOM_STEM);
				createCarvedLogRecipe(recipeExporter, GWBlocks.CARVED_MUSHROOM_STEM.get(), Blocks.MUSHROOM_STEM);
				createBeamRecipe(recipeExporter, GWBlocks.MUSHROOM_BEAM.get(), Blocks.MUSHROOM_STEM);
				createHollowLogRecipe(recipeExporter, GWBlocks.HOLLOW_MUSHROOM_STEM.get(), Blocks.MUSHROOM_STEM);
			}

			private void createStumpRecipe(RecipeOutput exporter, Block stump, Block log) {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.DECORATIONS, stump, 6)
					.pattern("###")
					.define('#', log)
					.unlockedBy(getHasName(log), has(log))
					.save(exporter);
			}

			private void createCarvedLogRecipe(RecipeOutput exporter, Block carvedLog, Block log) {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.DECORATIONS, carvedLog, 6)
					.pattern("#  ")
					.pattern("#  ")
					.pattern("###")
					.define('#', log)
					.unlockedBy(getHasName(log), has(log))
					.save(exporter);
			}

			private void createBeamRecipe(RecipeOutput exporter, Block carvedLog, Block log) {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.DECORATIONS, carvedLog, 12)
					.pattern("#")
					.pattern("#")
					.pattern("#")
					.define('#', log)
					.unlockedBy(getHasName(log), has(log))
					.save(exporter);
			}

			private void createHollowLogRecipe(RecipeOutput exporter, Block carvedLog, Block log) {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.DECORATIONS, carvedLog, 12)
					.pattern("# #")
					.pattern("# #")
					.pattern("# #")
					.define('#', log)
					.unlockedBy(getHasName(log), has(log))
					.save(exporter);
			}

			private void createSupportRecipe(RecipeOutput exporter, Block supportBlock, Block plank) {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.DECORATIONS, supportBlock, 6)
					.pattern("##")
					.pattern("# ")
					.define('#', plank)
					.unlockedBy(getHasName(plank), has(plank))
					.save(exporter);
			}

			private void createShutterRecipe(RecipeOutput exporter, Block supportBlock, Block plank) {
				ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.DECORATIONS, supportBlock, 6)
					.pattern("#")
					.pattern("#")
					.pattern("#")
					.define('#', plank)
					.unlockedBy(getHasName(plank), has(plank))
					.save(exporter);
			}
		};
	}

	@Override
	public String getName() {
		return "guita's Woodworks";
	}
}
//?}
