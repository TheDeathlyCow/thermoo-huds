package com.thedeathlycow.thermoo.huds

import com.thedeathlycow.thermoo.huds.config.ThermooHudsConfig
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigHolder
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ThermooHUDs : ModInitializer {

	const val MODID = "thermoo-huds"

    val LOGGER: Logger = LoggerFactory.getLogger(MODID)

	private lateinit var _config: ConfigHolder<ThermooHudsConfig>

	val config: ThermooHudsConfig
		get() = _config.config

	override fun onInitialize() {
		AutoConfig.register(ThermooHudsConfig::class.java, ::GsonConfigSerializer)
		_config = AutoConfig.getConfigHolder(ThermooHudsConfig::class.java)

		ServerTickEvents.END_SERVER_TICK.register(TitleIndicator)
	}
}