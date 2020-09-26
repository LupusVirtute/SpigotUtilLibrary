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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class PlayerRelated {
	/**
	 * Gets player nickname based on UUID
	 * @param uuid UUID of player
	 * @return nickname of player
	 */
	public static String getPlayerNick(UUID uuid){
		Player p = Bukkit.getPlayer(uuid);
		if(p == null){
			return Bukkit.getOfflinePlayer(uuid).getName();
		}
		return p.getName();
	}

	/**
	 * Adds items to player Inventory if player doesn't have space it pops out on the ground
	 * @param p player to add
	 * @param items items to add
	 */
	public static void addItemToPlayerInventory(Player p, ItemStack[] items){
		if(p == null || items == null){
			return;
		}
		Inventory playerInventory = p.getInventory();
		for(int i=0;i<items.length;i++){
			int firstEmptySpace = playerInventory.firstEmpty();
			if(firstEmptySpace == -1){
				Location playerLoc = p.getLocation();
				playerLoc.add(0,1,0);
				playerLoc.getWorld().dropItem(playerLoc,items[i]);
			}else{
				playerInventory.addItem(items[i]);
			}

		}
	}
	/**
	 * Adds item to player Inventory if player doesn't have space it pops out on the ground
	 * @param p player to add
	 * @param item item to add
	 */
	public static void addItemToPlayerInventory(Player p, ItemStack item){
		addItemToPlayerInventory(p,new ItemStack[]{item});
	}
	
}
