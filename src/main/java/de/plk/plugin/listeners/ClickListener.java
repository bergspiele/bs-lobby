package de.plk.plugin.listeners;

import de.plk.plugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author SoftwareBuilds
 * @since 02.11.2023 23:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ClickListener implements Listener {

    public ClickListener(LobbyPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        final Player player = (Player) event.getWhoClicked();
        event.setCancelled(GameMode.CREATIVE != player.getGameMode());
    }

}
