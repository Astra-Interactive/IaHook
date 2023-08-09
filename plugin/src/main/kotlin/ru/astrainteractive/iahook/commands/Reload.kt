package ru.astrainteractive.iahook.commands

import CommandManager
import ru.astrainteractive.astralibs.commands.registerCommand
import ru.astrainteractive.iahook.plugin.PluginPermissions

/**
 * Reload command handler
 */
fun CommandManager.reload() = plugin.registerCommand("atempreload") {
    if (!PluginPermissions.Reload.hasPermission(sender)) {
        sender.sendMessage(translation.general.noPermission)
        return@registerCommand
    }
    sender.sendMessage(translation.general.reload)
    plugin.reloadPlugin()
    sender.sendMessage(translation.general.reloadComplete)
}
