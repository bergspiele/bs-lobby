package de.plk.plugin.listeners;

import de.plk.plugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * @author SoftwareBuilds
 * @since 04.11.2023 00:08
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class PlayerInteractListener implements Listener {

    public PlayerInteractListener(LobbyPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final List<ActionvationClass> activationMap = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final Player player = event.getPlayer();

            if (GameMode.CREATIVE == player.getGameMode()) {
                event.setCancelled(false);
                return;
            }

            final ItemStack mainItem = player.getInventory().getItemInMainHand();

            if (mainItem == null) {
                return;
            }

            final ItemMeta mainMeta = mainItem.getItemMeta();

            if (mainMeta.getDisplayName().toLowerCase().contains("nick")) {
                boolean switching = switchtingActivation(new ActionvationClass(
                        "nick",
                        player.getUniqueId()
                ));

                ItemStack nick = new ItemStack(Material.NAME_TAG);
                ItemMeta nickMeta = nick.getItemMeta();
                nickMeta.setDisplayName("§5§lNick §7§l(" + (switching ? "§a§lAn" : "§c§lAus") + "§7§l)§r");
                nick.setItemMeta(nickMeta);

                player.getInventory().setItemInMainHand(nick);
            }
        }
    }

    private boolean switchtingActivation(ActionvationClass actionvationClass) {
        if (activationMap.contains(actionvationClass)) {
            activationMap.remove(actionvationClass);
            return false;
        }

        activationMap.add(actionvationClass);
        return true;
    }

    record ActionvationClass(
        String key,
        UUID uuid
    ) {}

}
