package ru.astrainteractive.iahook.plugin

import kotlinx.serialization.Serializable

/**
 * Example config file with kotlinx.serialization
 */
@kotlinx.serialization.Serializable
data class MainConfiguration(
    val logging: LoggingConfig = LoggingConfig(),
    val damageEntitiesItemIds: List<String> = emptyList()
) {
    @Serializable
    data class LoggingConfig(
        val logDamageEntities: Boolean = true
    )
}
