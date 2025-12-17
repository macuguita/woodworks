package com.macuguita.woodworks.block.property;

import java.util.Locale;

import net.minecraft.util.StringRepresentable;

public enum SupportFaceShapeProperty implements StringRepresentable {
	BIG, SMALL, HIDDEN;

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	public boolean isHidden() {
		return this == HIDDEN;
	}

	public SupportFaceShapeProperty getSwitched() {
		return switch (this) {
			case HIDDEN -> HIDDEN;
			case SMALL -> BIG;
			case BIG -> SMALL;
		};
	}
}
