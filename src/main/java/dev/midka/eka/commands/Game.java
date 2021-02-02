package dev.midka.eka.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Game implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("gmc")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (strings.length == 0) {
                    player.sendMessage(ChatColor.GREEN + "Works");
                    player.setGameMode(GameMode.CREATIVE);
                }
            } else {
                commandSender.sendMessage("You cannot execute this command");
            }
        }
        return false;
    }
}
