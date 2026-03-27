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

package com.macuguita.woodworks.neoforge;

//? neoforge {
import net.minecraft.util.TriState;
import net.minecraft.world.InteractionResult;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import com.macuguita.woodworks.GWEventHandler;
import com.macuguita.woodworks.GuitaWoodworks;
import com.macuguita.woodworks.client.GuitaWoodworksClient;

@Mod(GuitaWoodworks.MOD_ID)
@EventBusSubscriber
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(IEventBus modEventBus) {
		GuitaWoodworks.init();
		modEventBus.addListener(this::commonSetup);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		GuitaWoodworks.commonSetup();
	}

	@EventBusSubscriber(modid = GuitaWoodworks.MOD_ID, value = Dist.CLIENT)
	public static class ClientEvents {
		@SubscribeEvent
		public static void onClientSetup(final FMLClientSetupEvent event) {
			GuitaWoodworksClient.init();
		}
	}

	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
		InteractionResult result = GWEventHandler.onRightClickBlock(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
		if (result != InteractionResult.PASS) {
			if (event.getLevel().isClientSide()) {
				event.getEntity().swing(event.getHand());
			}
			event.setUseBlock(TriState.FALSE);
			event.setUseItem(TriState.FALSE);
		}
	}

}
//?}
