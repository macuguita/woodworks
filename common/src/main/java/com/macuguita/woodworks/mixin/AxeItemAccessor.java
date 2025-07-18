package com.macuguita.woodworks.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

@Mixin(AxeItem.class)
public interface AxeItemAccessor {

	@Accessor("STRIPPED_BLOCKS")
	static Map<Block, Block> gwoodworks$getStrippedBlocksMap() {
		throw new AssertionError();
	}
}
