package ru.astrainteractive.iahook.commands.di

import ru.astrainteractive.astralibs.Dependency
import ru.astrainteractive.astralibs.Provider
import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.plugin.Translation

interface CommandManagerModule {
    val plugin: Dependency<IaHook>
    val translationModule: Dependency<Translation>
    val pluginScope: Dependency<AsyncComponent>
    val dispatchers: Dependency<BukkitDispatchers>
    val randomIntProvider: Provider<Int>
}
