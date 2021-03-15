package me.ehurturk.enchanter.main;

import me.ehurturk.enchanter.commands.EndCommand;
import me.ehurturk.enchanter.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Enchanter has initialized...");
        new StartCommand(this);
        new EndCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getServer().broadcastMessage(ChatColor.RED + "Enchanter is closing down...");
    }

}
