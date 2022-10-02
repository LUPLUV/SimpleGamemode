package dev.lupluv.simplegamemode.events;

import dev.lupluv.simplegamemode.utils.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(e.getPlayer().hasPermission("sgm.updates")) UpdateChecker.sendUpdateMessage(e.getPlayer());
    }

}
