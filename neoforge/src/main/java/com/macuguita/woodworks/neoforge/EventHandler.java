package com.macuguita.woodworks.neoforge;

import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.compat.Callbacks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.util.TriState;
import net.minecraft.world.InteractionResult;

@EventBusSubscriber(modid = GuitaWoodworks.MOD_ID)
public class EventHandler {

	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
		InteractionResult result = Callbacks.onRightClickBlock(event.getEntity(), event.getLevel(), event.getItemStack(), event.getHitVec());
		if (result != InteractionResult.PASS) {
			if (event.getLevel().isClientSide()) {
				event.getEntity().swing(event.getHand());
			}
			event.setUseBlock(TriState.FALSE);
			event.setUseItem(TriState.FALSE);
		}
	}
}
