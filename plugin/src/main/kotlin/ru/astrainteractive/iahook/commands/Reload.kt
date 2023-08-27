package ru.astrainteractive.iahook.commands

import CommandManager
import ru.astrainteractive.astralibs.commands.registerCommand
import ru.astrainteractive.iahook.plugin.PluginPermissions

/**
 * Reload command handler
 */
fun CommandManager.reload() = plugin.registerCommand("iahookreload") {
    if (!PluginPermissions.Reload.hasPermission(sender)) {
        sender.sendMessage(translation.general.noPermission)
        return@registerCommand
    }
    sender.sendMessage(translation.general.reload)
    plugin.reloadPlugin()
    sender.sendMessage(translation.general.reloadComplete)
}

fun CommandManager.disable() = plugin.registerCommand("iahookdisable") {
    if (!PluginPermissions.Reload.hasPermission(sender)) {
        sender.sendMessage(translation.general.noPermission)
        return@registerCommand
    }
    plugin.onDisable()
}
