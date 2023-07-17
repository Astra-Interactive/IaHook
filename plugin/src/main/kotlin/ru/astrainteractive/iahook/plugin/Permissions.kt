package ru.astrainteractive.iahook.plugin

import ru.astrainteractive.astralibs.utils.Permission

/**
 * Permissions sealed itnerface
 */
sealed class Permissions(override val value: String) : Permission {
    object Reload : Permissions("iahook.reload")
    object Damage : Permissions("iahook.damage")
}
