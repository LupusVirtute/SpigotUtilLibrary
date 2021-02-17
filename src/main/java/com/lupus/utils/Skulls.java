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


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class Skulls {
	/**
	 * Skulls representing numbers
	 */
	public static ItemStack[] numberSkulls;
	private static boolean initialized = false;
	private static String VERSION;
	public static void InitClass() {
		if (initialized)
			return;
		VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		FileConfiguration fileConfig = LupusUtils.getINSTANCE().getConfig();
		numberSkulls = new ItemStack[10];
		if (fileConfig.isConfigurationSection("numbers")) {
			ConfigurationSection configSect = fileConfig.getConfigurationSection("numbers");
			if (configSect != null)
				for (String numbers : configSect.getKeys(false)) {
					if (!NumberUtils.isNumber(numbers))
						continue;
					int idx = Integer.parseInt(numbers);
					numberSkulls[idx] = setSkullTexture(new ItemStack(Material.PLAYER_HEAD), fileConfig.getString("numbers." + numbers));
				}
		}
		initialized = true;
	}

	// Set Skull Owner From Sataniel spigotmc.org
	public static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
		if (itemStack == null || id == null || textureValue == null)
			return new ItemStack(Material.PLAYER_HEAD);
		if (itemStack.getType() != Material.PLAYER_HEAD)
			itemStack.setType(Material.PLAYER_HEAD);
		ItemMeta skullMeta = itemStack.getItemMeta();
		GameProfile gameProfile = new GameProfile(UUID.fromString(id),null);
		byte[] encodedData =textureValue.getBytes();
		gameProfile.getProperties().put("textures",new Property("textures",new String(encodedData)));
		Field profileField = null;
		try{
			profileField = skullMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(skullMeta,gameProfile);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		itemStack.setItemMeta(skullMeta);
		return itemStack;
	}
	public static String getB64SkullTexture(ItemStack skull){
		return NBTEditor.getTexture(skull);
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
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD,1);
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
			itemStack = new ItemStack(Material.PLAYER_HEAD,1);
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
		ItemStack is = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
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
	 * Checkes whether ItemStack is a skull from number skull array
	 * @param item ItemStack to check
 	 * @return boolean whether itemstack is a skull
	 */
	public static boolean isThisItemANumberSkull(ItemStack item) {
		if (!item.getType().equals(Material.PLAYER_HEAD)) {
			return false;
		}
		for (int i = 0; i < numberSkulls.length; i++) {
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
		int slotRange = toSlot - fromSlot +1;
		if(digits.length() > slotRange){
			return;
		}
		// Fill with zeros
		for(int i=fromSlot;(i+digits.length())<=toSlot;i++){
			inv.setItem(i,numberSkulls[0]);
		}
		for(
				int i=digits.length()-1,j=toSlot;
				i>=0 && j >= fromSlot;
				i--,j--
		){
			inv.setItem(j,numberSkulls[(digits.charAt(i) - '0')]);
		}
	}
}
