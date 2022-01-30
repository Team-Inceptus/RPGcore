package us.teaminceptus.RPG.Utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import us.teaminceptus.RPG.Rpg;

public class LevelUp {
	
	public static void FireLevelUp(Player p, Integer level, Double damage, Double damage2, Double ultdamage, Double lvl) {
		
		UUID uuid = p.getUniqueId();
		File playerFile = new File(Rpg.playersDirectory, uuid + ".yml");
		
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		p.sendMessage((ChatColor.GREEN+ "Your ") + (ChatColor.RED + "Fire ") + (ChatColor.GREEN + "element leveled up to level " + (ChatColor.GOLD + level.toString()) + (ChatColor.GREEN + " !")));
		p.sendMessage((ChatColor.GREEN + "Dragon Breath\'s damage increased to ") + (ChatColor.RED + damage.toString()) + (ChatColor.GREEN + " , Fire Aura\'s damage increased to ") + (ChatColor.RED + damage2.toString()) + (ChatColor.GREEN + " , and your Ultimate\'s damage increased to ") + (ChatColor.RED + ultdamage.toString() + " ."));
		
		playerConfig.set("Fire.level", level);
		playerConfig.set("Fire.xp", playerConfig.getInt("Fire.xp") - lvl );
		
		
		try {
			playerConfig.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	
	public static void AirLevelUp(Player p, Integer level, Double damage, Integer duration, Double lvl) {
		
		UUID uuid = p.getUniqueId();
		File playerFile = new File(Rpg.playersDirectory, uuid + ".yml");
		
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		p.sendMessage((ChatColor.GREEN+ "Your ") + (ChatColor.AQUA + "Air ") + (ChatColor.GREEN + "element leveled up to level " + (ChatColor.GOLD + level.toString()) + (ChatColor.GREEN + " !")));
		p.sendMessage((ChatColor.GREEN + "Freezing Winds\' damage increased to ") + (ChatColor.RED + damage.toString()) + (ChatColor.GREEN + " , and your Ultimate\'s duration increased to ") + (ChatColor.RED + duration.toString()) + (ChatColor.GREEN + " seconds."));
		
		playerConfig.set("Air.level", level);
		playerConfig.set("Air.xp", playerConfig.getInt("Air.xp") - lvl );
		
		
		try {
			playerConfig.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	public static void AirLevelUp(Player p, Integer level, Double damage, Integer range, Integer duration, Double lvl) {
		
		UUID uuid = p.getUniqueId();
		File playerFile = new File(Rpg.playersDirectory, uuid + ".yml");
		
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		p.sendMessage((ChatColor.GREEN+ "Your ") + (ChatColor.AQUA + "Air ") + (ChatColor.GREEN + "element leveled up to level " + (ChatColor.GOLD + level.toString()) + (ChatColor.GREEN + " !")));
		p.sendMessage((ChatColor.GREEN + "Freezing Winds\' damage increased to ") + (ChatColor.RED + damage.toString()) + (ChatColor.GREEN + " , Lofty Winds\' range increased to ") + (ChatColor.RED + range.toString()) + (ChatColor.GREEN + " blocks, and your Ultimate\'s duration increased to ") + (ChatColor.RED + duration.toString()) + (ChatColor.GREEN + " seconds."));						
		
		playerConfig.set("Air.level", level);
		playerConfig.set("Air.xp", playerConfig.getInt("Air.xp") - lvl );
		
		
		try {
			playerConfig.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	
	public static void WaterLevelUp(Player p, Integer level, Double damage, Integer heal, Integer ultdamage, Double lvl) {
		
		UUID uuid = p.getUniqueId();
		File playerFile = new File(Rpg.playersDirectory, uuid + ".yml");
		
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		p.sendMessage((ChatColor.GREEN+ "Your ") + (ChatColor.AQUA + "Air ") + (ChatColor.GREEN + "element leveled up to level " + (ChatColor.GOLD + level.toString()) + (ChatColor.GREEN + " !")));
		p.sendMessage((ChatColor.GREEN + "Freezing Winds\' damage increased to ") + (ChatColor.RED + damage.toString()) + (ChatColor.GREEN + " , Lofty Winds\' range increased to ") + (ChatColor.RED + heal.toString()) + (ChatColor.GREEN + " health per second, and your Ultimate\'s duration increased to ") + (ChatColor.RED + ultdamage.toString()) + (ChatColor.GREEN + " seconds."));						
		
		playerConfig.set("Air.level", level);
		playerConfig.set("Air.xp", playerConfig.getInt("Air.xp") - lvl );
		
		
		try {
			playerConfig.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}

}
