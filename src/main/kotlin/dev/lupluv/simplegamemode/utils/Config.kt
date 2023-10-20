package dev.lupluv.simplegamemode.utils

import com.google.gson.Gson
import org.bukkit.GameMode
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import java.io.File

class Config(
    val prefix: Array<String> = arrayOf("§8[§aSimpleGamemode§8]§7"),
    val noPermission: Array<String> = arrayOf("%prefix% §cYou don't have permission to do this."),
    val onlyPlayer: Array<String> = arrayOf("%prefix% §cOnly players can do this."),
    val playerNotFound: Array<String> = arrayOf("%prefix% §cPlayer not found."),
    val usageInfo: Array<String> = arrayOf("%prefix% §6Usage: §f/gm §e<survival§c|§ecreative§c|§eadventure§c|§espectator> §7[player] §6- Sets the gamemode of either you or another player if specified"),
    val loadingPlugin: Array<String> = arrayOf("%prefix% §aLoading plugin"),
    val updateInfo: Array<String> = arrayOf(
        "%prefix% §cDetected an old version of the plugin.",
        "%prefix% §6Please update to the newest version at %url%"
    ),
    val invalidArguments: Array<String> = arrayOf("%prefix% §cInvalid arguments."),
    val gamemodeUpdate: Array<String> = arrayOf("%prefix% §6Set game mode §c%gamemode% §6for §4%player%§6."),
    val words: Words = Words()
) {
    class Words(
        val survival: String = "survival",
        val creative: String = "creative",
        val adventure: String = "adventure",
        val spectator: String = "spectator"
    )
    companion object {
        lateinit var instance: Config

        fun load(file: File) {
            instance = Gson().fromJson(file.bufferedReader(), Config::class.java)
        }

        fun saveDefault(file: File) {
            if (file.exists()) return
            file.createNewFile()
            file.writeText(Gson().toJson(instance))
        }
    }

    init {
        instance = this
    }
}

fun Player.sendCMessage(message: Array<String>, placeholders: Map<String, String> = mapOf()) {
    message.forEach {
        var msg = it.replace("%prefix%", Config.instance.prefix.joinToString(" "))
        placeholders.forEach { (key, value) ->
            msg = msg.replace("%$key%", value)
        }
        sendMessage(msg)
    }
}

fun ConsoleCommandSender.sendCMessage(message: Array<String>, placeholders: Map<String, String> = mapOf()) {
    message.forEach {
        var msg = it.replace("%prefix%", Config.instance.prefix.joinToString(" "))
        placeholders.forEach { (key, value) ->
            msg = msg.replace("%$key%", value)
        }
        sendMessage(msg)
    }
}

val GameMode.translation: String
    get() = when (this) {
        GameMode.SURVIVAL -> Config.instance.words.survival
        GameMode.CREATIVE -> Config.instance.words.creative
        GameMode.ADVENTURE -> Config.instance.words.adventure
        GameMode.SPECTATOR -> Config.instance.words.spectator
    }