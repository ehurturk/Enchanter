package me.ehurturk.enchanter.commands;

import me.ehurturk.enchanter.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EndCommand implements CommandExecutor {

    private Main plugin;

    public EndCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("endenchanter").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        p.getServer().broadcastMessage(ChatColor.RED+"Enchanter plugin is now stopping...");
        p.getServer().getScheduler().cancelTasks(this.plugin);
        return false;
    }
}
