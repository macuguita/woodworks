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

package com.macuguita.woodworks.reg;

import com.macuguita.woodworks.GuitaWoodworks;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class GWBlockTags {

	public static TagKey<Block> STUMP = createTag("stump");
	public static TagKey<Block> CARVED_LOG = createTag("carved_log");
	public static TagKey<Block> BEAM = createTag("beam");
	public static TagKey<Block> HOLLOW_LOG = createTag("hollow_log");
	public static TagKey<Block> SUPPORT = createTag("support");
	public static TagKey<Block> SHUTTER = createTag("shutter");
	public static TagKey<Block> CONNECTING_MUSHROOM = createTag("connecting_mushroom");

	private static TagKey<Block> createTag(String name) {
		return TagKey.create(Registries.BLOCK, GuitaWoodworks.id(name));
	}
}
