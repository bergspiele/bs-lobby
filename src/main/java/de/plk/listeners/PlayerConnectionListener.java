package de.plk.listeners;

import de.plk.Circle;
import de.plk.Tablist;
import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.board.IScoreboardManager;
import de.plk.core.api.spigot.board.team.ITeamBuilder;
import de.plk.core.api.spigot.board.team.TeamIdentifierFilter;
import de.plk.core.api.spigot.inventory.item.ItemBuilder;
import de.plk.core.api.task.ITaskManager;
import de.plk.core.api.task.delayed.IDelayedTask;
import de.plk.core.api.task.repeat.IRepeatingTask;
import de.plk.plugin.LobbyPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.util.Vector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 15:13
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class PlayerConnectionListener implements Listener {

    private IScoreboardManager scoreboardManager;

    public PlayerConnectionListener(IScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }

    public static void setJoinItems(Player player) {
        final PlayerInventory inventory = player.getInventory();

        inventory.clear();

        inventory.setItem(0, new ItemBuilder(Material.NETHER_STAR).setLore(
                "§8--------------------------",
                "§7Wechsel §7zwischen",
                "§7verschiedenen §fLobbies§7.",
                "§8--------------------------"
        ).setName("§8» §6Lobby §7(Lobby-1)").build().getItemStack());

        inventory.setItem(2, new ItemBuilder(Material.LIGHT_GRAY_SHULKER_BOX).setLore(
                "§8------------------------",
                "§fGeschenke §7und coolen",
                "§fStuff §7findest du hier.",
                "§8------------------------"
        ).setName("§8» §2Schatztruhe").build().getItemStack());

        inventory.setItem(4, new ItemBuilder(Material.COMPASS).setLore(
                "§8-------------------------------------",
                "§7Springe direkt zu unseren §fAngeboten",
                "§7(§2Skywars§7, §dSmash§7, etc...)",
                "§8-------------------------------------"
        ).setName("§8» §eNavigation").build().getItemStack());

        inventory.setItem(6, new ItemBuilder(Material.COMPARATOR).setLore(
                "§8---------------------------",
                "§7Stelle verschiedene §fLobby",
                "§fAttribute §7hier ein.",
                "§8---------------------------"
        ).setName("§8» §cEinstellungen").build().getItemStack());

        inventory.setItem(7, new ItemBuilder(Material.NAME_TAG).setLore(
                "§8----------------------------------",
                "§7Du mächtest dich §5Nicken§7?",
                "§7Kein Problem, hiermit §aaktivierst",
                "§7oder §cdeaktiverst §7du es.",
                "§8----------------------------------"
        ).setName("§8» §5Nick §7(§cX§7)").build().getItemStack());

        inventory.setItem(8, new ItemBuilder(Material.PLAYER_HEAD).setLore(
                "§8----------------------------------",
                "§7Verwalte §fFreunde§7, §fParties",
                "§7und deinen §fStatus §7direkt hier.",
                "§8----------------------------------"
        ).setName("§8» §6Mein Profil").build().getItemStack());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        setJoinItems(player);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));

        IScoreboard scoreboard = scoreboardManager.createScoreboard("lobby", DisplaySlot.SIDEBAR,
                "§3§lB§b§lERG§3§lS§b§lPIELE §7(" + dateFormat.format(date) + "§7)"
        );

        ITeamBuilder teamBuilder = scoreboard.getTeamBuilder();
        teamBuilder.setPrefix("§7➥ ");
        teamBuilder.setIdentifier("team-rank");
        teamBuilder.setEntry(ChatColor.DARK_RED);
        teamBuilder.setUpdatedString("%s", player.isOp() ? "§4§lOwner" : "§7§lSpieler");

        scoreboard.addRow(14, () -> "§r§8----------------------");
        scoreboard.addRow(13, ChatColor.DARK_PURPLE::toString);
        scoreboard.addRow(12, () -> "§7■ Gruppe");
        scoreboard.addRow(11, teamBuilder.build());
        scoreboard.addRow(10, ChatColor.GRAY::toString);
        scoreboard.addRow(9, () -> "§7■ Bergtaler");
        scoreboard.addRow(8, () -> "§7➥ §e20.000");
        scoreboard.addRow(7, ChatColor.BLUE::toString);
        scoreboard.addRow(6, () -> "§7■ Bergpass");
        scoreboard.addRow(5, () -> "§7➥ §cKein Monatspass §8/ §c✖");
        scoreboard.addRow(4, ChatColor.YELLOW::toString);
        scoreboard.addRow(3, () -> "§7■ Level §8(§e1§8)");
        scoreboard.addRow(2, () -> "§7➥ §e▊§7▊▊▊▊▊▊▊▊▊  §e10%");
        scoreboard.addRow(1, ChatColor.RED::toString);
        scoreboard.addRow(0, () -> "§8----------------------");

        // TODO fixing player.
        scoreboardManager.sendScoreboard(null, scoreboard);

        // TODO: fixing team update
        // scoreboard.getScoreboardTeam(new TeamIdentifierFilter("team-rank")).get().update("%s", player.isOp() ? "§4§lOwner" : "§7§lSpieler");

        event.setJoinMessage(null);

        Bukkit.getOnlinePlayers().forEach(op -> {
            new Tablist().update(op);
            op.setPlayerListHeader("\n§3§lB§b§lERG§3§lS§b§lPIELE\n\n§a" + Bukkit.getOnlinePlayers().size() + "§7/50\n\n");
        });

        player.setPlayerListFooter("\n§7Füge deine Freunde hinzu oder erstelle Parties:\n§a/friend§7, §d/party\n");


        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 66));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 66));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 66));

        player.getWorld().strikeLightningEffect(player.getLocation()).getLocation();
        player.setVelocity(new Vector(0, 3.5F, 0));

        player.spawnParticle(Particle.MOB_APPEARANCE, player.getLocation(), 1);

        player.sendTitle(" §3§lB§b§lERG§3§lS§b§lPIELE", "§7The versatile minecraft network...", 1, 20, 1);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(LobbyPlugin.class), () -> {
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 10F, 10F);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.removePotionEffect(PotionEffectType.SLOW);
            player.removePotionEffect(PotionEffectType.CONFUSION);
            player.teleport(new Location(player.getWorld(), 88.5, 24, -1.5, 135.5F, 2.3F));
        }, 20);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(LobbyPlugin.class), () -> {
            new Circle(player, player.getLocation(), Particle.SPELL_WITCH).send();
        }, 20);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(LobbyPlugin.class), () -> {
            Bukkit.getOnlinePlayers().forEach(op -> {
                op.setPlayerListHeader("\n§3§lB§b§lERG§3§lS§b§lPIELE\n\n§a" + Bukkit.getOnlinePlayers().size() + "§7/50\n\n");
                new Tablist().update(op);
            });
        }, 2L);

        event.setQuitMessage(null);
    }
}
