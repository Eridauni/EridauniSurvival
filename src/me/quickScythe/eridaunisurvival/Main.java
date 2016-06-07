package me.quickScythe.eridaunisurvival;

import org.bukkit.plugin.java.JavaPlugin;

import me.quickScythe.eridaunicore.utils.CoreUtils;
import me.quickScythe.eridaunisurvival.commands.PollCommand;
import me.quickScythe.eridaunisurvival.listeners.ArrowListener;
import me.quickScythe.eridaunisurvival.utils.Utils;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public void onEnable(){
		plugin = this;
		new ArrowListener(this);
		new PollCommand(this, "poll");
		
		Utils.start();
	}
	public static Main getPlugin(){
		return plugin;
	}

}
