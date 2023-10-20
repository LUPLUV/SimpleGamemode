package dev.lupluv.simplegamemode.commands

import dev.lupluv.simplegamemode.utils.Config
import dev.lupluv.simplegamemode.utils.sendCMessage
import dev.lupluv.simplegamemode.utils.translation
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GamemodeCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) return true
        if (!sender.hasPermission("sgm.gamemode")) {
            sender.sendCMessage(Config.instance.noPermission)
            return true
        }
        when (args.size) {
            0 -> {
                sender.sendCMessage(Config.instance.usageInfo)
                return true
            }

            1 -> {
                val gamemode =
                    gamemodeAliases.filter { it.value.contains(args[0].lowercase()) }.keys.firstOrNull() ?: run {
                        sender.sendCMessage(Config.instance.usageInfo)
                        return true
                    }
                if (!sender.hasPermission("sgm.${gamemodeAliases[gamemode]!![0]}")
                    && !sender.hasPermission("sgm.${gamemodeAliases[gamemode]!![0]}.self")
                    && !sender.hasPermission("sgm.all")
                    && !sender.hasPermission("sgm.all.self")
                ) {
                    sender.sendCMessage(Config.instance.noPermission)
                    return true
                }
                sender.gameMode = gamemode
                sender.sendCMessage(
                    Config.instance.gamemodeUpdate,
                    mapOf("gamemode" to gamemode.translation, "player" to sender.name)
                )
            }

            2 -> {
                val target = Bukkit.getPlayer(args[1]) ?: run {
                    sender.sendCMessage(Config.instance.playerNotFound)
                    return true
                }
                val gamemode =
                    gamemodeAliases.filter { it.value.contains(args[0].lowercase()) }.keys.firstOrNull() ?: run {
                        sender.sendCMessage(Config.instance.usageInfo)
                        return true
                    }
                if (!sender.hasPermission("sgm.${gamemodeAliases[gamemode]!![0]}")
                    && !sender.hasPermission("sgm.${gamemodeAliases[gamemode]!![0]}.others")
                    && !sender.hasPermission("sgm.all")
                    && !sender.hasPermission("sgm.all.others")
                ) {
                    sender.sendCMessage(Config.instance.noPermission)
                    return true
                }
                target.gameMode = gamemode
                sender.sendCMessage(
                    Config.instance.gamemodeUpdate,
                    mapOf("gamemode" to gamemode.translation, "player" to target.name)
                )
            }

            else -> {
                sender.sendCMessage(Config.instance.usageInfo)
            }
        }
        return true
    }
}

val gamemodeAliases = mapOf(
    GameMode.ADVENTURE to listOf("adventure", "adv", "a", "2"),
    GameMode.CREATIVE to listOf("creative", "c", "1"),
    GameMode.SPECTATOR to listOf("spectator", "spec", "sp", "3"),
    GameMode.SURVIVAL to listOf("survival", "sur", "s", "0")
)