package com.ayoree.simpleviewdistance.commands;

import com.ayoree.simpleviewdistance.SimpleViewDistance;
import com.ayoree.simpleviewdistance.Config;
import com.ayoree.simpleviewdistance.utility.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private final SimpleViewDistance plugin;

    public CommandManager(SimpleViewDistance plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length > 0) {
            if (args[0].equals("reload")) {
                if (sender.hasPermission("viewdistance.reload")) {
                    plugin.config.loadConfig();
                    MessageUtility.processMessage(Config.MSG_CONFIG_RELOAD, sender);
                } else {
                    MessageUtility.processMessage(Config.MSG_NO_PERMISSION, sender);
                }
            } else try {
                int amount = Integer.parseInt(args[0]);
                if (sender instanceof Player player) {
                    PlayerUtility playerUtility = new PlayerUtility(player);
                    amount = playerUtility.calculatePlayerVD(amount);
                    if (sender.hasPermission("viewdistance.set")) {
                        MessageUtility.processMessage(Config.MSG_CHANGE, player, amount);
                        player.setViewDistance(amount);
                        PlayerUtility.save(player, amount);
                    } else {
                        MessageUtility.processMessage(Config.MSG_NO_PERMISSION, sender);
                    }
                }
            } catch (Exception e) {
                MessageUtility.processMessage(Config.MSG_INCORRECT_ARGS, sender);
            }
        }
        else {
            MessageUtility.processMessage(Config.MSG_INCORRECT_ARGS, sender);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        ArrayList<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("viewdistance.reload") && "reload".startsWith(args[0])) {
                suggestions.add("reload");
            }
            if (sender instanceof Player player) {
                PlayerUtility playerUtility = new PlayerUtility(player);
                int maxPlayerVD = playerUtility.getMaxPlayerVD();
                for (int i = Config.minDistance; i <= maxPlayerVD; i++) {
                    suggestions.add(Integer.toString(i));
                }
            }
            suggestions.removeIf((String suggestion) -> !suggestion.startsWith(args[0]));
        }
        return suggestions;
    }
}
