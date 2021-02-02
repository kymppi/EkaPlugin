package dev.midka.eka.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpearOfDestiny implements CommandExecutor, Listener {

    public List<String> list = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("destinyspear") || label.equalsIgnoreCase("dspear")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getInventory().firstEmpty() == -1) {
                    // Inv is full
                    Location loc = player.getLocation();
                    World world = player.getWorld();

                    world.dropItemNaturally(loc, getItem());
                    player.sendMessage(ChatColor.GOLD + "The Minecraft Gods dropped you a gift.");
                }
                player.getInventory().addItem(getItem());
                player.sendMessage(ChatColor.GOLD + "The Minecraft Gods gave you a gift.");
            } else {
                sender.sendMessage("Sorry, but only players can use this command.");
            }
        }
        return false;
    }

    public ItemStack getItem() {
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addEnchant(Enchantment.LOYALTY, 10, true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Spear Of Destiny");
        meta.setUnbreakable(true);

        List<String> lore = new ArrayList<String>();
        lore.add("This spear is used to control destiny.");
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7(&aRight-Click&7) &a&oSpawn minions"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7(&aLeft-Click&7) &a&oShoot Fireball"));
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Spear of Destiny made by the Minecraft Gods");

        meta.setLore(lore);
        trident.setItemMeta(meta);

        return trident;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.TRIDENT)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                if (event.getPlayer().getInventory()
                        .getItemInMainHand().getItemMeta().getDisplayName().contains("Spear Of Destiny")) {
                    Player player = event.getPlayer();
                    // Right click
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (!list.contains(player.getName())) {
                            list.add(player.getName());
                        }
                        return;
                    }

                    // Left click
                    if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        player.launchProjectile(Fireball.class);
                    }
                }
            }
        }
        list.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void onLand(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.TRIDENT) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                if (list.contains(player.getName())) {
                    // Spawn minions
                    Location loc = event.getEntity().getLocation();
                    loc.setY(loc.getY() + 1);

                    // spawn minions
                    for (int i = 1; i < 4; i++) {
                        loc.getWorld().spawnEntity(loc, EntityType.DROWNED);
                        loc.setX(loc.getX() + i);
                    }
                }
            }
        }
    }
}
