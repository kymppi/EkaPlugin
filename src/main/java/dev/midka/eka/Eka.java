package dev.midka.eka;

import dev.midka.eka.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Eka extends JavaPlugin {

    // Plugin
    public static Eka instance;

    // PluginManager
    PluginManager pm = Bukkit.getServer().getPluginManager();

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Instance
        setInstance(this);

        // Commands
        this.getCommand("Launch").setExecutor(new Launch());
        this.getCommand("Doctor").setExecutor(new Doctor());
        this.getCommand("Godboots").setExecutor(new BootsOfLeaping());
        this.getCommand("DestinySpear").setExecutor(new SpearOfDestiny());
        this.getCommand("ChangeTeam").setExecutor(new ChangeTeam());
        this.getCommand("Gamble").setExecutor(new Gamble());
        this.getCommand("Gmc").setExecutor(new Game());

        // Listeners
        pm.registerEvents(new BootsOfLeaping(), this);
        pm.registerEvents(new SpearOfDestiny(), this);
        pm.registerEvents(new ChangeTeam(), this);
        pm.registerEvents(new Gamble(), this);

        // Create changeTeam GUI
        ChangeTeam.createInv();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Eka getInstance() {
        return instance;
    }

    public void setInstance(Eka instance) {
        this.instance = instance;
    }
}
