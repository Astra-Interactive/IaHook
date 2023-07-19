import ru.astrainteractive.astralibs.getValue
import ru.astrainteractive.iahook.commands.di.CommandManagerModule
import ru.astrainteractive.iahook.commands.reload

/**
 * Command handler for your plugin
 * It's better to create different executors for different commands
 */
class CommandManager(module: CommandManagerModule) : CommandManagerModule by module {
    /**
     * Here you should declare commands for your plugin
     *
     * Commands stored in plugin.yml
     *
     * etemp has TabCompleter
     */
    init {
        reload()
    }
}
