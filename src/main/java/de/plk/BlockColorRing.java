package de.plk;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

/**
 * @author SoftwareBuilds
 * @since 19.11.2023 21:18
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class BlockColorRing {

    private static final Random RANDOM = new Random();

    private static final Material[] MATERIALS = new Material[] {
        Material.CYAN_CONCRETE, Material.RED_CONCRETE, Material.BLUE_CONCRETE,
            Material.BLACK_CONCRETE, Material.YELLOW_CONCRETE, Material.WHITE_CONCRETE,
            Material.BROWN_CONCRETE, Material.GRAY_CONCRETE, Material.GREEN_CONCRETE,
            Material.PINK_CONCRETE, Material.PURPLE_CONCRETE, Material.MAGENTA_CONCRETE,
            Material.ORANGE_CONCRETE
    };

    private final Location location;

    public BlockColorRing(Location location) {
        this.location = location;
    }

    public void execute() {
        location.clone().add(1, 0, 0).getBlock().setType(getRandomMaterial());
        location.clone().add(1, 0, 1).getBlock().setType(getRandomMaterial());
        location.clone().add(1, 0, 0).subtract(0, 0, 1).getBlock().setType(getRandomMaterial());

        location.clone().subtract(1, 0, 0).getBlock().setType(getRandomMaterial());
        location.clone().subtract(1, 0, 0).add(0, 0, 1).getBlock().setType(getRandomMaterial());
        location.clone().subtract(1, 0, 1).getBlock().setType(getRandomMaterial());

        location.clone().add(0, 0, 1).getBlock().setType(getRandomMaterial());
        location.clone().subtract(0, 0, 1).getBlock().setType(getRandomMaterial());
    }

    private Material getRandomMaterial() {
        return MATERIALS[RANDOM.nextInt(MATERIALS.length)];
    }

}
