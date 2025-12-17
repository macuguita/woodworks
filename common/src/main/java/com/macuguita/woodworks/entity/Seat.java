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

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityInLevelCallback;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@MethodsReturnNonnullByDefault
public class Seat extends Entity {

	public static final Multimap<ResourceKey<Level>, BlockPos> SITTING_POSITIONS = ArrayListMultimap.create();

	private AABB shape;
	private boolean remove;
	private boolean canRotate;

	public Seat(EntityType<? extends Entity> type, Level world) {
		super(type, world);
		this.setLevelCallback(EntityInLevelCallback.NULL);
	}

	public Seat(Level world, AABB shape) {
		super(GWEntityTypes.SEAT.get(), world);
		this.shape = copyBox(shape);
	}

	public static Seat of(Level world, BlockPos pos, Direction dir) {
		BlockState state = world.getBlockState(pos);
		AABB shape = new AABB(pos);
		if (state.getBlock() instanceof SittableBlock seat) {
			shape = seat.getSeatSize(state);
		}

		Seat entity = new Seat(world, shape);
		if (dir != null) {
			entity.setYRot(dir.toYRot());
		} else {
			entity.canRotate = true;
		}

		entity.setPosRaw(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		return entity;
	}

	private static AABB copyBox(AABB box) {
		return new AABB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity tracker) {
		return new ClientboundAddEntityPacket(this, tracker, canRotate ? 1 : 0);
	}

	@Override
	public void recreateFromPacket(ClientboundAddEntityPacket packet) {
		super.recreateFromPacket(packet);
		this.canRotate = packet.getData() == 1;
	}

	@Override
	public boolean isInvulnerable() {
		return true;
	}

	@Override
	public boolean isVehicle() {
		return true;
	}

	@Override
	public boolean shouldRender(double x, double y, double z) {
		return false;
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
		if (!this.level().isClientSide() &&
				(!(this.level().getBlockState(blockPosition()).getBlock() instanceof SittableBlock) || remove)) {
			removeSeat();
		}
	}

	@Override
	protected void removePassenger(Entity passenger) {
		super.removePassenger(passenger);
		if (!this.level().isClientSide() && getPassengers().isEmpty()) {
			remove = true;
		}
	}

	public void removeSeat() {
		SITTING_POSITIONS.get(this.level().dimension()).remove(blockPosition());
		discard();
	}

	@Override
	protected AABB makeBoundingBox() {
		return shape == null ? super.makeBoundingBox() : shape.move(blockPosition());
	}

	@Override
	protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dims, float partialTick) {
		if (shape == null) return super.getPassengerAttachmentPoint(entity, dims, partialTick);
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
		if (!canRotate) {
			clampRotation(entity);
		}
	}

	@Override
	public void setLevelCallback(EntityInLevelCallback callback) {
		super.setLevelCallback(new WrappedCallback(callback));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
	}

	private class WrappedCallback implements EntityInLevelCallback {

		private final EntityInLevelCallback delegate;

		public WrappedCallback(EntityInLevelCallback delegate) {
			this.delegate = delegate;
		}

		@Override
		public void onMove() {
			if (delegate != null) {
				delegate.onMove();
				Block block = Seat.this.level().getBlockState(blockPosition()).getBlock();
				if (block instanceof SittableBlock seat) {
					shape = seat.getSeatSize(Seat.this.level().getBlockState(blockPosition()));
				}
			} else {
				shape = null;
			}
		}

		@Override
		public void onRemove(RemovalReason reason) {
			if (delegate != null) {
				delegate.onRemove(reason);
			}
		}
	}
}
