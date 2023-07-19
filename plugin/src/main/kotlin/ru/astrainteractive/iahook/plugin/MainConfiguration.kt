package ru.astrainteractive.iahook.plugin

/**
 * Example config file with kotlinx.serialization
 */
@kotlinx.serialization.Serializable
data class MainConfiguration(
    val damageEntitiesItemIds: List<String> = emptyList()
)
