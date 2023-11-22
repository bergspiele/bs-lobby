package de.plk.plugin;

import de.plk.BlockColorRing;
import de.plk.Quadrat;
import de.plk.Tablist;
import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.hologram.IHologram;
import de.plk.core.api.spigot.hologram.IHologramManager;
import de.plk.core.api.task.repeat.IRepeatingTask;
import de.plk.gui.Einstellungen;
import de.plk.gui.Navigation;
import de.plk.gui.Profil;
import de.plk.gui.Schatztruhe;
import de.plk.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

/**
 * @author SoftwareBuilds
 * @since 21.08.2023 22:36
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public final class LobbyPlugin extends AbstractVersatileSpigot {

    @Override
    public void onEnable() {
        super.onEnable();

        IRepeatingTask task = getInstance().getTaskManager().createRepeatingTask("test", 0, 80);
        task.taskExecutionContent((t) -> {
            Location spawn = new Location(Bukkit.getWorld("world"), 88.5, 23.5, -1.5);
            Location smash = new Location(Bukkit.getWorld("world"), 103.5, 18, -8.5);
            Location skywars = new Location(Bukkit.getWorld("world"), 65.5, 12, -54.5);
            Location bedwars = new Location(Bukkit.getWorld("world"), 42.5, 20, 22.5);
            Location kit1o1 = new Location(Bukkit.getWorld("world"), 87.5, 21, -52.5);
            Location other = new Location(Bukkit.getWorld("world"), 96.5, 17, -32.5);
            Location chest = new Location(Bukkit.getWorld("world"), 63.5, 16, 30.5);

            Bukkit.getOnlinePlayers().forEach((p) -> {
                new Quadrat(p, spawn, Particle.SPELL_WITCH).send();
                new Quadrat(p, smash, Particle.SPELL_WITCH).send();
                new Quadrat(p, skywars, Particle.SPELL_WITCH).send();
                new Quadrat(p, bedwars, Particle.SPELL_WITCH).send();
                new Quadrat(p, kit1o1, Particle.SPELL_WITCH).send();
                new Quadrat(p, other, Particle.SPELL_WITCH).send();
                new Quadrat(p, chest, Particle.SPELL_WITCH).send();
            });
        });

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Location smash = new Location(Bukkit.getWorld("world"), 103, 17, -9);
            Location other = new Location(Bukkit.getWorld("world"), 96, 16, -33);
            Location skywars = new Location(Bukkit.getWorld("world"), 65, 11, -55);
            Location bedwars = new Location(Bukkit.getWorld("world"), 42, 19, 22);
            Location kit1o1 = new Location(Bukkit.getWorld("world"), 87, 20, -53);

            new BlockColorRing(smash).execute();
            new BlockColorRing(other).execute();
            new BlockColorRing(skywars).execute();
            new BlockColorRing(bedwars).execute();
            new BlockColorRing(kit1o1).execute();
        }, 0, 20 * 3);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            new Tablist().update(onlinePlayer);
        }

        IHologramManager hologramManager = getInstance().getHologramManager();
        IHologram hologram = hologramManager.createHologram(
                "holo-one",
                new String[]{
                        "§7The versatile minecraft network...",
                        "§3§lB§b§lERG§3§lS§b§lPIELE"
                },
                new Location(Bukkit.getWorld("world"), 88.5, 23.5, -1.5),
                false
        );

        hologram.setHeadItem(new ItemStack(Material.NETHER_STAR));

        IHologram instagram = hologramManager.createHologram(
                "holo-instagram",
                new String[]{
                        "§7Folge uns auf Instagram!",
                        "§e@bergspiele"
                },
                new Location(Bukkit.getWorld("world"), 97.5, 17.5, -37.5),
                true
        );

        IHologram chest = hologramManager.createHologram(
                "holo-chest",
                new String[]{
                        "§7Gewinne noch heute den Jackpot!",
                        "§fJackpot: §e20.000 §fBergtaler."
                },
                new Location(Bukkit.getWorld("world"), 63.5, 16, 30.5),
                true
        );

        chest.setHeadItem(new ItemStack(Material.GOLD_NUGGET));

        IHologram youtube = hologramManager.createHologram(
                "holo-youtube",
                new String[]{
                        "§7Folge uns auf YouTube!",
                        "§c@bergspiele"
                },
                new Location(Bukkit.getWorld("world"), 99.5, 17.5, -36.5),
                true
        );

        IRepeatingTask boardTime = getInstance().getTaskManager().createRepeatingTask("board-sched-time", 0, 20);
        boardTime.taskExecutionContent((r) -> {
            Bukkit.getOnlinePlayers().forEach((player -> {
                // TODO: fixing player
                Optional<IScoreboard> iScoreboard = getInstance().getScoreboardManager().getScoreboardByPlayer(null);
                if (iScoreboard.isPresent()) {
                    Scoreboard scoreboard = player.getScoreboard();
                    Objective objective = scoreboard.getObjective("xxx");
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));

                    if (objective != null)
                        objective.setDisplayName("§3§lB§b§lERG§3§lS§b§lPIELE §7(" + dateFormat.format(date) + "§7)");
                }
            }));
        });
        boardTime.start();

        IHologram smash = hologramManager.createHologram(
                "holo-smash",
                new String[]{
                        "§8■ §b§lSMASH §8■"
                },
                new Location(Bukkit.getWorld("world"), 105.8, 17.65, -8.5),
                false
        );

        smash.setHeadItem(new ItemStack(Material.RED_MUSHROOM_BLOCK));

        hologramManager.summon(smash);

        IHologram skywars = hologramManager.createHologram(
                "holo-sykwars",
                new String[]{
                        "§8■ §2§lSKYWARS §8■"
                },
                new Location(Bukkit.getWorld("world"), 65.5, 11.65, -56.8),
                false
        );

        skywars.setHeadItem(new ItemStack(Material.GRASS_BLOCK));

        hologramManager.summon(skywars);

        IHologram bedwars = hologramManager.createHologram(
                "holo-bedwars",
                new String[]{
                        "§8■ §e§lBEDWARS §8■"
                },
                new Location(Bukkit.getWorld("world"), 42.5, 19.65, 24.8),
                false
        );

        bedwars.setHeadItem(new ItemStack(Material.RED_BED));

        hologramManager.summon(bedwars);

        IHologram kit1o1 = hologramManager.createHologram(
                "holo-kit1o1",
                new String[]{
                        "§8■ §b§lKIT1o1 §8■"
                },
                new Location(Bukkit.getWorld("world"), 89.8, 20.65, -52.5),
                false
        );

        kit1o1.setHeadItem(new ItemStack(Material.NETHERITE_SWORD));

        hologramManager.summon(kit1o1);
        hologramManager.summon(hologram);
        hologramManager.summon(chest);
        hologramManager.summon(instagram);
        hologramManager.summon(youtube);

        task.start();

        Bukkit.getPluginManager().registerEvents(new ItemHeldListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(getInstance().getScoreboardManager()), this);
        Bukkit.getPluginManager().registerEvents(new WaterumpListener(), this);
        Bukkit.getPluginManager().registerEvents(new DoublejumpListener(), this);
        Bukkit.getPluginManager().registerEvents(new StickListener(), this);

        new Einstellungen(getInstance().getInventoryManager());
        new Navigation(getInstance().getInventoryManager(), getInstance().getTaskManager());
        new Profil(getInstance().getInventoryManager());
        new Schatztruhe(getInstance().getInventoryManager());

        getInstance().getInventoryManager().registerAllListeners();

        Bukkit.getPluginManager().registerEvents(new ProtectionListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        Bukkit.getWorld("world").getEntitiesByClass(ArmorStand.class).forEach(Entity::remove);
    }
}
