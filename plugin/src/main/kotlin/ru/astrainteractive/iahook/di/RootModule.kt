package ru.astrainteractive.iahook.di

import CommandManager
import ru.astrainteractive.astralibs.Dependency
import ru.astrainteractive.astralibs.Module
import ru.astrainteractive.astralibs.Reloadable
import ru.astrainteractive.iahook.events.EventManager
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.iahook.plugin.Translation

interface RootModule : Module {
    val pluginModule: PluginModule
    val configurationModule: Reloadable<MainConfiguration>
    val translationModule: Reloadable<Translation>
    val eventHandlerModule: Dependency<EventManager>
    val commandManager: Dependency<CommandManager>
}
