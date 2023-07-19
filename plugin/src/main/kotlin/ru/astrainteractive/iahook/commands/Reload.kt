package ru.astrainteractive.iahook.commands

import CommandManager
import ru.astrainteractive.astralibs.commands.registerCommand
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.commands.di.CommandManagerModule
import ru.astrainteractive.iahook.plugin.PluginPermissions

/**
 * Reload command handler
 */
fun CommandManager.reload(
    plugin: IaHook,
    module: CommandManagerModule
) = plugin.registerCommand("atempreload") {
    val translation by module.translationModule

    if (!PluginPermissions.Reload.hasPermission(sender)) {
        sender.sendMessage(translation.general.noPermission)
        return@registerCommand
    }
    sender.sendMessage(translation.general.reload)
    plugin.reloadPlugin()
    sender.sendMessage(translation.general.reloadComplete)
}
