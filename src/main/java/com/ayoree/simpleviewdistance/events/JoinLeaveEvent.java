package com.ayoree.simpleviewdistance.events;

import com.ayoree.simpleviewdistance.SimpleViewDistance;
import com.ayoree.simpleviewdistance.utility.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class JoinLeaveEvent implements Listener {

    private final SimpleViewDistance plugin;

    public JoinLeaveEvent(SimpleViewDistance plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        int amount;

        PlayerUtility playerUtility = new PlayerUtility(e.getPlayer());
        File playerDataFile = playerUtility.getPlayerDataFile();

        if (playerDataFile.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(playerDataFile);
            amount = cfg.getInt("chunks", playerUtility.getMaxPlayerVD());
        }
        else {
            amount = playerUtility.getMaxPlayerVD();
        }

        e.getPlayer().setViewDistance(amount);
        PlayerUtility.save(e.getPlayer(), amount);
    }


    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerUtility playerUtility = new PlayerUtility(player);
        SavePlayerUtility saveUtility = new SavePlayerUtility(player, plugin);
        int viewDistance = playerUtility.getPlayerVD();

        if (viewDistance == playerUtility.getMaxPlayerVD()) {
            saveUtility.delete();
        }
        else {
            saveUtility.save(viewDistance);
        }

        PlayerUtility.remove(player);
    }
}
