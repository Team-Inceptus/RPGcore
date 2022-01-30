package us.teaminceptus.RPG.Dungeons.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import us.teaminceptus.RPG.Utils.CustomSkulls;

public class ElementSelection implements Listener {

	public static ItemStack lavaSelection;
	public static ItemStack fireSelection;
	public static ItemStack airSelection;
	public static ItemStack waterSelection;
	
	public static ItemStack comingSoon;
	
	public static void init() {
		LavaSelection();
		FireSelection();
		AirSelection();
		WaterSelection();
		ComingSoon();
		elementSelection();
	}
	
	public static void LavaSelection() {
		ItemStack item = CustomSkulls.getSkull("http://textures.minecraft.net/texture/1a69ccf7ad904c9a852ea2ff3f5b4e23adebf72ed12d5f24b78ce2d44b4a2");
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§4Lava");
		
		
		lore.add("§aAttack: LavaBall");
		lore.add("");
		lore.add("§aAttack 2: Pit of Eternal Heat");
		lore.add("");
		lore.add("Ultimate Attack: Wrath of the Phoenix");
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		lavaSelection = item;
	}
	
	public static void FireSelection() {
		ItemStack item = CustomSkulls.getSkull("http://textures.minecraft.net/texture/7717933c40fbf936aa9288513efe19bda4601efc0e4ecad2e023b0c1d28444b");
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§cFire");
		
		
		lore.add("§aAttack: Dragon Breath");
		lore.add("");
		lore.add("§aAttack 2: Blazing Aura");
		lore.add("");
		lore.add("Ultimate Attack: The Fury of Hell");
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		fireSelection = item;
	}
	
	public static void AirSelection() {
		ItemStack item = CustomSkulls.getSkull("http://textures.minecraft.net/texture/cb9b2a4d59781d1bec2d8278f23985e749c881b72d7876c979e71fda5bd3c");
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§bAir");
		
		
		lore.add("§3Attack: Freezing Winds");
		lore.add("");
		lore.add("§3Attack 2: Lofty Winds");
		lore.add("");
		lore.add("Ultimate Attack: Tornado");
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		airSelection = item;
	}
	
	
	public static void WaterSelection() {
		ItemStack item = CustomSkulls.getSkull("http://textures.minecraft.net/texture/2484aa5bee898a6e8960a3f9a99759b1f39f9dcb321050f714cd72b3d8a8041");
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§9Water");
		
		
		lore.add("§3Attack: Pressurized Jet");
		lore.add("");
		lore.add("§3Attack 2: Healing Geyser");
		lore.add("");
		lore.add("Ultimate Attack: Attack Dolphin");
		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		waterSelection = item;
	}
	
	
	public static void ComingSoon() {
		ItemStack item = new ItemStack(Material.COAL_BLOCK, 1);
		ItemMeta itemmeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		itemmeta.setDisplayName("§6Coming Soon");
		
		
		lore.add("This element is coming soon!");

		
		
		itemmeta.setLore(lore);
		
		item.setItemMeta(itemmeta);
		comingSoon = item;
	}
	
	public static Inventory selection;
	
	public static void elementSelection() {
		
		selection = Bukkit.createInventory(null, 9, "Element Selection");
		
		initializeItems();
		
	}
	
    // You can call this whenever you want to put the items in

	public static void initializeItems() {
        selection.setItem(0, lavaSelection);
        selection.setItem(1, fireSelection);
        selection.setItem(2, airSelection);
        selection.setItem(3, waterSelection);
        selection.setItem(4, comingSoon);
        selection.setItem(5, comingSoon);
        selection.setItem(6, comingSoon);
        selection.setItem(7, comingSoon);
        selection.setItem(8, comingSoon);
        
    }


	@EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
		
		final Player p = (Player) e.getWhoClicked();
        if (e.getInventory() == selection) {
        	
        	e.setCancelled(true);
            final ItemStack clickedItem = e.getCurrentItem();
            
            
            if (e.getSlot() >= 4) {
            	p.sendMessage(ChatColor.RED + "That element is coming soon.");
            	
            	return;
            }

            

            
           if (e.getSlot() == 0) {
            	p.sendMessage((ChatColor.GOLD + "You selected the ") + (ChatColor.DARK_RED + "LAVA ") + (ChatColor.GOLD + "element!"));
            	
            	//save inventory to a YML file here
            	
            	p.getInventory().clear();
            	
            	p.getInventory().addItem(ElementAttacks.lavaBall);
            	p.getInventory().addItem(ElementAttacks.lavaPit);
            	p.getInventory().addItem(ElementAttacks.lavaUltimate);
            	
            	
            	p.closeInventory();
            	
            
           }
           
           if (e.getSlot() == 1) {
           	p.sendMessage((ChatColor.GOLD + "You selected the ") + (ChatColor.RED + "FIRE ") + (ChatColor.GOLD + "element!"));
           	
           	//save inventory to a YML file here
           	
           	p.getInventory().clear();
           	
           	p.getInventory().addItem(ElementAttacks.fireBreath);
           	p.getInventory().addItem(ElementAttacks.fireShield);
           	p.getInventory().addItem(ElementAttacks.fireUltimate);

           	
           	
           	p.closeInventory();
           	
           
          }
           
           
           if (e.getSlot() == 2) {
              	p.sendMessage((ChatColor.GOLD + "You selected the ") + (ChatColor.AQUA + "AIR ") + (ChatColor.GOLD + "element!"));
              	
              	//save inventory to a YML file here
              	
              	p.getInventory().clear();
              	
              	p.getInventory().addItem(ElementAttacks.airFreeze);
              	p.getInventory().addItem(ElementAttacks.airTeleport);
              	p.getInventory().addItem(ElementAttacks.airUltimate);
  

              	
              	
              	p.closeInventory();
              	
              
             }
           
           
           if (e.getSlot() == 3) {
             	p.sendMessage((ChatColor.GOLD + "You selected the ") + (ChatColor.BLUE + "WATER ") + (ChatColor.GOLD + "element!"));
             	
             	//save inventory to a YML file here
             	
             	p.getInventory().clear();
             	
             	p.getInventory().addItem(ElementAttacks.waterLaser);
             	p.getInventory().addItem(ElementAttacks.waterBubble);
             	p.getInventory().addItem(ElementAttacks.waterUltimate);

             	
             	
             	p.closeInventory();
             	
             
            }
        }

    }

	
    
    
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(selection)) {
          e.setCancelled(true);
        }
    }
}
