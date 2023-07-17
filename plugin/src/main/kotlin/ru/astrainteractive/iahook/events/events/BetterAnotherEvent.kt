package ru.astrainteractive.iahook.events.events

import org.bukkit.event.block.BlockBreakEvent
import ru.astrainteractive.astralibs.events.EventListener

/**
 * This is a more convenient way with base class
 */
class BetterAnotherEvent : EventListener {
    @org.bukkit.event.EventHandler
    fun blockBreakEvent(e: BlockBreakEvent) {
        println("blockBreakEvent ${e.player.name}")
    }
}
