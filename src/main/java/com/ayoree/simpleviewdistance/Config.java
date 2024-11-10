package com.ayoree.simpleviewdistance;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.*;

public class Config {

    private final Plugin plugin;
    protected final File configFile;

    public Config(SimpleViewDistance plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public static String PREFIX = "§f[§lV§r§7iew§f§lD§r§7instance§f] §r";
    public static String MSG_CHANGE = "§aВы установили вашу дальность прорисовки на {chunks} чанков";
    public static String MSG_INCORRECT_ARGS = "§cОшибка синтаксиса§e - используйте §l/vd <число>";
    public static String MSG_CONFIG_RELOAD = "§aКонфиг успешно перезагружен";
    public static String MSG_NO_PERMISSION = "§cУ вас нет прав для использования данной команды";

    public static int minDistance = 2;
    public static int defaultDistance = 12;
    public static Map<String, Integer> groupDistances = new HashMap<>();

    public void loadConfig() {
        plugin.saveDefaultConfig();
        loadAll();
    }

    private void loadAll() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        PREFIX = config.getString("prefix", PREFIX);
        MSG_CHANGE = config.getString("view-distance-change-msg", MSG_CHANGE);
        MSG_INCORRECT_ARGS = config.getString("incorrect-args-msg", MSG_INCORRECT_ARGS);
        MSG_CONFIG_RELOAD = config.getString("reload-config-msg", MSG_CONFIG_RELOAD);
        MSG_NO_PERMISSION = config.getString("no-permission-msg", MSG_NO_PERMISSION);

        minDistance = config.getInt("min-distance", minDistance);
        defaultDistance = config.getInt("distances.default", defaultDistance);

        groupDistances.clear();
        ConfigurationSection groupsSection = config.getConfigurationSection("distances");
        if (groupsSection != null) {
            for (String group : groupsSection.getKeys(false)) {
                groupDistances.put(
                        group,
                        config.getInt("distances." + group, defaultDistance)
                );
            }
        }
    }
}
