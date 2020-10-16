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

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.UUID;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class Skulls {
	// Set Skull Owner From Sataniel spigotmc.org
	public static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
		net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound compound = nmsStack.getTag();
		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
			compound = nmsStack.getTag();
		}
		NBTTagCompound skullOwner = new NBTTagCompound();
		skullOwner.set("Id", new NBTTagString(id));
		NBTTagCompound properties = new NBTTagCompound();
		NBTTagList textures = new NBTTagList();
		NBTTagCompound value = new NBTTagCompound();
		value.set("Value", new NBTTagString(textureValue));
		textures.add(value);
		properties.set("textures", textures);
		skullOwner.set("Properties", properties);
		compound.set("SkullOwner", skullOwner);
		nmsStack.setTag(compound);
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	/**
	 * Gets texture of given offline player
	 * @param p offline player to get skull of
	 * @return ItemStack skull of the given player
	 */
	public static ItemStack getSkull(UUID p){
		return getSkull(Bukkit.getOfflinePlayer(p));
	}

	/**
	 * Gets texture of given offline player
	 * @param p offline player to get skull of
	 * @return ItemStack skull of the given player
	 */
	public static ItemStack getSkull(OfflinePlayer p){
		ItemStack itemStack = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
		SkullMeta skull = (SkullMeta) itemStack.getItemMeta();
		skull.setOwningPlayer(p);
		itemStack.setItemMeta(skull);
		return itemStack;
	}

	/**
	 * Sets texture of the skull
	 * @param itemStack itemStack to set texture
	 * @param b64texture Base 64 string texture value
	 * @return ItemStack with given texture
	 */
	public static ItemStack setSkullTexture(ItemStack itemStack,String b64texture){
		if (itemStack == null) {
			itemStack = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
		}
		return setSkullOwner(itemStack, UUID.randomUUID().toString(),b64texture);
	}

	/**
	 * Creates quickly skull with displayname and lore
	 * @param skullid id of skull
	 * @param skullTextureValue texture value of skull
	 * @param displayName display name of skull
	 * @param lore lore of the skull
	 * @return ItemStack of skull with given values
	 */
	public static ItemStack createNameableSkull(String skullid, String skullTextureValue, String displayName, String[] lore) {
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		is = setSkullOwner(is, skullid, skullTextureValue);
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		for(int i =0 ;i < lore.length;i++){
			lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
		}
		im.setLore(Arrays.asList(lore));
		is.setItemMeta(im);
		return is;
	}

	/**
	 * Skulls representing numbers
	 */
	public static final ItemStack[] numberSkulls = {
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"4f90eae5-5fc3-4e4f-a6e0-482f5427d5f4",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NjhmNTU5OGFmN2M2NmYxYzFkNzM0NjVlYzMxZGNkNjdhODhkOTAwNTFiODk3ZGFkODRiYjM2MGIzMzc5OSJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"09201882-f3e9-4267-aedb-f9a1e039009e",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDliMzAzMDNmOTRlN2M3ODVhMzFlNTcyN2E5MzgxNTM1ZGFmNDc1MzQ0OWVhNDFkYjc0NmUxMjM0ZTlkZDJiNSJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"9000dd5d-fe9a-40f9-8724-5f9165616929",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTNjYTdkN2MxNTM0ZGM2YjllZDE2NDdmOTAyNWRkZjI0NGUwMTA3ZGM4ZGQ0ZjRmMDg1MmM4MjA4MWQ2MzUwZSJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"29845bee-1871-46e3-9032-570e97ccf47e",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk1ZTFlMmZiMmRlN2U2Mjk5YTBmNjFkZGY3ZDlhNmQxMDFmOGQ2NjRmMTk1OWQzYjY3ZGNlOGIwNDlhOGFlMSJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"3380511f-879b-4301-a10d-109ab30e7b3f",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzgzOWVhZjllZTA3NjcwNjA3ZDNkYTRkNmMxZDMwZmU1OWRiNTY0NThmYWQ1ZjU1YjU0MTJkNTZiM2RlYjU1OSJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"ad28dfda-67bf-46aa-b033-750e89e6cabc",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTgxNDBlYzQ4NDU2MzFhODlmZmU4MzQ0YWU5OGQxMDQ5YjgyYzIxNTkzOTMyOTBiZDM4YThhMDA4NDY1YjNkOSJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"d9a6b2cb-936f-4890-aec5-12401c987caf",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGViZjliNmE2YzRlYjBjYmZkMGEzZDM3YzM0YzQ1ODYwNjgxZjEzZjcyNzBmMTMzN2ZkMTM2YTcwMTE4OThmMCJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"6c33dea9-a139-4ace-8eab-94e5ee388ce3",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRjMmQ2Y2JkZmYwMGI5N2FmN2Y4Y2ZlODc2N2ZkODdjZDY1NjM5YWJkZjgzZWMxMDM5YTQ2NDE1ZTY5ZTM5OCJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"1b215951-8545-40bd-be5a-d08230b897aa",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQyNzkxNzUyZjY3YTY0NTBiOTc1ZDM1NzQxMjM1NmIwYTk5ZTM1NTVlNjdlYmRhZDJkNDAzMjYxMjliZGRhNCJ9fX0="
		),
		setSkullOwner(new ItemStack(Material.SKULL_ITEM,1,(short)3),
				"a5feaf7c-382c-4f47-935a-802816366812",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE3MDc3YzBjNjhjNDk2OTFhYTcwNDQzOTRhYWI5MDgzODYzOTRjM2Q0N2FhZmM1N2IwNmI4ZTMyZDE2NTZmNSJ9fX0="
		),
	};

	/**
	 * Checkes whether ItemStack is a skull from number skull array
	 * @param item ItemStack to check
 	 * @return boolean whether itemstack is a skull
	 */
	public static boolean isThisItemANumberSkull(ItemStack item){
		if (item.getType() != Material.SKULL_ITEM) {
			return false;
		}
		for(int i=0;i<numberSkulls.length;i++){
			if (item.isSimilar(numberSkulls[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function converts integers and puts integer skulls in desired slots
	 * @param inv inventory to put the numbers from integer to slots
	 * @param number integer number to put in slots
	 * @param fromSlot from slot (id)
	 * @param toSlot to slot (id)
	 */
	public static void intToSkullConverter(Inventory inv,int number,int fromSlot,int toSlot){
		if(number < 0){
			throw new NotImplementedException();
		}
		if (fromSlot > toSlot){
			int b = fromSlot;
			fromSlot = toSlot;
			toSlot = b;
		}
		String digits = String.valueOf(number);
		int slotRange = toSlot - fromSlot;
		if(digits.length() > slotRange){
			return;
		}
		// Fill with zeros
		for(int i=fromSlot;(i+digits.length())<=toSlot;i++){
			inv.setItem(i,numberSkulls[0]);
		}
		for(
				int i=digits.length()-1,j=toSlot;
				i>=0 && j > fromSlot;
				i--,j--
		){
			inv.setItem(j,numberSkulls[(digits.charAt(i) - '0')]);
		}
	}
}
