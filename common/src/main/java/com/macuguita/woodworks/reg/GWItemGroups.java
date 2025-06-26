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

import com.macuguita.lib.platform.registry.GuitaRegistries;
import com.macuguita.lib.platform.registry.GuitaRegistry;
import com.macuguita.lib.platform.registry.GuitaRegistryEntry;
import com.macuguita.woodworks.GuitaWoodworks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

public class GWItemGroups {

	public static final GuitaRegistry<ItemGroup> ITEM_GROUPS = GuitaRegistries.create(Registries.ITEM_GROUP, GuitaWoodworks.MOD_ID);

	public static final GuitaRegistryEntry<ItemGroup> GW_TAB = ITEM_GROUPS.register("gwoodworks", () ->
			ItemGroup.create(ItemGroup.Row.TOP, 0)
					.displayName(Text.translatable("itemGroup." + GuitaWoodworks.MOD_ID + ".gwoodworks"))
					.icon(() -> new ItemStack(GWObjects.OAK_STUMP.get().asItem()))
					.entries((itemDisplayParameters, output) ->
							GWObjects.ITEMS.stream().map(item -> item.get().getDefaultStack()).forEach(output::add)
					).build());

	public static void init() {
		ITEM_GROUPS.init();
	}
}
