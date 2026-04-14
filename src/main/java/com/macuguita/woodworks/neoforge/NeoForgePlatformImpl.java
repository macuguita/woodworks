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

package com.macuguita.woodworks.neoforge;

//? neoforge {
/*import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.nio.file.Path;

import net.minecraft.world.level.ItemLike;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.Platform;

@EventBusSubscriber(modid = GuitaWoodworks.MOD_ID)
public class NeoForgePlatformImpl implements Platform {

	private static final Object2IntMap<ItemLike> FUEL_ITEMS = new Object2IntLinkedOpenHashMap<>();

	@SubscribeEvent
	public static void fuelEvent(FurnaceFuelBurnTimeEvent event) {
		if (!event.getItemStack().isEmpty()) {
			int time = FUEL_ITEMS.getOrDefault(event.getItemStack().getItem(), Integer.MIN_VALUE);
			if (time != Integer.MIN_VALUE) {
				event.setBurnTime(time);
			}
		}
	}

	@Override
	public boolean isModLoaded(String modid) {
		return ModList.get().isLoaded(modid);
	}

	@Override
	public String loader() {
		return "neoforge";
	}

	@Override
	public Path getConfigDir() {
		return FMLPaths.CONFIGDIR.get();
	}

	@Override
	public boolean isDevelopment() {
		return !FMLEnvironment.isProduction();
	}

	@Override
	public void registerFuel(int tickTime, ItemLike item) {
		FUEL_ITEMS.put(item, tickTime);
	}
}
*///?}
