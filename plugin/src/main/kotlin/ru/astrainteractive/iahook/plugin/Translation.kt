package ru.astrainteractive.iahook.plugin

import org.bukkit.plugin.Plugin
import ru.astrainteractive.astralibs.filemanager.DefaultSpigotFileManager
import ru.astrainteractive.astralibs.filemanager.SpigotFileManager
import ru.astrainteractive.astralibs.utils.BaseTranslation

/**
 * All translation stored here
 */
class Translation(plugin: Plugin) : BaseTranslation() {
    override val translationFile: SpigotFileManager = DefaultSpigotFileManager(plugin, "translations.yml")

    val colors = Colors()
    val general = General()

    inner class Colors {
        val success = translationValue("colors.success", SUCCESS_COLOR)
        val error = translationValue("colors.error", ERROR_COLOR)
    }

    inner class General {
        // General
        val prefix = translationValue(
            "general.prefix",
            "$SUCCESS_COLOR[EmpireItems]"
        )
        val reload = translationValue(
            "general.reload",
            "${SUCCESS_COLOR}Перезагрузка плагина"
        )
        val reloadComplete = translationValue(
            "general.reload_complete",
            "${SUCCESS_COLOR}Перезагрузка успешно завершена"
        )
        val noPermission = translationValue(
            "general.no_permission",
            "${ERROR_COLOR}У вас нет прав!"
        )
        private val moneyReceived = translationValue(
            "general.received_money",
            "$SUCCESS_COLOR+ %.2f монет"
        )

        fun moneyReceived(amount: Number) = moneyReceived.format(amount.toInt())
    }

    companion object {
        private const val SUCCESS_COLOR = "#18dbd1"
        private const val ERROR_COLOR = "#db2c18"
    }
}
