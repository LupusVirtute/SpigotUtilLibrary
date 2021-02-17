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

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class Particles {
	private static final double RENDER_DISTANCE = 16.0;
	public static void spawnColoredParticleForPlayer(Player p,Particle particleToUse,Location loc,Particle.DustOptions options){
		p.spawnParticle(particleToUse, loc,1,options);
	}
	public static void visualizeRectangularBorderForPlayer(World warudo, int maxX, int maxZ, int minX, int minZ, Player p, Color color) {
		if (!p.isOnline()) {
			return;
		}
		if (!p.getLocation().getWorld().getUID().equals(warudo.getUID()))
			return;
		maxZ++;
		minX--;
		Location loc;
		Particle particleToUse = Particle.REDSTONE;
		Particle.DustOptions options = new Particle.DustOptions(color,4);
		final int pYLoc = p.getLocation().getBlockY();
		for (int i = minX; i <= maxX; i++) {
			loc = new Location(warudo, i, pYLoc, maxZ);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
			loc = new Location(warudo, i, pYLoc, minZ+1);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
		}
		for (int i = minZ; i <= maxZ; i++) {
			loc = new Location(warudo, minX+1, pYLoc, i);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
			loc = new Location(warudo, maxX, pYLoc, i);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
		}
	}
	public static void VisualizeRectangularBorderForWorld(World warudo, int minX, int minZ, int maxX, int maxZ,int yCoordinate) {
		Location loc;
		Particle particleToUse = Particle.PORTAL;
		for (int i = maxX; i < minX; i++) {
			loc = new Location(warudo, i, yCoordinate, maxZ);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
		for (int i = maxZ; i < minZ; i++) {
			// z iteration
			loc = new Location(warudo, maxX, yCoordinate, i);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
		for (int i = minX; i > maxX; i--) {
			// x decrementation
			loc = new Location(warudo, i, yCoordinate, minZ);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
		for (int i = minZ; i > maxZ; i--) {
			// z decrementation
			loc = new Location(warudo, minX, yCoordinate, i);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
	}
	
}
