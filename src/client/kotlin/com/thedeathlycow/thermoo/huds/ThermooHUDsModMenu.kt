package com.thedeathlycow.thermoo.huds

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.thedeathlycow.thermoo.huds.config.ThermooHudsConfig
import me.shedaniel.autoconfig.AutoConfig

object ThermooHUDsModMenu : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory {
            AutoConfig.getConfigScreen(ThermooHudsConfig::class.java, it).get()
        }
    }
    
}