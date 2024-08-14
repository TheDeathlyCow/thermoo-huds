package com.thedeathlycow.thermoo.huds.config

import com.thedeathlycow.thermoo.huds.ThermooHUDs
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry
import me.shedaniel.autoconfig.serializer.PartitioningSerializer

@Config(name = ThermooHUDs.MODID)
class ThermooHudsConfig: PartitioningSerializer.GlobalData() {


    @ConfigEntry.Gui.CollapsibleObject
    val indicatorConfig: TitleIndicatorConfig = TitleIndicatorConfig()

}