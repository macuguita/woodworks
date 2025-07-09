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

package com.macuguita.woodworks.compat;

import static com.macuguita.woodworks.GuitaWoodworks.MOD_ID;
import static com.macuguita.woodworks.GuitaWoodworks.id;

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.StumpSeatBlock;
import com.macuguita.woodworks.mixin.FireBlockAccessor;
import com.macuguita.woodworks.reg.GWBlockTags;
import com.macuguita.woodworks.reg.GWItemTags;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.ImageTransformer;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

@SuppressWarnings("removal")
public class WoodGood extends SimpleModule {

	public final SimpleEntrySet<WoodType, Block> stump;
	public final SimpleEntrySet<WoodType, Block> strippedStump;
	public final SimpleEntrySet<WoodType, Block> carvedLog;
	public final SimpleEntrySet<WoodType, Block> strippedCarvedLog;

	public WoodGood(String modId) {
		super(modId, "gww");
		Identifier tab = modRes("main");

		stump = SimpleEntrySet.builder(WoodType.class, "stump",
						GWObjects.OAK_STUMP, () -> WoodTypeRegistry.OAK_TYPE,
						w -> new StumpSeatBlock(Utils.copyPropertySafe(w.log))
				)
				//TEXTURE: log
				.createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE)
				.addTexture(modRes("block/oak_stump_top"))
				.addTag(GWItemTags.STUMP, RegistryKeys.ITEM)
				.addTag(GWBlockTags.STUMP, RegistryKeys.BLOCK)
				.setTabKey(tab)
				//REASON: take a look at their textures, you'll see why.
				.excludeBlockTypes("natures_spirit", "joshua")
				.excludeBlockTypes("terrestria", "sakura")
				.excludeBlockTypes("terrestria", "yucca_palm")
				.defaultRecipe()
				.build();
		this.addEntry(stump);

		strippedStump = SimpleEntrySet.builder(WoodType.class, "stump", "stripped",
						GWObjects.STRIPPED_OAK_STUMP, () -> WoodTypeRegistry.OAK_TYPE,
						w -> new StumpSeatBlock(Utils.copyPropertySafe(w.log))
				)
				.requiresChildren("stripped_log")
				//TEXTURE: stripped_log
				.createPaletteFromChild("stripped_log", SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE)
				.addTexture(modRes("block/stripped_oak_stump_top"))
				.addTag(GWItemTags.STUMP, RegistryKeys.ITEM)
				.addTag(GWBlockTags.STUMP, RegistryKeys.BLOCK)
				.setTabKey(tab)
				//REASON: take a look at their textures, you'll see why.
				.excludeBlockTypes("natures_spirit", "joshua")
				.excludeBlockTypes("terrestria", "sakura")
				.excludeBlockTypes("terrestria", "yucca_palm")
				.defaultRecipe()
				.build();
		this.addEntry(strippedStump);

		carvedLog = SimpleEntrySet.builder(WoodType.class, "log", "carved",
						GWObjects.CARVED_OAK_LOG, () -> WoodTypeRegistry.OAK_TYPE,
						w -> new CarvedLogSeatBlock(Utils.copyPropertySafe(w.log))
				)
				//TEXTURE: log
				.addTag(GWItemTags.CONNECTING, RegistryKeys.ITEM)
				.addTag(GWBlockTags.CONNECTING, RegistryKeys.BLOCK)
				.setTabKey(tab)
				//REASON: take a look at their textures, you'll see why.
				.excludeBlockTypes("natures_spirit", "joshua")
				.excludeBlockTypes("terrestria", "sakura")
				.excludeBlockTypes("terrestria", "yucca_palm")
				.defaultRecipe()
				.build();
		this.addEntry(carvedLog);

		strippedCarvedLog = SimpleEntrySet.builder(WoodType.class, "log", "stripped_carved",
						GWObjects.STRIPPED_CARVED_OAK_LOG, () -> WoodTypeRegistry.OAK_TYPE,
						w -> new CarvedLogSeatBlock(Utils.copyPropertySafe(w.log))
				)
				//TEXTURE: stripped_log
				.addTag(GWItemTags.CONNECTING, RegistryKeys.ITEM)
				.addTag(GWBlockTags.CONNECTING, RegistryKeys.BLOCK)
				.setTabKey(tab)
				//REASON: take a look at their textures, you'll see why.
				.excludeBlockTypes("natures_spirit", "joshua")
				.excludeBlockTypes("terrestria", "sakura")
				.excludeBlockTypes("terrestria", "yucca_palm")
				.defaultRecipe()
				.build();
		this.addEntry(strippedCarvedLog);
	}

	@Override
	public void onModSetup() {
		stump.blocks.forEach((w, block) -> {

			Block stripped = strippedStump.blocks.get(w);
			GWUtils.registerFuel(200, block);
			((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
			if (stripped != null) {
				StumpSeatBlock.STRIPPED_STUMPS.put(block, stripped);
				GWUtils.registerFuel(200, stripped);
				((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(stripped, 5, 5);
			}
		});
		carvedLog.blocks.forEach((w, block) -> {

			Block stripped = strippedCarvedLog.blocks.get(w);
			GWUtils.registerFuel(200, block);
			((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
			if (stripped != null) {
				CarvedLogSeatBlock.STRIPPED_CARVED_LOGS.put(block, stripped);
				GWUtils.registerFuel(200, stripped);
				((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(stripped, 5, 5);
			}
		});
	}

	@Override
	public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
		super.addDynamicClientResources(handler, manager);
		try {
			stump.blocks.forEach((w, block) -> {
				Identifier id = Utils.getID(block);

				try (TextureImage topTexture = TextureImage.open(manager,
						RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

					String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/oak_stump_top", w, id, "oak");

					var newTop = topTexture.makeCopy();
					generateStumpTexture(topTexture, newTop);

					handler.addTextureIfNotPresent(manager, newId, () -> newTop);

				} catch (Exception e) {
					handler.getLogger().error("Failed to generate Branch block texture for for {} : {}", block, e);
				}
			});
		} catch (Exception e) {
			GuitaWoodworks.LOGGER.error("Failed to open branch_top texture: ", e);
		}
		try {
			strippedStump.blocks.forEach((w, block) -> {
				Identifier id = Utils.getID(block);

				try (TextureImage topTexture = TextureImage.open(manager,
						RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

					String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/stripped_oak_stump_top", w, id, "oak");

					var newTop = topTexture.makeCopy();
					generateStumpTexture(topTexture, newTop);

					handler.addTextureIfNotPresent(manager, newId, () -> newTop);

				} catch (Exception e) {
					handler.getLogger().error("Failed to generate Branch block texture for for {} : {}", block, e);
				}
			});
		} catch (Exception e) {
			GuitaWoodworks.LOGGER.error("Failed to open branch_top texture: ", e);
		}

		// gwoodworks/textures block/carved_log_inside_edge.png
		try (TextureImage insideEdgeMask = TextureImage.open(manager, id("block/mask/carved_log_inside_edge"));
			 TextureImage insideMask = TextureImage.open(manager, id("block/mask/carved_log_inside"))
		) {
			carvedLog.blocks.forEach((woodType, block) -> {
				Identifier id = Utils.getID(block);
				String texturePath = "block/carved_oak_log_inside";

				try (TextureImage carvedOakLogInsideTexture = TextureImage.open(manager, id(texturePath));
					 TextureImage logSideTexture = TextureImage.open(manager,
							 RPUtils.findFirstBlockTextureLocation(manager, woodType.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
					 TextureImage planksTexture = TextureImage.open(manager,
							 RPUtils.findFirstBlockTextureLocation(manager, woodType.planks))
				) {

					String newId = BlockTypeResTransformer.replaceTypeNoNamespace(texturePath, woodType, id, "oak");

					TextureImage finishedTexture = generateCarvedLogInsideTexture(carvedOakLogInsideTexture,
							logSideTexture, planksTexture, insideEdgeMask, insideMask);

					handler.addTextureIfNotPresent(manager, newId, () -> finishedTexture);

				} catch (Exception e) {
					handler.getLogger().error("Failed to generate texture for {} : {}", block, e);
				}
			});

			strippedCarvedLog.blocks.forEach((w, block) -> {
				Identifier id = Utils.getID(block);
				String texturePath = "block/stripped_carved_oak_log_inside";

				try (TextureImage strippedCarvedLogTexture = TextureImage.open(manager, id(texturePath));
					 TextureImage logSideTexture = TextureImage.open(manager,
							 RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
					 TextureImage planksTexture = TextureImage.open(manager,
							 RPUtils.findFirstBlockTextureLocation(manager, w.planks))
				) {

					String newId = BlockTypeResTransformer.replaceTypeNoNamespace(texturePath, w, id, "oak");

					TextureImage finishedTexture = generateCarvedLogInsideTexture(strippedCarvedLogTexture, logSideTexture, planksTexture, insideEdgeMask, insideMask);

					handler.addTextureIfNotPresent(manager, newId, () -> finishedTexture);

				} catch (Exception e) {
					handler.getLogger().error("Failed to generate texture for {} : {}", block, e);
				}
			});
		} catch (Exception e) {
			GuitaWoodworks.LOGGER.error("Failed to open the mask texture: ", e);
		}
	}

	private void generateStumpTexture(TextureImage original, TextureImage target) {
		ImageTransformer transformer = ImageTransformer.builder(16, 16, 16, 16)
				.copyRect(0, 0, 16, 1, 0, 2, 16, 1) // Top border
				.copyRect(0, 15, 16, 1, 0, 13, 16, 1) // Bottom border
				.copyRect(0, 0, 1, 16, 2, 0, 1, 16) // Left border
				.copyRect(15, 0, 1, 16, 13, 0, 1, 16) // Right border
				.build();

		transformer.apply(original, target);

		target.forEachFramePixel((i, x, y) -> {
			int localX = x - target.getFrameStartX(i);
			int localY = y - target.getFrameStartY(i);

			boolean insideOpaqueRegion = (localX >= 2 && localX <= 13) && (localY >= 2 && localY <= 13);

			if (!insideOpaqueRegion) {
				target.getImage().setColor(x, y, 0);
			}
		});
	}

	private TextureImage generateCarvedLogInsideTexture(TextureImage mainTexture, TextureImage logSideTexture, TextureImage planksTexture,
														TextureImage insideEdgeMask, TextureImage insideMask) {
		Respriter targetEdge = Respriter.masked(mainTexture, insideMask);

		TextureImage recoloredEdge = targetEdge.recolorWithAnimationOf(logSideTexture);

		Respriter targetInside = Respriter.masked(recoloredEdge, insideEdgeMask);

		// Finished Texture
		return targetInside.recolorWithAnimationOf(planksTexture);

	}

	@Override
	public boolean isEntryAlreadyRegistered(String blockId, BlockType blockType, Registry<?> registry) {
		// blockId: everycomp:twigs/biomesoplenty/willow_table | blockName: willow_table
		String blockName = blockId.substring(blockId.lastIndexOf("/") + 1);

		if (blockType instanceof WoodType wt) {
			Boolean hardcoded = CustomHardcodedBlockType.isWoodBlockAlreadyRegistered(blockName, wt, modId, shortenedId());
			if (hardcoded != null) return hardcoded;
		}

		return super.isEntryAlreadyRegistered(blockId, blockType, registry);
	}

	public static class CustomHardcodedBlockType extends HardcodedBlockType {

		@Nullable
		public static Boolean isWoodBlockAlreadyRegistered(String blockName, WoodType woodType, String ModId, String shortenedId) {
			woodTypeFromMod = woodType.getNamespace();
			woodidentify = woodType.getId().toString();
			modId = ModId;
			supportedBlockName = blockName;
			shortenedIdenfity = shortenedId;

			/// ========== INCLUDE VANILLA TYPE ========== \\\
			if (isWoodFrom(MOD_ID, "", "", "minecraft:(acacia|jungle|dark_oak|spruce|mangrove|cherry)", "(stripped_)?carved_\\w+_log")) return false;

			return null;
		}
	}
}
