package us.teaminceptus.RPG.Dungeons.Elements;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
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

import us.teaminceptus.RPG.Rpg;

public class Water implements Listener{
	static Map<String, Long> laserCooldown = new HashMap<String, Long>();
	static Map<String, Long> healCooldown = new HashMap<String, Long>();
	static Map<String, Long> ultimateCooldown = new HashMap<String, Long>();
	
	
	static Rpg plugin;
	
	public Water(Rpg plugin) {
		this.plugin = plugin;
	}
	
@EventHandler
	
	public void onInteract(PlayerInteractEvent e) {
		
	
	
		
	
		
		final Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§3Presurized Jet: Shoot a powerful pressurized jet of water, dealing lots of damage!")) {
				
				if(laserCooldown.containsKey(p.getName())) {
					
					if(laserCooldown.get(p.getName()) > System.currentTimeMillis()) {
						
				
						return;

					}
					
				}

				laserCooldown.put(p.getName(), System.currentTimeMillis() + (1 * 1000));
				
				
				final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 152, (int) 255), 1); 
				
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
					int distance = 15;
					public void run() {
						
						if(!p.isDead()) {
						
						as.teleport(as.getLocation().add(vector.normalize()));
						
						
						world.spawnParticle(Particle.REDSTONE, as.getLocation().getX(), as.getEyeLocation().getY(), as.getLocation().getZ(), 0, 0, 0, 0, dust);
						world.spawnParticle(Particle.WATER_DROP, as.getEyeLocation(), 20);
						
						
						
						
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
											
											LE.damage(20, p);
											
											
											as.remove();
											
											
											

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

			else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§3Healing Geyser: Spawn a geyser at your location that heals players within a 5x5 range!")) {
	
				if(healCooldown.containsKey(p.getName())) {
		
					if(healCooldown.get(p.getName()) > System.currentTimeMillis()) {
						
						
						
						long timeLeft = ((healCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("This utility can be used again in: " + timeLeft + " seconds"));
	
						return;

					}
		
				}

				healCooldown.put(p.getName(), System.currentTimeMillis() + (13 * 1000));
	
	
				final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 
	
				final World world = p.getWorld();
				final ArmorStand o = (ArmorStand) world.spawnEntity(p.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
	
				o.setInvisible(true);
				o.setInvulnerable(true);
				o.setArms(false);
				o.setBasePlate(false);
				o.setMarker(true);
				o.setSmall(true);

				
				
				final BukkitTask task1;
				final BukkitTask task2;
				
				new BukkitRunnable() {
					public void run() {
						for(Entity entity: o.getLocation().getChunk().getEntities()) {
							if(!o.isDead()) {
								if(o.getLocation().distanceSquared(entity.getLocation()) <= 11) {
									
									if(entity instanceof Player) {
										
										final LivingEntity LE = (LivingEntity) entity;
										
										if(LE.getHealth() == LE.getMaxHealth()) {
											continue;
										}
										
										else if ((LE.getMaxHealth() - LE.getHealth()) <= 3) {
											LE.setHealth(LE.getMaxHealth());
										}
										else {
											LE.setHealth(LE.getHealth() + 3);
										}
										
										
										
										
									}
									
									
								}
							}
						}
					}
				}.runTaskTimer(plugin, 0L, 20L);
				
				
				
				
				
				
				task1 = new BukkitRunnable() {
					float radius = 1f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.VILLAGER_HAPPY, o.getLocation().getX()+x, o.getLocation().getY()+ 1.5, o.getLocation().getZ()+z, 1);
							angle += 0.3;
							
							world.spawnParticle(Particle.WATER_DROP, o.getLocation().add(0,2,0), 100);
							
							if(o.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				task2 = new BukkitRunnable() {
					float radius = 2.5f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.VILLAGER_HAPPY, o.getLocation().getX()+x, o.getLocation().getY()+ 0.5, o.getLocation().getZ()+z, 1);
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				new BukkitRunnable() {
					
					public void run() {
						o.remove();
						task1.cancel();
						task2.cancel();
					}
					
				}.runTaskLater(plugin, 200L);
				
				

	
				
	
	
			}
			
			
			
			else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§3Attack Dolphin: Summons a pet dolphin to aid you in battle,")) {
				
				if(ultimateCooldown.containsKey(p.getName())) {
		
					if(ultimateCooldown.get(p.getName()) > System.currentTimeMillis()) {
						
						
						
						long timeLeft = ((ultimateCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("Your ultimate can be used again in: " + timeLeft + " seconds"));
	
						return;

					}
		
				}

				ultimateCooldown.put(p.getName(), System.currentTimeMillis() + (35 * 1000));
	
	
				final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 203, (int) 255, (int) 255), 1); 
	
				final World world = p.getWorld();
				final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0, 2, 0), EntityType.ARMOR_STAND);
				
				final Dolphin d = (Dolphin) world.spawnEntity(as.getLocation(), EntityType.DOLPHIN);
				
				
				as.setInvisible(true);
				as.setInvulnerable(true);
				as.setArms(false);
				as.setBasePlate(false);
				as.setMarker(true);
				as.setGravity(false);
				as.setSmall(true);
				as.setCollidable(false);
				
				d.setInvulnerable(true);
				d.setGravity(false);
				d.setAI(false);
				d.setCollidable(false);
				as.addPassenger(d);
				
				
				
				

				Location dest = p.getLocation().add(p.getEyeLocation().getDirection().multiply(10));
				final Vector vector = dest.subtract(p.getEyeLocation()).toVector();
				
				
				new BukkitRunnable() {
					
					
					public void run() {
			
						if(!p.isDead()) {
			

							
			
							for(Entity entity: as.getLocation().getChunk().getEntities()) {
								if(!as.isDead()) {
									
									if(!(entity instanceof Player) && !(entity instanceof ArmorStand) && !(entity instanceof Dolphin)) {
										if(entity instanceof LivingEntity) {
											final LivingEntity LE = (LivingEntity) entity;
											
											final ArmorStand as1 = (ArmorStand) world.spawnEntity(as.getLocation(), EntityType.ARMOR_STAND);
											as1.setInvisible(true);
											as1.setInvulnerable(true);
											as1.setGravity(false);
											as1.setSmall(true);
											as1.setBasePlate(false);
											
												
											new BukkitRunnable() {
												
												
												public void run() {
													Vector eV = LE.getLocation().toVector();
													double eY = LE.getLocation().getY();
													
													Vector asV = as1.getLocation().toVector();
													Vector fV = eV.subtract(asV).normalize();
													Location asLoc = (Location) as1.getLocation();
													asLoc.setDirection(fV);
													as1.teleport(asLoc);
													Location dest = as1.getLocation().add(as1.getEyeLocation().getDirection().multiply(10));
													final Vector vector = dest.subtract(as1.getEyeLocation()).toVector();
													
													world.spawnParticle(Particle.REDSTONE, as1.getLocation().getX(), as1.getEyeLocation().getY(), as1.getLocation().getZ(), 0, 0, 0, 0, dust);
													
													
													as1.teleport(as1.getEyeLocation().add(vector.normalize()));
													
													
													
													if(as1.getTargetBlockExact(1) != null && !(as1.getTargetBlockExact(1).isPassable())) {
														
														if(!as1.isDead()) {
															
															as1.remove();
															cancel();
														}
													}
													
													
													for(Entity entity: as.getLocation().getChunk().getEntities()) {
														if(!as1.isDead()) {
															if(as1.getLocation().distanceSquared(entity.getLocation()) <= 3) {
																if(!(entity instanceof Player) && !(entity instanceof ArmorStand) && !(entity instanceof Dolphin)) {
																	if(entity instanceof LivingEntity) {
																		final LivingEntity LE = (LivingEntity) entity;
																		
																		LE.damage(5, p);
																		
																		
																		as1.remove();
																		cancel();
																		
																		

																	}
																}
															}
														}
													}
													
													
													if(LE.isDead()) {
														
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
						else if (p.isDead()) {
							cancel();
							
							as.remove();
							d.remove();
						}
						
						else if (as.isDead()) {
							cancel();
						}
			
					}
		
				}.runTaskTimer(plugin, 0L, 20L);
				
				new BukkitRunnable() {
					public void run() {
						
						if(!p.isDead()) {
							as.teleport(p.getLocation().add(0, 2, 0).subtract(vector.normalize()));
						
							d.teleport(as);
						}
						
						
						if(as.isDead()) {
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				new BukkitRunnable() {
					public void run() {
						as.remove();
						d.remove();
					}
				}.runTaskLater(plugin, 600L);
					

				
	
			}
			
			
			
			
		}
	}
	
	
	
	
}
