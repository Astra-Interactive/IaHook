@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook.di.impl

import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.astralibs.economy.EconomyProvider
import ru.astrainteractive.astralibs.events.GlobalEventListener
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.iahook.events.di.EventModule
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

internal class EventModuleImpl(
    rootModule: RootModule
) : EventModule {

    @OptIn(UnsafeApi::class)
    override val eventListener by Single { GlobalEventListener }
    override val plugin by rootModule.plugin
    override val translation by rootModule.translation
    override val logger: Logger by rootModule.logger
    override val configuration: MainConfiguration by rootModule.configuration
    override val scope: AsyncComponent by rootModule.pluginScope
    override val dispatchers: BukkitDispatchers by rootModule.bukkitDispatchers
    override val economyProvider: EconomyProvider by rootModule.economyProvider
}
