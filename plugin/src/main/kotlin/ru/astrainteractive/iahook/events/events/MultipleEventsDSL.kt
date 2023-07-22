package ru.astrainteractive.iahook.events.events

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.attribute.Attribute
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffectType
import ru.astrainteractive.astralibs.events.DSLEvent
import ru.astrainteractive.iahook.events.di.EventModule
import kotlin.math.max
import kotlin.math.min

/**
 * This is a most convenient way to use bukkit events in kotlin
 */
class MultipleEventsDSL(module: EventModule) : EventModule by module {

    private val eventsData = HashSet<EventData>()

    data class EventData(
        val player: Player,
        val entity: LivingEntity
    )

    val entityDamageEvent = DSLEvent<EntityDamageEvent>(eventListener, plugin) { e ->
        if (configuration.logging.logDamageEntities) {
            logger.info("MultipleEventsDSL: entityDamageEvent", "finalDamage: ${e.finalDamage}; ${e.damage}")
        }
        (e as? EntityDamageByEntityEvent)?.let {
            EventData(
                player = it.damager as? Player ?: return@let,
                entity = it.entity as? LivingEntity ?: return@let
            ).run(eventsData::add)
            return@DSLEvent
        }
        val data = eventsData.firstOrNull { it.entity == e.entity } ?: return@DSLEvent
        eventsData.remove(data)

        val damageItem = data.player.inventory.itemInMainHand ?: return@DSLEvent
        val customDamageItem = CustomStack.byItemStack(damageItem) ?: return@DSLEvent
        configuration.damageEntitiesItemIds.firstOrNull { customDamageItem.id == it } ?: return@DSLEvent

        val armor = data.entity.getAttribute(Attribute.GENERIC_ARMOR)?.value ?: 0.0
        val toughness = data.entity.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)?.value ?: 0.0

        val protection = data.entity.equipment?.armorContents?.sumOf {
            it.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)
        }?.toDouble() ?: 0.0
        val resistance = data.entity.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)?.amplifier?.toDouble() ?: 0.0
        val damage = e.finalDamage

        val result = damage * (1 - min(20.0, max(armor / 5, armor - damage / (2 + toughness / 4))) / 25) * (
            1 - min(
                protection,
                20.0
            ) / 25
            ) * (1 - min(resistance, 5.0) / 5)

        e.isCancelled = true
        data.entity.damage(result)
    }
}
