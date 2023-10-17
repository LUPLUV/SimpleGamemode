package dev.lupluv.simplegamemode.utils

import org.bukkit.entity.Player

object UpdateChecker {
    private const val REQUEST_URL = "https://api.github.com/repos/LUPLUV/SimpleGamemode/releases/latest"
    val newestVersion: String?
        get() {
            val response = khttp.get(REQUEST_URL)
            if (response.statusCode != 200) return null
            return response.jsonObject["tag_name"]?.toString() ?: return null
        }

    fun Player.sendUpdateMessage() {
        sendCMessage(Config.instance.updateInfo, mapOf("url" to "https://github.com/LUPLUV/SimpleGamemode/releases/latest"))
    }
}
