package ru.astrainteractive.iahook.events.events

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.astrainteractive.astralibs.events.DSLEvent
import ru.astrainteractive.iahook.events.di.EventModule
import java.util.UUID

/**
 * This is a most convenient way to use bukkit events in kotlin
 */
class MultipleEventsDSL(module: EventModule) : EventModule by module {

    private val customDamages = HashSet<UUID>()

    val entityDamageByEntityEvent = DSLEvent<EntityDamageByEntityEvent>(eventListener, plugin) {
        val playerDamager = it.damager as? Player ?: return@DSLEvent
        val damageItem = playerDamager.inventory.itemInMainHand ?: return@DSLEvent
        val customDamageItem = CustomStack.byItemStack(damageItem) ?: return@DSLEvent
        configuration.damageEntitiesItemIds.firstOrNull { customDamageItem.id == it } ?: return@DSLEvent
        val entity = it.entity as? LivingEntity ?: return@DSLEvent
        if (customDamages.contains(it.entity.uniqueId)) {
            customDamages.remove(it.entity.uniqueId)
            return@DSLEvent
        }
        customDamages.add(it.entity.uniqueId)
        val lastDamage = entity.lastDamage
        entity.lastDamage = 0.0
        it.damage = 0.0
        it.isCancelled = true

        entity.damage(lastDamage, playerDamager)
    }
}
