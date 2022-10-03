package dev.lupluv.simplegamemode.commands;

import dev.lupluv.simplegamemode.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!p.hasPermission("sgm.gamemode")){
                p.sendMessage(Strings.noPerms);
            }else{
                if(args.length == 0){
                    p.sendMessage("§6Usage: §f/gm §e<survival§c|§ecreative§c|§eadventure§c|§espectator> §7[player] §6- Sets the gamemode of either you or another player if specified");
                }else if(args.length == 1){
                    String ar0 = args[0];
                    if(ar0.equalsIgnoreCase("survival") || ar0.equalsIgnoreCase("0")){
                        if(!p.hasPermission("sgm.survival") && !p.hasPermission("sgm.survival.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage("§6Set game mode §csurvival §6for §4" + p.getName() + "§6.");
                        }
                    }else if(ar0.equalsIgnoreCase("creative") || ar0.equalsIgnoreCase("1")){
                        if(!p.hasPermission("sgm.creative") && !p.hasPermission("sgm.creative.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage("§6Set game mode §ccreative §6for §4" + p.getName() + "§6.");
                        }
                    }else if(ar0.equalsIgnoreCase("adventure") || ar0.equalsIgnoreCase("2")){
                        if(!p.hasPermission("sgm.adventure") && !p.hasPermission("sgm.adventure.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage("§6Set game mode §cadventure §6for §4" + p.getName() + "§6.");
                        }
                    }else if(ar0.equalsIgnoreCase("spectator") || ar0.equalsIgnoreCase("spec") || ar0.equalsIgnoreCase("3")){
                        if(!p.hasPermission("sgm.spectator") && !p.hasPermission("sgm.spectator.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage("§6Set game mode §cspectator §6for §4" + p.getName() + "§6.");
                        }
                    }else{
                        p.sendMessage("§6Usage: §f/gm §e<survival§c|§ecreative§c|§eadventure§c|§espectator> §7[player] §6- Sets the gamemode of either you or another player if specified");
                    }
                }else if(args.length == 2){
                    Player t = Bukkit.getPlayer(args[1]);
                    if(t != null){
                        String ar0 = args[0];
                        if(ar0.equalsIgnoreCase("survival") || ar0.equalsIgnoreCase("0")){
                            if(!p.hasPermission("sgm.survival") && !p.hasPermission("sgm.survival.other")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage("§6Set game mode §csurvival §6for §4" + t.getName() + "§6.");
                            }
                        }else if(ar0.equalsIgnoreCase("creative") || ar0.equalsIgnoreCase("1")){
                            if(!p.hasPermission("sgm.creative") && !p.hasPermission("sgm.creative.other")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.CREATIVE);
                                p.sendMessage("§6Set game mode §ccreative §6for §4" + t.getName() + "§6.");
                            }
                        }else if(ar0.equalsIgnoreCase("adventure") || ar0.equalsIgnoreCase("2")){
                            if(!p.hasPermission("sgm.adventure") && !p.hasPermission("sgm.adventure.other")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage("§6Set game mode §cadventure §6for §4" + t.getName() + "§6.");
                            }
                        }else if(ar0.equalsIgnoreCase("spectator") || ar0.equalsIgnoreCase("spec") || ar0.equalsIgnoreCase("3")){
                            if(!p.hasPermission("sgm.spectator") && !p.hasPermission("sgm.spectator.other")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.SPECTATOR);
                                p.sendMessage("§6Set game mode §cspectator §6for §4" + t.getName() + "§6.");
                            }
                        }else{
                            p.sendMessage("§6Usage: §f/gm §e<survival§c|§ecreative§c|§eadventure§c|§espectator> §7[player] §6- Sets the gamemode of either you or another player if specified");
                        }
                    }else{
                        p.sendMessage("§cError: §4Player not found.");
                    }
                }else{
                    p.sendMessage("§6Usage: §f/gm §e<survival§c|§ecreative§c|§eadventure§c|§espectator> §7[player] §6- Sets the gamemode of either you or another player if specified");
                }
            }
        }

        return true;
    }
}
