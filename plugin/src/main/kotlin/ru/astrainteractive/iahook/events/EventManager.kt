package ru.astrainteractive.iahook.events

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.events.EventListener
import ru.astrainteractive.iahook.events.di.EventModule
import ru.astrainteractive.iahook.events.events.MoneyPickUpEvent
import ru.astrainteractive.iahook.events.events.MultipleEventsDSL

/**
 * Handler for all your events
 */
class EventManager(
    private val module: EventModule
) : EventListener {

    override fun onEnable(plugin: Plugin) {
        super.onEnable(plugin)
        // DSL Events
        MultipleEventsDSL(module)
        MoneyPickUpEvent(module)
    }

    override fun onDisable() {
        super.onDisable()
    }
}
