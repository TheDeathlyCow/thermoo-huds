package com.thedeathlycow.thermoo.huds

import com.thedeathlycow.thermoo.huds.config.TitleIndicatorConfig
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting
import net.minecraft.util.math.MathHelper
import kotlin.math.abs

object TitleIndicator : ServerTickEvents.EndTick {

    private val WARM_STYLE: Style = Style.EMPTY
        .withColor(Formatting.GOLD)

    private val COLD_STYLE: Style = Style.EMPTY
        .withColor(Formatting.AQUA)


    override fun onEndTick(server: MinecraftServer) {
        val config = ThermooHUDs.config.indicatorConfig
        if (!config.enabled) {
            return
        }

        server.playerManager.playerList.forEach { player ->
            tickPlayerIndicator(player, config)
        }
    }

    private fun tickPlayerIndicator(player: ServerPlayerEntity, config: TitleIndicatorConfig) {
        val temperature = player.`thermoo$getTemperature`()

        if (temperature == 0) {
            return
        }

        val temperatureChar = if (temperature > 0) config.warmTemperatureChar else config.coldTemperatureChar
        val numPoints: Int = MathHelper.floor(abs(player.`thermoo$getTemperatureScale`() * config.maxPoints))

        val barString = temperatureChar.repeat(numPoints)

        val title = OverlayMessageS2CPacket(
            Text.literal(barString)
                .setStyle(if (temperature > 0) WARM_STYLE else COLD_STYLE)
        )

        player.networkHandler.sendPacket(title)
    }


}

