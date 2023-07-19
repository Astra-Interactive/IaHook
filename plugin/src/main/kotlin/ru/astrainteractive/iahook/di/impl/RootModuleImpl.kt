@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook.di.impl

import CommandManager
import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.Reloadable
import ru.astrainteractive.astralibs.Single
import ru.astrainteractive.astralibs.configloader.ConfigLoader
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.iahook.di.PluginModule
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.iahook.events.EventManager
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.iahook.plugin.Translation

internal object RootModuleImpl : RootModule {
    override val pluginModule: PluginModule by PluginModuleImpl

    override val configurationModule = Reloadable {
        val configFile by FilesModuleImpl.configFile
        ConfigLoader.toClassOrDefault(configFile.configFile, ::MainConfiguration)
    }

    override val translationModule = Reloadable {
        val plugin by pluginModule.plugin
        Translation(plugin)
    }

    override val eventHandlerModule = Single {
        EventManager(EventModuleImpl)
    }

    override val commandManager = Single {
        CommandManager(CommandManagerModuleImpl)
    }
}
