package ru.astrainteractive.iahook.di.impl

import ru.astrainteractive.astralibs.filemanager.DefaultSpigotFileManager
import ru.astrainteractive.iahook.di.FilesModule
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

internal class FilesModuleImpl(
    rootModule: RootModule
) : FilesModule {

    override val configFile = Single {
        val plugin by rootModule.plugin
        DefaultSpigotFileManager(plugin, "config.yml")
    }
}
