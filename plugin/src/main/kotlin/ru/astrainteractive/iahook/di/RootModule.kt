package ru.astrainteractive.iahook.di

import CommandManager
import ru.astrainteractive.astralibs.Dependency
import ru.astrainteractive.astralibs.Module
import ru.astrainteractive.astralibs.Reloadable
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.astralibs.economy.EconomyProvider
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.commands.di.CommandManagerModule
import ru.astrainteractive.iahook.events.EventManager
import ru.astrainteractive.iahook.events.di.EventModule
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.iahook.plugin.Translation
import ru.astrainteractive.iahook.util.PassiveManaRestore

interface RootModule : Module {
    val plugin: Dependency<IaHook>
    val logger: Dependency<Logger>
    val bukkitDispatchers: Dependency<BukkitDispatchers>
    val pluginScope: Dependency<AsyncComponent>
    val configuration: Reloadable<MainConfiguration>
    val translation: Reloadable<Translation>
    val eventManager: Dependency<EventManager>
    val commandManager: Dependency<CommandManager>
    val economyProvider: Dependency<EconomyProvider>

    val passiveManaRestoreJob: Reloadable<PassiveManaRestore>

    val commandManagerModule: CommandManagerModule
    val eventModule: EventModule
    val filesModule: FilesModule
}
