package me.quickScythe.eridaunisurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;
import me.quickScythe.eridaunisurvival.Main;
import me.quickScythe.eridaunisurvival.utils.PollUtils;

public class PollCommand implements CommandExecutor {
	Main plugin;
	public PollCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("poll")){
			if(args.length == 0){
				if(sender.hasPermission("poll.create"))
					sender.sendMessage(Utils.colorize("&e&lPoll &f>&7 To create a poll, type &f\"/poll create <question>\"&7."));
				sender.sendMessage(Utils.colorize("&e&lPoll &f>&7 To answer a poll, type &f\"/poll <yes|no>\"&7."));
			}
			if(args.length > 1){
				if(args[0].equalsIgnoreCase("create")){
					if(sender.hasPermission("poll.create")){
						String poll = "";
						for(int i=0;i!=args.length;i++){
							if(i==0) continue;
							poll = poll + " " + args[i];
						}
						poll = poll.replaceFirst(" ", "");
						PollUtils.createPoll(sender, poll);
						return false;
					}
					sender.sendMessage(Utils.colorize("&e&lPoll &f>&7 You don't have permission to create a poll."));
					return false;
				}
				
			}
			if(args.length == 1 && sender instanceof Player){
				if(args[0].equalsIgnoreCase("yes") || args[0].equalsIgnoreCase("no")){
					PollUtils.submitPoll((Player) sender, args[0].toLowerCase());
					return false;
				}
				sender.sendMessage(Utils.colorize("&e&lPoll &f>&7 The only acceptable answers are &f\"yes\"&7 and &f\"no\"&7."));
				return false;
			}
		}
		return false;
	}

}
