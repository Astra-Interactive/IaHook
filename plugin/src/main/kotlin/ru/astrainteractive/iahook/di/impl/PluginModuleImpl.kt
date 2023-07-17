package ru.astrainteractive.iahook.di.impl

import ru.astrainteractive.astralibs.Lateinit
import ru.astrainteractive.astralibs.Single
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.DefaultBukkitDispatchers
import ru.astrainteractive.astralibs.logging.Logger
import ru.astrainteractive.astralibs.utils.buildWithSpigot
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.di.PluginModule

internal object PluginModuleImpl : PluginModule {
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
}
