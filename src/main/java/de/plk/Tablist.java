package de.plk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author SoftwareBuilds
 * @since 19.11.2023 22:02
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Tablist {

    public void update(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        Team op = scoreboard.getTeam("0001OP");
        Team nonOP = scoreboard.getTeam("0002NONOP");

        if (op == null)
            op = scoreboard.registerNewTeam("0001OP");

        if (nonOP == null)
            nonOP = scoreboard.registerNewTeam("0002NONOP");

        op.setColor(ChatColor.DARK_RED);
        nonOP.setColor(ChatColor.GRAY);

        op.setPrefix("§4§lO §7■ ");
        nonOP.setPrefix("§7§lS §7■ ");

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            nonOP.removeEntry(onlinePlayer.getName());
            op.removeEntry(onlinePlayer.getName());

            if (onlinePlayer.isOp()) {
                op.addEntry(onlinePlayer.getName());
                onlinePlayer.setDisplayName(op.getColor() + onlinePlayer.getName());
            } else {
                nonOP.addEntry(onlinePlayer.getName());
                onlinePlayer.setDisplayName(nonOP.getColor() + onlinePlayer.getName());
            }
        }

    }

}
