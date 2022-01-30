package us.teaminceptus.RPG.Dungeons.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import us.teaminceptus.RPG.Rpg;
import us.teaminceptus.RPG.Utils.CustomSkulls;

public class Fire implements Listener{

	static Rpg plugin;
	public Fire(Rpg plugin) {
		this.plugin = plugin;
	}
	
	//dragon breath
	public static double lvl0damage = 1;
	public static double lvl1damage = 1.5;
	public static double lvl2damage = 2;
	public static double lvl3damage = 2.5;
	public static double lvl4damage = 3;
	public static double lvl5damage = 3.5;
	public static double lvl6damage = 4;
	public static double lvl7damage = 4.5;
	public static double lvl8damage = 5;
	public static double lvl9damage = 5.5;
	public static double lvl10damage = 6;
	public static double lvl11damage = 6.5;
	public static double lvl12damage = 7;
	public static double lvl13damage = 7.5;
	public static double lvl14damage = 8;
	public static double lvl15damage = 8.5;
	public static double lvl16damage = 9;
	public static double lvl17damage = 9.5;
	public static double lvl18damage = 10;
	public static double lvl19damage = 10.5;
	public static double lvl20damage = 11;
	
	
	//fire aura
	public static double alvl0damage = 2;
	public static double alvl1damage = 2.7;
	public static double alvl2damage = 3.4;
	public static double alvl3damage = 4.1;
	public static double alvl4damage = 4.8;
	public static double alvl5damage = 5.5;
	public static double alvl6damage = 6.2;
	public static double alvl7damage = 6.9;
	public static double alvl8damage = 7.6;
	public static double alvl9damage = 8.3;
	public static double alvl10damage = 9;
	public static double alvl11damage = 9.7;
	public static double alvl12damage = 10.4;
	public static double alvl13damage = 11.1;
	public static double alvl14damage = 11.8;
	public static double alvl15damage = 12.5;
	public static double alvl16damage = 13.2;
	public static double alvl17damage = 13.9;
	public static double alvl18damage = 14.6;
	public static double alvl19damage = 15.3;
	public static double alvl20damage = 16;
	
	
	//Ultimate
	
	public static double ulvl0damage = 20;
	public static double ulvl1damage = 25;
	public static double ulvl2damage = 30;
	public static double ulvl3damage = 35;
	public static double ulvl4damage = 40;
	public static double ulvl5damage = 45;
	public static double ulvl6damage = 50;
	public static double ulvl7damage = 55;
	public static double ulvl8damage = 60;
	public static double ulvl9damage = 65;
	public static double ulvl10damage = 70;
	public static double ulvl11damage = 75;
	public static double ulvl12damage = 80;
	public static double ulvl13damage = 85;
	public static double ulvl14damage = 90;
	public static double ulvl15damage = 95;
	public static double ulvl16damage = 100;
	public static double ulvl17damage = 105;
	public static double ulvl18damage = 110;
	public static double ulvl19damage = 115;
	public static double ulvl20damage = 120;
	
	public static int xp = 1;
	public static int axp = 1;
	public static int uxp = 1;
	
	
	
	
	static Map<String, Long> ultimateCooldown = new HashMap<String, Long>();
	static Map<String, Long> auraCooldown = new HashMap<String, Long>();

	
	
	@EventHandler
	
	public void onInteract(PlayerInteractEvent e) {
		
		
		final Player p = e.getPlayer();
		
		final FileConfiguration config= Rpg.getFile(p);
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§4Dragon Breath: Lights your enemies on fire and deals massive damage.")) {
				final World world = p.getWorld();
				final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
				
				as.setInvisible(true);
				as.setInvulnerable(true);
				as.setArms(false);
				as.setBasePlate(false);
				as.setMarker(true);
				as.setGravity(false);
				as.setSmall(true);

				Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
				final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
				
				new BukkitRunnable() {
					int i = 0;
					int distance = 20;
					public void run() {
						
						if(!p.isDead()) {
						
						as.teleport(as.getLocation().add(vector.normalize()));
						world.spawnParticle(Particle.LAVA, as.getEyeLocation().add(0, 0.7, 0), 5);
						if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
							
							if(!as.isDead()) {
								
								as.remove();
								cancel();
							}
						}
						
						for(Entity entity: as.getLocation().getChunk().getEntities()) {
							if(!as.isDead()) {
								if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
									if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
										if(entity instanceof LivingEntity) {
											final LivingEntity LE = (LivingEntity) entity;
											
											String uuid = p.getUniqueId().toString();
											
											if(config.getInt("Fire.level") == 0) {
												LE.damage(lvl0damage);
											}
											if(config.getInt("Fire.level") == 1) {
												LE.damage(lvl1damage);
											}
											if(config.getInt("Fire.level") == 2) {
												LE.damage(lvl2damage);
											}
											
											if(config.getInt("Fire.level") == 3) {
												LE.damage(lvl3damage);
											}
											if(config.getInt("Fire.level") == 4) {
												LE.damage(lvl4damage);
											}
											if(config.getInt("Fire.level") == 5) {
												LE.damage(lvl5damage);
											}
											
											if(config.getInt("Fire.level") == 6) {
												LE.damage(lvl6damage);
											}
											if(config.getInt("Fire.level") == 7) {
												LE.damage(lvl7damage);
											}
											if(config.getInt("Fire.level") == 8) {
												LE.damage(lvl8damage);
											}
											
											if(config.getInt("Fire.level") == 9) {
												LE.damage(lvl9damage);
											}
											if(config.getInt("Fire.level") == 10) {
												LE.damage(lvl10damage);
											}
											if(config.getInt("Fire.level") == 11) {
												LE.damage(lvl13damage);
											}
											
											if(config.getInt("Fire.level") == 12) {
												LE.damage(lvl12damage);
											}
											if(config.getInt("Fire.level") == 13) {
												LE.damage(lvl13damage);
											}
											if(config.getInt("Fire.level") == 14) {
												LE.damage(lvl14damage);
											}
											
											if(config.getInt("Fire.level") == 15) {
												LE.damage(lvl15damage);
											}
											if(config.getInt("Fire.level") == 16) {
												LE.damage(lvl16damage);
											}
											if(config.getInt("Fire.level") == 17) {
												LE.damage(lvl17damage);
											}
											if(config.getInt("Fire.level") == 18) {
												LE.damage(lvl18damage);
											}
											if(config.getInt("Fire.level") == 19) {
												LE.damage(lvl19damage);
											}
											if(config.getInt("Fire.level") == 20) {
												LE.damage(lvl20damage);
											}
											
											
											if(LE.isDead()) {
												config.set("Fire.xp", config.getInt("Fire.xp") + uxp);
											
												
												
											
												try {
													config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
												} catch (IOException e) {
												e.printStackTrace();
												}
												
											}
											
											LE.setFireTicks(60);
											

										}
									}
								}
							}
						}
						
						if (i>distance) {
							
							as.remove();
							cancel();
							
							
							
						}
						i++;
						}
						else if (p.isDead()) {
							cancel();
							as.remove();
						}
						
					}
					
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				
				
			}
			
			
			else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§4Fire Aura: Creates a temporary aura around you that will damage nearby enemies.")) {
				
				if(auraCooldown.containsKey(p.getName())) {
					
					if(auraCooldown.get(p.getName()) > System.currentTimeMillis()) {
						long timeLeft = ((auraCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("This attack can be used again in: " + timeLeft + " seconds"));
						return;
					}
					
				}
				
				
				auraCooldown.put(p.getName(), System.currentTimeMillis() + (15 * 1000));
				
				
				final BukkitTask task;
				final BukkitTask task1;
				final BukkitTask task2;
				final World world = p.getWorld();
				final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
				final ArmorStand as1 = (ArmorStand) world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
				final ArmorStand as2 = (ArmorStand) world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
				
				as.setInvisible(true);
				as.setInvulnerable(true);
				as.setSmall(true);
				as.setArms(false);
				
				as1.setInvisible(true);
				as1.setInvulnerable(true);
				as1.setSmall(true);
				as1.setArms(false);
				
				as2.setInvisible(true);
				as2.setInvulnerable(true);
				as2.setSmall(true);
				as2.setArms(false);
				
				final String uuid = p.getUniqueId().toString();
				new BukkitRunnable() {
					public void run() {
						for(Entity entity: as.getLocation().getChunk().getEntities()) {
							if(!as.isDead()) {
								if(as.getLocation().distanceSquared(entity.getLocation()) <= 10) {
									if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
										if(entity instanceof LivingEntity) {
											final LivingEntity LE = (LivingEntity) entity;
											
											
											if(config.getInt("Fire.level") == 0) {
												LE.damage(alvl0damage);
											}
											if(config.getInt("Fire.level") == 1) {
												LE.damage(alvl1damage);
											}
											if(config.getInt("Fire.level") == 2) {
												LE.damage(alvl2damage);
											}
											
											if(config.getInt("Fire.level") == 3) {
												LE.damage(alvl3damage);
											}
											if(config.getInt("Fire.level") == 4) {
												LE.damage(alvl4damage);
											}
											if(config.getInt("Fire.level") == 5) {
												LE.damage(alvl5damage);
											}
											
											if(config.getInt("Fire.level") == 6) {
												LE.damage(alvl6damage);
											}
											if(config.getInt("Fire.level") == 7) {
												LE.damage(alvl7damage);
											}
											if(config.getInt("Fire.level") == 8) {
												LE.damage(alvl8damage);
											}
											
											if(config.getInt("Fire.level") == 9) {
												LE.damage(alvl9damage);
											}
											if(config.getInt("Fire.level") == 10) {
												LE.damage(alvl10damage);
											}
											if(config.getInt("Fire.level") == 11) {
												LE.damage(alvl13damage);
											}
											
											if(config.getInt("Fire.level") == 12) {
												LE.damage(alvl12damage);
											}
											if(config.getInt("Fire.level") == 13) {
												LE.damage(alvl13damage);
											}
											if(config.getInt("Fire.level") == 14) {
												LE.damage(alvl14damage);
											}
											
											if(config.getInt("Fire.level") == 15) {
												LE.damage(alvl15damage);
											}
											if(config.getInt("Fire.level") == 16) {
												LE.damage(alvl16damage);
											}
											if(config.getInt("Fire.level") == 17) {
												LE.damage(alvl17damage);
											}
											if(config.getInt("Fire.level") == 18) {
												LE.damage(alvl18damage);
											}
											if(config.getInt("Fire.level") == 19) {
												LE.damage(alvl19damage);
											}
											if(config.getInt("Fire.level") == 20) {
												LE.damage(alvl20damage);
											}
											
											
											if(LE.isDead()) {
												config.set("Fire.xp", config.getInt("Fire.xp") + axp);
												
												
												
											
												try {
													config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
												} catch (IOException e) {
												e.printStackTrace();
												}
												
											}
											
											LE.setFireTicks(60);
											
											
											
											
											

										}
									}
								}
							}
						}
						if(as.isDead()) {
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				new BukkitRunnable() {
					public void run() {
						for(Entity entity: as1.getLocation().getChunk().getEntities()) {
							if(!as1.isDead()) {
								if(as1.getLocation().distanceSquared(entity.getLocation()) <= 10) {
									if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
										if(entity instanceof LivingEntity) {
											final LivingEntity LE = (LivingEntity) entity;
											
											if(config.getInt("Fire.level") == 0) {
												LE.damage(alvl0damage);
											}
											if(config.getInt("Fire.level") == 1) {
												LE.damage(alvl1damage);
											}
											if(config.getInt("Fire.level") == 2) {
												LE.damage(alvl2damage);
											}
											
											if(config.getInt("Fire.level") == 3) {
												LE.damage(alvl3damage);
											}
											if(config.getInt("Fire.level") == 4) {
												LE.damage(alvl4damage);
											}
											if(config.getInt("Fire.level") == 5) {
												LE.damage(alvl5damage);
											}
											
											if(config.getInt("Fire.level") == 6) {
												LE.damage(alvl6damage);
											}
											if(config.getInt("Fire.level") == 7) {
												LE.damage(alvl7damage);
											}
											if(config.getInt("Fire.level") == 8) {
												LE.damage(alvl8damage);
											}
											
											if(config.getInt("Fire.level") == 9) {
												LE.damage(alvl9damage);
											}
											if(config.getInt("Fire.level") == 10) {
												LE.damage(alvl10damage);
											}
											if(config.getInt("Fire.level") == 11) {
												LE.damage(alvl13damage);
											}
											
											if(config.getInt("Fire.level") == 12) {
												LE.damage(alvl12damage);
											}
											if(config.getInt("Fire.level") == 13) {
												LE.damage(alvl13damage);
											}
											if(config.getInt("Fire.level") == 14) {
												LE.damage(alvl14damage);
											}
											
											if(config.getInt("Fire.level") == 15) {
												LE.damage(alvl15damage);
											}
											if(config.getInt("Fire.level") == 16) {
												LE.damage(alvl16damage);
											}
											if(config.getInt("Fire.level") == 17) {
												LE.damage(alvl17damage);
											}
											if(config.getInt("Fire.level") == 18) {
												LE.damage(alvl18damage);
											}
											if(config.getInt("Fire.level") == 19) {
												LE.damage(alvl19damage);
											}
											if(config.getInt("Fire.level") == 20) {
												LE.damage(alvl20damage);
											}
											
											
											if(LE.isDead()) {
												config.set("Fire.xp", config.getInt("Fire.xp") + axp);
											
												
												
											
												try {
													config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
												} catch (IOException e) {
												e.printStackTrace();
												}
												
											}
											LE.setFireTicks(60);
											
											
											
											
											

										}
									}
								}
							}
						}
						if(as1.isDead()) {
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				new BukkitRunnable() {
					public void run() {
						for(Entity entity: as.getLocation().getChunk().getEntities()) {
							if(!as2.isDead()) {
								if(as2.getLocation().distanceSquared(entity.getLocation()) <= 10) {
									if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
										if(entity instanceof LivingEntity) {
											final LivingEntity LE = (LivingEntity) entity;
											
											if(config.getInt("Fire.level") == 0) {
												LE.damage(alvl0damage);
											}
											if(config.getInt("Fire.level") == 1) {
												LE.damage(alvl1damage);
											}
											if(config.getInt("Fire.level") == 2) {
												LE.damage(alvl2damage);
											}
											
											if(config.getInt("Fire.level") == 3) {
												LE.damage(alvl3damage);
											}
											if(config.getInt("Fire.level") == 4) {
												LE.damage(alvl4damage);
											}
											if(config.getInt("Fire.level") == 5) {
												LE.damage(alvl5damage);
											}
											
											if(config.getInt("Fire.level") == 6) {
												LE.damage(alvl6damage);
											}
											if(config.getInt("Fire.level") == 7) {
												LE.damage(alvl7damage);
											}
											if(config.getInt("Fire.level") == 8) {
												LE.damage(alvl8damage);
											}
											
											if(config.getInt("Fire.level") == 9) {
												LE.damage(alvl9damage);
											}
											if(config.getInt("Fire.level") == 10) {
												LE.damage(alvl10damage);
											}
											if(config.getInt("Fire.level") == 11) {
												LE.damage(alvl13damage);
											}
											
											if(config.getInt("Fire.level") == 12) {
												LE.damage(alvl12damage);
											}
											if(config.getInt("Fire.level") == 13) {
												LE.damage(alvl13damage);
											}
											if(config.getInt("Fire.level") == 14) {
												LE.damage(alvl14damage);
											}
											
											if(config.getInt("Fire.level") == 15) {
												LE.damage(alvl15damage);
											}
											if(config.getInt("Fire.level") == 16) {
												LE.damage(alvl16damage);
											}
											if(config.getInt("Fire.level") == 17) {
												LE.damage(alvl17damage);
											}
											if(config.getInt("Fire.level") == 18) {
												LE.damage(alvl18damage);
											}
											if(config.getInt("Fire.level") == 19) {
												LE.damage(alvl19damage);
											}
											if(config.getInt("Fire.level") == 20) {
												LE.damage(alvl20damage);
											}
											
											
											if(LE.isDead()) {
												config.set("Fire.xp", config.getInt("Fire.xp") + axp);
											
												
												
											
												try {
													config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
												} catch (IOException e) {
												e.printStackTrace();
												}
												
											}
											LE.setFireTicks(60);
											
											
											
											
											

										}
									}
								}
							}
						}
						if(as2.isDead()) {
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				
				
				task = new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 2f;
							float angle = 0f;
							
							public void run() {
								
								if(!as.isDead()) {
							
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 118, (int) 0), 1);
									as.teleport(new Location(world, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z));
									world.spawnParticle(Particle.REDSTONE, as.getEyeLocation(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
									if(p.isDead() || as.isDead()) {
										cancel();
									}
								
								}
								
								
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task1 = new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 2f;
							float angle = 120f;
							
							public void run() {
								
								if(!as1.isDead()) {
							
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 118, (int) 0), 1);
									as1.teleport(new Location(world, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z));
									world.spawnParticle(Particle.REDSTONE, as1.getEyeLocation(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
									
									if(p.isDead() || as1.isDead()) {
										cancel();
									}
								
								}
								
								
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task2 = new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 2f;
							float angle = 240f;
							
							public void run() {
								
								if(!as2.isDead()) {
							
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 118, (int) 0), 1);
									as2.teleport(new Location(world, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z));
									world.spawnParticle(Particle.REDSTONE, as2.getEyeLocation(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
									
									if(p.isDead() || as2.isDead()) {
										cancel();
									}
								}
								
								
								
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				new BukkitRunnable() {
					public void run() {
						as.remove();
						as1.remove();
						as2.remove();
						
						
						task.cancel();
						task1.cancel();
						task2.cancel();
						
					}
				}.runTaskLater(plugin, 200L);
				
			}
			
			else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§4anything within the circle gets hit with a massive fireball.")) {
				
				if(ultimateCooldown.containsKey(p.getName())) {
					
					if(ultimateCooldown.get(p.getName()) > System.currentTimeMillis()) {
						long timeLeft = ((ultimateCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("Your ultimate can be used again in: " + timeLeft + " seconds"));
						return;
					}
					
				}
				
				
				ultimateCooldown.put(p.getName(), System.currentTimeMillis() + (10 * 1000));
				
				final BukkitTask task;
				final World world = p.getWorld();
				final Location loc = p.getLocation();

				
				final ArmorStand origin = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
				origin.setInvisible(true);
				origin.setInvulnerable(true);
				origin.setMarker(true);
				final String uuid = p.getUniqueId().toString();
				
				task = new BukkitRunnable() {
					public void run() {
						
						p.teleport(loc);
						if(p.isDead()) {
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				

				
				final float radius = 2f; 
				final float radius1 = 3f; 
				final float radius2 = 4f; 
				final float radius3 = 5f;
				final float radius4 = 6f;
				
				
				final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 
				
				final Particle.DustOptions dust1 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 0), 1); 

				final Particle.DustOptions dust2 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 171, (int) 0), 1); 
				
				final Particle.DustOptions dust3 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 118, (int) 0), 1); 
				
				final Particle.DustOptions dust4 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 0, (int) 0), 1); 
				
				
				final BukkitTask inner;
				
				final BukkitTask inner1;

				final BukkitTask inner2;
				
				final BukkitTask inner3;
				
				final BukkitTask inner4;
				
				final BukkitTask inner5; 
				
				final BukkitTask c2;
				
				final BukkitTask c21;

				final BukkitTask c22;
				
				final BukkitTask c23;
				
				final BukkitTask c24;
				
				final BukkitTask c25;
				
				
				final BukkitTask c3;
				
				final BukkitTask c31;

				final BukkitTask c32;
				
				final BukkitTask c33;
				
				final BukkitTask c34;
				
				final BukkitTask c35;
				
				
				final BukkitTask c4;
				
				final BukkitTask c41;

				final BukkitTask c42;
				
				final BukkitTask c43;
				
				final BukkitTask c44;
				
				final BukkitTask c45; 
				
				
				final BukkitTask c5;
				
				final BukkitTask c51;

				final BukkitTask c52;
				
				final BukkitTask c53;
				
				final BukkitTask c54;
				
				final BukkitTask c55; 
				
				inner = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				inner1 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				inner2 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				inner3 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				inner4 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				inner5 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c2 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 20L, 1L);
				
				
				c21 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 20L, 1L);
				
				c22 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 20L, 1L);
				
				
				
				c23 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 20L, 1L);
				
				
				c24 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 20L, 1L);
				
				c25 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 20L, 1L);
				
				
				c3 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 40L, 1L);
				
				
				c31 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 40L, 1L);
				
				
				c32 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 40L, 1L);
				
				
				
				c33 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 40L, 1L);
				
				
				c34 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 40L, 1L);
				
				
				c35 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 40L, 1L);
				
				c4 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 60L, 1L);
				
				
				c41 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 60L, 1L);
				
				
				c42 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 60L, 1L);
				
				
				
				c43 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 60L, 1L);
				
				
				c44 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 60L, 1L);
				
				
				c45 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 60L, 1L);
				
				
				
				c5 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 80L, 1L);
				
				
				c51 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 80L, 1L);
				
				
				c52 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 80L, 1L);
				
				
				c53 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 80L, 1L);
				
				
				c54 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 80L, 1L);
				
				
				c55 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 80L, 1L);
				
				
				
				new BukkitRunnable() {
					public void run() {
						

						inner.cancel();
						inner1.cancel();
						inner2.cancel();
						inner3.cancel();
						inner4.cancel();
						inner5.cancel();
						c2.cancel();
						c21.cancel();
						c22.cancel();
						c23.cancel();
						c24.cancel();
						c25.cancel();
						c3.cancel();
						c31.cancel();
						c32.cancel();
						c33.cancel();
						c34.cancel();
						c35.cancel();
						c4.cancel();
						c41.cancel();
						c42.cancel();
						c43.cancel();
						c44.cancel();
						c45.cancel();
						c5.cancel();
						c51.cancel();
						c52.cancel();
						c53.cancel();
						c54.cancel();
						c55.cancel();
					}
				}.runTaskLater(plugin, 100L);
				
				
				final BukkitTask inner0;
				
				final BukkitTask inner11;

				final BukkitTask inner21;
				
				final BukkitTask inner31;
				
				final BukkitTask inner41;
				
				final BukkitTask inner51;
				

				
				final BukkitTask c20;
				
				final BukkitTask c211;

				final BukkitTask c221;
				
				final BukkitTask c231;
				
				final BukkitTask c241;
				
				final BukkitTask c251;
				

				
				final BukkitTask c30;
				
				final BukkitTask c311;

				final BukkitTask c321;
				
				final BukkitTask c331;
				
				final BukkitTask c341;
				
				final BukkitTask c351;
				
				
				final BukkitTask c40;
				
				final BukkitTask c411;

				final BukkitTask c421;
				
				final BukkitTask c431;
				
				final BukkitTask c441;
				
				final BukkitTask c451;
				

				
				final BukkitTask c50;
				
				final BukkitTask c511;

				final BukkitTask c521;
				
				final BukkitTask c531;
				
				final BukkitTask c541;
				
				final BukkitTask c551;
				
				
				inner0 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				inner11 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				inner21 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				
				inner31 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				inner41 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				inner51 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c20 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c211 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				c221 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				
				c231 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c241 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				c251 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c30 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c311 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c321 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				
				c331 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c341 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c351 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				c40 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c411 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c421 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				
				c431 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c441 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c451 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				
				c50 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c511 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c521 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c531 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c541 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				
				c551 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!p.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, p.getLocation().getX()+x, p.getLocation().getY() + 1, p.getLocation().getZ()+z, 0, 0, 0, 0, dust4);
							
							angle += 0.3;
							
							
							
							if(p.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 100L, 1L);
				
				


				
				
				new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 2f;
							float angle = 0f;
							
							public void run() {
								
								
								if(angle <= 6) {	
									
									
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z), EntityType.ARMOR_STAND);
									as.setInvisible(true);
									as.setInvulnerable(true);
									as.setSmall(true);
									
									new BukkitRunnable() {
										public void run() {
											if(origin.isDead()) {
												as.remove();
												cancel();
											}
										}
									}.runTaskTimer(plugin, 0L, 1L);
									
									new BukkitRunnable() {
										public void run() {
											
											if(!origin.isDead()) {
												
												for(Entity entity: as.getLocation().getChunk().getEntities()) {
													if(!as.isDead()) {
														if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
															if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																if(entity instanceof LivingEntity) {
																	final LivingEntity LE = (LivingEntity) entity;
																
																	final ArmorStand as1 = (ArmorStand) world.spawnEntity(as.getEyeLocation().add(0, 7, 0), EntityType.ARMOR_STAND);
																	as1.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/9c2e9d8395cacd9922869c15373cf7cb16da0a5ce5f3c632b19ceb3929c9a11"));
																	as1.setInvisible(true);
																	as1.setInvulnerable(true);
																	as1.setSmall(false);
																	
																	new BukkitRunnable() {
																		public void run() {
																			if(!as.isDead()) {
																				
																			if(as1.getTargetBlockExact(1) != null && !(as1.getTargetBlockExact(1).isPassable())) {
																					
																					if(!as1.isDead()) {
																						
																						world.createExplosion(as1.getLocation(), 0.7f, false, false);
																						as1.remove();
																						as.remove();
																						cancel();
																					}
																				}
																			if(as1.isOnGround()) {
																				world.createExplosion(as1.getLocation(), 0.7f, false, false);
																				as1.remove();
																				as.remove();
																				cancel();
																			}
																			for(Entity entity: as.getLocation().getChunk().getEntities()) {
																				if(!as.isDead()) {
																					if(as.getLocation().distanceSquared(entity.getLocation()) <= 2) {
																						if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																							if(entity instanceof LivingEntity) {
																								final LivingEntity LE1 = (LivingEntity) entity;
																								
																								if(config.getInt("Fire.level") == 0) {
																									LE.damage(ulvl0damage);
																								}
																								if(config.getInt("Fire.level") == 1) {
																									LE.damage(ulvl1damage);
																								}
																								if(config.getInt("Fire.level") == 2) {
																									LE.damage(ulvl2damage);
																								}
																								
																								if(config.getInt("Fire.level") == 3) {
																									LE.damage(ulvl3damage);
																								}
																								if(config.getInt("Fire.level") == 4) {
																									LE.damage(ulvl4damage);
																								}
																								if(config.getInt("Fire.level") == 5) {
																									LE.damage(ulvl5damage);
																								}
																								
																								if(config.getInt("Fire.level") == 6) {
																									LE.damage(ulvl6damage);
																								}
																								if(config.getInt("Fire.level") == 7) {
																									LE.damage(ulvl7damage);
																								}
																								if(config.getInt("Fire.level") == 8) {
																									LE.damage(ulvl8damage);
																								}
																								
																								if(config.getInt("Fire.level") == 9) {
																									LE.damage(ulvl9damage);
																								}
																								if(config.getInt("Fire.level") == 10) {
																									LE.damage(ulvl10damage);
																								}
																								if(config.getInt("Fire.level") == 11) {
																									LE.damage(ulvl13damage);
																								}
																								
																								if(config.getInt("Fire.level") == 12) {
																									LE.damage(ulvl12damage);
																								}
																								if(config.getInt("Fire.level") == 13) {
																									LE.damage(ulvl13damage);
																								}
																								if(config.getInt("Fire.level") == 14) {
																									LE.damage(ulvl14damage);
																								}
																								
																								if(config.getInt("Fire.level") == 15) {
																									LE.damage(ulvl15damage);
																								}
																								if(config.getInt("Fire.level") == 16) {
																									LE.damage(ulvl16damage);
																								}
																								if(config.getInt("Fire.level") == 17) {
																									LE.damage(ulvl17damage);
																								}
																								if(config.getInt("Fire.level") == 18) {
																									LE.damage(ulvl18damage);
																								}
																								if(config.getInt("Fire.level") == 19) {
																									LE.damage(ulvl19damage);
																								}
																								if(config.getInt("Fire.level") == 20) {
																									LE.damage(ulvl20damage);
																								}
																								
																								
																								if(LE.isDead()) {
																									config.set("Fire.xp", config.getInt("Fire.xp") + uxp);
																								
																									
																									
																								
																									try {
																										config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
																									} catch (IOException e) {
																									e.printStackTrace();
																									}
																									
																								}
																								world.createExplosion(as1.getLocation(), 0.7f, false, false);
																								as1.remove();
																								as.remove();
																								cancel();
																								
																								
																							}
																						}
																					}
																				}
																			}
																			}
																			else if(as.isDead()) {
																				as1.remove();
																				cancel();
																			}
																		}
																	}.runTaskTimer(plugin, 0L, 1L); 
																	
																														
																
																

																}
															}
														}
													}
												}
											
											}
											

										}
									}.runTaskLater(plugin, 10L);
									
									
									
								}
								
								if(angle > 6) {
									cancel();
								}
						
								angle += 0.3;
									
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskLater(plugin, 100L);
				
				
				new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 3f;
							float angle = 0f;
							
							public void run() {
								
								
								if(angle <= 7) {	
									
									
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z), EntityType.ARMOR_STAND);
									as.setInvisible(true);
									as.setInvulnerable(true);
									as.setSmall(true);
									
									new BukkitRunnable() {
										public void run() {
											if(origin.isDead()) {
												as.remove();
												cancel();
											}
										}
									}.runTaskTimer(plugin, 0L, 1L);
									
									new BukkitRunnable() {
										public void run() {
											
											if(!origin.isDead()) {
												
												for(Entity entity: as.getLocation().getChunk().getEntities()) {
													if(!as.isDead()) {
														if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
															if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																if(entity instanceof LivingEntity) {
																	final LivingEntity LE = (LivingEntity) entity;
																
																	final ArmorStand as1 = (ArmorStand) world.spawnEntity(as.getEyeLocation().add(0, 7, 0), EntityType.ARMOR_STAND);
																	as1.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/9c2e9d8395cacd9922869c15373cf7cb16da0a5ce5f3c632b19ceb3929c9a11"));
																	as1.setInvisible(true);
																	as1.setInvulnerable(true);
																	as1.setSmall(false);
																	
																	new BukkitRunnable() {
																		public void run() {
																			if(!as.isDead()) {
																				
																			if(as1.getTargetBlockExact(1) != null && !(as1.getTargetBlockExact(1).isPassable())) {
																					
																					if(!as1.isDead()) {
																						
																						world.createExplosion(as1.getLocation(), 0.7f, false, false);
																						as1.remove();
																						as.remove();
																						cancel();
																					}
																				}
																			
																			if(as1.isOnGround()) {
																				world.createExplosion(as1.getLocation(), 0.7f, false, false);
																				as1.remove();
																				as.remove();
																				cancel();
																			}
																			for(Entity entity: as.getLocation().getChunk().getEntities()) {
																				if(!as.isDead()) {
																					if(as.getLocation().distanceSquared(entity.getLocation()) <= 2) {
																						if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																							if(entity instanceof LivingEntity) {
																								final LivingEntity LE1 = (LivingEntity) entity;
																								
																								if(config.getInt("Fire.level") == 0) {
																									LE.damage(ulvl0damage);
																								}
																								if(config.getInt("Fire.level") == 1) {
																									LE.damage(ulvl1damage);
																								}
																								if(config.getInt("Fire.level") == 2) {
																									LE.damage(ulvl2damage);
																								}
																								
																								if(config.getInt("Fire.level") == 3) {
																									LE.damage(ulvl3damage);
																								}
																								if(config.getInt("Fire.level") == 4) {
																									LE.damage(ulvl4damage);
																								}
																								if(config.getInt("Fire.level") == 5) {
																									LE.damage(ulvl5damage);
																								}
																								
																								if(config.getInt("Fire.level") == 6) {
																									LE.damage(ulvl6damage);
																								}
																								if(config.getInt("Fire.level") == 7) {
																									LE.damage(ulvl7damage);
																								}
																								if(config.getInt("Fire.level") == 8) {
																									LE.damage(ulvl8damage);
																								}
																								
																								if(config.getInt("Fire.level") == 9) {
																									LE.damage(ulvl9damage);
																								}
																								if(config.getInt("Fire.level") == 10) {
																									LE.damage(ulvl10damage);
																								}
																								if(config.getInt("Fire.level") == 11) {
																									LE.damage(ulvl13damage);
																								}
																								
																								if(config.getInt("Fire.level") == 12) {
																									LE.damage(ulvl12damage);
																								}
																								if(config.getInt("Fire.level") == 13) {
																									LE.damage(ulvl13damage);
																								}
																								if(config.getInt("Fire.level") == 14) {
																									LE.damage(ulvl14damage);
																								}
																								
																								if(config.getInt("Fire.level") == 15) {
																									LE.damage(ulvl15damage);
																								}
																								if(config.getInt("Fire.level") == 16) {
																									LE.damage(ulvl16damage);
																								}
																								if(config.getInt("Fire.level") == 17) {
																									LE.damage(ulvl17damage);
																								}
																								if(config.getInt("Fire.level") == 18) {
																									LE.damage(ulvl18damage);
																								}
																								if(config.getInt("Fire.level") == 19) {
																									LE.damage(ulvl19damage);
																								}
																								if(config.getInt("Fire.level") == 20) {
																									LE.damage(ulvl20damage);
																								}
																								
																								
																								if(LE.isDead()) {
																									config.set("Fire.xp", config.getInt("Fire.xp") + uxp);
																								
																									
																									
																								
																									try {
																										config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
																									} catch (IOException e) {
																									e.printStackTrace();
																									}
																									
																								}
																								world.createExplosion(as1.getLocation(), 0.7f, false, false);
																								as1.remove();
																								as.remove();
																								cancel();
																								
																								
																							}
																						}
																					}
																				}
																			}
																			}
																			else if(as.isDead()) {
																				as1.remove();
																				cancel();
																			}
																		}
																	}.runTaskTimer(plugin, 0L, 1L); 
																	
																														
																
																

																}
															}
														}
													}
												}
											
											}
											

										}
									}.runTaskLater(plugin, 10L);
									
									
									
								}
								
								if(angle > 7) {
									cancel();
								}
						
								angle += 0.4;
									
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskLater(plugin, 100L);
				
				
				new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 4f;
							float angle = 0f;
							
							public void run() {
								
								
								if(angle <= 9) {	
									
									
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z), EntityType.ARMOR_STAND);
									as.setInvisible(true);
									as.setInvulnerable(true);
									as.setSmall(true);
									
									new BukkitRunnable() {
										public void run() {
											if(origin.isDead()) {
												as.remove();
												cancel();
											}
										}
									}.runTaskTimer(plugin, 0L, 1L);
									
									new BukkitRunnable() {
										public void run() {
											
											if(!origin.isDead()) {
												
												for(Entity entity: as.getLocation().getChunk().getEntities()) {
													if(!as.isDead()) {
														if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
															if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																if(entity instanceof LivingEntity) {
																	final LivingEntity LE = (LivingEntity) entity;
																
																	final ArmorStand as1 = (ArmorStand) world.spawnEntity(as.getEyeLocation().add(0, 7, 0), EntityType.ARMOR_STAND);
																	as1.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/9c2e9d8395cacd9922869c15373cf7cb16da0a5ce5f3c632b19ceb3929c9a11"));
																	as1.setInvisible(true);
																	as1.setInvulnerable(true);
																	as1.setSmall(false);
																	
																	new BukkitRunnable() {
																		public void run() {
																			if(!as.isDead()) {
																				
																			if(as1.getTargetBlockExact(1) != null && !(as1.getTargetBlockExact(1).isPassable())) {
																					
																					if(!as1.isDead()) {
																						
																						world.createExplosion(as1.getLocation(), 0.7f, false, false);
																						as1.remove();
																						as.remove();
																						cancel();
																					}
																				}
																			if(as1.isOnGround()) {
																				world.createExplosion(as1.getLocation(), 0.7f, false, false);
																				as1.remove();
																				as.remove();
																				cancel();
																			}
																			for(Entity entity: as.getLocation().getChunk().getEntities()) {
																				if(!as.isDead()) {
																					if(as.getLocation().distanceSquared(entity.getLocation()) <= 2) {
																						if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																							if(entity instanceof LivingEntity) {
																								final LivingEntity LE1 = (LivingEntity) entity;
																								
																								if(config.getInt("Fire.level") == 0) {
																									LE.damage(ulvl0damage);
																								}
																								if(config.getInt("Fire.level") == 1) {
																									LE.damage(ulvl1damage);
																								}
																								if(config.getInt("Fire.level") == 2) {
																									LE.damage(ulvl2damage);
																								}
																								
																								if(config.getInt("Fire.level") == 3) {
																									LE.damage(ulvl3damage);
																								}
																								if(config.getInt("Fire.level") == 4) {
																									LE.damage(ulvl4damage);
																								}
																								if(config.getInt("Fire.level") == 5) {
																									LE.damage(ulvl5damage);
																								}
																								
																								if(config.getInt("Fire.level") == 6) {
																									LE.damage(ulvl6damage);
																								}
																								if(config.getInt("Fire.level") == 7) {
																									LE.damage(ulvl7damage);
																								}
																								if(config.getInt("Fire.level") == 8) {
																									LE.damage(ulvl8damage);
																								}
																								
																								if(config.getInt("Fire.level") == 9) {
																									LE.damage(ulvl9damage);
																								}
																								if(config.getInt("Fire.level") == 10) {
																									LE.damage(ulvl10damage);
																								}
																								if(config.getInt("Fire.level") == 11) {
																									LE.damage(ulvl13damage);
																								}
																								
																								if(config.getInt("Fire.level") == 12) {
																									LE.damage(ulvl12damage);
																								}
																								if(config.getInt("Fire.level") == 13) {
																									LE.damage(ulvl13damage);
																								}
																								if(config.getInt("Fire.level") == 14) {
																									LE.damage(ulvl14damage);
																								}
																								
																								if(config.getInt("Fire.level") == 15) {
																									LE.damage(ulvl15damage);
																								}
																								if(config.getInt("Fire.level") == 16) {
																									LE.damage(ulvl16damage);
																								}
																								if(config.getInt("Fire.level") == 17) {
																									LE.damage(ulvl17damage);
																								}
																								if(config.getInt("Fire.level") == 18) {
																									LE.damage(ulvl18damage);
																								}
																								if(config.getInt("Fire.level") == 19) {
																									LE.damage(ulvl19damage);
																								}
																								if(config.getInt("Fire.level") == 20) {
																									LE.damage(ulvl20damage);
																								}
																								
																								
																								if(LE.isDead()) {
																									config.set("Fire.xp", config.getInt("Fire.xp") + uxp);
																								
																									
																									
																								
																									try {
																										config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
																									} catch (IOException e) {
																									e.printStackTrace();
																									}
																									
																								}
																								world.createExplosion(as1.getLocation(), 0.7f, false, false);
																								as1.remove();
																								as.remove();
																								cancel();
																								
																								
																							}
																						}
																					}
																				}
																			}
																			}
																			else if(as.isDead()) {
																				as1.remove();
																				cancel();
																			}
																		}
																	}.runTaskTimer(plugin, 0L, 1L); 
																	
																														
																
																

																}
															}
														}
													}
												}
											
											}
											

										}
									}.runTaskLater(plugin, 10L);
									
									
									
								}
								
								if(angle > 9) {
									cancel();
								}
						
								angle += 0.5;
									
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskLater(plugin, 100L);
				
				
				
				
				new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 5f;
							float angle = 0f;
							
							public void run() {
								
								
								if(angle <= 11) {	
									
									
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z), EntityType.ARMOR_STAND);
									as.setInvisible(true);
									as.setInvulnerable(true);
									as.setSmall(true);
									
									new BukkitRunnable() {
										public void run() {
											if(origin.isDead()) {
												as.remove();
												cancel();
											}
										}
									}.runTaskTimer(plugin, 0L, 1L);
									
									new BukkitRunnable() {
										public void run() {
											
											if(!origin.isDead()) {
												
												for(Entity entity: as.getLocation().getChunk().getEntities()) {
													if(!as.isDead()) {
														if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
															if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																if(entity instanceof LivingEntity) {
																	final LivingEntity LE = (LivingEntity) entity;
																
																	final ArmorStand as1 = (ArmorStand) world.spawnEntity(as.getEyeLocation().add(0, 7, 0), EntityType.ARMOR_STAND);
																	as1.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/9c2e9d8395cacd9922869c15373cf7cb16da0a5ce5f3c632b19ceb3929c9a11"));
																	as1.setInvisible(true);
																	as1.setInvulnerable(true);
																	as1.setSmall(false);
																	
																	new BukkitRunnable() {
																		public void run() {
																			if(!as.isDead()) {
																				
																			if(as1.getTargetBlockExact(1) != null && !(as1.getTargetBlockExact(1).isPassable())) {
																					
																					if(!as1.isDead()) {
																						
																						world.createExplosion(as1.getLocation(), 0.7f, false, false);
																						as1.remove();
																						as.remove();
																						cancel();
																					}
																				}
																			if(as1.isOnGround()) {
																				world.createExplosion(as1.getLocation(), 0.7f, false, false);
																				as1.remove();
																				as.remove();
																				cancel();
																			}
																			for(Entity entity: as.getLocation().getChunk().getEntities()) {
																				if(!as.isDead()) {
																					if(as.getLocation().distanceSquared(entity.getLocation()) <= 2) {
																						if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																							if(entity instanceof LivingEntity) {
																								final LivingEntity LE1 = (LivingEntity) entity;
																								
																								if(config.getInt("Fire.level") == 0) {
																									LE.damage(ulvl0damage);
																								}
																								if(config.getInt("Fire.level") == 1) {
																									LE.damage(ulvl1damage);
																								}
																								if(config.getInt("Fire.level") == 2) {
																									LE.damage(ulvl2damage);
																								}
																								
																								if(config.getInt("Fire.level") == 3) {
																									LE.damage(ulvl3damage);
																								}
																								if(config.getInt("Fire.level") == 4) {
																									LE.damage(ulvl4damage);
																								}
																								if(config.getInt("Fire.level") == 5) {
																									LE.damage(ulvl5damage);
																								}
																								
																								if(config.getInt("Fire.level") == 6) {
																									LE.damage(ulvl6damage);
																								}
																								if(config.getInt("Fire.level") == 7) {
																									LE.damage(ulvl7damage);
																								}
																								if(config.getInt("Fire.level") == 8) {
																									LE.damage(ulvl8damage);
																								}
																								
																								if(config.getInt("Fire.level") == 9) {
																									LE.damage(ulvl9damage);
																								}
																								if(config.getInt("Fire.level") == 10) {
																									LE.damage(ulvl10damage);
																								}
																								if(config.getInt("Fire.level") == 11) {
																									LE.damage(ulvl13damage);
																								}
																								
																								if(config.getInt("Fire.level") == 12) {
																									LE.damage(ulvl12damage);
																								}
																								if(config.getInt("Fire.level") == 13) {
																									LE.damage(ulvl13damage);
																								}
																								if(config.getInt("Fire.level") == 14) {
																									LE.damage(ulvl14damage);
																								}
																								
																								if(config.getInt("Fire.level") == 15) {
																									LE.damage(ulvl15damage);
																								}
																								if(config.getInt("Fire.level") == 16) {
																									LE.damage(ulvl16damage);
																								}
																								if(config.getInt("Fire.level") == 17) {
																									LE.damage(ulvl17damage);
																								}
																								if(config.getInt("Fire.level") == 18) {
																									LE.damage(ulvl18damage);
																								}
																								if(config.getInt("Fire.level") == 19) {
																									LE.damage(ulvl19damage);
																								}
																								if(config.getInt("Fire.level") == 20) {
																									LE.damage(ulvl20damage);
																								}
																								
																								
																								if(LE.isDead()) {
																									config.set("Fire.xp", config.getInt("Fire.xp") + uxp);
																								
																									
																									
																								
																									try {
																										config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
																									} catch (IOException e) {
																									e.printStackTrace();
																									}
																									
																								}
																								world.createExplosion(as1.getLocation(), 0.7f, false, false);
																								as1.remove();
																								as.remove();
																								cancel();
																								
																								
																							}
																						}
																					}
																				}
																			}
																			}
																			else if(as.isDead()) {
																				as1.remove();
																				cancel();
																			}
																		}
																	}.runTaskTimer(plugin, 0L, 1L); 
																	
																														
																
																

																}
															}
														}
													}
												}
											
											}
											

										}
									}.runTaskLater(plugin, 10L);
									
									
									
								}
								
								if(angle > 11) {
									cancel();
								}
						
								angle += 0.7;
									
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskLater(plugin, 100L);
				
				
				new BukkitRunnable() {
					public void run() {
						new BukkitRunnable() {
							float radius = 6f;
							float angle = 0f;
							
							public void run() {
								
								
								if(angle <= 13) {	
									
									
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z), EntityType.ARMOR_STAND);
									as.setInvisible(true);
									as.setInvulnerable(true);
									as.setSmall(true);
									
									new BukkitRunnable() {
										public void run() {
											if(origin.isDead()) {
												as.remove();
												cancel();
											}
										}
									}.runTaskTimer(plugin, 0L, 1L);
									
									new BukkitRunnable() {
										public void run() {
											
											if(!origin.isDead()) {
												
												for(Entity entity: as.getLocation().getChunk().getEntities()) {
													if(!as.isDead()) {
														if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
															if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																if(entity instanceof LivingEntity) {
																	final LivingEntity LE = (LivingEntity) entity;
																
																	final ArmorStand as1 = (ArmorStand) world.spawnEntity(as.getEyeLocation().add(0, 7, 0), EntityType.ARMOR_STAND);
																	as1.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/9c2e9d8395cacd9922869c15373cf7cb16da0a5ce5f3c632b19ceb3929c9a11"));
																	as1.setInvisible(true);
																	as1.setInvulnerable(true);
																	as1.setSmall(false);
																	
																	new BukkitRunnable() {
																		public void run() {
																			if(!as.isDead()) {
																				
																			if(as1.getTargetBlockExact(1) != null && !(as1.getTargetBlockExact(1).isPassable())) {
																					
																					if(!as1.isDead()) {
																						
																						world.createExplosion(as1.getLocation(), 0.7f, false, false);
																						as1.remove();
																						as.remove();
																						cancel();
																					}
																				}
																			
																			if(as1.isOnGround()) {
																				world.createExplosion(as1.getLocation(), 0.7f, false, false);
																				as1.remove();
																				as.remove();
																				cancel();
																			}
																			for(Entity entity: as.getLocation().getChunk().getEntities()) {
																				if(!as.isDead()) {
																					if(as.getLocation().distanceSquared(entity.getLocation()) <= 2) {
																						if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
																							if(entity instanceof LivingEntity) {
																								final LivingEntity LE1 = (LivingEntity) entity;
																								
																								if(config.getInt("Fire.level") == 0) {
																									LE.damage(ulvl0damage);
																								}
																								if(config.getInt("Fire.level") == 1) {
																									LE.damage(ulvl1damage);
																								}
																								if(config.getInt("Fire.level") == 2) {
																									LE.damage(ulvl2damage);
																								}
																								
																								if(config.getInt("Fire.level") == 3) {
																									LE.damage(ulvl3damage);
																								}
																								if(config.getInt("Fire.level") == 4) {
																									LE.damage(ulvl4damage);
																								}
																								if(config.getInt("Fire.level") == 5) {
																									LE.damage(ulvl5damage);
																								}
																								
																								if(config.getInt("Fire.level") == 6) {
																									LE.damage(ulvl6damage);
																								}
																								if(config.getInt("Fire.level") == 7) {
																									LE.damage(ulvl7damage);
																								}
																								if(config.getInt("Fire.level") == 8) {
																									LE.damage(ulvl8damage);
																								}
																								
																								if(config.getInt("Fire.level") == 9) {
																									LE.damage(ulvl9damage);
																								}
																								if(config.getInt("Fire.level") == 10) {
																									LE.damage(ulvl10damage);
																								}
																								if(config.getInt("Fire.level") == 11) {
																									LE.damage(ulvl13damage);
																								}
																								
																								if(config.getInt("Fire.level") == 12) {
																									LE.damage(ulvl12damage);
																								}
																								if(config.getInt("Fire.level") == 13) {
																									LE.damage(ulvl13damage);
																								}
																								if(config.getInt("Fire.level") == 14) {
																									LE.damage(ulvl14damage);
																								}
																								
																								if(config.getInt("Fire.level") == 15) {
																									LE.damage(ulvl15damage);
																								}
																								if(config.getInt("Fire.level") == 16) {
																									LE.damage(ulvl16damage);
																								}
																								if(config.getInt("Fire.level") == 17) {
																									LE.damage(ulvl17damage);
																								}
																								if(config.getInt("Fire.level") == 18) {
																									LE.damage(ulvl18damage);
																								}
																								if(config.getInt("Fire.level") == 19) {
																									LE.damage(ulvl19damage);
																								}
																								if(config.getInt("Fire.level") == 20) {
																									LE.damage(ulvl20damage);
																								}
																								
																								
																								if(LE.isDead()) {
																									config.set("Fire.xp", config.getInt("Fire.xp") + uxp);
																								
																									
																									
																								
																									try {
																										config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
																									} catch (IOException e) {
																									e.printStackTrace();
																									}
																									
																								}
																								world.createExplosion(as1.getLocation(), 0.7f, false, false);
																								as1.remove();
																								as.remove();
																								cancel();
																								
																								
																							}
																						}
																					}
																				}
																			}
																			}
																			else if(as.isDead()) {
																				as1.remove();
																				cancel();
																			}
																		}
																	}.runTaskTimer(plugin, 0L, 1L); 
																	
																														
																
																

																}
															}
														}
													}
												}
											
											}
											

										}
									}.runTaskLater(plugin, 10L);
									
									
									
								}
								
								if(angle > 13) {
									cancel();
								}
						
								angle += 0.7;
									
								
							}
						}.runTaskTimer(plugin, 0L, 1L);
					}
				}.runTaskLater(plugin, 100L);
				
				
				
				
				new BukkitRunnable() {
					public void run() {
						inner0.cancel();
						inner11.cancel();
						inner21.cancel();
						inner31.cancel();
						inner41.cancel();
						inner51.cancel();
						c20.cancel();
						c211.cancel();
						c221.cancel();
						c231.cancel();
						c241.cancel();
						c251.cancel();
						c30.cancel();
						c311.cancel();
						c321.cancel();
						c331.cancel();
						c341.cancel();
						c351.cancel();
						c40.cancel();
						c411.cancel();
						c421.cancel();
						c431.cancel();
						c441.cancel();
						c451.cancel();
						c50.cancel();
						c511.cancel();
						c521.cancel();
						c531.cancel();
						c541.cancel();
						c551.cancel();
						
						task.cancel();
						
						origin.remove();
					}
				}.runTaskLater(plugin, 140L);
			}
		
		}
	}
	
	
	
	
	
	
}
