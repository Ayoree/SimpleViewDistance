package com.ayoree.simpleviewdistance.utility;

import com.ayoree.simpleviewdistance.SimpleViewDistance;
import com.ayoree.simpleviewdistance.Config;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class PlayerUtility {

    private final Player player;
    private static final Map<String, Integer> PlayerDataHandlerMap = new HashMap<>();
    private static final ClampUtility clampUtility = new ClampUtility();

    public PlayerUtility(Player player) {
        this.player = player;
    }

    public int getPlayerVD() {
        int distance = PlayerDataHandlerMap.getOrDefault(player.getName().toLowerCase(), getMaxPlayerVD(false));
        return clampUtility.clampChunkValue(distance);
    }

    public int getMaxPlayerVD() {
        return getMaxPlayerVD(true);
    }

    public int calculatePlayerVD(int chunks) {
        chunks = Math.min(chunks, getMaxPlayerVD(false));
        return clampUtility.clampChunkValue(chunks);
    }

    private int getMaxPlayerVD(boolean clamp) {
        int distance = Config.defaultDistance;
        for(String group : getPlayerGroups()) {
            distance = Math.max(distance, Config.groupDistances.getOrDefault(group, Config.minDistance));
        }
        if (clamp) return clampUtility.clampChunkValue(distance);
        else return distance;
    }

    public File getPlayerDataFile() {
        File dataFolder = SimpleViewDistance.getPlugin(SimpleViewDistance.class).getDataFolder();
        return new File(dataFolder, "players/" + player.getName().toLowerCase() + ".yml");
    }

    private List<String> getPlayerGroups() {
        List<String> lst = new ArrayList<>();
        Config.groupDistances.forEach((String group, Integer value) ->  {
            if (player.hasPermission("group." + group)) {
                lst.add(group);
            }
        });

        return lst;
    }

    public static void save(Player player, int chunks) {
        PlayerDataHandlerMap.put(player.getName().toLowerCase(), chunks);
    }

    public static void remove(Player player) {
        PlayerDataHandlerMap.remove(player.getName().toLowerCase());
    }
}
