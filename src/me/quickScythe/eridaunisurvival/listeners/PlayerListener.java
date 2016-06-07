package me.quickScythe.eridaunisurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.quickScythe.eridaunisurvival.Main;


public class PlayerListener implements Listener {
	
	Main plugin;
	
	public PlayerListener(Main main){
		this.plugin = main;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(e.getPlayer().getInventory().getChestplate().getType().equals(Material.ELYTRA)){
			
			if(e.getPlayer().isGliding() && e.getPlayer().isSneaking()){
				e.getPlayer().setGliding(false);
			}
			Location l = e.getPlayer().getLocation();
			if(!e.getPlayer().isFlying()&&!e.getPlayer().isGliding()&&l.getBlock().getType() == Material.AIR&&l.add(0, -1, 0).getBlock().getType() == Material.AIR&&l.add(0, -2, 0).getBlock().getType() == Material.AIR&&l.add(0, -3, 0).getBlock().getType() == Material.AIR)
				e.getPlayer().setGliding(true);
			
			if(!e.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)) return;
			if(e.getPlayer().isGliding() && ChatColor.stripColor(e.getPlayer().getItemInHand().getItemMeta().getDisplayName()).equals("Elytra Boost")){
				Vector unitVector = e.getPlayer().getVelocity().add(e.getPlayer().getEyeLocation().getDirection());
		        e.getPlayer().setVelocity((unitVector.multiply(0.5)));
			}
		}
		
	}

}
