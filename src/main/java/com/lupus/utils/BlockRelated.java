package com.lupus.utils;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockRelated {
	@SuppressWarnings("deprecation")
	public static ItemStack parseArgumentAsItemStack(String arg){
		Material material;
		material = Material.getMaterial(arg);
		if(material != null)
			return new ItemStack(material,1);
		return null;
	}
}
