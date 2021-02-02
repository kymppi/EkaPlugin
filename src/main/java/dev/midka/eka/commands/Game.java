package dev.midka.eka.commands;


import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Game implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("eka.gamemode.change")) {
                switch (s.toLowerCase()) {
                    case "gms":
                        player.setGameMode(GameMode.SURVIVAL);
                        break;
                    case "gma":
                        player.setGameMode(GameMode.ADVENTURE);
                        break;
                    case "gmc":
                        player.setGameMode(GameMode.CREATIVE);
                        break;
                    case "gmsr":
                        player.setGameMode(GameMode.SPECTATOR);
                        break;
                }
            }

        }

        if (commandSender instanceof Player) {
            if (s.equalsIgnoreCase("command")) {
                // do stuff
            } else if (s.equalsIgnoreCase("command1")) {
                // do stuff
            } else if (s.equalsIgnoreCase("command2")) {
                // do stuff
            }
        }
        return false;
    }
}
