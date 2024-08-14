package com.thedeathlycow.thermoo.huds.config

import com.thedeathlycow.thermoo.huds.ThermooHUDs
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket
import net.minecraft.network.packet.s2c.play.TitleS2CPacket
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.max

@Config(name = "${ThermooHUDs.MODID}.title_indicator")
class TitleIndicatorConfig : ConfigData {

    val enabled: Boolean = false

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    val titleType: TitleType = TitleType.ACTION_BAR

    val warmTemperatureChar: String = "\uD83D\uDD25"

    val coldTemperatureChar: String = "❄"

    var maxPoints: Int = 10

    var updateInterval: Int = 20

    @ConfigEntry.ColorPicker
    val warmIndicatorColor: Int? = Formatting.GOLD.colorValue

    @ConfigEntry.ColorPicker
    val coldIndicatorColor: Int? = Formatting.AQUA.colorValue

    override fun validatePostLoad() {
        super.validatePostLoad()

        updateInterval = max(updateInterval, 1)
        maxPoints = max(maxPoints, 0)
    }

    enum class TitleType {
        TITLE {
            override fun createPacket(text: Text): Packet<ClientPlayPacketListener> = TitleS2CPacket(text)
        },
        SUBTITLE {
            override fun createPacket(text: Text): Packet<ClientPlayPacketListener> = SubtitleS2CPacket(text)
        },
        ACTION_BAR {
            override fun createPacket(text: Text): Packet<ClientPlayPacketListener> = OverlayMessageS2CPacket(text)
        };

        abstract fun createPacket(text: Text): Packet<ClientPlayPacketListener>
    }
}