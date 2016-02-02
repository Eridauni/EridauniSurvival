package me.quickScythe.eridaunisurvival;

import org.bukkit.plugin.java.JavaPlugin;

import me.quickScythe.eridaunisurvival.listeners.ArrowListener;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public void onEnable(){
		plugin = this;
		new ArrowListener(this);
	}
	public static Main getPlugin(){
		return plugin;
	}

}
