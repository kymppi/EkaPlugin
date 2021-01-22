package dev.midka.eka.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Doctor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("doctor")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("doctor.use"))) {
                    player.sendMessage(ChatColor.RED + "You do not have required permissions. Needed: doctor.use");
                    return true;
                }
                if (args.length == 0) {
                    // /doctor
                    TextComponent message = new TextComponent("Would you like to be healed?");
                    message.setColor(ChatColor.GOLD);
                    message.setBold(true);
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/doctor healme"));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new Text("Click here to be healed.")));
                    player.spigot().sendMessage(message);
                    return true;
                }
                if (args[0].equalsIgnoreCase("healme")) {
                    player.setHealth(20.0);
                    player.sendMessage(ChatColor.GREEN + "You've been healed!");
                }

                return true;
            } else {
                sender.sendMessage("Sorry this command is only for players.");
                return true;
            }
        }
        return false;
    }
}
