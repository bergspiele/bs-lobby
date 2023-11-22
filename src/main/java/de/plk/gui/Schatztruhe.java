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
public class Schatztruhe implements Listener {

    private IInventory inventory;

    private IInventoryManager inventoryManager;

    public Schatztruhe(IInventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;

        inventory = inventoryManager.createInventory("schatztruhe", "§8» §2Schatztruhe", 3 * 9, true);
        inventory.setListener(this);
    }

    public void initInventory(Player player) {
        inventory.getInventoryContents().clear();

        for (int i = 0; i < inventory.getInventorySize(); i++) {
            if (i == 11 || i == 13 || i == 15)
                continue;

            inventory.getInventoryContents().put(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").build());
        }

        inventory.getInventoryContents().put(11, new ItemBuilder(Material.NETHERITE_BOOTS)
                .setName("§8» §eSchuhe")
                .setLore(
                        "§8------------------------------",
                        "§7Kleide dich mich den richtigen",
                        "§6Schuhen §7ein. §8#notrademark",
                        "§8------------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("SCHUHE");
                }));

        inventory.getInventoryContents().put(13, new ItemBuilder(Material.NETHER_WART)
                .setName("§8» §6Effekte")
                .setLore(
                        "§8-----------------------",
                        "§7Verwende §6Effekte §7um",
                        "§fGefühle §7zu äußern.",
                        "§8------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("EFFEKTE");
                }));

        inventory.getInventoryContents().put(15, new ItemBuilder(Material.FISHING_ROD)
                .setName("§8» §5Gadgets")
                .setLore(
                        "§8--------------------------",
                        "§7Verwende §5Gadgets §7damit",
                        "§7du deine Langeweile",
                        "§7vertreiben kannst.",
                        "§8--------------------------"
                )
                .build((clickEvent) -> {
                    clickEvent.getWhoClicked().sendMessage("GADGETS");
                }));

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.LIGHT_GRAY_SHULKER_BOX) {
                initInventory(player);

                inventoryManager.openInventory(null, inventory);
            }
        }
    }

}
