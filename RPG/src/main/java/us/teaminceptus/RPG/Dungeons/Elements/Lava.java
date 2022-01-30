package us.teaminceptus.RPG.Dungeons.Elements;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import us.teaminceptus.RPG.Rpg;
import us.teaminceptus.RPG.Utils.CustomSkulls;

public class Lava implements Listener{
	
	
	
	
	
	
	static Map<String, Long> ultimateCooldown = new HashMap<String, Long>();
	static Map<String, Long> pitCooldown = new HashMap<String, Long>();
	static Map<String, Long> ballCooldown = new HashMap<String, Long>();
	
	
	
	static Rpg plugin;
	public Lava(Rpg plugin) {
		this.plugin = plugin;
	}
	
	

	
	@EventHandler
	
	public static void onInteract(PlayerInteractEvent e) {
		
		
		final Player p = e.getPlayer();

		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§4Wrath of the Phoenix: Turns you into a phoenix that shoots lava balls wherever you look!")) {
			
				if(ultimateCooldown.containsKey(p.getName())) {
					
					if(ultimateCooldown.get(p.getName()) > System.currentTimeMillis()) {
						long timeLeft = ((ultimateCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("Your ultimate can be used again in: " + timeLeft + " seconds"));
						return;
					}
					
				}
				
				
				ultimateCooldown.put(p.getName(), System.currentTimeMillis() + (15 * 1000));
				final Location loc = p.getLocation().add(0, 5, 0);
				p.setGravity(false);
				p.teleport(loc);	
				final World world = p.getWorld();
				final BukkitTask task;
				final BukkitTask task1;
				final BukkitTask task2;
				final BukkitTask task3;
				final BukkitTask task4;
				final BukkitTask task5;
				final BukkitTask task6;
				
				
				
				task1 = new BukkitRunnable() {
					float radius = 2f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.DRIP_LAVA, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(p.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task2 = new BukkitRunnable() {
					float radius = 2f;
					float angle = 60f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.DRIP_LAVA, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(p.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task3 = new BukkitRunnable() {
					float radius = 2f;
					float angle = 120f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.DRIP_LAVA, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(p.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task4 = new BukkitRunnable() {
					float radius = 2f;
					float angle = 180f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.DRIP_LAVA, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(p.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task5 = new BukkitRunnable() {
					float radius = 2f;
					float angle = 240f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.DRIP_LAVA, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(p.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task6 = new BukkitRunnable() {
					float radius = 2f;
					float angle = 300f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.DRIP_LAVA, p.getLocation().getX()+x, p.getLocation().getY()+ 3, p.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(p.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				
				task = new BukkitRunnable() {
					public void run() {
						
						final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
						
						as.setInvisible(true);
						as.setInvulnerable(true);
						as.setArms(false);
						as.setBasePlate(false);
						as.setMarker(true);
						as.setGravity(false);
						as.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/1a69ccf7ad904c9a852ea2ff3f5b4e23adebf72ed12d5f24b78ce2d44b4a2"));
						
						Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
						final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
						
						new BukkitRunnable() {
							int i = 0;
							int distance = 30;
							public void run() {
								
								if(!p.isDead()) {
								
								as.teleport(as.getLocation().add(vector.normalize()));
								world.spawnParticle(Particle.LAVA, as.getEyeLocation().add(0, 1, 0), 5);
								if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
									
									if(!as.isDead()) {
										world.createExplosion(as.getLocation(), 0.5f, true, false);
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
													
													LE.damage(100, p);
													world.createExplosion(as.getLocation(), 0.5f, true, false);
													as.remove();
													
													
													cancel();

												}
											}
										}
									}
								}
								
								if (i>distance) {
									world.createExplosion(as.getLocation(), 0.5f, true, false);
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
							
						}.runTaskTimer(plugin, 0L, 3L);
						
					}
				}.runTaskTimer(plugin, 0L, 20L);
				
				new BukkitRunnable() {
					public void run() {
						p.setGravity(true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 0));
						task.cancel();
						task1.cancel();
						task2.cancel();
						task3.cancel();
						task4.cancel();
						task5.cancel();
						task6.cancel();
					}
				}.runTaskLater(plugin, 200L);
			
			
			}
		
		
		}
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§4Pit of Eternal Heat: Creates a pit on the floor that constantly damages anything that touches it!")) {
			
				if(pitCooldown.containsKey(p.getName())) {
					
					if(pitCooldown.get(p.getName()) > System.currentTimeMillis()) {
						long timeLeft = ((pitCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("This attack can be used again in: " + timeLeft + " seconds"));
						return;
						
						
						
					}
					

					
					
				}
				
				
				pitCooldown.put(p.getName(), System.currentTimeMillis() + (20 * 1000));
				
				
				
				final Location loc = p.getLocation();
				final World world = p.getWorld();
				
				final ArmorStand origin = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
				origin.setInvisible(true);
				origin.setInvulnerable(true);
				origin.setMarker(true);
				
				final BukkitTask task;
				
				task = new BukkitRunnable() {
					public void run() {
						if(p.isDead()) {
							cancel();
						}
						p.teleport(loc);
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				new BukkitRunnable() {
					
					public void run() {
						task.cancel();
					}
				}.runTaskLater(plugin, 20L);
				
				new BukkitRunnable() {
					float radius = 2f;
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
									as.setFireTicks(300);
								}
							}.runTaskLater(plugin, 10L);
							
							
							new BukkitRunnable() {
								public void run() {
									
									if(!origin.isDead()) {
										
										for(Entity entity: as.getLocation().getChunk().getEntities()) {
											if(!as.isDead()) {
												if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
													if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
														if(entity instanceof LivingEntity) {
															final LivingEntity LE = (LivingEntity) entity;
														
															LE.damage(3, p);
														
																												
														
														

														}
													}
												}
											}
										}
									
									}
									
									else if(origin.isDead()) {
										as.remove();
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
						}
						
						if(angle > 7) {
							cancel();
						}
							
							
							
							
							
							
							
							
							
							
						angle += 0.3;
							
							
							
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				new BukkitRunnable() {
					float radius = 1f;
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
									as.setFireTicks(300);
								}
							}.runTaskLater(plugin, 10L);
							
							
							
							new BukkitRunnable() {
								public void run() {
									
									if(!origin.isDead()) {
										world.spawnParticle(Particle.LAVA, origin.getLocation(), 1);
										for(Entity entity: as.getLocation().getChunk().getEntities()) {
											if(!as.isDead()) {
												if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
													if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
														if(entity instanceof LivingEntity) {
															final LivingEntity LE = (LivingEntity) entity;
														
															LE.damage(7, p);
														
																												
														
														

														}
													}
												}
											}
										}
									
									}
									
									else if(origin.isDead()) {
										as.remove();
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
						}
						
						if(angle > 6) {
							cancel();
						}
				
						angle += 0.3;
							

					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				new BukkitRunnable() {
					public void run() {
						origin.remove();
					}
				}.runTaskLater(plugin, 300L);
				
				
			}
		
		
		if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§4LavaBall: A fireball, but better.")) {
			
			if(ballCooldown.containsKey(p.getName())) {
				
				if(ballCooldown.get(p.getName()) > System.currentTimeMillis()) {
					
					
					
					return;
					
					
					
				}
				

				
				
			}
			
			
			ballCooldown.put(p.getName(), System.currentTimeMillis() + (500));
			
			final World world = p.getWorld();
			final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
			
			as.setInvisible(true);
			as.setInvulnerable(true);
			as.setArms(false);
			as.setBasePlate(false);
			as.setMarker(true);
			as.setGravity(false);
			as.setSmall(true);
			as.getEquipment().setHelmet(CustomSkulls.getSkull("http://textures.minecraft.net/texture/1a69ccf7ad904c9a852ea2ff3f5b4e23adebf72ed12d5f24b78ce2d44b4a2"));
			
			Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
			final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
			
			new BukkitRunnable() {
				int i = 0;
				int distance = 30;
				public void run() {
					
					if(!p.isDead()) {
					
					as.teleport(as.getLocation().add(vector.normalize()));
					world.spawnParticle(Particle.LAVA, as.getEyeLocation().add(0, 1, 0), 5);
					if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
						
						if(!as.isDead()) {
							world.createExplosion(as.getLocation(), 0.3f, true, false);
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
										
										LE.damage(10, p);
										world.createExplosion(as.getLocation(), 0.3f, true, false);
										as.remove();
										
										
										cancel();

									}
								}
							}
						}
					}
					
					if (i>distance) {
						world.createExplosion(as.getLocation(), 0.3f, true, false);
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
	
	}
	}
	
	
}
