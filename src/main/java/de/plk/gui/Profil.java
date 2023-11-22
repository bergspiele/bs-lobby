package de.plk.gui;

import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.spigot.inventory.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 21:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Profil implements Listener {

    private IInventory inventory;

    private IInventoryManager inventoryManager;

    public Profil(IInventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;

        inventory = inventoryManager.createInventory("profile", "§8» §6Mein Profil", 3 * 9, true);
        inventory.setListener(this);
    }

    public void initInventory(Player player) {
        inventory.getInventoryContents().clear();

        for (int i = 0; i < inventory.getInventorySize(); i++) {
            if (i == 11 || i == 13 || i == 15)
                continue;

            inventory.getInventoryContents().put(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").build());
        }

        inventory.getInventoryContents().put(11, new ItemBuilder(Material.BOOK)
                .setName("§8» §5Status")
                .setLore(
                        "§8-----------------------------------",
                        "§7Setze deinen persänlichen §5Status.",
                        "§7Andere Spieler kännen Ihn unter",
                        "§6Nametag §7sehen.",
                        "§8-----------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("STATUS");
                }));

        inventory.getInventoryContents().put(13, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§8» §6Freunde")
                .setLore(
                        "§8----------------------------------------",
                        "§7Verwalte deine §fFreundesliste §7hier.",
                        "§7(§fFreunde§7, §fAnfragen§7, §fParties§7)",
                        "§8----------------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("FREUNDE");
                }));

        inventory.getInventoryContents().put(15, new ItemBuilder(Material.DRAGON_HEAD)
                .setName("§8» §8Skin")
                .setLore(
                        "§8---------------------------------",
                        "§7Setze dir deinen §8 §7aus unserer",
                        "§fSmash §7auswahl für dich.",
                        "§8---------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("SKIN");
                }));

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.PLAYER_HEAD) {
                initInventory(player);

                // TODO: set player
                inventoryManager.openInventory(null, inventory);
            }
        }
    }

}
