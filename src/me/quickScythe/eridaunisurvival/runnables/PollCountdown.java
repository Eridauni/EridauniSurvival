package me.quickScythe.eridaunisurvival.runnables;

import org.bukkit.Bukkit;

import me.quickScythe.eridaunicore.utils.Utils;
import me.quickScythe.eridaunisurvival.Main;
import me.quickScythe.eridaunisurvival.utils.PollUtils;

public class PollCountdown implements Runnable {

	int i;
	Main plugin;
	public PollCountdown(Main plugin, int i) {
		this.plugin = plugin;
		this.i = i;
	}
	
	@Override
	public void run() {
		if(i > 1){
			Bukkit.broadcastMessage(Utils.colorize("&e&lPoll&f >&7 The current poll is ending in &f" + i + "&7 seconds..."));
			Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new PollCountdown(plugin, i-1), 20);
		}
		if(i == 1){
			Bukkit.broadcastMessage(Utils.colorize("&e&lPoll&f >&7 The current poll is ending in &f1&7 second..."));
			Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new PollCountdown(plugin, i-1), 20);
		}
		if(i == 0){
			Bukkit.broadcastMessage(Utils.colorize("&e&lPoll&f >&7 The current poll has ended."));
			PollUtils.pollResults();
		}
	}

}
