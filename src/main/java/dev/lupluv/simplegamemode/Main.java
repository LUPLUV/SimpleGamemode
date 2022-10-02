package dev.lupluv.simplegamemode;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("gamemode").setExecutor();
        getCommand("survival").setExecutor();
        getCommand("creative").setExecutor();
        getCommand("adventure").setExecutor();
        getCommand("spectator").setExecutor();

    }

    @Override
    public void onDisable() {

    }
}
