package com.thedeathlycow.thermoo.huds

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ThermooHUDs : ModInitializer {

    val LOGGER: Logger = LoggerFactory.getLogger("thermoo-huds")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!")
	}
}