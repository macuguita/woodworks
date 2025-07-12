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

package com.macuguita.woodworks.utils;

import java.util.Objects;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.targets.ArchitecturyTarget;

import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class GWUtils {

	@ExpectPlatform
	public static boolean isModLoaded(String id) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void registerFuel(int time, ItemConvertible item) {
		throw new AssertionError();
	}

	public static boolean isFabric() {
		return Objects.equals(ArchitecturyTarget.getCurrentTarget(), "fabric");
	}

	public static boolean isNeoForge() {
		return Objects.equals(ArchitecturyTarget.getCurrentTarget(), "neoforge");
	}

	public static boolean isForge() {
		return Objects.equals(ArchitecturyTarget.getCurrentTarget(), "forge");
	}

	public static VoxelShape rotateVoxelShape(VoxelShape shape, Direction.Axis axis, int degrees) {
		int times = ((degrees % 360) + 360) % 360 / 90;
		if (times == 0 || shape.isEmpty()) return shape;

		VoxelShape result = shape;
		for (int i = 0; i < times; ++i) {
			VoxelShape rotated = VoxelShapes.empty();
			for (Box box : result.getBoundingBoxes()) {
				Box rotatedBox = switch (axis) {
					case Y -> new Box(
							1 - box.maxZ, box.minY, box.minX,
							1 - box.minZ, box.maxY, box.maxX
					);
					case X -> new Box(
							box.minX, 1 - box.maxZ, box.minY,
							box.maxX, 1 - box.minZ, box.maxY
					);
					case Z -> new Box(
							box.minY, box.minX, box.minZ,
							box.maxY, box.maxX, box.maxZ
					);
				};
				rotated = VoxelShapes.union(rotated, VoxelShapes.cuboid(rotatedBox));
			}
			result = rotated;
		}
		return result;
	}

}
