package dev.midka.eka.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BootsOfLeaping implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("godboots")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getInventory().firstEmpty() == -1) {
                    // Inv is full
                    Location loc = player.getLocation();
                    World world = player.getWorld();

                    world.dropItemNaturally(loc, getGodBoots());
                    player.sendMessage(ChatColor.GOLD + "The Minecraft Gods dropped you a gift.");
                }
                player.getInventory().addItem(getGodBoots());
                player.sendMessage(ChatColor.GOLD + "The Minecraft Gods gave you a gift.");
            } else {
                sender.sendMessage("You're not allowed to fly.");
            }
        }
        return false;
    }

    public static ItemStack getGodBoots() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = boots.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 10, true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Boots of Leaping");
        meta.setUnbreakable(true);

        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Boots made by the Minecraft Gods");

        meta.setLore(lore);
        boots.setItemMeta(meta);

        return boots;
    }

    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getBoots() != null) {
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping")) {
                if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                    if (event.getFrom().getY() < event.getTo().getY() &&
                            player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                        player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (player.getInventory().getBoots() != null) {
                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping")) {
                        if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
