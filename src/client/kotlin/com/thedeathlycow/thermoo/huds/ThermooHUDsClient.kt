package com.thedeathlycow.thermoo.huds

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback

object ThermooHUDsClient : ClientModInitializer {

	override fun onInitializeClient() {
		HudRenderCallback.EVENT.register { drawContext, tickCounter ->
//			ThermooHUDs.LOGGER.info("render hud")
		}
	}

}