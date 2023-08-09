package ru.astrainteractive.iahook.di.impl

import ru.astrainteractive.iahook.commands.di.CommandManagerModule
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.klibs.kdi.getValue

internal class CommandManagerModuleImpl(
    rootModule: RootModule,
) : CommandManagerModule {

    override val plugin by rootModule.plugin
    override val translation by rootModule.translation
    override val pluginScope by rootModule.pluginScope
    override val dispatchers by rootModule.bukkitDispatchers
}
