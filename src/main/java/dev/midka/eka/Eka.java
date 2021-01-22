package dev.midka.eka;

import dev.midka.eka.commands.BootsOfLeaping;
import dev.midka.eka.commands.Doctor;
import dev.midka.eka.commands.Launch;
import org.bukkit.plugin.java.JavaPlugin;

public final class Eka extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("Launch").setExecutor(new Launch());
        this.getCommand("Doctor").setExecutor(new Doctor());
        this.getCommand("Godboots").setExecutor(new BootsOfLeaping());

        this.getServer().getPluginManager().registerEvents(new BootsOfLeaping(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
