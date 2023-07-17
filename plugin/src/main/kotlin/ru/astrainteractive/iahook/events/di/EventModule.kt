package ru.astrainteractive.iahook.events.di

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.Dependency
import ru.astrainteractive.astralibs.events.EventListener
import ru.astrainteractive.iahook.plugin.Translation

interface EventModule {
    val eventListener: Dependency<EventListener>
    val plugin: Dependency<Plugin>
    val translation: Dependency<Translation>
}
