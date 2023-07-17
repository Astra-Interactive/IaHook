package ru.astrainteractive.iahook.commands

import CommandManager
import ru.astrainteractive.astralibs.commands.registerCommand
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.commands.di.CommandManagerModule
import ru.astrainteractive.iahook.plugin.Permissions

/**
 * Reload command handler
 */
fun CommandManager.reload(
    plugin: IaHook,
    module: CommandManagerModule
) = plugin.registerCommand("atempreload") {
    val translation by module.translationModule

    if (!Permissions.Reload.hasPermission(sender)) {
        sender.sendMessage(translation.noPermission)
        return@registerCommand
    }
    sender.sendMessage(translation.reload)
    plugin.reloadPlugin()
    sender.sendMessage(translation.reloadComplete)
}
