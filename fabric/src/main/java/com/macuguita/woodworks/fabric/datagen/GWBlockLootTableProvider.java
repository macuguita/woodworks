package com.macuguita.woodworks.fabric.datagen;

import java.util.concurrent.CompletableFuture;

import com.macuguita.woodworks.reg.GWObjects;

import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class GWBlockLootTableProvider extends FabricBlockLootTableProvider {

	protected GWBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		GWObjects.STUMP_BLOCKS.stream().forEach(regEntry -> {
			addDrop(regEntry.get());
		});
		GWObjects.STRIPPED_STUMP_BLOCKS.stream().forEach(regEntry -> {
			addDrop(regEntry.get());
		});
		GWObjects.CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			addDrop(regEntry.get());
		});
		GWObjects.STRIPPED_CARVED_LOG_BLOCKS.stream().forEach(regEntry -> {
			addDrop(regEntry.get());
		});
		GWObjects.BEAM_BLOCKS.stream().forEach(regEntry -> {
			addDrop(regEntry.get());
		});
		GWObjects.STRIPPED_BEAM_BLOCKS.stream().forEach(regEntry -> {
			addDrop(regEntry.get());
		});
	}
}
