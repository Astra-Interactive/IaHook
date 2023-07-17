@file:OptIn(UnsafeApi::class)

package ru.astrainteractive.iahook.di.impl

import org.jetbrains.kotlin.tooling.core.UnsafeApi
import ru.astrainteractive.astralibs.Dependency
import ru.astrainteractive.astralibs.Provider
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.commands.di.CommandManagerModule
import ru.astrainteractive.iahook.di.PluginModule
import ru.astrainteractive.iahook.di.RootModule
import ru.astrainteractive.iahook.plugin.Translation
import kotlin.random.Random

internal object CommandManagerModuleImpl : CommandManagerModule {
    private val rootModule: RootModule by RootModuleImpl
    private val pluginModule: PluginModule by PluginModuleImpl

    override val plugin: Dependency<IaHook> = pluginModule.plugin
    override val translationModule: Dependency<Translation> = rootModule.translationModule
    override val pluginScope: Dependency<AsyncComponent> = pluginModule.pluginScope
    override val dispatchers: Dependency<BukkitDispatchers> = pluginModule.bukkitDispatchers
    override val randomIntProvider: Provider<Int> = Provider { Random.nextInt(1, 100) }
}
