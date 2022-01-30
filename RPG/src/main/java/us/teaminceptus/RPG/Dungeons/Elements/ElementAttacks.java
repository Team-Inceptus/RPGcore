package us.teaminceptus.RPG.Dungeons.Elements;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ElementAttacks {

	public static ItemStack lavaUltimate;
	public static ItemStack lavaPit;
	public static ItemStack lavaBall;
	public static ItemStack fireBreath;
	public static ItemStack fireShield;
	public static ItemStack fireUltimate;
	public static ItemStack airFreeze;
	public static ItemStack airTeleport;
	public static ItemStack airUltimate;
	public static ItemStack waterLaser;
	public static ItemStack waterBubble;
	public static ItemStack waterUltimate;
	public static void init() {
		LavaUltimate();
		LavaPit();
		LavaBall();
		FireBreath();
		FireShield();
		FireUltimate();
		AirFreeze();
		AirTeleport();
		AirUltimate();
		WaterLaser();
		WaterBubble();
		WaterUltimate();
	}
	
	public static void LavaUltimate() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§4Ultimate");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§4Wrath of the Phoenix: Turns you into a phoenix that shoots lava balls wherever you look!");
		lore.add("");
		lore.add("§aDuration: 10 seconds");
		lore.add("");
		lore.add("§aCooldown: 60 seconds");
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		lavaUltimate = item;
	}
	
	public static void LavaPit() {
		ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§4Summon Pit");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§4Pit of Eternal Heat: Creates a pit on the floor that constantly damages anything that touches it!");
		lore.add("");
		lore.add("§aDuration: 15 seconds");
		lore.add("");
		lore.add("§aCooldown: 20 seconds");
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		lavaPit = item;
	}
	
	public static void LavaBall() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§4Summon LavaBall");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§4LavaBall: A fireball, but better.");
		lore.add("");
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		lavaBall = item;
	}
	
	public static void FireBreath() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§cDragon's Breath");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§4Dragon Breath: Lights your enemies on fire and deals massive damage.");
		
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		fireBreath = item;
	}
	
	public static void FireShield() {
		ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§cFire Aura");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§4Fire Aura: Creates a temporary aura around you that will damage nearby enemies.");
		lore.add("");
		lore.add("§aDuration: 10 seconds");
		lore.add("");
		lore.add("§aCooldown: 15 seconds");
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		fireShield = item;
	}
	
	public static void FireUltimate() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§4Ultimate");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§4The Fury of Hell: You charge up a circle of attack, ");
		lore.add("");
		lore.add("§4anything within the circle gets hit with a massive fireball.");
		lore.add("§aCooldown: 60 seconds");
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		fireUltimate = item;
	}
	
	
	public static void AirFreeze() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§bFreezing Winds");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§3Freezing Winds: Summons a gust of ice cold wind that freezes enemies and deals damage.");
		
		
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		airFreeze = item;
	}
	
	public static void AirTeleport() {
		ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§bLofty Winds");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§3Lofty Winds: Summons a gust of wind that pushes you forward by 10 blocks.");
		lore.add("");
		lore.add("§3Cooldown: 10 seconds");
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		airTeleport = item;
	}
	
	public static void AirUltimate() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§bUltimate");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§3Tornado: Summons a torrnado that pulls any mob within its chunk and damages it.");
		
		lore.add("§aDuration: 10 seconds");
		lore.add("");
		lore.add("§aCooldown: 60 seconds");
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		airUltimate = item;
	}
	
	
	public static void WaterLaser() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§9Pressurized Jet");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§3Presurized Jet: Shoot a powerful pressurized jet of water, dealing lots of damage!");
		
		
		
		
		
		
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		waterLaser = item;
	}
	
	
	public static void WaterBubble() {
		ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§9Healing Geyser");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§3Healing Geyser: Spawn a geyser at your location that heals players within a 5x5 range!");
		lore.add("");
		lore.add("§3Duration: 10 seconds");
		lore.add("");
		lore.add("§3Cooldown: 30 seconds");
		
		
		
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		waterBubble = item;
	}
	
	public static void WaterUltimate() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§9Ultimate");
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		lore.add("§3Attack Dolphin: Summons a pet dolphin to aid you in battle,");
		lore.add("");
		lore.add("it shoots beams of water that home into enemies!");
		lore.add("§3Duration: 30 seconds");
		lore.add("");
		lore.add("§3Cooldown: 60 seconds");
		
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		waterUltimate = item;
	}
	
	
}
