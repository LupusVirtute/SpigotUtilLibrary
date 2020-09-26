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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class InventoryUtility {
	public static Inventory createCustomGUI(InventoryHolder holder, int slots, String name) {
		if(slots <= 0 || slots > 54 || name == null){
			return null;
		}
		slots /= 9;
		if (slots < 1) {
			slots = 1;
		}
		slots *= 9;
		return Bukkit.createInventory(holder, slots, name);
	}
	public static void fillSlots(ItemStack item,Inventory inv,int from,int to){
		for(int i=from;i<=to;i++){
			inv.setItem(i,item);
		}
	}
	public static void fillSlots(ItemStack[] items,Inventory inv,int from){
		for(int i=from;i<items.length+from;i++){
			inv.setItem(i,items[i-from]);
		}
	}
	public static void fillBorder(ItemStack item, Inventory inv){
		int slots = inv.getSize();
		inv.setItem(8,item);
		for (int i = 0; i < slots; i++) {
			// Across First row
			if (i-9 <= 0){
				inv.setItem(i,item);
			}
			// Last Row
			else if(slots-i < 9){
				inv.setItem(i,item);
			}
			// Vertical Last Column
			else if (i%9 == 0){
				inv.setItem(i,item);
				inv.setItem(i-1,item);
			}
		}
	}
	public static void centerItemsInInventory(ItemStack[] items,Inventory inv){
		int slots = inv.getSize();
		// Calculating the center
		int y = slots/18;
		int x = 4;
		boolean right = true;
		boolean up = true;
		for (int i=0,j=1,k=1;i<items.length;i++,j++){
			if (y >= 6)
				break;
			if(x >= 8 || x < 1){
				x = 4;
				j = 1;
				y = up ? y - k : y + k;
				up = !up;
				k++;
			}
			inv.setItem(y*9+x,items[i]);
			x = right ? x+j : x-j;
			right = !right;
		}
	}
	public static void fillSquare(ItemStack item, Inventory invToFill, int minX, int minY, int maxX, int maxY){
		if(minX > 9 || maxX > 9 || minY > 9 || maxY > 9){
			return;
		}

		int b = Math.max(minX,maxX);
		minX = Math.min(minX,maxX);
		maxX = b;
		b = Math.max(minY,maxY);
		minY = Math.min(minY,maxY);
		maxY = b;
		for(int i=minY;i<maxY;i++){
			for(int j=minX;j<maxX;j++){
				invToFill.setItem(9*i+j,item);
			}
		}
	}
}
