/*
 * Copyright (c) 2026 macuguita
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.macuguita.woodworks.common.entity;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.entity.Pose;

import net.minecraft.world.item.SpawnEggItem;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityInLevelCallback;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import com.macuguita.woodworks.common.block.SittableBlock;
import com.macuguita.woodworks.common.reg.GWEntityTypes;

@SuppressWarnings("resource")
public class Seat extends Entity {

	private @Nullable AABB shape;
	private boolean remove;
	private static final EntityDataAccessor<Boolean> CAN_ROTATE = SynchedEntityData.defineId(Seat.class, EntityDataSerializers.BOOLEAN);

	public Seat(EntityType<? extends Entity> type, Level level) {
		super(type, level);
		this.setLevelCallback(EntityInLevelCallback.NULL);
	}

	@Nullable
	public static Seat of(Level world, BlockPos pos, @Nullable Direction dir) {
		BlockState state = world.getBlockState(pos);
		AABB shape = new AABB(pos);
		if (state.getBlock() instanceof SittableBlock seat) {
			shape = seat.getSeatSize(state);
		}

		Seat entity = GWEntityTypes.SEAT.get().create(world, EntitySpawnReason.TRIGGERED);
		if (entity == null) return null;
		if (dir != null) {
			entity.setYRot(dir.toYRot());
		} else {
			entity.setCanRotate(true);
		}

		entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

		entity.shape = copyBox(shape);
		return entity;
	}

	public boolean canRotate() {
		return getEntityData().get(CAN_ROTATE);
	}

	public void setCanRotate(boolean rotate) {
		getEntityData().set(CAN_ROTATE, rotate);
	}


	private static AABB copyBox(AABB box) {
		return new AABB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return EntityDimensions.fixed(0.001f, 0.001f);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity tracker) {
		return new ClientboundAddEntityPacket(this, tracker, canRotate() ? 1 : 0);
	}

	@Override
	public void recreateFromPacket(ClientboundAddEntityPacket packet) {
		super.recreateFromPacket(packet);
		setCanRotate(packet.getData() == 1);
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
	protected void readAdditionalSaveData(ValueInput view) {
		setCanRotate(view.read("can_rotate", Codec.BOOL).orElse(false));
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput view) {
		view.store("can_rotate", Codec.BOOL, canRotate());
	}

	@Override
	public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
		Direction facing = this.getDirection();
		BlockPos seatPos = this.blockPosition();
		Vec3 seatCenter = this.position();

		for (Direction offset : new Direction[]{facing, facing.getClockWise(), facing.getCounterClockWise(), facing.getOpposite()}) {
			BlockPos targetPos = seatPos.relative(offset);
			Vec3 dismountPos = DismountHelper.findSafeDismountLocation(
				passenger.getType(),
				this.level(),
				targetPos,
				false
			);

			if (dismountPos != null) {
				double distance = dismountPos.distanceToSqr(seatCenter);
				if (distance > 9.0) {
					return seatCenter.add(0.0, 1.0, 0.0);
				}

				return new Vec3(
					dismountPos.x,
					dismountPos.y + passenger.getBbHeight() * 0.5 + 0.1,
					dismountPos.z
				);
			}
		}

		return this.position().add(0.0, 1.0, 0.0);
	}


	@Override
	public void tick() {
		super.tick();
		if (this.level() instanceof ServerLevel serverWorld &&
			(!(serverWorld.getBlockState(blockPosition()).getBlock() instanceof SittableBlock) || remove)) {
			discard();
		}
	}

	@Override
	public boolean hurtClient(DamageSource source) {
		return !this.isInvulnerableToBase(source);
	}

	@Override
	public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
		return false;
	}

	@Override
	protected void removePassenger(Entity passenger) {
		super.removePassenger(passenger);
		if (this.level() instanceof ServerLevel && getPassengers().isEmpty()) {
			remove = true;
		}
	}

	@Override
	protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dims, float partialTick) {
		return new Vec3(0, (float) (shape.getYsize() * 0.75) + 0.2f, 0);
	}

	protected void clampRotation(Entity entity) {
		entity.setYBodyRot(getYRot());
		float diff = Mth.wrapDegrees(entity.getYRot() - getYRot());
		float clamped = Mth.clamp(diff, -105.0f, 105.0f);
		entity.yRotO += clamped - diff;
		entity.setYRot(entity.getYRot() + clamped - diff);
		entity.setYHeadRot(entity.getYRot());
	}

	@Override
	public void onPassengerTurned(Entity entity) {
		if (!canRotate()) {
			clampRotation(entity);
		}
	}

	@Override
	public void setLevelCallback(EntityInLevelCallback callback) {
		super.setLevelCallback(new WrappedCallback(callback));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(CAN_ROTATE, false);
	}

	private class WrappedCallback implements EntityInLevelCallback {

		private final EntityInLevelCallback delegate;

		public WrappedCallback(EntityInLevelCallback delegate) {
			this.delegate = delegate;
		}

		@Override
		public void onMove() {
			delegate.onMove();
			Block block = Seat.this.level().getBlockState(blockPosition()).getBlock();
			if (block instanceof SittableBlock seat) {
				shape = seat.getSeatSize(Seat.this.level().getBlockState(blockPosition()));
			}
		}

		@Override
		public void onRemove(RemovalReason reason) {
			delegate.onRemove(reason);
		}
	}
}
