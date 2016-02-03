package me.quickScythe.eridaunisurvival.runnables;

import org.bukkit.Bukkit;

import me.quickScythe.eridaunicore.utils.Utils;
import me.quickScythe.eridaunisurvival.Main;

public class PollInitialTimer implements Runnable {

	Main plugin;
	public PollInitialTimer(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		Bukkit.broadcastMessage(Utils.colorize("&e&lPoll &f>&7 The current poll is ending in &f5&7 seconds.."));
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new PollCountdown(plugin, 4), 20);
	}

}
