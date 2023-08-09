package ru.astrainteractive.iahook.util

import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.FontImages.PlayerHudsHolderWrapper
import dev.lone.itemsadder.api.FontImages.PlayerQuantityHudWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.iahook.plugin.MainConfiguration
import kotlin.random.Random

class PassiveManaRestore(
    private val configuration: MainConfiguration,
    private val logger: Logger,
    private val dispatchers: BukkitDispatchers,
) : AsyncComponent(), Lifecycle {
    private val jobId = Random.nextInt()
    private val manaConfiguration: MainConfiguration.ManaConfiguration
        get() = configuration.manaConfiguration

    override fun onCreate() {
        manaConfiguration.passiveManaRestore.values.forEach(::startModel)
    }

    override fun onDestroy() {
        close()
    }

    private fun startModel(model: MainConfiguration.ManaConfiguration.PassiveManaRestoreModel) =
        launch(dispatchers.BukkitAsync) {
            while (this.isActive) {
                delay(model.everyMillis)
                Bukkit.getOnlinePlayers().forEach { player ->
                    processPlayer(model, player)
                }
            }
        }

    private suspend fun processPlayer(
        model: MainConfiguration.ManaConfiguration.PassiveManaRestoreModel,
        player: Player
    ) {
        val allContent = listOf(
            player.inventory.itemInMainHand,
            player.inventory.itemInOffHand
        ).filter {
            !it.type.name.endsWith("_HELMET", true) &&
                !it.type.name.endsWith("_CHESTPLATE", true) &&
                !it.type.name.endsWith("_LEGGINGS", true) &&
                !it.type.name.endsWith("_BOOTS", true)
        } + player.inventory.armorContents.filterNotNull()

        allContent.firstOrNull {
            val customItem = it.let(CustomStack::byItemStack)
            customItem?.id == model.id
        } ?: return
        withContext(dispatchers.BukkitMain) {
            val playerHudsHolderWrapper = PlayerHudsHolderWrapper(player) ?: return@withContext
            val hud = PlayerQuantityHudWrapper(playerHudsHolderWrapper, manaConfiguration.id) ?: return@withContext
            if (!hud.exists()) return@withContext
            val newValue = (hud.floatValue + model.mana).coerceIn(manaConfiguration.min, manaConfiguration.max)
            hud.floatValue = newValue
            if (configuration.logging.manaRestoration) {
                if (player.isOp) {
                    logger.info("PassiveManaRestore-$jobId", "Player ${player.name} has OP! Mana will not be changed!")
                }

                logger.info(
                    "PassiveManaRestore-$jobId",
                    "Player ${player.name} mana now is $newValue. Increased by ${model.mana}"
                )
            }
        }
    }
}
