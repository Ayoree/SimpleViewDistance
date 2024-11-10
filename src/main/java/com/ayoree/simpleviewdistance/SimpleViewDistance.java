package com.ayoree.simpleviewdistance;

import com.ayoree.simpleviewdistance.commands.CommandManager;
import com.ayoree.simpleviewdistance.events.JoinLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SimpleViewDistance extends JavaPlugin {
    public Config config;

    @Override
    public void onEnable() {
        config = new Config(this);
        config.loadConfig();

        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(this), this);

        CommandManager commandManager = new CommandManager(this);
        Objects.requireNonNull(getCommand("viewdistance")).setExecutor(commandManager);
        Objects.requireNonNull(getCommand("viewdistance")).setTabCompleter(commandManager);

        getLogger().info("Плагин запущен.");
    }

    @Override
    public void onDisable() { getLogger().info("Плагин выключен."); }
}
