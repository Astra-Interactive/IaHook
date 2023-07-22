@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook

import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.Reloadable
import ru.astrainteractive.astralibs.async.PluginScope
import ru.astrainteractive.astralibs.events.GlobalEventListener
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.astralibs.menu.event.GlobalInventoryClickEvent
import ru.astrainteractive.iahook.di.impl.RootModuleImpl
import ru.astrainteractive.iahook.events.EventManager

/**
 * Initial class for your plugin
 */

class IaHook : JavaPlugin() {
    private val rootModuleReloadable = Reloadable {
        RootModuleImpl()
    }
    private val rootModule by rootModuleReloadable
    private val eventManager: EventManager by rootModule.eventManager
    private val commandManager by rootModule.commandManager
    private val jLogger by rootModule.logger

    /**
     * This method called when server starts or PlugMan load plugin.
     */
    override fun onEnable() {
        rootModule.plugin.initialize(this)
        jLogger.info("Logger enabled", "IaHook")
        jLogger.warning("Warn message from logger", "IaHook")
        jLogger.error("Error message", "IaHook")

        GlobalEventListener.onEnable(this)
        GlobalInventoryClickEvent.onEnable(this)
        commandManager
        eventManager.onEnable(this)
        rootModule.passiveManaRestoreJob.value
    }

    /**
     * This method called when server is shutting down or when PlugMan disable plugin.
     */
    override fun onDisable() {
        eventManager.onDisable()
        rootModule.passiveManaRestoreJob.value.close()
        HandlerList.unregisterAll(this)
        GlobalEventListener.onDisable()
        GlobalInventoryClickEvent.onDisable()
        PluginScope.close()
    }

    /**
     * As it says, function for plugin reload
     */
    fun reloadPlugin() {
        rootModule.passiveManaRestoreJob.value.close()
        rootModule.passiveManaRestoreJob.reload()
        rootModule.filesModule.configFile.value.reload()
        rootModule.configuration.reload()
        rootModule.translation.reload()
    }
}
