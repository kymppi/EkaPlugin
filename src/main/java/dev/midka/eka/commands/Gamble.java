package dev.midka.eka.commands;

import dev.midka.eka.Eka;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Gamble implements CommandExecutor {

    Eka plugin = Eka.getInstance();

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("gamble")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                ItemStack fee = new ItemStack(Material.DIAMOND);
                fee.setAmount(3);
                if (player.hasPermission("eka.gamble.use"))
                    if (player.getInventory().getItemInMainHand().isSimilar(fee)) {
                        player.getInventory().remove(fee);
                        // spin GUI
                        spin(player);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "Fee: 3 diamonds");
                        return true;
                    }
            } else {
                commandSender.sendMessage("No gambling for you");
            }
        }
        return false;
    }

    public void shuffle(Inventory inv) {
        if (contents == null) {
            ItemStack[] items = new ItemStack[6];
            items[0] = new ItemStack(Material.DIRT, 64);
            items[1] = new ItemStack(Material.DIAMOND_BOOTS, 1);
            items[2] = new ItemStack(Material.DIAMOND, 6);
            items[3] = new ItemStack(Material.DIAMOND_BLOCK, 64);
            items[4] = new ItemStack(Material.GOLD_BLOCK, 32);
            items[5] = BootsOfLeaping.getGodBoots();

            contents = items;
        }

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for (int index = 0; index < startingIndex; index++) {
            for (int itemStacks = 9; itemStacks < 18; itemStacks++) {
                inv.setItem(itemStacks, contents[(itemStacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }
        // Customize
        ItemStack item = new ItemStack(Material.HOPPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "|");
        item.setItemMeta(meta);
        inv.setItem(4, item);
    }

    public void spin(final Player player) {
        Inventory inv = Bukkit.createInventory(null, 3 * 9, ChatColor.GOLD + "Good luck!");
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);

        Random r = new Random();
        double seconds = 1.0 + (20 - 1) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks =  0;
            boolean done = false;

            public void run() {
                if (done) return;
                ticks++;
                delay += (1 / 20 * seconds);
                if (ticks > delay * 10) {
                    ticks = 0;
                    for (int itemStacks = 9; itemStacks < 18; itemStacks++) {
                        inv.setItem(itemStacks, contents[(itemStacks + itemIndex) % contents.length]);
                    }
                    itemIndex++;

                    if (delay >= .5) {
                        done = true;
                        new BukkitRunnable() {
                            public void run() {
                                ItemStack item = inv.getItem(13);
                                if (player.getInventory().firstEmpty() == -1) {
                                    // Inv is full
                                    Location loc = player.getLocation();
                                    World world = player.getWorld();

                                    world.dropItemNaturally(loc, item);
                                    player.updateInventory();
                                    player.closeInventory();
                                    player.sendMessage(ChatColor.GOLD + "You won: " + item.getItemMeta().getDisplayName());
                                    cancel();
                                } else {
                                    player.getInventory().addItem(item);
                                    player.updateInventory();
                                    player.closeInventory();
                                    player.sendMessage(ChatColor.GOLD + "You won: " + item.getItemMeta().getDisplayName());
                                    cancel();
                                }
                            }
                        }.runTaskLater(Eka.getPlugin(Eka.class), 50);
                        cancel();
                    }
                }
            }

        }.runTaskTimer(plugin, 0, 1);
    }
}
