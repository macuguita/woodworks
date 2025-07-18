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

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class GWItemTags {

	public static TagKey<Item> STUMP = createTag("stump");
	public static TagKey<Item> CARVED_LOG = createTag("carved_log");
	public static TagKey<Item> BEAM = createTag("beam");
	public static TagKey<Item> HOLLOW_LOG = createTag("hollow_log");
	public static TagKey<Item> SECATEURS = createTag("secateurs");
	public static TagKey<Item> WATER_BUCKETS = createCommonTag("buckets/water");
	public static TagKey<Item> SHEARS = createCommonTag("tools/shear");
	public static TagKey<Item> KNIVES = createCommonTag("tools/knife");
	public static TagKey<Item> EMPTY_BUCKETS = createCommonTag("buckets/empty");

	private static TagKey<Item> createTag(String name) {
		return TagKey.of(RegistryKeys.ITEM, GuitaWoodworks.id(name));
	}

	private static TagKey<Item> createCommonTag(String name) {
		return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", name));
	}
}
