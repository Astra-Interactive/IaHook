@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook.di.impl

import CommandManager
import kotlinx.serialization.encodeToString
import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.DefaultBukkitDispatchers
import ru.astrainteractive.astralibs.configloader.ConfigLoader
import ru.astrainteractive.astralibs.economy.EconomyProvider
import ru.astrainteractive.astralibs.economy.VaultEconomyProvider
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.astralibs.utils.buildWithSpigot
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.iahook.events.EventManager
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.iahook.plugin.Translation
import ru.astrainteractive.iahook.util.PassiveManaRestore
import ru.astrainteractive.klibs.kdi.Dependency
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Reloadable
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

internal class RootModuleImpl : RootModule {
    // core
    override val plugin = Lateinit<IaHook>()
    override val logger = Single {
        Logger.buildWithSpigot("IaHook", plugin.value)
    }
    override val bukkitDispatchers = Single {
        DefaultBukkitDispatchers(plugin.value)
    }
    override val pluginScope = Single {
        object : AsyncComponent() {} as AsyncComponent
    }

    // modules
    override val commandManagerModule by Single {
        CommandManagerModuleImpl(this)
    }

    override val eventModule by Single {
        EventModuleImpl(this)
    }

    override val filesModule by Single {
        FilesModuleImpl(this)
    }

    override val configuration: Reloadable<MainConfiguration> = Reloadable {
        val configFile by filesModule.configFile
        runCatching {
            ConfigLoader().unsafeParse<MainConfiguration>(configFile.configFile)
        }.onFailure {
            it.printStackTrace()
        }.onSuccess {
            configFile.configFile.writeText(ConfigLoader().defaultYaml.encodeToString(it))
        }.getOrNull() ?: MainConfiguration()
    }

    override val translation = Reloadable {
        val plugin by plugin
        Translation(plugin)
    }

    override val eventManager = Single {
        EventManager(eventModule)
    }

    override val commandManager = Single {
        CommandManager(commandManagerModule)
    }
    override val economyProvider: Dependency<EconomyProvider> = Single {
        VaultEconomyProvider()
    }

    override val passiveManaRestoreJob: Reloadable<PassiveManaRestore> = Reloadable {
        PassiveManaRestore(
            dispatchers = bukkitDispatchers.value,
            configuration = configuration.value,
            logger = logger.value,
        )
    }
}
