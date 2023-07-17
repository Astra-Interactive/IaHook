package ru.astrainteractive.iahook.events

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.events.EventListener
import ru.astrainteractive.iahook.events.di.EventModule
import ru.astrainteractive.iahook.events.events.BetterAnotherEvent
import ru.astrainteractive.iahook.events.events.MultipleEventsDSL
import ru.astrainteractive.iahook.events.events.TemplateEvent

/**
 * Handler for all your events
 */
class EventManager(
    private val module: EventModule
) : EventListener {
    private val events = buildList {
        TemplateEvent(module).also(::add)
        BetterAnotherEvent().also(::add)
    }

    override fun onEnable(plugin: Plugin) {
        super.onEnable(plugin)
        events.forEach { it.onEnable(plugin) }
        // DSL Events
        MultipleEventsDSL(module)
    }

    override fun onDisable() {
        super.onDisable()
        events.forEach(EventListener::onDisable)
    }
}
