package dev.midka.eka;

import dev.midka.eka.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Eka extends JavaPlugin {

    // PluginManager
    PluginManager pm = Bukkit.getServer().getPluginManager();

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Commands
        this.getCommand("Launch").setExecutor(new Launch());
        this.getCommand("Doctor").setExecutor(new Doctor());
        this.getCommand("Godboots").setExecutor(new BootsOfLeaping());
        this.getCommand("DestinySpear").setExecutor(new SpearOfDestiny());
        this.getCommand("ChangeTeam").setExecutor(new ChangeTeam());

        // Listeners
        pm.registerEvents(new BootsOfLeaping(), this);
        pm.registerEvents(new SpearOfDestiny(), this);
        pm.registerEvents(new ChangeTeam(), this);

        // Create changeTeam GUI
        ChangeTeam.createInv();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
