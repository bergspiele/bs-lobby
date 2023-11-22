package de.plk.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 15:00
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ItemHeldListener implements Listener {

    @EventHandler
    public void onHandle(PlayerItemHeldEvent event) {
        final Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 2F, 2F);
    }

}
