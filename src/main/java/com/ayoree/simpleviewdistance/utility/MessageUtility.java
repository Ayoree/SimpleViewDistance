package com.ayoree.simpleviewdistance.utility;

import com.ayoree.simpleviewdistance.Config;
import com.ayoree.simpleviewdistance.SimpleViewDistance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageUtility {
    public static void processMessage(@NotNull String msg, CommandSender commandSender) {
        SimpleViewDistance plugin = SimpleViewDistance.getPlugin(SimpleViewDistance.class);
        msg = Config.PREFIX + msg;
        if (!(commandSender instanceof Player)) {
            msg = msg.replaceAll("§.", "");
            plugin.getLogger().info(msg);
        }
        else {
            commandSender.sendMessage(msg);
        }
    }

    public static void processMessage(@NotNull String msg, Player target, int amount) {
        processMessage(replacePlaceholders(msg, amount), target);
    }

    private static String replacePlaceholders(@NotNull String msg, int amount) {
        msg = msg.replace("{chunks}", String.valueOf(amount));
        return msg;
    }
}
