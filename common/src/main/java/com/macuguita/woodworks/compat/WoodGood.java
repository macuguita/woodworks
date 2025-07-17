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

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.HollowLogBlock;
import com.macuguita.woodworks.block.ResizableBeamBlock;
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
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("removal")
public class WoodGood extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> stump;
    public final SimpleEntrySet<WoodType, Block> strippedStump;
    public final SimpleEntrySet<WoodType, Block> carvedLog;
    public final SimpleEntrySet<WoodType, Block> strippedCarvedLog;
    public final SimpleEntrySet<WoodType, Block> beam;
    public final SimpleEntrySet<WoodType, Block> strippedBeam;
    public final SimpleEntrySet<WoodType, Block> hollowLog;
    public final SimpleEntrySet<WoodType, Block> strippedHollowLog;

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
                //TEXTURE: manually generated texture below (carved_oak_log_inside.png)
                .addTag(GWItemTags.CARVED_LOG, RegistryKeys.ITEM)
                .addTag(GWBlockTags.CARVED_LOG, RegistryKeys.BLOCK)
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
                //TEXTURE: manually generated texture below (stripped_carved_oak_log_inside.png)
                .addTag(GWItemTags.CARVED_LOG, RegistryKeys.ITEM)
                .addTag(GWBlockTags.CARVED_LOG, RegistryKeys.BLOCK)
                .setTabKey(tab)
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("natures_spirit", "joshua")
                .excludeBlockTypes("terrestria", "sakura")
                .excludeBlockTypes("terrestria", "yucca_palm")
                .defaultRecipe()
                .build();
        this.addEntry(strippedCarvedLog);

        beam = SimpleEntrySet.builder(WoodType.class, "beam",
                        GWObjects.OAK_BEAM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ResizableBeamBlock(Utils.copyPropertySafe(w.log))
                )
                .addTag(GWItemTags.BEAM, RegistryKeys.ITEM)
                .addTag(GWBlockTags.BEAM, RegistryKeys.BLOCK)
                .setTabKey(tab)
                .excludeBlockTypes("natures_spirit", "joshua")
                .excludeBlockTypes("terrestria", "sakura")
                .excludeBlockTypes("terrestria", "yucca_palm")
                .defaultRecipe()
                .build();
        this.addEntry(beam);

        strippedBeam = SimpleEntrySet.builder(WoodType.class, "beam", "stripped",
                        GWObjects.STRIPPED_OAK_BEAM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ResizableBeamBlock(Utils.copyPropertySafe(w.log))
                )
                .addTag(GWItemTags.BEAM, RegistryKeys.ITEM)
                .addTag(GWBlockTags.BEAM, RegistryKeys.BLOCK)
                .setTabKey(tab)
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("natures_spirit", "joshua")
                .excludeBlockTypes("terrestria", "sakura")
                .excludeBlockTypes("terrestria", "yucca_palm")
                .defaultRecipe()
                .build();
        this.addEntry(strippedBeam);

        hollowLog = SimpleEntrySet.builder(WoodType.class, "log", "hollow",
                        GWObjects.HOLLOW_OAK_LOG, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HollowLogBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURE: stripped_log
                .requiresChildren("stripped_log")
                .addTag(GWItemTags.BEAM, RegistryKeys.ITEM)
                .addTag(GWBlockTags.BEAM, RegistryKeys.BLOCK)
                .setTabKey(tab)
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("natures_spirit", "joshua")
                .excludeBlockTypes("terrestria", "sakura")
                .excludeBlockTypes("terrestria", "yucca_palm")
                .defaultRecipe()
                .build();
        this.addEntry(hollowLog);

        strippedHollowLog = SimpleEntrySet.builder(WoodType.class, "log", "stripped_hollow",
                        GWObjects.STRIPPED_HOLLOW_OAK_LOG, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HollowLogBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURE: stripped_log
                .requiresChildren("stripped_log")
                .addTag(GWItemTags.BEAM, RegistryKeys.ITEM)
                .addTag(GWBlockTags.BEAM, RegistryKeys.BLOCK)
                .setTabKey(tab)
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("natures_spirit", "joshua")
                .excludeBlockTypes("terrestria", "sakura")
                .excludeBlockTypes("terrestria", "yucca_palm")
                .defaultRecipe()
                .build();
        this.addEntry(strippedHollowLog);
    }

    @Override
    public void onModSetup() {
        stump.blocks.forEach((w, block) -> {

            Block stripped = strippedStump.blocks.get(w);
            GWUtils.registerFuel(150, block);
            ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
            if (stripped != null) {
                StumpSeatBlock.STRIPPED_STUMPS.put(block, stripped);
                GWUtils.registerFuel(150, stripped);
                ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(stripped, 5, 5);
            }
        });
        carvedLog.blocks.forEach((w, block) -> {

            Block stripped = strippedCarvedLog.blocks.get(w);
            GWUtils.registerFuel(250, block);
            ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
            if (stripped != null) {
                CarvedLogSeatBlock.STRIPPED_CARVED_LOGS.put(block, stripped);
                GWUtils.registerFuel(250, stripped);
                ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(stripped, 5, 5);
            }
        });
        beam.blocks.forEach((w, block) -> {

            Block stripped = strippedBeam.blocks.get(w);
            GWUtils.registerFuel(75, block);
            ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
            if (stripped != null) {
                ResizableBeamBlock.STRIPPED_BEAM_BLOCKS.put(block, stripped);
                GWUtils.registerFuel(75, stripped);
                ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(stripped, 5, 5);
            }
        });
        hollowLog.blocks.forEach((w, block) -> {

            Block stripped = strippedHollowLog.blocks.get(w);
            GWUtils.registerFuel(150, block);
            ((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(block, 5, 5);
            if (stripped != null) {
                HollowLogBlock.STRIPPED_HOLLOW_LOGS.put(block, stripped);
                GWUtils.registerFuel(150, stripped);
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
                    handler.getLogger().error("Failed to generate texture for {} : {}", block, e);
                }
            });
        } catch (Exception e) {
            GuitaWoodworks.LOGGER.error("Failed to open stump_top texture: ", e);
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
                    handler.getLogger().error("Failed to generate texture for {} : {}", block, e);
                }
            });
        } catch (Exception e) {
            GuitaWoodworks.LOGGER.error("Failed to open stump_top texture: ", e);
        }

        // gwoodworks/textures block/carved_log_inside_edge.png
        try (TextureImage insideEdgeMask = TextureImage.open(manager, GuitaWoodworks.id("block/mask/carved_log_inside_edge"));
             TextureImage insideMask = TextureImage.open(manager, GuitaWoodworks.id("block/mask/carved_log_inside"))
        ) {
            carvedLog.blocks.forEach((woodType, block) -> {
                Identifier id = Utils.getID(block);
                String texturePath = "block/carved_oak_log_inside";

                try (TextureImage carvedOakLogInsideTexture = TextureImage.open(manager, GuitaWoodworks.id(texturePath));
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

                try (TextureImage strippedCarvedLogTexture = TextureImage.open(manager, GuitaWoodworks.id(texturePath));
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

        try {
            beam.blocks.forEach((w, block) -> {
                Identifier id = Utils.getID(block);
                String baseTexturePath = "block/oak_beam_top";

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId2x2 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_2x2", w, id, "oak");
                    String newId4x4 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_4x4", w, id, "oak");
                    String newId6x6 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_6x6", w, id, "oak");
                    String newId8x8 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_8x8", w, id, "oak");
                    String newId10x10 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_10x10", w, id, "oak");
                    String newId12x12 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_12x12", w, id, "oak");
                    String newId14x14 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_14x14", w, id, "oak");
                    String[] newIds = {
                            newId2x2,
                            newId4x4,
                            newId6x6,
                            newId8x8,
                            newId10x10,
                            newId12x12,
                            newId14x14
                    };

                    var newTop2x2 = topTexture.makeCopy();
                    var newTop4x4 = topTexture.makeCopy();
                    var newTop6x6 = topTexture.makeCopy();
                    var newTop8x8 = topTexture.makeCopy();
                    var newTop10x10 = topTexture.makeCopy();
                    var newTop12x12 = topTexture.makeCopy();
                    var newTop14x14 = topTexture.makeCopy();
                    TextureImage[] newTopTextures = {
                            newTop2x2,
                            newTop4x4,
                            newTop6x6,
                            newTop8x8,
                            newTop10x10,
                            newTop12x12,
                            newTop14x14
                    };
                    for (int i = 0; i < 7; ++i) {
                        var newTopTexture = newTopTextures[i];
                        generateBeamTexture(topTexture, newTopTexture, i);
                        handler.addTextureIfNotPresent(manager, newIds[i], () -> newTopTexture);
                    }

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate texture for {} : {}", block, e);
                }
            });
        } catch (Exception e) {
            GuitaWoodworks.LOGGER.error("Failed to open the beam textures: ", e);
        }

        try {
            strippedBeam.blocks.forEach((w, block) -> {
                Identifier id = Utils.getID(block);
                String baseTexturePath = "block/stripped_oak_beam_top";

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId2x2 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_2x2", w, id, "oak");
                    String newId4x4 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_4x4", w, id, "oak");
                    String newId6x6 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_6x6", w, id, "oak");
                    String newId8x8 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_8x8", w, id, "oak");
                    String newId10x10 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_10x10", w, id, "oak");
                    String newId12x12 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_12x12", w, id, "oak");
                    String newId14x14 = BlockTypeResTransformer.replaceTypeNoNamespace(baseTexturePath + "_14x14", w, id, "oak");
                    String[] newIds = {
                            newId2x2,
                            newId4x4,
                            newId6x6,
                            newId8x8,
                            newId10x10,
                            newId12x12,
                            newId14x14
                    };

                    var newTop2x2 = topTexture.makeCopy();
                    var newTop4x4 = topTexture.makeCopy();
                    var newTop6x6 = topTexture.makeCopy();
                    var newTop8x8 = topTexture.makeCopy();
                    var newTop10x10 = topTexture.makeCopy();
                    var newTop12x12 = topTexture.makeCopy();
                    var newTop14x14 = topTexture.makeCopy();
                    TextureImage[] newTopTextures = {
                            newTop2x2,
                            newTop4x4,
                            newTop6x6,
                            newTop8x8,
                            newTop10x10,
                            newTop12x12,
                            newTop14x14
                    };
                    for (int i = 0; i < 7; ++i) {
                        var newTopTexture = newTopTextures[i];
                        generateBeamTexture(topTexture, newTopTexture, i);
                        handler.addTextureIfNotPresent(manager, newIds[i], () -> newTopTexture);
                    }

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate texture for {} : {}", block, e);
                }
            });
        } catch (Exception e) {
            GuitaWoodworks.LOGGER.error("Failed to open the beam textures: ", e);
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

    private void generateBeamTexture(TextureImage original, TextureImage target, int radius) {
        int boxSize = ++radius * 2;
        int offset = (16 - boxSize) / 2;

        ImageTransformer transformer = ImageTransformer.builder(16, 16, 16, 16)
                .copyRect(0, 0, 16, 1, offset, offset, boxSize, 1) // Top border
                .copyRect(15, 0, 1, 16, offset + boxSize - 1, offset, 1, boxSize) // Right border
                .copyRect(0, 15, 16, 1, offset, offset + boxSize - 1, boxSize, 1) // Bottom border
                .copyRect(0, 0, 1, 16, offset, offset, 1, boxSize) // Left border
                .build();

        transformer.apply(original, target);

        target.forEachFramePixel((i, x, y) -> {
            int localX = x - target.getFrameStartX(i);
            int localY = y - target.getFrameStartY(i);

            boolean inBox = (localX >= offset && localX < offset + boxSize) &&
                    (localY >= offset && localY < offset + boxSize);

            if (!inBox) {
                target.getImage().setColor(x, y, 0);
            }
        });
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
            //if (isWoodFrom(GuitaWoodworks.MOD_ID, "", "", "minecraft:(spruce|birch|jungle|acacia|dark_oak|mangrove|cherry|crimson|warped)", "(stripped_)?\\w+_beam")) return false;

            return null;
        }
    }
}
