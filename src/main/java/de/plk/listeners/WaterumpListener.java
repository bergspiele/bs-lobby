package de.plk.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 21:00
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class WaterumpListener implements Listener {

    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        if (player.getLocation().getBlock().getType() == Material.WATER) {
            player.setVelocity(player.getLocation().getDirection().add(new Vector(0, 1.5F, 0)));
            player.playSound(player.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 5F, 5F);
        }
    }

}
