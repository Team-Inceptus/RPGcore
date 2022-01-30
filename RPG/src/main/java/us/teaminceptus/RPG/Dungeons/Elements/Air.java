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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import us.teaminceptus.RPG.Rpg;

public class Air implements Listener{

	static Map<String, Long> teleportCooldown = new HashMap<String, Long>();
	static Map<String, Long> ultimateCooldown = new HashMap<String, Long>();
	static Rpg plugin;
	
	public Air(Rpg plugin) {
		
	
	this.plugin = plugin;
	
	}
	
	public static double lvl0damage = 1;
	public static double lvl1damage = 1.7;
	public static double lvl2damage = 2.4;
	public static double lvl3damage = 3.1;
	public static double lvl4damage = 3.8;
	public static double lvl5damage = 4.5;
	public static double lvl6damage = 5.2;
	public static double lvl7damage = 5.9;
	public static double lvl8damage = 6.6;
	public static double lvl9damage = 7.3;
	public static double lvl10damage = 8;
	public static double lvl11damage = 8.7;
	public static double lvl12damage = 9.4;
	public static double lvl13damage = 10.1;
	public static double lvl14damage = 10.8;
	public static double lvl15damage = 11.5;
	public static double lvl16damage = 12.2;
	public static double lvl17damage = 12.9;
	public static double lvl18damage = 13.6;
	public static double lvl19damage = 14.3;
	public static double lvl20damage = 15;
	
	public static int lvl0distance = 10;
	public static int lvl5distance = 11;
	public static int lvl10distance = 12;
	public static int lvl15distance = 13;
	public static int lvl20distance = 14;
	
	public static int ulvl0 = 20;
	public static int ulvl1 = 21;
	public static int ulvl2 = 22;
	public static int ulvl3 = 23;
	public static int ulvl4 = 24;
	public static int ulvl5 = 25;
	public static int ulvl6 = 26;
	public static int ulvl7 = 27;
	public static int ulvl8 = 28;
	public static int ulvl9 = 29;
	public static int ulvl10 = 30;
	public static int ulvl11 = 31;
	public static int ulvl12 = 32;
	public static int ulvl13 = 33;
	public static int ulvl14 = 34;
	public static int ulvl15 = 35;
	public static int ulvl16 = 36;
	public static int ulvl17 = 37;
	public static int ulvl18 = 38;
	public static int ulvl19 = 39;
	public static int ulvl20 = 40;
	
	public static int xp = 2;

	
	
@EventHandler
	
	public void onInteract(PlayerInteractEvent e) {
		
		
		final Player p = e.getPlayer();
		final FileConfiguration config= Rpg.getFile(p);
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§3Freezing Winds: Summons a gust of ice cold wind that freezes enemies and deals damage.")) {
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
					int distance = 10;
					public void run() {
						
						if(!p.isDead()) {
						
						as.teleport(as.getLocation().add(vector.normalize()));
						world.spawnParticle(Particle.WHITE_ASH, as.getEyeLocation().add(0, 0.5, 0), 5);
						
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
											
											if(config.getInt("Air.level") == 0) {
												LE.damage(lvl0damage);
											}
											if(config.getInt("Air.level") == 1) {
												LE.damage(lvl1damage);
											}
											if(config.getInt("Air.level") == 2) {
												LE.damage(lvl2damage);
											}
											
											if(config.getInt("Air.level") == 3) {
												LE.damage(lvl3damage);
											}
											if(config.getInt("Air.level") == 4) {
												LE.damage(lvl4damage);
											}
											if(config.getInt("Air.level") == 5) {
												LE.damage(lvl5damage);
											}
											
											if(config.getInt("Air.level") == 6) {
												LE.damage(lvl6damage);
											}
											if(config.getInt("Air.level") == 7) {
												LE.damage(lvl7damage);
											}
											if(config.getInt("Air.level") == 8) {
												LE.damage(lvl8damage);
											}
											
											if(config.getInt("Air.level") == 9) {
												LE.damage(lvl9damage);
											}
											if(config.getInt("Air.level") == 10) {
												LE.damage(lvl10damage);
											}
											if(config.getInt("Air.level") == 11) {
												LE.damage(lvl13damage);
											}
											
											if(config.getInt("Air.level") == 12) {
												LE.damage(lvl12damage);
											}
											if(config.getInt("Air.level") == 13) {
												LE.damage(lvl13damage);
											}
											if(config.getInt("Air.level") == 14) {
												LE.damage(lvl14damage);
											}
											
											if(config.getInt("Air.level") == 15) {
												LE.damage(lvl15damage);
											}
											if(config.getInt("Air.level") == 16) {
												LE.damage(lvl16damage);
											}
											if(config.getInt("Air.level") == 17) {
												LE.damage(lvl17damage);
											}
											if(config.getInt("Air.level") == 18) {
												LE.damage(lvl18damage);
											}
											if(config.getInt("Air.level") == 19) {
												LE.damage(lvl19damage);
											}
											if(config.getInt("Air.level") == 20) {
												LE.damage(lvl20damage);
											}
											
											
											if(LE.isDead()) {
												config.set("Air.xp", config.getInt("Air.xp") + xp);
											
												
												
											
												try {
													config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
												} catch (IOException e) {
												e.printStackTrace();
												}
												
											}
											
											LE.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 100));
											
											
											
											

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
			
			else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§3Lofty Winds: Summons a gust of wind that pushes you forward by 10 blocks.")) {
				
				
				if(teleportCooldown.containsKey(p.getName())) {
					
					if(teleportCooldown.get(p.getName()) > System.currentTimeMillis()) {
						long timeLeft = ((teleportCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
						
						p.sendMessage(ChatColor.RED + ("This utility can be used again in: " + timeLeft + " seconds"));
						return;
					}
					
				}
				
				
				
				if(!p.isOp()) {
					teleportCooldown.put(p.getName(), System.currentTimeMillis() + (10 * 1000));
				}
				
				if(p.isOp()) {
					p.sendMessage(ChatColor.GREEN + "Operator Bypass.");
				}
				
				
				
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
				
				
				

				if(config.getInt("Air.level") == 0) {
					
					
					new BukkitRunnable() {
						int i = 0;
						int distance = lvl0distance;

						
						
						public void run() {
							
							if(!p.isDead()) {
								
								
							final BukkitTask task;
							
							task = new BukkitRunnable() {
								
								public void run() {
									if(!as.isDead()) {
										p.teleport(as.getLocation());
									}
									
									else if(as.isDead()) {
										cancel();
									}
								}
								
							}.runTaskTimer(plugin, 0L, 1L);
								
							
							as.teleport(as.getLocation().add(vector.normalize()));
							world.spawnParticle(Particle.SWEEP_ATTACK, as.getEyeLocation().add(0, 0.5, 0), 1);
							
							
							if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
								
								if(!as.isDead()) {
									task.cancel();
									as.remove();
									cancel();
								}
							}
							
							
							
							if (i>distance) {
								task.cancel();
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
				
				
				
				
				if(config.getInt("Air.level") == 5) {
					
					
					new BukkitRunnable() {
						int i = 0;
						int distance = lvl5distance;

						
						
						public void run() {
							
							if(!p.isDead()) {
								
								
							final BukkitTask task;
							
							task = new BukkitRunnable() {
								
								public void run() {
									if(!as.isDead()) {
										p.teleport(as.getLocation());
									}
									
									else if(as.isDead()) {
										cancel();
									}
								}
								
							}.runTaskTimer(plugin, 0L, 1L);
								
							
							as.teleport(as.getLocation().add(vector.normalize()));
							world.spawnParticle(Particle.SWEEP_ATTACK, as.getEyeLocation().add(0, 0.5, 0), 1);
							
							
							if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
								
								if(!as.isDead()) {
									task.cancel();
									as.remove();
									cancel();
								}
							}
							
							
							
							if (i>distance) {
								task.cancel();
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

				
				if(config.getInt("Air.level") == 10) {
					
					
					new BukkitRunnable() {
						int i = 0;
						int distance = lvl10distance;

						
						
						public void run() {
							
							if(!p.isDead()) {
								
								
							final BukkitTask task;
							
							task = new BukkitRunnable() {
								
								public void run() {
									if(!as.isDead()) {
										p.teleport(as.getLocation());
									}
									
									else if(as.isDead()) {
										cancel();
									}
								}
								
							}.runTaskTimer(plugin, 0L, 1L);
								
							
							as.teleport(as.getLocation().add(vector.normalize()));
							world.spawnParticle(Particle.SWEEP_ATTACK, as.getEyeLocation().add(0, 0.5, 0), 1);
							
							
							if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
								
								if(!as.isDead()) {
									task.cancel();
									as.remove();
									cancel();
								}
							}
							
							
							
							if (i>distance) {
								task.cancel();
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

				if(config.getInt("Air.level") == 15) {
					
					
					new BukkitRunnable() {
						int i = 0;
						int distance = lvl15distance;

						
						
						public void run() {
							
							if(!p.isDead()) {
								
								
							final BukkitTask task;
							
							task = new BukkitRunnable() {
								
								public void run() {
									if(!as.isDead()) {
										p.teleport(as.getLocation());
									}
									
									else if(as.isDead()) {
										cancel();
									}
								}
								
							}.runTaskTimer(plugin, 0L, 1L);
								
							
							as.teleport(as.getLocation().add(vector.normalize()));
							world.spawnParticle(Particle.SWEEP_ATTACK, as.getEyeLocation().add(0, 0.5, 0), 1);
							
							
							if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
								
								if(!as.isDead()) {
									task.cancel();
									as.remove();
									cancel();
								}
							}
							
							
							
							if (i>distance) {
								task.cancel();
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
				
				
				
				if(config.getInt("Air.level") == 20) {
					
					
					new BukkitRunnable() {
						int i = 0;
						int distance = lvl20distance;

						
						
						public void run() {
							
							if(!p.isDead()) {
								
								
							final BukkitTask task;
							
							task = new BukkitRunnable() {
								
								public void run() {
									if(!as.isDead()) {
										p.teleport(as.getLocation());
									}
									
									else if(as.isDead()) {
										cancel();
									}
								}
								
							}.runTaskTimer(plugin, 0L, 1L);
								
							
							as.teleport(as.getLocation().add(vector.normalize()));
							world.spawnParticle(Particle.SWEEP_ATTACK, as.getEyeLocation().add(0, 0.5, 0), 1);
							
							
							if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
								
								if(!as.isDead()) {
									task.cancel();
									as.remove();
									cancel();
								}
							}
							
							
							
							if (i>distance) {
								task.cancel();
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
			
			
			else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§3Tornado: Summons a torrnado that pulls any mob within its chunk and damages it.")) {
				
				
				if(ultimateCooldown.containsKey(p.getName())) {
		
					if(ultimateCooldown.get(p.getName()) > System.currentTimeMillis()) {
						long timeLeft = ((ultimateCooldown.get(p.getName()) - System.currentTimeMillis()) / 1000) + 1;
			
						p.sendMessage(ChatColor.RED + ("Your ultimate can be used again in: " + timeLeft + " seconds"));
						return;
					}
		
				}
	
	
				ultimateCooldown.put(p.getName(), System.currentTimeMillis() + (15 * 1000));
			
				
				
				
				
				final World world = p.getWorld();
				
				final ArmorStand o = (ArmorStand) world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
				o.setInvisible(true);
				o.setInvulnerable(true);
				o.setArms(false);
				o.setBasePlate(false);
				o.setMarker(true);
				o.setGravity(false);
				o.setSmall(true);
				
				final String uuid = p.getUniqueId().toString();
				Location dest = o.getLocation().add(o.getEyeLocation().getDirection().multiply(10));
				final Vector vector = dest.subtract(o.getEyeLocation()).toVector();
				
				
				
				if(config.getInt("Air.level") == 0) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl0;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 1) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl1;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				
				if(config.getInt("Air.level") == 2) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl2;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 3) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl3;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 4) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl4;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 5) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl5;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 6) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl6;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 7) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl7;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 8) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl8;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 9) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl9;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 10) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl10;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 11) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl11;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 12) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl12;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 13) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl13;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 14) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl14;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 15) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl15;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 16) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl16;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 17) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl17;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 18) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl18;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 19) {
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl19;
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				if(config.getInt("Air.level") == 20) {
					
					new BukkitRunnable() {
						int i = 0;
						int distance = ulvl20;
						
						public void run() {
							
							
							o.teleport(o.getLocation().add(vector.normalize()));
							
							if(o.getTargetBlockExact(1) != null && !(o.getTargetBlockExact(1).isPassable())) {
								
								o.teleport(o.getLocation().subtract(vector.normalize()));

							}
							
							
							
							if(i>distance) {
								if(!o.isDead()) {
									o.remove();
									cancel();
								}
							}
							
							i++;
						}
					}.runTaskTimer(plugin, 0L, 10L);
				}
				
				
				
				
				
				new BukkitRunnable() {
					public void run() {
						if(!o.isDead()) {
							for(Entity entity: o.getLocation().getChunk().getEntities()) {
								if(!(entity instanceof Player) && !(entity instanceof ArmorStand)&& !(entity instanceof Dolphin)) {
									if(entity instanceof LivingEntity) {
										final LivingEntity LE = (LivingEntity) entity;
										LE.teleport(o);
										LE.damage(2);
										
										if(LE.isDead()) {
											config.set("Air.xp", config.getInt("Air.xp") + xp);
										
											
											
										
											try {
												config.save(new File(Rpg.getPlayersDirectory(), uuid + ".yml"));
											} catch (IOException e) {
											e.printStackTrace();
											}
											
										}
										
									}
								}
							}
						}
						
						if(o.isDead()) {
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				final float radius = 1f; 
				final float radius1 = 1.3f; 
				final float radius2 = 1.7f; 
				final float radius3 = 2f;
				final float radius4 = 2.3f;
				final float radius5 = 2.7f; 
				final float radius6 = 3f; 
				final float radius7 = 3.3f;
				final float radius8 = 3.7f;
				
				
				final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 
				
				final Particle.DustOptions dust1 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 

				final Particle.DustOptions dust2 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 
				
				final Particle.DustOptions dust3 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 
				
				final Particle.DustOptions dust4 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1); 
				
				
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
				
				 
				
				final BukkitTask c6;
				
				final BukkitTask c61;

				final BukkitTask c62;
				
				final BukkitTask c63;
				
				final BukkitTask c64;
				
				final BukkitTask c65;
				
				
				final BukkitTask c7;
				
				final BukkitTask c71;

				final BukkitTask c72;
				
				final BukkitTask c73;
				
				final BukkitTask c74;
				
				final BukkitTask c75;
				
				
				final BukkitTask c8;
				
				final BukkitTask c81;

				final BukkitTask c82;
				
				final BukkitTask c83;
				
				final BukkitTask c84;
				
				final BukkitTask c85; 
				
				
				final BukkitTask c9;
				
				final BukkitTask c91;

				final BukkitTask c92;
				
				final BukkitTask c93;
				
				final BukkitTask c94;
				
				final BukkitTask c95; 
				
				inner = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 0.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				inner1 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 0.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				inner2 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 0.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				inner3 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 0.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				inner4 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 0.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				inner5 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 0.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c2 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 1.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c21 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 1.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				c22 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 1.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c23 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 1.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c24 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 1.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				c25 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius1 * Math.sin(angle));
							double z = (radius1 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 1.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c3 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 2.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c31 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 2.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c32 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 2.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c33 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 2.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c34 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 2.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c35 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius2 * Math.sin(angle));
							double z = (radius2 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 2.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				c4 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 3.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c41 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 3.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c42 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 3.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c43 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 3.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c44 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 3.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c45 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius3 * Math.sin(angle));
							double z = (radius3 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 3.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c5 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 4.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c51 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 4.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c52 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 4.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c53 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 4.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c54 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 4.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c55 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius4 * Math.sin(angle));
							double z = (radius4 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 4.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				
				
				
				c6 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius5 * Math.sin(angle));
							double z = (radius5 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 5.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c61 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius5 * Math.sin(angle));
							double z = (radius5 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 5.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				c62 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius5 * Math.sin(angle));
							double z = (radius5 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 5.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c63 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius5 * Math.sin(angle));
							double z = (radius5 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 5.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c64 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius5 * Math.sin(angle));
							double z = (radius5 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 5.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				c65 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius5 * Math.sin(angle));
							double z = (radius5 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 5.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust1);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c7 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius6 * Math.sin(angle));
							double z = (radius6 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 6.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c71 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius6 * Math.sin(angle));
							double z = (radius6 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 6.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c72 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius6 * Math.sin(angle));
							double z = (radius6 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 6.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c73 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius6 * Math.sin(angle));
							double z = (radius6 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 6.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c74 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius6 * Math.sin(angle));
							double z = (radius6 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 6.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c75 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius6 * Math.sin(angle));
							double z = (radius6 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 6.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust2);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				c8 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius7 * Math.sin(angle));
							double z = (radius7 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 7.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c81 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius7 * Math.sin(angle));
							double z = (radius7 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 7.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c82 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius7 * Math.sin(angle));
							double z = (radius7 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 7.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c83 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius7 * Math.sin(angle));
							double z = (radius7 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 7.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c84 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius7 * Math.sin(angle));
							double z = (radius7 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 7.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c85 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius7 * Math.sin(angle));
							double z = (radius7 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 7.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				c9 = new BukkitRunnable() {
					
					float angle = 0f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius8 * Math.sin(angle));
							double z = (radius8 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 8.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c91 = new BukkitRunnable() {
					
					float angle = 60f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius8 * Math.sin(angle));
							double z = (radius8 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 8.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c92 = new BukkitRunnable() {
					
					float angle = 120f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius8 * Math.sin(angle));
							double z = (radius8 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 8.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c93 = new BukkitRunnable() {
					
					float angle = 180f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius8 * Math.sin(angle));
							double z = (radius8 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 8.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c94 = new BukkitRunnable() {
					
					float angle = 240f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius8 * Math.sin(angle));
							double z = (radius8 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 8.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				c95 = new BukkitRunnable() {
					
					float angle = 300f;
					
					public void run() {
						
						if(!o.isDead()) {
					
							
							double x = (radius8 * Math.sin(angle));
							double z = (radius8 * Math.cos(angle));
							world.spawnParticle(Particle.REDSTONE, o.getLocation().getX()+x, o.getLocation().getY() + 8.5, o.getLocation().getZ()+z, 0, 0, 0, 0, dust3);
							
							angle += 0.3;
							
							
							
							if(o.isDead()) {
								cancel();
							}
						}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				

			}
			
		}
	}
	
	
}
