package ru.astrainteractive.iahook.di.factories

import ru.astrainteractive.astralibs.Factory
import ru.astrainteractive.astralibs.configuration.AnyConfiguration
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.iahook.di.impl.FilesModuleImpl
import ru.astrainteractive.iahook.plugin.CustomConfiguration

internal object CustomConfigurationFactory : Factory<CustomConfiguration> {
    override fun build(): CustomConfiguration {
        val configFile by FilesModuleImpl.configFile
        return CustomConfiguration(
            pluginVersion = AnyConfiguration(
                fileConfiguration = configFile.fileConfiguration,
                path = "custom.version",
                default = "0.88.0"
            )
        )
    }
}
