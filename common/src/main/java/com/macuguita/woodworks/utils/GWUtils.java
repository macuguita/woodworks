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

import com.macuguita.woodworks.block.CarvedLogSeatBlock;
import com.macuguita.woodworks.block.StumpSeatBlock;
import dev.architectury.injectables.annotations.ExpectPlatform;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public class GWUtils {

	@ExpectPlatform
	public static boolean isModLoaded(String id) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void registerFuel(int time, ItemConvertible item) {
		throw new AssertionError();
	}

	public static Block getStrippedStump(Block stump) {
		return StumpSeatBlock.STRIPPED_STUMPS.get(stump);
	}

	public static Block getStrippedCarvedLog(Block carvedLog) {
		return CarvedLogSeatBlock.STRIPPED_CARVED_LOGS.get(carvedLog);
	}
}
