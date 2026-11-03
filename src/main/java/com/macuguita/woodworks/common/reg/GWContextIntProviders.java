package com.macuguita.woodworks.common.reg;

//? >=26.3 {
/*import com.macuguita.woodworks.GuitaWoodworks;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public final class GWContextIntProviders {

	private GWContextIntProviders() {}

	public static final ResourceKey<ContextIntProvider> COOKING_TIME_STUMP = createKey("cooking/time_bamboo");
	public static final ResourceKey<ContextIntProvider> COOKING_TIME_CARVED_LOG = createKey("cooking/time_bamboo");
	public static final ResourceKey<ContextIntProvider> COOKING_TIME_BEAM = createKey("cooking/time_bamboo");
	public static final ResourceKey<ContextIntProvider> COOKING_TIME_HOLLOW_LOG = createKey("cooking/time_bamboo");
	public static final ResourceKey<ContextIntProvider> COOKING_TIME_SUPPORT = createKey("cooking/time_bamboo");
	public static final ResourceKey<ContextIntProvider> COOKING_TIME_SHUTTER = createKey("cooking/time_bamboo");

	private static ResourceKey<ContextIntProvider> createKey(final String location) {
		return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, GuitaWoodworks.id(location));
	}

	public static void bootstrap(BootstrapContext<ContextIntProvider> context) {
		HolderGetter<LootItemCondition> predicates = context.lookup(Registries.PREDICATE);
		HolderGetter<ContextIntProvider> contextIntProv = context.lookup(Registries.CONTEXT_INT_PROVIDER);
		Holder.Reference<ContextIntProvider> normalBurnTime = contextIntProv.getOrThrow(ContextIntProviders.COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR);
		Holder.Reference<ContextIntProvider> fastBurnTime = contextIntProv.getOrThrow(ContextIntProviders.COOKING_FAST_BURN_TIME_REDUCTION_FACTOR);
		context.register(COOKING_TIME_STUMP, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 150));
		context.register(COOKING_TIME_CARVED_LOG, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 250));
		context.register(COOKING_TIME_BEAM, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 75));
		context.register(COOKING_TIME_HOLLOW_LOG, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 150));
		context.register(COOKING_TIME_SUPPORT, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 150));
		context.register(COOKING_TIME_SHUTTER, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 150));
	}
}
*///?}
