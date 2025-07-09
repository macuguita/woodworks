package com.macuguita.woodworks.block.property;

import java.util.Locale;

import org.jetbrains.annotations.NotNull;

import net.minecraft.util.StringIdentifiable;

public enum NoCornerModularSeatProperty implements StringIdentifiable {
	SINGLE,
	LEFT,
	MIDDLE,
	RIGHT;

	@Override
	public @NotNull String asString() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return asString();
	}
}
