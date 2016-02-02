package me.quickScythe.eridaunisurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import me.quickScythe.eridaunisurvival.Main;
import me.quickScythe.eridaunisurvival.runnables.ArrowRunnable;

public class ArrowListener implements Listener {
	

	Main plugin;
	
	public ArrowListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e){
		if(e.getEntity() instanceof Arrow){
			Arrow arrow = (Arrow) e.getEntity();
			if(arrow.isCritical()){
				Bukkit.getScheduler().runTask(plugin, new ArrowRunnable(plugin, arrow));
			}
		}
	}

}
