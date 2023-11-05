package de.plk.plugin.listeners;

import de.plk.plugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author SoftwareBuilds
 * @since 02.11.2023 23:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ItemListener implements Listener {

    public ItemListener(LobbyPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(GameMode.CREATIVE != event.getPlayer().getGameMode());
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        final Player player = (Player) event.getEntity();

        event.setCancelled(GameMode.CREATIVE != player.getGameMode());
    }

}
