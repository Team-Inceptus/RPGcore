package us.teaminceptus.RPG;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.google.gson.Gson;

import us.teaminceptus.RPG.Dungeons.Elements.ElementAttacks;
import us.teaminceptus.RPG.Dungeons.Elements.ElementSelection;
import us.teaminceptus.RPG.Utils.APIPlayer;
import us.teaminceptus.RPG.Utils.GeneralUtils;



public class Commands implements CommandExecutor{
	
	
	
	
	static Rpg plugin;
	public Commands(Rpg plugin) {
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use that command.");
			return true;
		}else {
			Player player = (Player) sender;


			if(player.isOp()) {
				
				//elements
				
				if(cmd.getName().equalsIgnoreCase("elementselection")) {
					
					player.openInventory(ElementSelection.selection);
					
					
				}
				
				if(cmd.getName().equalsIgnoreCase("wipe")) {
					
					if (args.length < 1) {
						sender.sendMessage(ChatColor.RED + "Please provide a valid player.");
						return false;
					}
					
					if (GeneralUtils.sendGETRequestStatusCode("https://api.mojang.com/users/profiles/minecraft/" + args[0]) != 200) {
						sender.sendMessage(ChatColor.RED + "This player does not exist.");
						return false;
					}
					Gson g = new Gson();
					UUID uuid = GeneralUtils.untrimUUID(g.fromJson(GeneralUtils.sendGETRequest("https://api.mojang.com/users/profiles/minecraft/" + args[0]), APIPlayer.class).id);
					
					OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
					if (target == null) {
						sender.sendMessage(ChatColor.RED + "This player does not exist.");
						return false;
					}
					FileConfiguration playerConfig = Rpg.getFile(target);
					
					//types
					player.sendMessage(args[1]);
					
					if(args[1].equalsIgnoreCase("fire")) {
						playerConfig.set("Fire.level", 0);
						playerConfig.set("Fire.xp", 0);
						try {
							playerConfig.save(new File(Rpg.getPlayersDirectory(), target.getUniqueId().toString() + ".yml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(target.isOnline()) {
							Player p = (Player) target;
							p.sendMessage((ChatColor.RED + "Your ") + (ChatColor.GOLD + "Fire") + (ChatColor.RED + " Skill was wiped by ") + (ChatColor.GOLD + player.getName()));
						}
						
						
						
						player.sendMessage(ChatColor.GREEN + "Succesfully wiped " + args[0] + "\'s fire skill.");
					}
					
					if(args[1].equalsIgnoreCase("air")) {
						playerConfig.set("Air.level", 0);
						playerConfig.set("Air.xp", 0);
						try {
							playerConfig.save(new File(Rpg.getPlayersDirectory(), target.getUniqueId().toString() + ".yml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(target.isOnline()) {
							Player p = (Player) target;
							p.sendMessage((ChatColor.RED + "Your ") + (ChatColor.GOLD + "Air") + (ChatColor.RED + " Skill was wiped by ") + (ChatColor.GOLD + player.getName()));
						}
						
						
						
						player.sendMessage(ChatColor.GREEN + "Succesfully wiped " + args[0] + "\'s air skill.");
					}
					
					
					
				}
				
				if(cmd.getName().equalsIgnoreCase("setlevel")) {
					
					if (args.length < 1) {
						sender.sendMessage(ChatColor.RED + "Please provide a valid player.");
						return false;
					}
					
					if (GeneralUtils.sendGETRequestStatusCode("https://api.mojang.com/users/profiles/minecraft/" + args[0]) != 200) {
						sender.sendMessage(ChatColor.RED + "This player does not exist.");
						return false;
					}
					Gson g = new Gson();
					UUID uuid = GeneralUtils.untrimUUID(g.fromJson(GeneralUtils.sendGETRequest("https://api.mojang.com/users/profiles/minecraft/" + args[0]), APIPlayer.class).id);
					
					OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
					if (target == null) {
						sender.sendMessage(ChatColor.RED + "This player does not exist.");
						return false;
					}
					FileConfiguration playerConfig = Rpg.getFile(target);
					
					//types
					player.sendMessage(args[1]);
					
					if(args[1].equalsIgnoreCase("fire")) {
						
						int level = Integer.parseInt(args[2]);
						if(level > 20) {
							
							player.sendMessage(ChatColor.RED + "Level can only be between 0-20.");
							return false;
						}
						
						
						playerConfig.set("Fire.level", level);
						playerConfig.set("Fire.xp", 0);
						try {
							playerConfig.save(new File(Rpg.getPlayersDirectory(), target.getUniqueId().toString() + ".yml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(target.isOnline()) {
							Player p = (Player) target;
							p.sendMessage((ChatColor.GREEN + "Your ") + (ChatColor.GOLD + "Fire") + (ChatColor.GREEN + " Skill was set to ") + (ChatColor.RED + String.valueOf(level)) + (ChatColor.GREEN + " by ") + (ChatColor.GOLD + player.getName()));
						}
						
						
						
						player.sendMessage(ChatColor.GREEN + "Succesfully set " + args[0] + "\'s fire skill to " + (ChatColor.RED + String.valueOf(level)));
					}
					
					if(args[1].equalsIgnoreCase("air")) {
						
						int level = Integer.parseInt(args[2]);
						if(level > 100) {
							
							player.sendMessage(ChatColor.RED + "Level can only be between 0-100.");
							return false;
						}
						
						
						playerConfig.set("Air.level", level);
						playerConfig.set("Air.xp", 0);
						try {
							playerConfig.save(new File(Rpg.getPlayersDirectory(), target.getUniqueId().toString() + ".yml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(target.isOnline()) {
							Player p = (Player) target;
							p.sendMessage((ChatColor.GREEN + "Your ") + (ChatColor.GOLD + "Air") + (ChatColor.GREEN + " Skill was set to ") + (ChatColor.RED + String.valueOf(level)) + (ChatColor.GREEN + " by ") + (ChatColor.GOLD + player.getName()));
						}
						
						
						
						player.sendMessage(ChatColor.GREEN + "Succesfully set " + args[0] + "\'s air skill to " + (ChatColor.RED + String.valueOf(level)));
					}
					
					
					
				}
				
				
			}
			
		}
		return false;
	}
	
	

}
