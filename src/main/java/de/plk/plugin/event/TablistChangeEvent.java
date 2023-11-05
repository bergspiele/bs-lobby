package de.plk.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

/**
 * @author SoftwareBuilds
 * @since 02.11.2023 22:34
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class TablistChangeEvent {

    public TablistChangeEvent() {
        executeTablistChange();
    }

    private void executeTablistChange() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setPlayerListHeader(getHeaderString(Bukkit.getOnlinePlayers().size()));
        });
    }

    private String getHeaderString(int playerCount) {
        return new StringBuilder()
                .append("\n")
                .append("§3§lB§b§lERG§3§lS§b§lPIELE")
                .append("\n")
                .append("\n")
                .append("§a")
                .append(playerCount)
                .append("§7/")
                .append(Bukkit.getMaxPlayers())
                .append("\n")
                .toString();
    }
}
