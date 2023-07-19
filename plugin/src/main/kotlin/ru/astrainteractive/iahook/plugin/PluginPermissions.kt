package ru.astrainteractive.iahook.plugin

import ru.astrainteractive.astralibs.utils.Permission

/**
 * Permissions sealed itnerface
 */
sealed class PluginPermissions(override val value: String) : Permission {
    data object Reload : PluginPermissions("iahook.reload")
    data object Damage : PluginPermissions("iahook.damage")
}
