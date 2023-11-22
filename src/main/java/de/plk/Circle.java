package de.plk;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

/**
 * @author ungecodet
 * @since 26.04.2020 15:29
 * Copyright © 2020 | ungecodet | All rights reserved.
 */
public class Circle {

    private Player player;

    private Location location;

    private Particle particle;

    public Circle(Player player, Location location, Particle particle) {
        this.player = player;
        this.location = location;
        this.particle = particle;
    }

    public void send() {
        new Thread(() -> {
            double x, z;

            location.add(0, .2f, 0);
            for (int i = 0; i < 200; i++) {
                double angle = (double) 2 * Math.PI * i / 25;
                x = Math.cos(angle) * 1.25;
                z = Math.sin(angle) * 1.25;

                location.add(x, .2f, z);

                player.spawnParticle(particle, new Location(player.getWorld(), location.getX(), location.getY(), location.getZ()), 1, 0, 0, 0, 0);

                location.subtract(x, .2f, z);
            }

            location.subtract(0, .2f, 0);
        }).start();
    }
}