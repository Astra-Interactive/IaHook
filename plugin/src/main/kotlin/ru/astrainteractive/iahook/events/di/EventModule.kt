package ru.astrainteractive.iahook.events.di

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.astralibs.events.EventListener
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.iahook.plugin.Translation

interface EventModule {
    val eventListener: EventListener
    val plugin: Plugin
    val translation: Translation
    val logger: Logger
    val configuration: MainConfiguration
    val scope: AsyncComponent
    val dispatchers: BukkitDispatchers
}
