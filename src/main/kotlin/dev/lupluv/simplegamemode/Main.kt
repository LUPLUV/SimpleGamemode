package dev.lupluv.simplegamemode

import dev.lupluv.simplegamemode.commands.*
import dev.lupluv.simplegamemode.utils.Config
import dev.lupluv.simplegamemode.utils.UpdateChecker
import dev.lupluv.simplegamemode.utils.UpdateChecker.sendUpdateMessage
import dev.lupluv.simplegamemode.utils.getItzLogger
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    init {
        instance = this
    }

    override fun onLoad() {
        getItzLogger(this::class).info("Loading plugin...")
        val currentVersion = description.version
        val newestVersion = UpdateChecker.newestVersion
        if (currentVersion != newestVersion) {
            getItzLogger(this::class).warn(Config.instance.updateInfo, mapOf("url" to "https://github.com/LUPLUV/SimpleGamemode/releases/latest"))
        }
    }

    override fun onEnable() {
        getItzLogger(this::class).info(arrayOf("%prefix% §aRegistering commands"))
        try {
            getCommand("gamemode")!!.setExecutor(GamemodeCommand())
            getCommand("survival")!!.setExecutor(SurvivalCommand())
            getCommand("creative")!!.setExecutor(CreativeCommand())
            getCommand("adventure")!!.setExecutor(AdventureCommand())
            getCommand("spectator")!!.setExecutor(SpectatorCommand())
        } catch (ex: Exception) {
            getItzLogger(this::class).error(arrayOf("%prefix% §cRegistering command failed."))
            getItzLogger(this::class).error(arrayOf("%prefix% §cError code below:"))
            ex.printStackTrace()
            server.pluginManager.disablePlugin(this)
        }
        getItzLogger(this::class).info(arrayOf("%prefix% §aCommands registered successful"))
        getItzLogger(this::class).info(arrayOf("%prefix% §aStartup successful!"))
        server.onlinePlayers.forEach { it.sendUpdateMessage() }
    }

    override fun onDisable() {
        getItzLogger(this::class).info(arrayOf("%prefix% §cStopping the plugin..."))
        getItzLogger(this::class).info(arrayOf("%prefix% §aShutdown successful!"))
    }

    fun sendConsoleMessage(message: String) {
        server.consoleSender.sendMessage(message)
    }
}