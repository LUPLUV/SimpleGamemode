package dev.lupluv.simplegamemode.commands

import dev.lupluv.simplegamemode.utils.Config
import dev.lupluv.simplegamemode.utils.sendCMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdventureCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) return true
        when (args.size) {
            0 -> {
                sender.performCommand("gamemode adventure")
            }
            1 -> {
                sender.performCommand("gamemode adventure " + args[0])
            }
            else -> {
                sender.sendCMessage(Config.instance.invalidArguments)
            }
        }
        return true
    }
}
