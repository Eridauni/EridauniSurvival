package me.quickScythe.eridaunisurvival.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.quickScythe.eridaunicore.utils.CoreUtils;

public class Utils {
	
	public static void start(){
		
		ItemStack grenade = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta gm = grenade.getItemMeta();
		gm.setDisplayName(CoreUtils.colorize("&cGrenade"));
		grenade.setItemMeta(gm);
		
		ShapedRecipe gr = new ShapedRecipe(grenade);
		gr.shape("SSS",
				 "SFS",
				 "SSS");
		gr.setIngredient('S', Material.SULPHUR);
		gr.setIngredient('F', Material.FIREWORK_CHARGE);
		Bukkit.addRecipe(gr);
		
		ShapedRecipe er = new ShapedRecipe(new ItemStack(Material.ELYTRA));
		er.shape("DDD",
				 "FXF",
				 "FXF");
		er.setIngredient('D', Material.DIAMOND);
		er.setIngredient('F', Material.FEATHER);
		Bukkit.addRecipe(er);
	}

}
