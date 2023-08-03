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
    val manaConfiguration: ManaConfiguration = ManaConfiguration(),
    @SerialName("money_pickup")
    val moneyPickUp: Map<String, MoneyPickUp> = emptyMap()
) {
    @Serializable
    data class LoggingConfig(
        val logDamageEntities: Boolean = true,
        val manaRestoration: Boolean = true,
        val moneyPickUp: Boolean = true
    )

    @Serializable
    data class MoneyPickUp(
        val item: String,
        val min: Double,
        val max: Double
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
