package de.plk.plugin.listeners;

import de.plk.plugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author SoftwareBuilds
 * @since 02.11.2023 23:38
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class PlayerBlockListener implements Listener  {

    public PlayerBlockListener(LobbyPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(GameMode.CREATIVE != event.getPlayer().getGameMode());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(GameMode.CREATIVE != event.getPlayer().getGameMode());
    }

}
