package com.lupus.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackUtil {
	public static ItemStack setItemTitle(ItemStack x,String title){
		ItemMeta meta = x.getItemMeta();
		meta.setDisplayName(title);
		x.setItemMeta(meta);
		return x;
	}
	public static ItemStack setItemLore(ItemStack x,String[] lore){
		ItemMeta meta = x.getItemMeta();
		List<String> listLore = new ArrayList<>(Arrays.asList(lore));
		meta.setLore(listLore);
		x.setItemMeta(meta);
		return x;
	}

	/**
	 * Watchout this also colors title and lore
	 * @param x ItemStack to set Item and Lore to
	 * @param title title to set
	 * @param lore lore to set
	 * @return itemstack with given title and Lore
	 */
	public static ItemStack setItemTitleAndLore(ItemStack x,String title,String[] lore){
		setItemTitle(x, ColorUtil.text2Color(title));
		setItemLore(x,ColorUtil.text2Color(lore));
		return x;
	}
	public static String getItemStackName(ItemStack item){
		String name;
		if (item.hasItemMeta()) {
		  ItemMeta meta = item.getItemMeta();
		  if (meta.hasDisplayName()) {
		    name = meta.getDisplayName();
		  } else {
		    name = item.getType().name().replace("_", " ").toLowerCase();
		    meta.setDisplayName(name);
		    item.setItemMeta(meta);
		  }
		} else {
		  ItemMeta meta = item.getItemMeta();
		  name = item.getType().name().replace("_", " ").toLowerCase();
		  meta.setDisplayName(name);
		  item.setItemMeta(meta);
		}
		return name;
	}
}