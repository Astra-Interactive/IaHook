package ru.astrainteractive.iahook.events.events

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityPickupItemEvent
import ru.astrainteractive.astralibs.events.DSLEvent
import ru.astrainteractive.iahook.events.di.EventModule
import kotlin.random.Random

class MoneyPickUpEvent(module: EventModule) : EventModule by module {
    val playerPickUpEvent = DSLEvent<EntityPickupItemEvent>(eventListener, plugin) { e ->
        val player = e.entity as? Player ?: return@DSLEvent
        val item = e.item.itemStack
        val amount = e.item.itemStack.amount
        val id = CustomStack.byItemStack(item)?.id ?: item.type.name
        val (_, data) = configuration.moneyPickUp.items.entries.firstOrNull { it.value.item == id } ?: run {
            if (configuration.logging.moneyPickUp) {
                logger.info("MoneyPickUpEvent", "Player picked up $amount $id, but it's not found in config")
            }
            return@DSLEvent
        }

        e.item.remove()
        e.isCancelled = true
        val money = Random.nextDouble(data.min, data.max) * amount
        val result = economyProvider.addMoney(player.uniqueId, money)
        translation.general.moneyReceived(money).let { message ->
            if (message.isEmpty()) return@let
            player.sendMessage(message)
        }
        player.location.world.playSound(player.location, configuration.moneyPickUp.sound, 1f, 1f)
        if (configuration.logging.moneyPickUp) {
            logger.info("MoneyPickUpEvent", "Player picked up $amount $id, received $money: $result")
        }
    }
}
