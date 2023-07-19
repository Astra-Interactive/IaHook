package ru.astrainteractive.iahook.commands.di

import ru.astrainteractive.astralibs.async.AsyncComponent
import ru.astrainteractive.astralibs.async.BukkitDispatchers
import ru.astrainteractive.iahook.IaHook
import ru.astrainteractive.iahook.plugin.Translation

interface CommandManagerModule {
    val plugin: IaHook
    val translation: Translation
    val pluginScope: AsyncComponent
    val dispatchers: BukkitDispatchers
}
