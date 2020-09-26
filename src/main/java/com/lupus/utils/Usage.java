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

import org.bukkit.ChatColor;

/**
 *
 * @author Lupus at https://github.com/PuccyDestroyerxXx
 */
public class Usage {
	public static String usage(String cmd,String usage){
		return ChatColor.RED + cmd + " " + ChatColor.YELLOW + usage;
	}
	public static String usage(String cmd){
		return ChatColor.RED + cmd;
	}
}
