package me.quickScythe.eridaunisurvival.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.utils.CoreUtils;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;
import me.quickScythe.eridaunisurvival.Main;

public class ArrowRunnable extends BukkitRunnable {
	
	Arrow arrow;
	Main plugin;
	public ArrowRunnable(Main plugin, Arrow arrow) {
		this.plugin = plugin;
		this.arrow = arrow;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if(!arrow.isOnGround() && !arrow.isDead()){
			ParticleEffect.FLAME.display(0, 0, 0, 0, 1, arrow.getLocation(), 50);
			Bukkit.getScheduler().runTaskLater(plugin, new ArrowRunnable(plugin, arrow), (long) 1);
		} else{
			Firework f = CoreUtils.createFirework(arrow.getLocation(), FireworkEffect.builder()
					.with(Type.BALL).
					withColor(Color.RED).
					withFlicker().build());
			
			
			
			
			f.setOp(true);
			Bukkit.getScheduler().runTaskLater(plugin, new BukkitRunnable() {
				
				@Override
				public void run() {
					f.detonate();
					
				}
			},0);
		}
		
		
	}

}
