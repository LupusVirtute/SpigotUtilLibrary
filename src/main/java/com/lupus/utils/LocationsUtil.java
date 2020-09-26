package com.lupus.utils;/*
 * Copyright (C) 2020 Lupus at https://github.com/PuccyDestroyerxXx
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class LocationsUtil {
	private static final Set<Material> DAMAGE_BLOCKS = damageTypes();
	private static Set<Material> damageTypes() {
		Set<Material> b = new HashSet<>();
		b.add(Material.CACTUS);
		b.add(Material.FIRE);
		b.add(Material.MAGMA);
		b.add(Material.LAVA);
		b.add(Material.STATIONARY_LAVA);
		return b;
	}

	private static final String MESSAGE_OF_TELEPORTING = ChatColor.GRAY + "Teleportacja....";
	/**
	 * Checks if location is safe to teleport to
	 *
	 * @param loc location to check
	 * @return
	 */
	public static boolean isSafeLocation(Location loc) {
        World world = loc.getWorld();

        Block legs = world.getBlockAt(loc);
        Block below = world.getBlockAt(loc.add(0, -1, 0));
        Block above = world.getBlockAt(loc.add(0, 2, 0));

        return below.getType().isSolid()
            && below.getType().isOccluding()
            && (above.getType() == Material.AIR)
            && (!DAMAGE_BLOCKS.contains(legs.getType()));
	}

	/**
	 * Teleports player safely to location
	 *
	 * @param p player to teleport
	 * @param loc location to teleport
	 */
	public static boolean teleportPlayer(Player p, Location loc) {
		if (isSafeLocation(loc)) {
			p.sendMessage(MESSAGE_OF_TELEPORTING);
			p.playSound(loc, Sound.BLOCK_PORTAL_TRAVEL, (float) 0.8, (float) 2.0);
			p.teleport(loc);
			return true;
		} else {
			boolean c = doTeleportScan(loc,1,1,p);
			if (c)
				return true;
			c = doTeleportScan(loc,-1,-1,p);
			if (c)
				return true;
			c = doTeleportScan(loc,1,-1,p);
			if (c)
				return true;
			c = doTeleportScan(loc,-1,1,p);
			if (c)
				return true;
		}

		p.sendMessage(ChatColor.DARK_RED + "Wystąpiły problemy z teleportacją spróbuj jeszcze raz");
		return false;
	}
	private static boolean doTeleportScan(Location loc,int x,int z,Player p){
		for (int i = 0; i < 10; i++) {
			Location locCopy = loc.clone();
			locCopy.add(x*i,0,z*i);
			locCopy.setY(loc.getWorld().getHighestBlockAt(loc).getY());
			if (isSafeLocation(locCopy)) {
				p.sendMessage(MESSAGE_OF_TELEPORTING);
				p.playSound(loc, Sound.BLOCK_PORTAL_TRAVEL, (float) 0.8, (float) 2.0);
				p.teleport(loc);
				return true;
			}
		}
		return false;
	}
}
