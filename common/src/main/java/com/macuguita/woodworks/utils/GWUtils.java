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

package com.macuguita.woodworks.utils;

import com.macuguita.woodworks.block.StumpBlock;
import com.macuguita.woodworks.mixin.AxeItemAccessor;
import dev.architectury.injectables.annotations.ExpectPlatform;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class GWUtils {

	@ExpectPlatform
	public static boolean isModLoaded(String id) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void registerFuel(int time, ItemConvertible item) {
		throw new AssertionError();
	}

	public static Block getStrippedLog(Block log) {
		return ((AxeItemAccessor) Items.DIAMOND_AXE).branches$getStrippedBlocks().get(log);
	}

	public static Block getStrippedStump(Block stump) {
		return StumpBlock.STRIPPED_STUMPS.get(stump);
	}

	public static Block getLogFromStump(Block stump) {
		Identifier stumpId = Registries.BLOCK.getId(stump);
		boolean isNether = stumpId.getPath().matches(".*(crimson|warped).*");
		Identifier log = isNether ? getWoodTypeId(stump, "stump", "stem") : getWoodTypeId(stump, "stump", "log");
		return Registries.BLOCK.get(log);
	}

	public static Identifier getWoodTypeId(Block block, String target, String replacement) {
		Identifier original = Registries.BLOCK.getId(block);
		String newPath = original.getPath().replace(target, replacement);
		return Identifier.ofVanilla(newPath);
	}
}
