package com.thedeathlycow.thermoo.huds.config

import com.thedeathlycow.thermoo.huds.ThermooHUDs
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "${ThermooHUDs.MODID}.title_indicator")
class TitleIndicatorConfig: ConfigData {

    val enabled: Boolean = false
}