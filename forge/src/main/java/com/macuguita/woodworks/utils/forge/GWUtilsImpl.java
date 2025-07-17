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

package com.macuguita.woodworks.utils.forge;

import java.util.HashMap;
import java.util.Map;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public class GWUtilsImpl {

	private static final Object2IntMap<ItemConvertible> FUEL_ITEMS = new Object2IntLinkedOpenHashMap<>();
	private static final Map<Block, Pair<Integer, Integer>> FLAMMABLE_BLOCKS = new HashMap<>();

	public static boolean isModLoaded(String id) {
		return ModList.get().isLoaded(id);
	}

	public static void registerFuel(int time, ItemConvertible item) {
		FUEL_ITEMS.put(item, time);
	}

	@SubscribeEvent
	public static void fuelEvent(FurnaceFuelBurnTimeEvent event) {
		if (!event.getItemStack().isEmpty()) {
			int time = FUEL_ITEMS.getOrDefault(event.getItemStack().getItem(), Integer.MIN_VALUE);
			if (time != Integer.MIN_VALUE) {
				event.setBurnTime(time);
			}
		}
	}

	static {
		MinecraftForge.EVENT_BUS.register(GWUtilsImpl.class);
	}
}
