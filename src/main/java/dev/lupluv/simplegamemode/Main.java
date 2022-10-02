package dev.lupluv.simplegamemode;

import dev.lupluv.simplegamemode.commands.*;
import dev.lupluv.simplegamemode.utils.Strings;
import dev.lupluv.simplegamemode.utils.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        getServer().getConsoleSender().sendMessage(Strings.prefix + "§aLoading plugin");

        String currentVersion = getDescription().getVersion();
        String newestVersion = UpdateChecker.getNewestVersion();

        if(!currentVersion.equalsIgnoreCase(newestVersion)){
            getServer().getConsoleSender().sendMessage(Strings.prefix + "§cDetected an old version of the plugin.");
            getServer().getConsoleSender().sendMessage(Strings.prefix + "§6Please update to the newest version at https://www.spigotmc.org/resources/simple-gamemode-gm-1-8-1-19.86743/");
        }

    }

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage(Strings.prefix + "§aRegistering commands");
        try {
            getCommand("gamemode").setExecutor(new GamemodeCmd());
            getCommand("survival").setExecutor(new SurvivalCmd());
            getCommand("creative").setExecutor(new CreativeCmd());
            getCommand("adventure").setExecutor(new AdventureCmd());
            getCommand("spectator").setExecutor(new SpectatorCmd());
        }catch (Exception ex){
            getServer().getConsoleSender().sendMessage(Strings.prefix + "§cRegistering command failed.");
            getServer().getConsoleSender().sendMessage(Strings.prefix + "§cError code below:");
            ex.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getConsoleSender().sendMessage(Strings.prefix + "§aCommands registered successful");
        getServer().getConsoleSender().sendMessage(Strings.prefix + "§aStartup successful!");

        getServer().getOnlinePlayers().forEach(UpdateChecker::sendUpdateMessage);

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Strings.prefix + "§cStopping the plugin...");
        getServer().getConsoleSender().sendMessage(Strings.prefix + "§aShutdown successful!");
    }
}
