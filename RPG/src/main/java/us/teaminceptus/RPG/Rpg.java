package us.teaminceptus.RPG;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import us.teaminceptus.RPG.Commands;
import us.teaminceptus.RPG.Dungeons.Elements.Air;
import us.teaminceptus.RPG.Dungeons.Elements.ElementAttacks;
import us.teaminceptus.RPG.Dungeons.Elements.ElementSelection;
import us.teaminceptus.RPG.Dungeons.Elements.Fire;
import us.teaminceptus.RPG.Dungeons.Elements.Lava;
import us.teaminceptus.RPG.Dungeons.Elements.Water;
import us.teaminceptus.RPG.Utils.LevelUp;

public class Rpg extends JavaPlugin implements Listener{


	public static File playersDirectory;
	//xp needed for elements
	public static double lvl1 = 100;
	public static double lvl2 = 150;
	public static double lvl3 = 250;
	public static double lvl4 = 375;
	public static double lvl5 = 500;
	public static double lvl6 = 750;
	public static double lvl7 = 1000;
	public static double lvl8 = 1325;
	public static double lvl9 = 1750;
	public static double lvl10 = 2000;
	public static double lvl11 = 2500;
	public static double lvl12 = 3000;
	public static double lvl13 = 3500;
	public static double lvl14 = 4000;
	public static double lvl15 = 5000;
	public static double lvl16 = 6000;
	public static double lvl17 = 7000;
	public static double lvl18 = 8000;
	public static double lvl19 = 9000;
	public static double lvl20 = 10000;
	public static File getPlayersDirectory() {
		return playersDirectory;
	}
	
	
	public static FileConfiguration getFile(OfflinePlayer p) {
		  File playerFile = new File(playersDirectory, p.getUniqueId().toString() + ".yml");
		  
		  if (!(playerFile.exists())) {
			  try {
				  playerFile.createNewFile();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  
		  
		  return YamlConfiguration.loadConfiguration(playerFile);
		  
		  
	}
	public void onEnable() {
		
		
		
		
		ElementAttacks.init();
		ElementSelection.init();
		
		this.getCommand("elementselection").setExecutor(new Commands(this));
		this.getCommand("wipe").setExecutor(new Commands(this));
		this.getCommand("setlevel").setExecutor(new Commands(this));
		//elements
		this.getServer().getPluginManager().registerEvents(new Lava(this), this);
		this.getServer().getPluginManager().registerEvents(new Fire(this), this);
		this.getServer().getPluginManager().registerEvents(new Air(this), this);
		this.getServer().getPluginManager().registerEvents(new Water(this), this);
		this.getServer().getPluginManager().registerEvents(new ElementSelection(), this);
		this.getServer().getPluginManager().registerEvents(this, this);
		
		
		playersDirectory = new File(this.getDataFolder(), "players");
		  
		if (!(playersDirectory.exists())) {
			 playersDirectory.mkdir();
		}
		
		  
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			String uuid = p.getUniqueId().toString();
		      
			File playerFile = new File(playersDirectory, uuid + ".yml");
		      
			if (!(playerFile.exists())) {
				try {
					playerFile.createNewFile();
				} 	catch (IOException e) {
						e.printStackTrace();
				}
			}
		      
			
		      
		      

		}
		
		
		
		
		new BukkitRunnable() {
			public void run() {
				for(Player p: Bukkit.getOnlinePlayers()) {
					String uuid = p.getUniqueId().toString();
					
					File playerFile = new File(playersDirectory, uuid + ".yml");
					
					FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
					
					if(playerConfig.getInt("Fire.level") == 0 && playerConfig.getInt("Fire.xp") >= lvl1) {
						

						
						LevelUp.FireLevelUp(p, 1, Fire.lvl1damage, Fire.alvl1damage, Fire.ulvl1damage, lvl1);
						
						
						
					}
					
					if(playerConfig.getInt("Fire.level") == 1 && playerConfig.getInt("Fire.xp") >= lvl2) {
						
						LevelUp.FireLevelUp(p, 2, Fire.lvl2damage, Fire.alvl2damage, Fire.ulvl2damage, lvl2);
					}
					
					if(playerConfig.getInt("Fire.level") == 2 && playerConfig.getInt("Fire.xp") >= lvl3) {
						
						LevelUp.FireLevelUp(p, 3, Fire.lvl3damage, Fire.alvl3damage, Fire.ulvl3damage, lvl3);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 3 && playerConfig.getInt("Fire.xp") >= lvl4) {
						
						LevelUp.FireLevelUp(p, 4, Fire.lvl4damage, Fire.alvl4damage, Fire.ulvl4damage, lvl4);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 4 && playerConfig.getInt("Fire.xp") >= lvl5) {
						
						LevelUp.FireLevelUp(p, 5, Fire.lvl5damage, Fire.alvl5damage, Fire.ulvl5damage, lvl5);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 5 && playerConfig.getInt("Fire.xp") >= lvl6) {
						
						LevelUp.FireLevelUp(p, 6, Fire.lvl6damage, Fire.alvl6damage, Fire.ulvl6damage, lvl6);
					}
					
					if(playerConfig.getInt("Fire.level") == 6 && playerConfig.getInt("Fire.xp") >= lvl7) {
						
						LevelUp.FireLevelUp(p, 7, Fire.lvl7damage, Fire.alvl7damage, Fire.ulvl7damage, lvl7);
						
					}
					
					
					if(playerConfig.getInt("Fire.level") == 7 && playerConfig.getInt("Fire.xp") >= lvl8) {
						
						LevelUp.FireLevelUp(p, 8, Fire.lvl8damage, Fire.alvl8damage, Fire.ulvl8damage, lvl8);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 8 && playerConfig.getInt("Fire.xp") >= lvl9) {
						LevelUp.FireLevelUp(p, 9, Fire.lvl9damage, Fire.alvl9damage, Fire.ulvl9damage, lvl9);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 9 && playerConfig.getInt("Fire.xp") >= lvl10) {
						
						LevelUp.FireLevelUp(p, 10, Fire.lvl10damage, Fire.alvl10damage, Fire.ulvl10damage, lvl10);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 10 && playerConfig.getInt("Fire.xp") >= lvl11) {
						
						LevelUp.FireLevelUp(p, 11, Fire.lvl11damage, Fire.alvl11damage, Fire.ulvl11damage, lvl11);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 11 && playerConfig.getInt("Fire.xp") >= lvl12) {
						
						LevelUp.FireLevelUp(p, 12, Fire.lvl12damage, Fire.alvl12damage, Fire.ulvl12damage, lvl12);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 12 && playerConfig.getInt("Fire.xp") >= lvl13) {
						
						LevelUp.FireLevelUp(p, 13, Fire.lvl13damage, Fire.alvl13damage, Fire.ulvl13damage, lvl13);
					}
					
					if(playerConfig.getInt("Fire.level") == 13 && playerConfig.getInt("Fire.xp") >= lvl14) {
						
						LevelUp.FireLevelUp(p, 14, Fire.lvl14damage, Fire.alvl14damage, Fire.ulvl14damage, lvl14);
					}
					
					if(playerConfig.getInt("Fire.level") == 14 && playerConfig.getInt("Fire.xp") >= lvl15) {
						
						LevelUp.FireLevelUp(p, 15, Fire.lvl15damage, Fire.alvl15damage, Fire.ulvl15damage, lvl15);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 15 && playerConfig.getInt("Fire.xp") >= lvl16) {
						
						LevelUp.FireLevelUp(p, 16, Fire.lvl16damage, Fire.alvl16damage, Fire.ulvl16damage, lvl16);
					}
					
					if(playerConfig.getInt("Fire.level") == 16 && playerConfig.getInt("Fire.xp") >= lvl17) {
						
						LevelUp.FireLevelUp(p, 17, Fire.lvl17damage, Fire.alvl17damage, Fire.ulvl17damage, lvl17);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 17 && playerConfig.getInt("Fire.xp") >= lvl18) {
						
						LevelUp.FireLevelUp(p, 18, Fire.lvl18damage, Fire.alvl18damage, Fire.ulvl18damage, lvl18);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 18 && playerConfig.getInt("Fire.xp") >= lvl19) {
						
						LevelUp.FireLevelUp(p, 19, Fire.lvl19damage, Fire.alvl19damage, Fire.ulvl19damage, lvl19);
						
					}
					
					if(playerConfig.getInt("Fire.level") == 19 && playerConfig.getInt("Fire.xp") >= lvl20) {
						
						LevelUp.FireLevelUp(p, 20, Fire.lvl20damage, Fire.alvl20damage, Fire.ulvl20damage, lvl20);
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					// AIR
					
					
					if(playerConfig.getInt("Air.level") == 0 && playerConfig.getInt("Air.xp") >= lvl1) {
						
						LevelUp.AirLevelUp(p, 1, Air.lvl1damage, Air.ulvl1, lvl1);
						
					}
					
					if(playerConfig.getInt("Air.level") == 1 && playerConfig.getInt("Air.xp") >= lvl2) {
						
						LevelUp.AirLevelUp(p, 2, Air.lvl2damage, Air.ulvl2, lvl2);
					}
					
					if(playerConfig.getInt("Air.level") == 2 && playerConfig.getInt("Air.xp") >= lvl3) {
						
						LevelUp.AirLevelUp(p, 3, Air.lvl3damage, Air.ulvl3, lvl3);
						
					}
					
					if(playerConfig.getInt("Air.level") == 3 && playerConfig.getInt("Air.xp") >= lvl4) {
						
						LevelUp.AirLevelUp(p, 4, Air.lvl4damage, Air.ulvl4, lvl4);
						
					}
					
					if(playerConfig.getInt("Air.level") == 4 && playerConfig.getInt("Air.xp") >= lvl5) {
						
						LevelUp.AirLevelUp(p, 5, Air.lvl5damage, Air.lvl5distance, Air.ulvl5, lvl5);
						
					}
					
					if(playerConfig.getInt("Air.level") == 5 && playerConfig.getInt("Air.xp") >= lvl6) {
						
						LevelUp.AirLevelUp(p, 6, Air.lvl6damage, Air.ulvl6, lvl6);
						
					}
					
					if(playerConfig.getInt("Air.level") == 6 && playerConfig.getInt("Air.xp") >= lvl7) {
						
						LevelUp.AirLevelUp(p, 7, Air.lvl7damage, Air.ulvl7, lvl7);
						
					}
					
					
					if(playerConfig.getInt("Air.level") == 7 && playerConfig.getInt("Air.xp") >= lvl8) {
						
						LevelUp.AirLevelUp(p, 8, Air.lvl8damage, Air.ulvl8, lvl8);
						
					}
					
					if(playerConfig.getInt("Air.level") == 8 && playerConfig.getInt("Air.xp") >= lvl9) {
						
						LevelUp.AirLevelUp(p, 9, Air.lvl9damage, Air.ulvl9, lvl9);
						
					}
					
					if(playerConfig.getInt("Air.level") == 9 && playerConfig.getInt("Air.xp") >= lvl10) {
						
						LevelUp.AirLevelUp(p, 10, Air.lvl10damage, Air.lvl10distance, Air.ulvl10, lvl10);
						
					}
					
					if(playerConfig.getInt("Air.level") == 10 && playerConfig.getInt("Air.xp") >= lvl11) {
						
						LevelUp.AirLevelUp(p, 11, Air.lvl11damage, Air.ulvl11, lvl11);
						
					}
					
					if(playerConfig.getInt("Air.level") == 11 && playerConfig.getInt("Air.xp") >= lvl12) {
						
						LevelUp.AirLevelUp(p, 12, Air.lvl12damage, Air.ulvl12, lvl12);
						
					}
					
					if(playerConfig.getInt("Air.level") == 12 && playerConfig.getInt("Air.xp") >= lvl13) {
						
						LevelUp.AirLevelUp(p, 13, Air.lvl13damage, Air.ulvl13, lvl13);
						
					}
					
					if(playerConfig.getInt("Air.level") == 13 && playerConfig.getInt("Air.xp") >= lvl14) {
						
						LevelUp.AirLevelUp(p, 14, Air.lvl14damage, Air.ulvl14, lvl14);
						
					}
					
					if(playerConfig.getInt("Air.level") == 14 && playerConfig.getInt("Air.xp") >= lvl15) {
						
						LevelUp.AirLevelUp(p, 15, Air.lvl15damage, Air.lvl15distance, Air.ulvl15, lvl15);
					}
					
					if(playerConfig.getInt("Air.level") == 15 && playerConfig.getInt("Air.xp") >= lvl16) {
						
						LevelUp.AirLevelUp(p, 16, Air.lvl16damage, Air.ulvl16, lvl16);
						
					}
					
					if(playerConfig.getInt("Air.level") == 16 && playerConfig.getInt("Air.xp") >= lvl17) {
						
						LevelUp.AirLevelUp(p, 17, Air.lvl17damage, Air.ulvl17, lvl17);
						
					}
					
					if(playerConfig.getInt("Air.level") == 17 && playerConfig.getInt("Air.xp") >= lvl18) {
						
						LevelUp.AirLevelUp(p, 18, Air.lvl18damage, Air.ulvl18, lvl18);
						
					}
					
					if(playerConfig.getInt("Air.level") == 18 && playerConfig.getInt("Air.xp") >= lvl19) {
						
						LevelUp.AirLevelUp(p, 19, Air.lvl19damage, Air.ulvl19, lvl19);
						
					}
					
					if(playerConfig.getInt("Air.level") == 19 && playerConfig.getInt("Air.xp") >= lvl20) {
						
						LevelUp.AirLevelUp(p, 20, Air.lvl20damage, Air.lvl20distance, Air.ulvl20, lvl20);
						
					}
					
					
					
				}
			}
		}.runTaskTimer(this, 0L, 1L);
		
		
		
		
		
		
	}
	
	@EventHandler
	
	public void onDeath(EntityDeathEvent e) {
		
		Entity ent = e.getEntity();
		Location loc = ent.getLocation();
		World world = ent.getWorld();
		
	
			ent.remove();
		}
	
	
	
	
	
}
