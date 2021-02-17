package com.lupus.utils;

import org.bukkit.block.Skull;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name="LupusUtils", version="1.0-SNAPSHOT")
@Description(value = "Simple utility plugin")
@Author(value = "LupusVirtute")
@Website(value="github.com/PuccyDestroyerxXx")
@ApiVersion(value = ApiVersion.Target.v1_15)
public class LupusUtils extends JavaPlugin {
	private static LupusUtils INSTANCE;
	public static LupusUtils getINSTANCE(){
		return INSTANCE;
	}
	@Override
	public void onEnable() {
		saveDefaultConfig();
		INSTANCE = this;
		Skulls.InitClass();
	}
}
