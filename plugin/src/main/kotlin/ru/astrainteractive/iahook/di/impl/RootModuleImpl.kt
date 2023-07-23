@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook.di.impl

import CommandManager
import kotlinx.serialization.encodeToString
import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.Lateinit
import ru.astrainteractive.astralibs.Reloadable
import ru.astrainteractive.astralibs.Single
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.DefaultBukkitDispatchers
import ru.astrainteractive.astralibs.configloader.ConfigLoader
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.astralibs.utils.buildWithSpigot
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.iahook.events.EventManager
import ru.astrainteractive.iahook.plugin.MainConfiguration
import ru.astrainteractive.iahook.plugin.Translation
import ru.astrainteractive.iahook.util.PassiveManaRestore

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

    override val configuration = Reloadable {
        val configFile by filesModule.configFile
        ConfigLoader.toClassOrDefault(configFile.configFile, ::MainConfiguration).also {
            configFile.configFile.writeText(ConfigLoader.defaultYaml.encodeToString(it))
        }
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

    override val passiveManaRestoreJob: Reloadable<PassiveManaRestore> = Reloadable {
        PassiveManaRestore(
            dispatchers = bukkitDispatchers.value,
            configuration = configuration.value,
            logger = logger.value,
        )
    }
}
