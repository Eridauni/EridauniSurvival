package me.quickScythe.eridaunisurvival.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;
import me.quickScythe.eridaunisurvival.Main;

public class ArrowRunnable extends BukkitRunnable {
	
	Arrow arrow;
	Main plugin;
	public ArrowRunnable(Main plugin, Arrow arrow) {
		this.plugin = plugin;
		this.arrow = arrow;
	}

	@Override
	public void run() {
		if(!arrow.isOnGround()){
			ParticleEffect.FLAME.display(0, 0, 0, 0, 1, arrow.getLocation(), 50);
			Bukkit.getScheduler().runTaskLater(plugin, new ArrowRunnable(plugin, arrow), (long) 1);
		}
	}

}
