package dev.lupluv.simplegamemode.events

import dev.lupluv.simplegamemode.utils.UpdateChecker.sendUpdateMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerHandler : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) = with(event) {
        if (player.hasPermission("sgm.updates")) player.sendUpdateMessage()
    }
}
