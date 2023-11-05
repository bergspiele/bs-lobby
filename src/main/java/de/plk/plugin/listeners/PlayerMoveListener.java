package de.plk.plugin.listeners;

import de.plk.plugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author SoftwareBuilds
 * @since 04.11.2023 00:00
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class PlayerMoveListener implements Listener {

    public PlayerMoveListener(LobbyPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (player.getLocation().getZ() >= 30D) {
            ItemStack navigation = new ItemStack(Material.COMPASS);
            ItemMeta navigationMeta = navigation.getItemMeta();
            navigationMeta.setDisplayName("§c§lNavigation §7§l(Developer)§r");
            navigation.setItemMeta(navigationMeta);
            player.getInventory().setItem(4, navigation);
        } else if (player.getLocation().getZ() <= -30D) {
            ItemStack navigation = new ItemStack(Material.COMPASS);
            ItemMeta navigationMeta = navigation.getItemMeta();
            navigationMeta.setDisplayName("§c§lNavigation §7§l(Developer)§r");
            navigation.setItemMeta(navigationMeta);
            player.getInventory().setItem(4, navigation);
        } else {
            ItemStack navigation = new ItemStack(Material.COMPASS);
            ItemMeta navigationMeta = navigation.getItemMeta();
            navigationMeta.setDisplayName("§c§lNavigation §7§l(Spawn)§r");
            navigation.setItemMeta(navigationMeta);
            player.getInventory().setItem(4, navigation);
        }
    }

}
