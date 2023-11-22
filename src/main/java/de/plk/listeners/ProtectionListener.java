package de.plk.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 15:08
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ProtectionListener implements Listener {


    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(!(event.getPlayer().getGameMode() == GameMode.CREATIVE));
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setDamage(0);
    }

    @EventHandler
    public void onDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onHelp(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0];

        if (Bukkit.getServer().getHelpMap().getHelpTopic(command) == null) {
            event.getPlayer().sendMessage("§7[§3§lC§b§lORE§7] Der eingegebende §3Command §7wurde nicht gefunden.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        Location spawn = new Location(player.getWorld(), 88, 24, -1);
        if (player.getLocation().distance(spawn) > 250) {
            player.teleport(spawn);
            player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON,  5F, 5F);
        }
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        event.setFormat(player.getDisplayName() + "§7: §r" + event.getMessage());
    }

}
