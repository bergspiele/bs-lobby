package de.plk.gui;

import de.plk.Circle;
import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.spigot.inventory.item.ItemBuilder;
import de.plk.listeners.PlayerConnectionListener;
import de.plk.plugin.LobbyPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 21:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Einstellungen implements Listener {

    private IInventory inventory;

    private IInventoryManager inventoryManager;

    public Einstellungen(IInventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;

        inventory = inventoryManager.createInventory("einstellungen", "§8» §cEinstellungen", 3 * 9, true);
        inventory.setListener(this);
    }

    public void initInventory(Player player) {
        inventory.getInventoryContents().clear();

        for (int i = 0; i < inventory.getInventorySize(); i++) {
            if (i == 11 || i == 13 || i == 15)
                continue;

            inventory.getInventoryContents().put(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").build());
        }

        inventory.getInventoryContents().put(11, new ItemBuilder(Material.DIAMOND_BOOTS)
                .setName("§8» §bDoppelsprung")
                .setLore(
                        "§8§m------------------------------",
                        "§7Lege fest ob du §bDoppelsprünge",
                        "§aAktivieren §7oder §cDeaktivieren",
                        "§7möchtest.",
                        "§8§m------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("DOUBLEJUMP");
                }));

        inventory.getInventoryContents().put(13, new ItemBuilder(Material.GRINDSTONE)
                .setName("§8» §eAnimationen")
                .setLore(
                        "§8§m------------------------------",
                        "§7Lege fest ob du §eAnimationen",
                        "§aAktivieren §7oder §cDeaktivieren",
                        "§7möchtest.",
                        "§8§m------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("ANIMATION");
                }));

        inventory.getInventoryContents().put(15, new ItemBuilder(Material.MUSIC_DISC_STAL)
                .setName("§8» §cTöne")
                .setLore(
                        "§8§m------------------------------",
                        "§7Lege fest ob du §cTöne",
                        "§aAktivieren §7oder §cDeaktivieren",
                        "§7möchtest.",
                        "§8§m------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("SOUNDS");
                }));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.COMPARATOR) {
                initInventory(player);

                // TODO: set player
                inventoryManager.openInventory(null, inventory);
            }
        }
    }

}
