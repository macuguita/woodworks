package com.macuguita.woodworks.common.reg;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import com.macuguita.woodworks.GuitaWoodworks;

public final class GWItemIds {

	private GWItemIds() {}

	public static final ResourceKey<Item> SECATEURS = create("secateurs");

	private static ResourceKey<Item> create(final String name) {
		return ResourceKey.create(Registries.ITEM, GuitaWoodworks.id(name));
	}
}
