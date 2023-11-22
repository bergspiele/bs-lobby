package de.plk.gui;

import de.plk.Circle;
import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.spigot.inventory.item.ItemBuilder;
import de.plk.core.api.task.ITaskManager;
import de.plk.core.api.task.delayed.IDelayedTask;
import de.plk.plugin.LobbyPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Objects;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 21:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Navigation implements Listener {

    private IInventory inventory;

    private IInventoryManager inventoryManager;

    private ITaskManager taskManager;

    public Navigation(IInventoryManager inventoryManager, ITaskManager taskManager) {
        this.inventoryManager = inventoryManager;
        this.taskManager = taskManager;

        inventory = inventoryManager.createInventory("navigation", "§8» §eNavigation", 6 * 9, true);
        inventory.setListener(this);
    }

    public void initInventory(Player player) {
        inventory.getInventoryContents().clear();

        for (int i = 0; i < inventory.getInventorySize(); i++) {
            if (i == 4 || i == 11 || i == 15 || i == 28 || i == 31 || i == 34 || i == 49)
                continue;

            inventory.getInventoryContents().put(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").build());
        }

        inventory.getInventoryContents().put(4, new ItemBuilder(Material.MAGMA_CREAM)
                .setName("§8» §fSpawn")
                .setLore(
                        "§8---------------------------",
                        "§7Kehre zum §fSpawn §7zurück.",
                        "§8---------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 88.5, 24, -1.5, 135.5F, 2.3F), "§f§lSPAWN");
                }));

        inventory.getInventoryContents().put(11, new ItemBuilder(Material.NETHERITE_SWORD)
                .setName("§8» §bKit1o1")
                .setLore(
                        "§8------------------------------------",
                        "§7Wähle ein §fKit §7und vernichte dein",
                        "§7Gegener in einem §f1o1 §7kampf.",
                        "§8------------------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 87.5, 21, -52.5, -90F, 2.5F), "§b§lKIT1o1");
                }));

        inventory.getInventoryContents().put(15, new ItemBuilder(Material.RED_MUSHROOM_BLOCK)
                .setName("§8» §bSmash")
                .setLore(
                        "§8--------------------------------------",
                        "§7Wähle eine §fSuperkraft §7und verbanne",
                        "§7dein §fGegner §7von der Karte, §fItems",
                        "§7und deine §fFähigkeiten §7unterstützen",
                        "§7dich dabei.",
                        "§8--------------------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 103.5, 18, -8.5, -90F, 2.5F), "§b§lSMASH");
                }));

        inventory.getInventoryContents().put(28, new ItemBuilder(Material.GRASS_BLOCK)
                .setName("§8» §2SkyWars")
                .setLore(
                        "§8------------------------------------",
                        "§7Sammel §fItems §7und wähle ein §fKit",
                        "§7aus. Vernichte §fGegner §7auf §7von",
                        "§7anderen §ffliegenden Inseln§7.",
                        "§8------------------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 65.5, 12, -54.5, -179.5F, 2.5F), "§2§lSKYWARS");
                }));

        inventory.getInventoryContents().put(31, new ItemBuilder(Material.BEACON)
                .setName("§8» §6CityBuild")
                .setLore(
                        "§8------------------------------------",
                        "§7Gründe eine §fHandelsmachst §7und",
                        "§7baue dir deine eigene §fGilde §7auf.",
                        "§fBaue §7und §fTausche §7gemeinsam mit",
                        "§7anderen §fGildenparteien§7.",
                        "§8------------------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 96.5, 17, -32.5, 179.5F, 2.5F), "§6§lCITYBUILD");
                }));

        inventory.getInventoryContents().put(34, new ItemBuilder(Material.RED_BED)
                .setName("§8» §eBedWars")
                .setLore(
                        "§8------------------------------------",
                        "§7Kaufe §fItems §7und zerstäre das",
                        "§fGegenerische Bett§7, pass auf",
                        "§7das es nicht dein §fBett §7erwischt.",
                        "§8------------------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 42.5, 22, 22.5, 0, 2.5F), "§e§lBEDWARS");
                }));

        inventory.getInventoryContents().put(49, new ItemBuilder(Material.STICK)
                .setName("§8» §5Knocki")
                .setLore(
                        "§8----------------------------------",
                        "§7Schlage dich mit anderen auf einer",
                        "§fKarte§7, fall aber nicht herunter.",
                        "§8----------------------------------"
                )
                .build((clickEvent) -> {
                    teleport((Player) clickEvent.getWhoClicked(), new Location(player.getWorld(), 96.5, 17, -32.5, 179.5F, 2.5F), "§5§lKNOCKI");
                }));

    }

    public void teleport(Player player, Location location, String title) {
        player.closeInventory();

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 66));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 66));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 66));

        player.getWorld().strikeLightningEffect(player.getLocation()).getLocation();
        player.setVelocity(new Vector(0, 3.5F, 0));

        player.spawnParticle(Particle.MOB_APPEARANCE, player.getLocation(), 1);

        player.sendTitle(title, "§7A versatile product...", 1, 20, 1);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(LobbyPlugin.class), () -> {
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 10F, 10F);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.removePotionEffect(PotionEffectType.SLOW);
            player.removePotionEffect(PotionEffectType.CONFUSION);
            player.teleport(location);
        }, 20);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(LobbyPlugin.class), () -> {
            new Circle(player, player.getLocation(), Particle.SPELL_WITCH).send();
        }, 20);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (Objects.equals(event.getHand(), EquipmentSlot.OFF_HAND)) return;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                initInventory(player);

                // TODO: set player
                inventoryManager.openInventory(null, inventory);
            }
        }
    }

}
