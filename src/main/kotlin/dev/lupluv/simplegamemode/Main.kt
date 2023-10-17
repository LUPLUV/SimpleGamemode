package dev.lupluv.simplegamemode

import dev.lupluv.simplegamemode.commands.*
import dev.lupluv.simplegamemode.utils.UpdateChecker
import dev.lupluv.simplegamemode.utils.getItzLogger
import org.bukkit.entity.Player
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
            server.consoleSender.sendMessage(Strings.prefix + "§cDetected an old version of the plugin.")
            server.consoleSender.sendMessage(Strings.prefix + "§6Please update to the newest version at https://www.spigotmc.org/resources/simple-gamemode-gm-1-8-1-19.86743/")
        }
    }

    override fun onEnable() {
        server.consoleSender.sendMessage(Strings.prefix + "§aRegistering commands")
        try {
            getCommand("gamemode")!!.setExecutor(GamemodeCmd())
            getCommand("survival")!!.setExecutor(SurvivalCmd())
            getCommand("creative")!!.setExecutor(CreativeCmd())
            getCommand("adventure")!!.setExecutor(AdventureCmd())
            getCommand("spectator")!!.setExecutor(SpectatorCmd())
        } catch (ex: Exception) {
            server.consoleSender.sendMessage(Strings.prefix + "§cRegistering command failed.")
            server.consoleSender.sendMessage(Strings.prefix + "§cError code below:")
            ex.printStackTrace()
            server.pluginManager.disablePlugin(this)
        }
        server.consoleSender.sendMessage(Strings.prefix + "§aCommands registered successful")
        server.consoleSender.sendMessage(Strings.prefix + "§aStartup successful!")
        server.onlinePlayers.forEach { p: Player? -> UpdateChecker.sendUpdateMessage(p) }
    }

    override fun onDisable() {
        server.consoleSender.sendMessage(Strings.prefix + "§cStopping the plugin...")
        server.consoleSender.sendMessage(Strings.prefix + "§aShutdown successful!")
    }

    fun sendConsoleMessage(message: String) {
        server.consoleSender.sendMessage(message)
    }
}