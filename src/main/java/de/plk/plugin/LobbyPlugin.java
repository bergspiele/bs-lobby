package de.plk.plugin;

import de.plk.plugin.event.TablistChangeEvent;
import de.plk.plugin.listeners.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author SoftwareBuilds
 * @since 21.08.2023 22:36
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public final class LobbyPlugin extends JavaPlugin {

    private TeamTablistScoreboard scoreboard;

    @Override
    public void onEnable() {
        new PlayerConnectionListener(this);
        new PlayerInteractListener(this);
        new ItemListener(this);
        new PlayerMoveListener(this);
        new PlayerBlockListener(this);
        new ClickListener(this);
        new DamageListener(this);

        String[] elements = new String[] {
                "§a+++ §f§lNEUE SPIELMODUS: §a§lSKYWARS §a+++",
                "§a+++ §6§lNEUEs CLANSYSTEM §a+++",
                "§a+++ §e§lERKUNDE UNSERE NEUE LOBBY §a+++",
                "§a+++ §f§lNEUE SHOPITEMS: §3§lCITYBUILD §a+++"
        };

        AtomicInteger count = new AtomicInteger(0);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Bukkit.getOnlinePlayers().forEach((player -> {
                if (count.get() == elements.length)
                    count.set(0);

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                        elements[count.getAndIncrement()]
                ));
            }));
        }, 0L, 60L);
    }

    @Override
    public void onDisable() {

    }

    public TeamTablistScoreboard getScoreboard() {
        return scoreboard;
    }
}
