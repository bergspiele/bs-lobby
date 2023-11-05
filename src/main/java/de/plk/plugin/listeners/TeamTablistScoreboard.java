package de.plk.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 04.11.2023 19:57
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class TeamTablistScoreboard {

    public TeamTablistScoreboard() {
        Bukkit.getOnlinePlayers().forEach(online -> {
            Team ops = createTeam(online, 1, "§4O §6- ", ChatColor.DARK_RED);
            Team players = createTeam(online, 0, "§7P §7- ", ChatColor.GRAY);

            Bukkit.getOnlinePlayers().forEach((target) -> {
                if (target.isOp()) {
                    ops.addEntry(target.getName());
                } else {
                    players.addEntry(target.getName());
                }
            });
        });
    }

    private Team createTeam(Player player, int rank, String prefix, ChatColor color) {
        String teamName = new StringBuilder()
                .append(rank)
                .append("_")
                .append(prefix)
                .toString();

        Team team = player.getScoreboard().getTeam(teamName);

        if (team == null) {
            team = player.getScoreboard().registerNewTeam(teamName);
        }

        team.setPrefix(prefix);
        team.setColor(color);

        return team;
    }
}

