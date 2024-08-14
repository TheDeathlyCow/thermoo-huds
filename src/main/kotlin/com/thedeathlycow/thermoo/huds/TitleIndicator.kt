package com.thedeathlycow.thermoo.huds

import com.thedeathlycow.thermoo.huds.config.TitleIndicatorConfig
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.abs
import kotlin.math.round

object TitleIndicator : ServerTickEvents.EndTick {

    private val DEFAULT_STYLE = IndicatorStyle(
        coldStyle = Style.EMPTY
            .withColor(Formatting.AQUA),
        warmStyle = Style.EMPTY
            .withColor(Formatting.GOLD)
    )


    override fun onEndTick(server: MinecraftServer) {
        val config = ThermooHUDs.config.serverSideTitles
        if (!config.enabled || server.ticks % config.updateInterval != 0) {
            return
        }

        val style = IndicatorStyle(
            coldStyle = Style.EMPTY
                .withColor(config.coldIndicatorColor ?: 0),
            warmStyle = Style.EMPTY
                .withColor(config.warmIndicatorColor ?: 0)
        )

        server.playerManager.playerList.forEach { player ->
            tickPlayerIndicator(player, config, style)
        }
    }

    private fun tickPlayerIndicator(
        player: ServerPlayerEntity,
        config: TitleIndicatorConfig,
        style: IndicatorStyle
    ) {
        val temperature = player.`thermoo$getTemperature`()

        if (temperature == 0 || player.isSpectator) {
            return
        }

        val temperatureChar = if (temperature > 0) config.warmTemperatureChar else config.coldTemperatureChar
        val numPoints: Int = round(abs(player.`thermoo$getTemperatureScale`() * config.maxPoints)).toInt()

        val barString = temperatureChar.repeat(numPoints)

        val titleS2CPacket = config.titleType.createPacket(
            Text.literal(barString)
                .setStyle(if (temperature > 0) style.warmStyle else style.coldStyle)
        )

        player.networkHandler.sendPacket(titleS2CPacket)
    }

    private data class IndicatorStyle(
        val coldStyle: Style,
        val warmStyle: Style
    )


}

