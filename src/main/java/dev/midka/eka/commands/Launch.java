package dev.midka.eka.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Launch implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("lch")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // /launch <number>
                if (args.length == 0) {
                    // launch
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooom!");
                    player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
                    return true;
                }
                // launch <value>
                if (isNum(args[0])) {
                    if (Integer.parseInt(args[0]) > 100) {
                        player.sendMessage(ChatColor.RED + "Sorry, but the maximum multiplier is 100");
                        return true;
                    }
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooom!");
                    player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /launch <multiplier>");
                }

            } else {
                sender.sendMessage("* console goes flying *");
            }
            return true;
        }
        return false;
    }

    public boolean isNum(String num) {
        try {
            Integer.parseInt(num);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
