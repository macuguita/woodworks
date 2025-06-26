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

package com.macuguita.woodworks;

import java.util.concurrent.atomic.AtomicInteger;

import com.macuguita.woodworks.block.StumpBlock;
import com.macuguita.woodworks.mixin.FireBlockAccessor;
import com.macuguita.woodworks.reg.GWEntityTypes;
import com.macuguita.woodworks.reg.GWItemGroups;
import com.macuguita.woodworks.reg.GWObjects;
import com.macuguita.woodworks.utils.GWUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public final class GuitaWoodworks {

	public static final String MOD_ID = "gwoodworks";

	public static void init() {
		GWObjects.init();
		GWEntityTypes.init();
		GWItemGroups.init();
	}

	public static void commonSetup() {
		GWObjects.STUMP_ITEMS.stream().forEach(regEntry -> {
			if (!regEntry.getId().getPath().matches(".*(crimson|warped).*")) {
				GWUtils.registerFuel(200, regEntry.get());
			}
		});
		AtomicInteger index = new AtomicInteger();
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			boolean isPresent = GWObjects.STRIPPED_STUMP_BLOCKS.stream().skip(index.get()).findFirst().isPresent();
			Block strippedBlock = null;
			if (isPresent)
				strippedBlock = GWObjects.STRIPPED_STUMP_BLOCKS.stream().skip(index.get()).findFirst().get().get();
			if (strippedBlock != null) StumpBlock.STRIPPED_STUMPS.put(regEntry.get(), strippedBlock);
			if (!regEntry.getId().getPath().matches(".*(crimson|warped).*")) {
				((FireBlockAccessor) Blocks.FIRE).gwoodworks$registerFlammableBlock(regEntry.get(), 5, 5);
			}
			index.getAndIncrement();
		});
	}

	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
}
