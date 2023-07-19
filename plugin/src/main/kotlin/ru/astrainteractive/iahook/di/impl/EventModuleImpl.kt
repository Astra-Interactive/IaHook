@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook.di.impl

import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.Single
import ru.astrainteractive.astralibs.events.GlobalEventListener
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.iahook.events.di.EventModule
import ru.astrainteractive.iahook.plugin.MainConfiguration

internal class EventModuleImpl(
    rootModule: RootModule
) : EventModule {

    override val eventListener by Single { GlobalEventListener }
    override val plugin by rootModule.plugin
    override val translation by rootModule.translation
    override val logger: Logger by rootModule.logger
    override val configuration: MainConfiguration by rootModule.configuration
}
