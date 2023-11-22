package de.plk.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 20:56
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class StickListener implements Listener {

    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player damager
                && event.getEntity() instanceof Player player
        ) {

            if (damager.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.CREATIVE) return;

            event.setCancelled(damager.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD
                    || player.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD);

            // Deaktiviere den Hit-Cooldown
            damager.setCooldown(damager.getItemInUse().getType(), 0);

            // Setze den Knockback z.B. inventiert den Knockback
            player.setVelocity(player.getLocation().getDirection().multiply(-1));

            event.setDamage(0);
        }
    }

}
