package com.lupus.utils;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockRelated {
	@SuppressWarnings("deprecation")
	public static ItemStack parseArgumentAsItemStack(String arg){
		if(arg.contains(":")){
			String[] args = arg.split(":");
			if(NumberUtils.isNumber(args[0]) && NumberUtils.isNumber(args[1])){
				// Need to use this as of 1.12.2 we still can use block ids
				@SuppressWarnings("deprecation") Material material = Material.getMaterial(Integer.parseInt(args[0]));
				short subValue = Short.parseShort(args[1]);
				if (material != null) {
					if (subValue >= 0 && subValue <= DyeColor.values().length)
						return new ItemStack(material, 1, subValue);
				}
			}

		}else {
			Material material;
			if (NumberUtils.isNumber(arg)) {
				material = Material.getMaterial(Integer.parseInt(arg));
			}else
				material = Material.getMaterial(arg);

			if(material != null)
				return new ItemStack(material,1);
		}
		return null;
	}
}
