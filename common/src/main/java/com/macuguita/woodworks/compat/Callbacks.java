package com.macuguita.woodworks.compat;

import com.macuguita.woodworks.block.ResizableBeamBlock;
import com.macuguita.woodworks.block.SupportBlock;
import com.macuguita.woodworks.reg.GWItemTags;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Callbacks {

	public static InteractionResult onRightClickBlock(Player player, Level level, ItemStack item, BlockHitResult hitResult) {
		BlockPos pos = hitResult.getBlockPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		if (item.is(ItemTags.AXES) && block instanceof SupportBlock) {
			SupportBlock.onSupportActivation(item, state, level, pos, player, hitResult.getLocation());
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else if ((item.is(ItemTags.AXES) || item.is(GWItemTags.SHEARS) || item.is(GWItemTags.SECATEURS)) && block instanceof ResizableBeamBlock beamBlock && beamBlock.isStrippable()) {
			ResizableBeamBlock.onResizableBeamActivation(state, level, pos, player, hitResult);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		return InteractionResult.PASS;
	}
}
