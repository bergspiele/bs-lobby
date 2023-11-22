package de.plk.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 20:58
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class DoublejumpListener implements Listener {

    @EventHandler
    public void onFly(final PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(1).add(new Vector(0, 0.5F, 0)));
        player.playSound(player.getLocation(), Sound.BLOCK_AZALEA_HIT, 5F, 5F);

        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;
        if (player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.AIR) return;

        player.setAllowFlight(true);
        player.setFlying(false);
    }

}
