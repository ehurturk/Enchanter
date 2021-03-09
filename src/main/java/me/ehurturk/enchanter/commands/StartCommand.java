package me.ehurturk.enchanter.commands;

import me.ehurturk.enchanter.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StartCommand implements CommandExecutor {

    private final Main plugin;
    private final Random rand = new Random();
    private int task1, task2;


    public StartCommand(Main plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("enchanter").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("enchanter")) {
            Bukkit.getServer().broadcastMessage("Enchanter Plugin made by ehurturk");
        }
        else {
            return false;
        }
        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Enchanter plugin is now enabled.");

        Player[] players = getPlayers(args);


        BukkitScheduler sched = send.getServer().getScheduler();

        Bukkit.getServer().broadcastMessage("Game is starting...");

        task1 = sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            int num = 10;

            @Override
            public void run() {
                if (num!=0) {
                    send.getServer().broadcastMessage("Game starting in: "+ num--);
                }
                else {
                    send.getServer().broadcastMessage("Go!");
                    Bukkit.getScheduler().cancelTask(task1);
                }
            }}, 20L, 20L); // countdown scheduler

        sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {

            @Override
            public void run() {
                task2 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {

                    int num = 10;

                    @Override
                    public void run() {
                        if (num==0) {
                            // add an enchantment to a random item in player inventory
                            addEnchantments(players);
                            Bukkit.getScheduler().cancelTask(task2);
                        }
                        else {
                            Bukkit.getServer().broadcastMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Enchanting in "+num--);


                        }
                    }
                }, 0L, 20L); // countdown scheduler
            }
        }, 1220L, 1200L);

        return true;
    }

    private Player[] getPlayers(String[] args) {
        Player[] players = new Player[args.length];

        for (int i = 0; i < args.length; i++) {
            players[i] = Bukkit.getServer().getPlayer(args[i]); // assign each name in args to playerArray
        }

        return players;
    }

    private void addEnchantments(Player[] players) {

        Enchantment[] allEnchants = Enchantment.values();
        int maxEnchantLevel = 10;

        for (Player p: players) {
            ItemStack[] pInventory = p.getInventory().getContents(); // ind items may be null

            int rEnchantment = rand.nextInt(allEnchants.length);
            int rLevel = rand.nextInt(maxEnchantLevel);
            Bukkit.getServer().broadcastMessage("Enchanting now!");
            ArrayList<ItemStack> inventory = new ArrayList<>();
            for (ItemStack item: pInventory)
            {
                if (item != null) {
                    inventory.add(item);
                }
            }
            if (inventory.size() == 0) {
                continue;
            }
            int rItem = rand.nextInt(inventory.size());
            ItemStack item = inventory.get(rItem);

            Bukkit.getServer().broadcastMessage("Enchanting Item: "+ item.toString() + " Enchanting: " + allEnchants[rEnchantment].toString());

            item.addUnsafeEnchantment(allEnchants[rEnchantment], rLevel); // pInventory[rItem] is null??

        }
    }

}
