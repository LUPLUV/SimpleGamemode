package dev.lupluv.simplegamemode.utils

import com.google.gson.Gson
import org.bukkit.entity.Player
import java.io.File

class Config(
    val prefix: Array<String> = arrayOf("§8[§aSimpleGamemode§8]§7"),
    val noPermission: Array<String> = arrayOf("%prefix% §cYou don't have permission to do this."),
    val onlyPlayer: Array<String> = arrayOf("%prefix% §cOnly players can do this."),
    val wrongUsage: Array<String> = arrayOf("%prefix% §cWrong usage. Use /gm <0/1/2/3> [player]"),
    val loadingPlugin: Array<String> = arrayOf("%prefix% §aLoading plugin"),
    val updateInfo: Array<String> = arrayOf(
        "%prefix% §cYou are using an old version of this plugin.",
        "%prefix% §cPlease update at %url%"
    )
) {
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
            msg = msg.replace(key, value)
        }
        sendMessage(msg)
    }
}