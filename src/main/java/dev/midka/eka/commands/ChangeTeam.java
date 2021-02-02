package dev.midka.eka.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ChangeTeam implements CommandExecutor, Listener {
    //  GUI
    static public Inventory changeTeamInv;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("changeteam")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // open GUI
                player.openInventory(changeTeamInv);
                return true;
            } else {
                sender.sendMessage("Sorry, but i can't open GUI's for you");
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        // check if user in inside this gui
        if (!event.getInventory().equals(changeTeamInv)) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        ItemStack[] armor = player.getEquipment().getArmorContents();
        switch (event.getSlot()) {
            case 0:
                armor = changeColor(armor, Color.RED);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 1:
                armor = changeColor(armor, Color.YELLOW);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 2:
                armor = changeColor(armor, Color.GREEN);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 3:
                armor = changeColor(armor, Color.AQUA);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 4:
                armor = changeColor(armor, Color.BLUE);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 5:
                armor = changeColor(armor, Color.PURPLE);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 6:
                armor = changeColor(armor, Color.WHITE);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team!");
                player.closeInventory();
                break;
            case 8:
                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "Closed team changer.");
                break;
        }
    }

    public ItemStack[] changeColor(ItemStack[] a, Color color) {
        for (ItemStack item : a) {
            try {
                if (item.getType() == Material.LEATHER_BOOTS || item.getType() == Material.LEATHER_LEGGINGS ||
                        item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_HELMET) {
                    LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                    meta.setColor(color);
                    item.setItemMeta(meta);
                }
            } catch (Exception e) {
                // do nothing
            }
        }

        return a;
    }

    public static void createInv() {
        changeTeamInv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Select Team");

        ItemStack item = new ItemStack(Material.RED_CONCRETE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        // Setting lore
        lore.add(ChatColor.GRAY + "Click to join this team!");
        meta.setLore(lore);

        // Red
        meta.setDisplayName(ChatColor.DARK_RED + "Red team");
        item.setItemMeta(meta);
        changeTeamInv.setItem(0, item);

        // Yellow
        meta.setDisplayName(ChatColor.GOLD + "Yellow team");
        item.setType(Material.YELLOW_CONCRETE);
        item.setItemMeta(meta);
        changeTeamInv.setItem(1, item);

        // Green
        meta.setDisplayName(ChatColor.DARK_GREEN + "Green team");
        item.setType(Material.GREEN_CONCRETE);
        item.setItemMeta(meta);
        changeTeamInv.setItem(2, item);

        // Aqua
        meta.setDisplayName(ChatColor.DARK_AQUA + "Cyan team");
        item.setType(Material.CYAN_CONCRETE);
        item.setItemMeta(meta);
        changeTeamInv.setItem(3, item);

        // Blue
        meta.setDisplayName(ChatColor.DARK_BLUE + "Blue team");
        item.setType(Material.BLUE_CONCRETE);
        item.setItemMeta(meta);
        changeTeamInv.setItem(4, item);

        // Purple
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Purple team");
        item.setType(Material.MAGENTA_CONCRETE);
        item.setItemMeta(meta);
        changeTeamInv.setItem(5, item);

        // White
        meta.setDisplayName(ChatColor.WHITE + "White team");
        item.setType(Material.WHITE_CONCRETE);
        item.setItemMeta(meta);
        changeTeamInv.setItem(6, item);

        // Cancel
        meta.setDisplayName(ChatColor.RED + "Cancel");
        item.setType(Material.BARRIER);
        item.setItemMeta(meta);
        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);
        changeTeamInv.setItem(8, item);
    }
}
