package de.plk.plugin.listeners;

import de.plk.plugin.LobbyPlugin;
import de.plk.plugin.event.TablistChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/**
 * @author SoftwareBuilds
 * @since 01.11.2023 21:01
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class PlayerConnectionListener implements Listener {

    private final LobbyPlugin plugin;

    public PlayerConnectionListener(LobbyPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private String getHeaderString(int playerCount) {
        return new StringBuilder()
                .append("\n")
                .append("§3§lB§b§lERG§3§lS§b§lPIELE")
                .append("\n")
                .append("\n")
                .append("§a")
                .append(playerCount)
                .append("§7/")
                .append(Bukkit.getMaxPlayers())
                .append("\n")
                .toString();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
       Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
           System.out.println(Bukkit.getServer().getOnlinePlayers().size());
           Bukkit.getServer().getOnlinePlayers().forEach(player -> {
               player.setPlayerListHeader(getHeaderString(Bukkit.getServer().getOnlinePlayers().size()));
           });
       }, 1L);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        System.out.println(Bukkit.getServer().getOnlinePlayers().size());

        Bukkit.getServer().getOnlinePlayers().forEach(oPlayer -> {
            oPlayer.setPlayerListHeader(getHeaderString(Bukkit.getServer().getOnlinePlayers().size()));
        });

        event.setJoinMessage(null);

        Inventory inventory = player.getInventory();

        ItemStack lobbySwitcher = new ItemStack(Material.NETHER_STAR);
        ItemMeta lobbySwitcherMeta = lobbySwitcher.getItemMeta();
        lobbySwitcherMeta.setDisplayName("§b§lLobby §7§l(lobby-1)§r");
        lobbySwitcher.setItemMeta(lobbySwitcherMeta);

        ItemStack shulkerBox = new ItemStack(Material.SHULKER_BOX);
        ItemMeta shulkerBoxMeta = shulkerBox.getItemMeta();
        shulkerBoxMeta.setDisplayName("§2§lSchatztruhe§l");
        shulkerBox.setItemMeta(shulkerBoxMeta);

        ItemStack navigation = new ItemStack(Material.COMPASS);
        ItemMeta navigationMeta = navigation.getItemMeta();
        navigationMeta.setDisplayName("§c§lNavigation §7§l(Spawn)§r");
        navigation.setItemMeta(navigationMeta);

        ItemStack repeater = new ItemStack(Material.REPEATER);
        ItemMeta repeaterMeta = repeater.getItemMeta();
        repeaterMeta.setDisplayName("§9§lEinstellungen");
        repeater.setItemMeta(repeaterMeta);

        ItemStack nick = new ItemStack(Material.NAME_TAG);
        ItemMeta nickMeta = nick.getItemMeta();
        nickMeta.setDisplayName("§5§lNick §7§l(§c§lAus§7§l)§r");
        nick.setItemMeta(nickMeta);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName("§6§lProfil§r");
        head.setItemMeta(headMeta);

        inventory.setItem(0, lobbySwitcher);
        inventory.setItem(2, shulkerBox);
        inventory.setItem(4, navigation);
        inventory.setItem(6, repeater);
        inventory.setItem(7, nick);
        inventory.setItem(8, head);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = null;

        if (scoreboard.getObjective("sangria") == null) {
            objective = scoreboard.registerNewObjective("sangria" , Criteria.DUMMY, "§3§lB§b§lERG§3§lS§b§lPIELE§r");
        } else objective = scoreboard.getObjective("sangria");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore(ChatColor.BLUE.toString()).setScore(11);
        objective.getScore("§f§lROLLE§r").setScore(10);
        objective.getScore("§4Owner§r").setScore(9);
        objective.getScore(ChatColor.YELLOW.toString()).setScore(8);
        objective.getScore("§f§lFREUNDE§r").setScore(7);
        objective.getScore("§a0§7/15§r").setScore(6);
        objective.getScore(ChatColor.GOLD.toString()).setScore(5);
        objective.getScore("§f§lSPIELZEIT§r").setScore(4);
        objective.getScore("§620h 32m§r").setScore(3);
        objective.getScore(ChatColor.RED.toString()).setScore(2);
        objective.getScore("§f§lCOINS§r").setScore(1);
        objective.getScore("§e2.000§r").setScore(0);

        player.setScoreboard(scoreboard);

        player.setPlayerListFooter("\n§7Füge deine Freunde hinzu, erstelle Partys oder gründe\ndeinen eigenen Clan mit: §2/freund§7, §d/party§7, §6/clan\n");

        new TeamTablistScoreboard();
    }

}
