package ru.astrainteractive.iahook.events.events

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.astrainteractive.astralibs.events.DSLEvent
import ru.astrainteractive.iahook.events.di.EventModule

/**
 * This is a most convenient way to use bukkit events in kotlin
 */
class MultipleEventsDSL(module: EventModule) : EventModule by module {

    val entityDamageByEntityEvent = DSLEvent<EntityDamageByEntityEvent>(eventListener, plugin) {
        val playerDamager = it.damager as? Player ?: return@DSLEvent
        val damageItem = playerDamager.inventory.itemInMainHand ?: return@DSLEvent
        val customDamageItem = CustomStack.byItemStack(damageItem) ?: return@DSLEvent
        if (it.damage != 0.0 || it.finalDamage != 0.0) return@DSLEvent
        configuration.damageEntitiesItemIds.firstOrNull { customDamageItem.id == it } ?: return@DSLEvent
        val entity = it.entity as? LivingEntity ?: return@DSLEvent
        val lastDamage = entity.lastDamage
        entity.lastDamage = 0.0
        entity.damage(lastDamage, playerDamager)
    }
}
