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

package com.macuguita.woodworks.entity;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.macuguita.woodworks.block.SittableBlock;
import com.macuguita.woodworks.reg.GWEntityTypes;
import com.mojang.serialization.Codec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Dismounting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.annotation.MethodsReturnNonnullByDefault;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityChangeListener;

@MethodsReturnNonnullByDefault
public class Seat extends Entity {

	public static final Multimap<RegistryKey<World>, BlockPos> SITTING_POSITIONS = ArrayListMultimap.create();

	private Box shape;
	private boolean remove;
	private static final TrackedData<Boolean> CAN_ROTATE = DataTracker.registerData(Seat.class, TrackedDataHandlerRegistry.BOOLEAN);

	public Seat(EntityType<? extends Entity> type, World world) {
		super(type, world);
		this.setChangeListener(EntityChangeListener.NONE);
	}

	@Nullable
	public static Seat of(World world, BlockPos pos, Direction dir) {
		BlockState state = world.getBlockState(pos);
		Box shape = new Box(pos);
		if (state.getBlock() instanceof SittableBlock seat) {
			shape = seat.getSeatSize(state);
		}

		Seat entity = GWEntityTypes.SEAT.get().create(world, SpawnReason.TRIGGERED);
		if (entity == null) return null;
		if (dir != null) {
			entity.setYaw(dir.getPositiveHorizontalDegrees());
		} else {
			entity.setCanRotate(true);
		}

		entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		entity.shape = copyBox(shape);
		return entity;
	}

	public boolean canRotate() {
		return getDataTracker().get(CAN_ROTATE);
	}

	public void setCanRotate(boolean rotate) {
		getDataTracker().set(CAN_ROTATE, rotate);
	}


	private static Box copyBox(Box box) {
		return new Box(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	}

	@Override
	public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry tracker) {
		return new EntitySpawnS2CPacket(this, tracker, canRotate() ? 1 : 0);
	}

	@Override
	public void onSpawnPacket(EntitySpawnS2CPacket packet) {
		super.onSpawnPacket(packet);
		setCanRotate(packet.getEntityData() == 1);
	}

	@Override
	public boolean isInvulnerable() {
		return true;
	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return true;
	}

	@Override
	protected void readCustomData(ReadView view) {
		setCanRotate(view.read("can_rotate", Codec.BOOL).orElse(false));
	}

	@Override
	protected void writeCustomData(WriteView view) {
		view.put("can_rotate", Codec.BOOL, canRotate());
	}

	@Override
	public Vec3d updatePassengerForDismount(LivingEntity passenger) {
		Direction facing = this.getHorizontalFacing();
		BlockPos seatPos = this.getBlockPos();
		Vec3d seatCenter = this.getPos();

		for (Direction offset : new Direction[]{facing, facing.rotateYClockwise(), facing.rotateYCounterclockwise(), facing.getOpposite()}) {
			BlockPos targetPos = seatPos.offset(offset);
			Vec3d dismountPos = Dismounting.findRespawnPos(
					passenger.getType(),
					this.getWorld(),
					targetPos,
					false
			);

			if (dismountPos != null) {
				double distance = dismountPos.squaredDistanceTo(seatCenter);
				if (distance > 9.0) {
					return seatCenter.add(0.0, 1.0, 0.0);
				}

				return new Vec3d(
						dismountPos.x,
						dismountPos.y + passenger.getHeight() * 0.5 + 0.1,
						dismountPos.z
				);
			}
		}

		return this.getPos().add(0.0, 1.0, 0.0);
	}


	@Override
	public void tick() {
		super.tick();
		if (this.getWorld() instanceof ServerWorld serverWorld &&
				(!(serverWorld.getBlockState(getBlockPos()).getBlock() instanceof SittableBlock) || remove)) {
			removeSeat();
		}
	}

	@Override
	public boolean clientDamage(DamageSource source) {
		return !this.isAlwaysInvulnerableTo(source);
	}

	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		return false;
	}

	@Override
	protected void removePassenger(Entity passenger) {
		super.removePassenger(passenger);
		if (this.getWorld() instanceof ServerWorld && getPassengerList().isEmpty()) {
			remove = true;
		}
	}

	public void removeSeat() {
		SITTING_POSITIONS.get(this.getWorld().getRegistryKey()).remove(getBlockPos());
		discard();
	}

	@Override
	protected Vec3d getPassengerAttachmentPos(Entity entity, EntityDimensions dims, float partialTick) {
		if (shape == null) return super.getPassengerAttachmentPos(entity, dims, partialTick);
		return new Vec3d(0, (float) (shape.getLengthY() * 0.75) + 0.2f, 0);
	}

	protected void clampRotation(Entity entity) {
		entity.setBodyYaw(getYaw());
		float diff = MathHelper.wrapDegrees(entity.getYaw() - getYaw());
		float clamped = MathHelper.clamp(diff, -105.0f, 105.0f);
		entity.lastYaw += clamped - diff;
		entity.setYaw(entity.getYaw() + clamped - diff);
		entity.setHeadYaw(entity.getYaw());
	}

	@Override
	public void onPassengerLookAround(Entity entity) {
		if (!canRotate()) {
			clampRotation(entity);
		}
	}

	@Override
	public void setChangeListener(EntityChangeListener callback) {
		super.setChangeListener(new WrappedCallback(callback));
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
		builder.add(CAN_ROTATE, false);
	}

	private class WrappedCallback implements EntityChangeListener {

		private final EntityChangeListener delegate;

		public WrappedCallback(EntityChangeListener delegate) {
			this.delegate = delegate;
		}

		@Override
		public void updateEntityPosition() {
			if (delegate != null) {
				delegate.updateEntityPosition();
				Block block = Seat.this.getWorld().getBlockState(getBlockPos()).getBlock();
				if (block instanceof SittableBlock seat) {
					shape = seat.getSeatSize(Seat.this.getWorld().getBlockState(getBlockPos()));
				}
			} else {
				shape = null;
			}
		}

		@Override
		public void remove(RemovalReason reason) {
			if (delegate != null) {
				delegate.remove(reason);
			}
		}
	}
}
