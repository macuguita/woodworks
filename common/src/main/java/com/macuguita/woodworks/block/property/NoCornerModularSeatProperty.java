package com.macuguita.woodworks.block.property;

import net.minecraft.util.StringIdentifiable;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

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
