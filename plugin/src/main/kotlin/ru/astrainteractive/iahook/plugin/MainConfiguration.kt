package ru.astrainteractive.iahook.plugin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Example config file with kotlinx.serialization
 */
@kotlinx.serialization.Serializable
data class MainConfiguration(
    val logging: LoggingConfig = LoggingConfig(),
    val damageEntitiesItemIds: List<String> = emptyList(),
    @SerialName("mana_configuration")
    val manaConfiguration: ManaConfiguration = ManaConfiguration()
) {
    @Serializable
    data class LoggingConfig(
        val logDamageEntities: Boolean = true
    )

    @Serializable
    data class ManaConfiguration(
        val id: String = "empiresmp:mana",
        val min: Float = 0.0f,
        val max: Float = 0.0f,
        @SerialName("passive_mana_restore")
        val passiveManaRestore: Map<String, PassiveManaRestoreModel> = emptyMap()
    ) {
        @Serializable
        data class PassiveManaRestoreModel(
            val id: String,
            @SerialName("every_millis")
            val everyMillis: Long,
            val mana: Float
        )
    }
}
