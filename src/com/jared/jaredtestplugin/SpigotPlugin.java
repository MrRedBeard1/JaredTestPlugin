package com.jared.jaredtestplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class SpigotPlugin extends JavaPlugin {
    private static SpigotPlugin instance;
    public static SpigotPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // TODO: whatever
        //
        //

        saveDefaultConfig();

        // register events
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // TODO: whatever
        //
        //

        getLogger().log(Level.INFO, "The sever unloaded me...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("test")) {
            Player player = (Player) sender;

            player.setGliding(true);
        }

        return true;
    }
}
