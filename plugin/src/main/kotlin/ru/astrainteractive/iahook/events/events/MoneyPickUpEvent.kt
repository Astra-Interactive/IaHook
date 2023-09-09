package ru.astrainteractive.iahook.events.events

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityPickupItemEvent
import ru.astrainteractive.astralibs.events.DSLEvent
import ru.astrainteractive.iahook.events.di.EventModule
import kotlin.random.Random
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack

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
        val money = Random.nextInt(data.min, data.max) * amount
        val result = economyProvider.addMoney(player.uniqueId, money.toDouble())
        translation.general.moneyReceived(money).let { message ->
            if (message.isEmpty()) return@let
            player.sendMessage(message)
        }
        player.location.world.playSound(player.location, configuration.moneyPickUp.sound, 1f, 1f)
        if (configuration.logging.moneyPickUp) {
            logger.info("MoneyPickUpEvent", "Player picked up $amount $id, received $money: $result")
        }
    }

    private fun isMoneyItem(itemStack: ItemStack): Boolean {
        val amount = itemStack.amount
        val id = CustomStack.byItemStack(itemStack)?.id ?: itemStack.type.name
        configuration.moneyPickUp.items.entries.firstOrNull { it.value.item == id } ?: return false
        return true
    }

    val inventoryMoveEvent = DSLEvent<InventoryMoveItemEvent>(eventListener, plugin) { e ->
        if (!isMoneyItem(e.item)) return@DSLEvent
        e.isCancelled = true
    }
    val inventoryPickupItemEvent = DSLEvent<InventoryPickupItemEvent>(eventListener, plugin) { e ->
        if (!isMoneyItem(e.item.itemStack)) return@DSLEvent
        e.isCancelled = true
    }
}
