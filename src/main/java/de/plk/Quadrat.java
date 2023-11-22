package de.plk;

import de.plk.plugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author ungecodet
 * @since 26.04.2020 15:29
 * Copyright © 2020 | ungecodet | All rights reserved.
 */
public class Quadrat {

    private Player player;

    private Location location;

    private Particle particle;

    public Quadrat(Player player, Location location, Particle particle) {
        this.player = player;
        this.location = location;
        this.particle = particle;
    }

    public void send() {
        new Thread(() -> {
            double x, z;

            Location start = location.clone().subtract(1.5, 0, 1.5);

            for (int i = 0; i < 4; i++) {
                for (int t = 0; t < 3; t++) {
                    for (int j = 0; j < 10; j++) {
                        Location next = null;

                        double abzug = .1;

                        if (i == 0) {
                            next = start.add(0, 0, abzug);
                        } else if (i == 1) {
                            next = start.add(abzug, 0, 0);
                        } else if (i == 2) {
                            next = start.subtract(0, 0, abzug);
                        } else {
                            next = start.subtract(abzug, 0, 0);
                        }

                        try {
                            Thread.sleep(5 * (long) (j + 1) + (t + 1) + (i + 1));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        player.spawnParticle(particle, next, 1, 0, 0, 0, 0);
                    }
                }
            }
        }).start();
    }
}