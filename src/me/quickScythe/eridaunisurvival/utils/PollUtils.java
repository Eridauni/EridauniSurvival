package me.quickScythe.eridaunisurvival.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;
import me.quickScythe.eridaunisurvival.Main;
import me.quickScythe.eridaunisurvival.runnables.PollInitialTimer;

public class PollUtils {
	
	private static Set<UUID> polledPlayers = new HashSet<>();
	private static Map<Integer, String> queuePolls = new HashMap<>();
	private static int yes = 0;
	private static int no = 0;
	
	private static boolean pollRunning = false;
	
	public static void submitPoll(Player player, String answer){
		if(!pollRunning){
			player.sendMessage(Utils.colorize("&e&lPoll &f>&7 There isn't a poll running right now."));
			return;
		}
		if(polledPlayers.contains(player.getUniqueId())){
			player.sendMessage(Utils.colorize("&e&lPoll &f>&7 You have already voted for this poll."));
			return;
		}
		polledPlayers.add(player.getUniqueId());
		if(answer.equalsIgnoreCase("yes")){
			yes = yes+1;
			player.sendMessage(Utils.colorize("&e&lPoll &f>&7 You have voted &f\"yes\"&7."));
		}
		if(answer.equalsIgnoreCase("no")){
			player.sendMessage(Utils.colorize("&e&lPoll &f>&7 You have voted &f\"no\"&7."));
			no = no+1;
		}
	}

	public static void createPoll(CommandSender sender, String poll) {
		if(pollRunning){
			sender.sendMessage(Utils.colorize("&e&lPoll &f>&7 There is a poll running. Adding your poll to queue. \n&8(Queue #" + (queuePolls.size()+1) + ")"));
			queuePolls.put(queuePolls.size()+1, poll);
			return;
		}
		pollRunning = true;
		
		if(!poll.endsWith("?") && !poll.endsWith("!") && !poll.endsWith("."))
			poll = poll + ".";
		
		Bukkit.broadcastMessage(Utils.colorize("&e&lPoll &f>&7 " + poll + "&7 The poll will end in &f30 &7seconds."));
		Bukkit.broadcastMessage(Utils.colorize("&e&lPoll &f>&7 To answer a poll, type &f\"/poll <yes|no>\"&7."));
		Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), new PollInitialTimer(Main.getPlugin()), 600);
		
	}

	public static void pollResults() {
		Bukkit.broadcastMessage(Utils.colorize("&e&l     Yes &f>&7 " + yes));
		Bukkit.broadcastMessage(Utils.colorize("&e&l     No &f>&7 " + no));
		yes=0;
		no=0;
		polledPlayers.clear();
		pollRunning = false;
		
		if(queuePolls.size() > 0){
			
			Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), new Runnable() {
				
				@Override
				public void run() {
					createPoll(null, queuePolls.get(1));
					queuePolls.remove(1);
					Map<Integer, String> temp_ = new HashMap<>();
					for(Entry<Integer, String> entry : queuePolls.entrySet()){
						temp_.put(entry.getKey()-1, entry.getValue());
					}
					queuePolls.clear();
					for(Entry<Integer, String> entry : temp_.entrySet()){
						queuePolls.put(entry.getKey(), entry.getValue());
					}	
				}
			}, 60);
			
			
			
			
		}
		
		
		
	}

}
