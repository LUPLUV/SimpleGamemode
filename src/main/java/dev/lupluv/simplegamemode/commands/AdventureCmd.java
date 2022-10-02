package dev.lupluv.simplegamemode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdventureCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                p.performCommand("gamemode adventure");
            }else if(args.length == 1){
                p.performCommand("gamemode adventure " + args[0]);
            }
        }

        return false;
    }
}
