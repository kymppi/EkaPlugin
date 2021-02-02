package dev.midka.eka.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
    public static void goodPlayer(Player player, String message) {
        player.sendMessage(ChatColor.GREEN + message);
    }

    public static void badPlayer(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);
    }

    public static void goodConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + message);
    }

    public static void badConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + message);
    }

    public static void translatePlayer(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void translateConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
