package ru.astrainteractive.iahook.events.di

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.events.EventListener
import ru.astrainteractive.iahook.plugin.Translation

interface EventModule {
    val eventListener: EventListener
    val plugin: Plugin
    val translation: Translation
}
