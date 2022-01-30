package com.amanaran1.smpcore;

import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;






public class MobAbils implements Listener {
	Main plugin;
	public MobAbils(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	
	public void onFish(PlayerFishEvent event) {
		
		final Player player = event.getPlayer();
		
		Random random = new Random();
		
		Entity ent1 = (Entity) event.getCaught();
		
		if(!(ent1 instanceof LivingEntity)) {
			
		
		
		if(random.nextInt(100) <= 9) {
			ent1.remove();
			
			player.sendMessage((ChatColor.GREEN +"You fished up a ") + (ChatColor.DARK_GREEN+ "Swamp Monster!"));
			
			final World world = player.getWorld();
			final Entity ent = world.spawnEntity(player.getLocation().add(3, 5, 3), EntityType.ZOMBIE);
			ent.setCustomName((ChatColor.DARK_GREEN + "Swamp Monster") + ChatColor.BOLD);
			final Zombie z = (Zombie) ent;
			z.setMaxHealth(100);
			z.setHealth(100);
			z.setAdult();
			z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
			z.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(3);
			z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32);
			z.getEquipment().setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/9204ea7701614b959a574bab293923bb9f15b0e86310dfaf7a52ebe95c7736e9"));
			
			new BukkitRunnable() {
				
				public void run() {
					
					if(!z.isDead()) {
					final ArmorStand as = (ArmorStand) world.spawnEntity(z.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
					as.setInvisible(true);
					as.setInvulnerable(true);
					as.setGravity(false);
					as.setMarker(true);
					as.setArms(false);
					as.setBasePlate(false);
					as.getEquipment().setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/a3acd6e7b6c91c7d7268b4641f44d7abda78a982177533e10e09ba7ec758ce49"));
					
					Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
					final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
					
					new BukkitRunnable() {
						int i = 0;
						int distance = 10;
						public void run() {
							
							as.teleport(as.getLocation().add(vector.normalize()));
							
							if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
								
								if(!as.isDead()) {
									as.remove();
									cancel();
								}
							}
							
							for(Entity entity: as.getLocation().getChunk().getEntities()) {
								if(!as.isDead()) {
									if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
										if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
											if(entity instanceof LivingEntity) {
												final LivingEntity LE = (LivingEntity) entity;
												
												LE.damage(10, z);
												LE.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
												LE.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
												as.remove();
												
												
												cancel();

											}
										}
									}
								}
							}
							
							if (i>10) {
								as.remove();
								cancel();
								
								
								
							}
							i++;
							
						}
						
					}.runTaskTimer(plugin, 0L, 2L);
					
					
					
					
					}
					
					
					else {
						cancel();
					}
				}
				
			}.runTaskTimer(plugin, 60L, 40L);
			
		}
		
		else if(random.nextInt(100) >= 10 && random.nextInt(100) <=99) { //fishing chance 
			ent1.remove();
			
				player.sendMessage(ChatColor.BLUE + "Poseidon is furious will all of the fishing, and has emerged to put a stop to it.");
				String name = player.getName();
				player.sendMessage((ChatColor.GOLD + "Poseidon: I grow tired of the constant fishing taking place in my waters. I may not be able to stop it all, but I can start with you!") + ChatColor.BOLD);
				player.sendMessage((ChatColor.GOLD + "Prepare to meet your doom, " + name + ".") + ChatColor.BOLD);
				
				final World world = player.getWorld();
				final Entity ent = world.spawnEntity(player.getLocation().add(3, 5, 3), EntityType.ZOMBIE);
				ent.setCustomName((ChatColor.DARK_AQUA + "Poseidon") + ChatColor.BOLD);
				final Zombie sk = (Zombie) ent; //sk because I copied and pasted this part from my snake and decided to keep it :)
				
				sk.setAdult();
				sk.setMaxHealth(5000);
				sk.setHealth(5000);
				sk.getEquipment().setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/a272c99a4c2bd95796038c98a88a1bc7f439bb9012a07d7cd9addaa19407"));
				
				ItemStack cp = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
				cp.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 100);
				cp.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 255);
				cp.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 3);
				
				
				ItemMeta cpmeta = cp.getItemMeta();
				cpmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 1000000000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				
				cpmeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 1000000000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));

				cpmeta.setUnbreakable(true);
				cp.setItemMeta(cpmeta);
				
				ItemStack lg = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
				lg.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 100);
				lg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 255);
				ItemMeta lgmeta = lg.getItemMeta();
				lgmeta.setUnbreakable(true);
				lgmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 1000000000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				
				lgmeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 300, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));

				lg.setItemMeta(lgmeta);
				
				ItemStack bt = new ItemStack(Material.NETHERITE_BOOTS, 1);
				bt.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
				bt.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 255);
				bt.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 255);
				
				ItemMeta btmeta = bt.getItemMeta();
				
				btmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 1000000000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				btmeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR_TOUGHNESS", 300, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
				btmeta.setUnbreakable(true);
				

				bt.setItemMeta(btmeta);
				
				
				sk.getEquipment().setChestplate(cp);
				sk.getEquipment().setLeggings(lg);
				sk.getEquipment().setBoots(bt);
				sk.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT, 1));
				sk.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100);
				sk.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(5);
				sk.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
				

				
				
				//despawn boss in 10 mins
				
				
				new BukkitRunnable() {
					
					
					public void run() {
						
						if(!sk.isDead()) {
							
							player.sendMessage(ChatColor.GOLD + "Poseidon: I grow bored of this fight, you were nothing but a waste of time.");
							sk.remove();
						}
						
					}
					
					
				}.runTaskLater(plugin, 12000L);
				
				
				
				
				
				
				
				//BASE ANIMATION
				
				//top circle
				
				new BukkitRunnable() {
					float radius = 1f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.WATER_DROP, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3, sk.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				new BukkitRunnable() {
					float radius = 1f;
					float angle = 60f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.WATER_DROP, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3, sk.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							
						
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				new BukkitRunnable() {
					float radius = 1f;
					float angle = 120f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.WATER_DROP, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3, sk.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							
						
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);

				
				new BukkitRunnable() {
					float radius = 1f;
					float angle = 180f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.WATER_DROP, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3, sk.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				new BukkitRunnable() {
					float radius = 1f;
					float angle = 240f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.WATER_DROP, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3, sk.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							
						
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				new BukkitRunnable() {
					float radius = 1f;
					float angle = 300f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							
							world.spawnParticle(Particle.WATER_DROP, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3, sk.getLocation().getZ()+z, 3);
							angle += 0.3;
							
							
							
						
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);

				
				
				//2nd circle particle 1
				
				new BukkitRunnable() {
					float radius = 1.5f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				//third circle particle 1
				new BukkitRunnable() {
					float radius = 2f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 1.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				//4th circle particle 1
				new BukkitRunnable() {
					float radius = 2.5f;
					float angle = 0f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 1, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				//2nd circle particle 2
				new BukkitRunnable() {
					float radius = 1.5f;
					float angle = 120f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							
						
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				//3rd circle particle 2
				new BukkitRunnable() {
					float radius = 2f;
					float angle = 120f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 1.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							if(sk.isDead()) {
								cancel();
							}
							
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				//4th circle particle 2
				new BukkitRunnable() {
					float radius = 2.5f;
					float angle = 120f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 1, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				//2nd circle particle 3
				new BukkitRunnable() {
					float radius = 1.5f;
					float angle = 240f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				//3rd circle particle 3
				new BukkitRunnable() {
					float radius = 2f;
					float angle = 240f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 1.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							
							if(sk.isDead()) {
								cancel();
							}
						
						
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				//3rd circle particle 3
				new BukkitRunnable() {
					float radius = 2.5f;
					float angle = 240f;
					
					public void run() {
						
						
					
							
							double x = (radius * Math.sin(angle));
							double z = (radius * Math.cos(angle));
							Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 0, (int) 255), 1);
							world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 1, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
							angle += 0.3;
							
							
							
						
						
							if(sk.isDead()) {
								cancel();
							}
						
						
						
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				//prevent cheesing in water
				
				new BukkitRunnable() {
					public void run() {
						if(!sk.isDead()) {
							if(sk.getLocation().getBlock().getBlockData().matches(Material.WATER.createBlockData())) {
								sk.teleport(sk.getLocation().add(0, 0.1, 0));
							}
						}
					}
				}.runTaskTimer(plugin, 0L, 1L);
				
				
				
				//MAIN ATTACK
				
				new BukkitRunnable() {


					public void run() {
						if(!(sk.isDead()) && !(player.isDead())) {
							
							
							
								
							

							
								
								final Player p = (Player) sk.getTarget();
								
								Random r = new Random();

								if(r.nextInt(100) <= 74) {
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(sk.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									final Entity ent = (Entity) world.spawnEntity(as.getLocation(), EntityType.DOLPHIN);
									final Dolphin d = (Dolphin) ent;
									d.setAI(false);
									d.setInvulnerable(true);
									d.setGravity(false);
									
									
									as.setPassenger(d);
									
									
									

									
									new BukkitRunnable() {

										int i = 0;
										int distance = 20;
										

										public void run() {
											
											// make the armorstands face the player (homing part)
											Vector pV = p.getLocation().toVector();
											double pY = p.getLocation().getY();
											
											Vector asV = as.getLocation().toVector();
											Vector fV = pV.subtract(asV).normalize();
											Location asLoc = (Location) as.getLocation();
											asLoc.setDirection(fV);
											as.teleport(asLoc);
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											//move the dolphin to the player
											
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											d.teleport(as);
											
											World world = as.getWorld();
											world.spawnParticle(Particle.WATER_SPLASH, as.getEyeLocation(), 5);
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													
													world.createExplosion(as.getLocation(), 2f, false, false);
													d.remove();
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand) && !(entity instanceof Dolphin)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk); //damage of the attack(modify for SMP use later.)
																world.createExplosion(as.getLocation(), 2f, false, false);
																d.remove();
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													world.createExplosion(as.getLocation(), 2f, false, false);
													d.remove();
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 0L, 2L);
								}

								
								//trident that pulls
								
								else if(r.nextInt(100) >= 75 && r.nextInt() <= 99) {


									World world = sk.getWorld();
									
									final ArmorStand as = (ArmorStand) world.spawnEntity(sk.getLocation().add(0,0.5,0), EntityType.ARMOR_STAND);
									as.setVisible(false);
									as.setSmall(false);
									as.setMarker(true);
									as.setArms(false);
									as.setBasePlate(false);
									as.setInvulnerable(true);
									as.setItemInHand(new ItemStack(Material.TRIDENT, 1));
									
									EulerAngle ea = as.getRightArmPose();
									EulerAngle ean = as.getRightArmPose().add(25.4, 0, 0);
									as.setRightArmPose(ean);
									
									sk.getEquipment().setItemInMainHand(new ItemStack(Material.AIR, 1));

									Location dest = sk.getLocation().add(sk.getLocation().getDirection().multiply(10));
									final Vector vector = dest.subtract(sk.getLocation()).toVector();

									new BukkitRunnable() {

										int i = 0;
										int distance = 20;

										public void run() {
											as.teleport(as.getLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											world.spawnParticle(Particle.SNOWBALL, as.getLocation(), 5);
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													as.remove();
													sk.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT, 1));
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 10) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(20, sk);
																
																
																new BukkitRunnable() {
																	int i = 0;
																	int distance = 5;
																	public void run() {
																		as.teleport(as.getLocation().subtract(vector.normalize()));
																		
																		if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
																			
																			if(!as.isDead()) {
																				as.remove();
																				
																				sk.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT, 1));
																				
																				cancel();
																			}
																			
																		}
																		LE.teleport(as);
																		if(i>distance) {
																			if(!(as.isDead())){
																				
																				
																				sk.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT, 1));
																				as.remove();
																				
																				cancel();
																			}
																			
																		}
																		i++;
																	}
																}.runTaskTimer(plugin, 0L, 1L);
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													sk.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT, 1));
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 0L, 2L);
									

								}
								
								

							


						

						else if(sk.isDead()) {
							cancel();
						}
					}
					}
				}.runTaskTimer(plugin, 0L, 60L);
				
				
				
			//ORBS	
			new BukkitRunnable() {
				
				public void run() {
					
					if(!sk.isDead() && !player.isDead()) {
						if(sk.getTarget() instanceof Player) {
							final Player p = (Player) sk.getTarget();
							final World world = sk.getWorld();
							Random r = new Random();
							
							
							//water orb
							
						if(r.nextInt(100) <=32) {
							
							p.sendMessage(ChatColor.RED + "With the 3 power orbs of Olympus, NOTHING CAN STOP ME!");
							final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0,3,0), EntityType.ARMOR_STAND);
							
							as.setVisible(false);
							as.setSmall(false);
							as.setMarker(true);
							as.setArms(false);
							as.setBasePlate(false);
							as.setInvulnerable(true);
							as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
							
							final ArmorStand as1 = (ArmorStand) world.spawnEntity(sk.getLocation().add(0,3,0), EntityType.ARMOR_STAND);
							
							as1.setVisible(false);
							as1.setSmall(false);
							as1.setMarker(true);
							as1.setArms(false);
							as1.setBasePlate(false);
							as1.setInvulnerable(true);
							as1.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
							
							
							
							new BukkitRunnable() {
								public void run() {
									if(!(as.isDead()) && !(p.isDead())) {
										as.teleport(p.getLocation().add(0, 2, 0));
										
										if((as.getEyeLocation().getBlock().getBlockData().matches(Material.AIR.createBlockData()))) {
											if(!p.isDead()) {
												p.damage(1000000, sk);
												
											world.createExplosion(as.getLocation(), 0.3f, false, false);
											as.remove();
											world.createExplosion(as1.getLocation(), 0.3f, false, false);
											as1.remove();
											}
										}
										
										else if(!(as.getEyeLocation().getBlock().getBlockData().matches(Material.AIR.createBlockData()))) {
											world.createExplosion(as.getLocation(), 0.3f, false, false);
											as.remove();
											
											
											as1.remove();
											
										}
									}
									
									
								}
							}.runTaskLater(plugin, 100L);
							
						    new BukkitRunnable() {
								
								public void run() {
									
									if(!(as.isDead()) && !(p.isDead())) {
									EulerAngle rot = as.getHeadPose();
									EulerAngle rotnew = rot.add(0, 0.3, 0);
									EulerAngle rot1 = as1.getHeadPose();
									EulerAngle rotnew1 = rot1.add(0, 0.3, 0);
									as.setHeadPose(rotnew);
									as1.setHeadPose(rotnew1);
									world.spawnParticle(Particle.WATER_SPLASH, sk.getLocation().add(0, 4, 0), 30);
									
									
									if(!p.isDead()) {
										as.teleport(p.getLocation().add(0, 4, 0));
										as1.teleport(sk.getLocation().add(0, 4 ,0));
									}
									
									
									}
									
									else if(as.isDead() || p.isDead() || sk.isDead()) {
										as.remove();
										as1.remove();
										
										cancel();
									}
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 255), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 60f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 255), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 120f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 255), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 180f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 255), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 240f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 255), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 300f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 255), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							
							
							
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 247, (int) 247, (int) 15), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 60f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 247, (int) 247, (int) 15), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 120f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 247, (int) 247, (int) 15), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 180f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 247, (int) 247, (int) 15), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 240f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 247, (int) 247, (int) 15), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 300f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 247, (int) 247, (int) 15), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
						}	
						
						//laser orb
						
						else if(r.nextInt(100) >=33 && r.nextInt(100) <= 65) {
							p.sendMessage(ChatColor.RED + "With the 3 power orbs of Olympus, NOTHING CAN STOP ME!");
							
							final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0,3,0), EntityType.ARMOR_STAND);
							
							as.setVisible(false);
							as.setSmall(false);
							as.setMarker(true);
							as.setArms(false);
							as.setBasePlate(false);
							as.setInvulnerable(true);
							as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/835ef9230031769a2e8192ab46a1714410d56c3b9b38a0302a058e47973d7c4d"));
							
							final ArmorStand as1 = (ArmorStand) world.spawnEntity(sk.getLocation().add(0,3,0), EntityType.ARMOR_STAND);
							
							as1.setVisible(false);
							as1.setSmall(false);
							as1.setMarker(true);
							as1.setArms(false);
							as1.setBasePlate(false);
							as1.setInvulnerable(true);
							as1.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/835ef9230031769a2e8192ab46a1714410d56c3b9b38a0302a058e47973d7c4d"));
							
							
							new BukkitRunnable() {
								
								public void run() {
									if(!as.isDead()) {
										as.remove();
										
									}
									
								}
								
							}.runTaskLater(plugin, 140L);
							new BukkitRunnable() {
								double damage = 1;
								public void run() {
									if(!(as.isDead()) && !(p.isDead())) {
										
										
										if((as.getEyeLocation().subtract(0, 1, 0).getBlock().getBlockData().matches(Material.AIR.createBlockData()))) {
											 
												p.damage(damage, sk);
												
												damage *= 1.07;
												
												
																							
										}
										
										else if(!(as.getEyeLocation().subtract(0, 2, 0).getBlock().getBlockData().matches(Material.AIR.createBlockData()))) {
											world.createExplosion(as.getLocation(), 0.3f, false, false);
											as.remove();
											
											
											as1.remove();
											cancel();
											
										}
										
										
									}
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							new BukkitRunnable() {
								
								public void run() {
									
									if(!(as.isDead()) && !(p.isDead())) {
									EulerAngle rot = as.getHeadPose();
									EulerAngle rotnew = rot.add(0, 0.3, 0);
									EulerAngle rot1 = as1.getHeadPose();
									EulerAngle rotnew1 = rot1.add(0, 0.3, 0);
									as.setHeadPose(rotnew);
									as1.setHeadPose(rotnew1);
									
									
									
									if(!p.isDead()) {
										as.teleport(p.getLocation().add(0, 3, 0));
										as1.teleport(sk.getLocation().add(0, 3 ,0));
										Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 0, (int) 0), 1);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.8, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.6, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.4, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.2, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 1.8, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 1.6, p.getLocation().getZ(), 0, 0, 0, 0, dust);
									}
									
									
									}
									
									else if(as.isDead() || p.isDead() || sk.isDead()) {
										as.remove();
										as1.remove();
										cancel();
									}
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 122, (int) 0, (int) 0), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 60f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 122, (int) 0, (int) 0), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 120f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 122, (int) 0, (int) 0), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 180f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 122, (int) 0, (int) 0), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 240f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 122, (int) 0, (int) 0), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 300f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 122, (int) 0, (int) 0), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							
							
							
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 160, (int) 160, (int) 160), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 60f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 160, (int) 160, (int) 160), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 120f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 160, (int) 160, (int) 160), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 180f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 160, (int) 160, (int) 160), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 240f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 160, (int) 160, (int) 160), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 300f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 160, (int) 160, (int) 160), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
						}
						//ice orb
						
						else if(r.nextInt(100) >=66 && r.nextInt(100) <= 99) {
							
							p.sendMessage(ChatColor.RED + "With the 3 power orbs of Olympus, NOTHING CAN STOP ME!");
							final ArmorStand as = (ArmorStand) world.spawnEntity(p.getLocation().add(0,3,0), EntityType.ARMOR_STAND);
							
							as.setVisible(false);
							as.setSmall(false);
							as.setMarker(true);
							as.setArms(false);
							as.setBasePlate(false);
							as.setInvulnerable(true);
							as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/7e28a49cf23a534535be736b90772b089417224c82e201144da713ea4ea167f5"));
							
							final ArmorStand as1 = (ArmorStand) world.spawnEntity(sk.getLocation().add(0,3,0), EntityType.ARMOR_STAND);
							
							as1.setVisible(false);
							as1.setSmall(false);
							as1.setMarker(true);
							as1.setArms(false);
							as1.setBasePlate(false);
							as1.setInvulnerable(true);
							as1.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/7e28a49cf23a534535be736b90772b089417224c82e201144da713ea4ea167f5"));
							
							
							
							new BukkitRunnable() {
								int slow = 0;
								public void run() {
									if(!(as.isDead()) && !(p.isDead()) && !sk.isDead()) {
										
										
										if((as.getEyeLocation().subtract(0,1,0).getBlock().getBlockData().matches(Material.AIR.createBlockData()))) {
											 
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100 , slow));
											
											
											if(slow == 50) {
												world.createExplosion(as.getLocation(), 0.3f, false, false);
												as.remove();
												as1.remove();
												p.removePotionEffect(PotionEffectType.SLOW);
												cancel();
											}
											
											else {
												slow+= 5;
											}
										}
										
										else if(!(as.getEyeLocation().subtract(0, 2, 0).getBlock().getBlockData().matches(Material.AIR.createBlockData()))) {
											world.createExplosion(as.getLocation(), 0.3f, false, false);
											as.remove();
											
											
											as1.remove();
											
											
											cancel();
											
										}
										
										
									}
									
									
								}
							}.runTaskTimer(plugin, 60L, 10L);
							
							new BukkitRunnable() {
								
								public void run() {
									
									if(!(as.isDead()) && !(p.isDead()) && !sk.isDead()) {
									EulerAngle rot = as.getHeadPose();
									EulerAngle rotnew = rot.add(0, 0.3, 0);
									EulerAngle rot1 = as1.getHeadPose();
									EulerAngle rotnew1 = rot1.add(0, 0.3, 0);
									as.setHeadPose(rotnew);
									as1.setHeadPose(rotnew1);
									
									
									
									if(!p.isDead()) {
										as.teleport(p.getLocation().add(0, 3, 0));
										as1.teleport(sk.getLocation().add(0, 3 ,0));
										Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 255), 1);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.8, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.6, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.4, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2.2, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 2, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 1.8, p.getLocation().getZ(), 0, 0, 0, 0, dust);
										world.spawnParticle(Particle.REDSTONE, p.getLocation().getX(), p.getLocation().getY()+ 1.6, p.getLocation().getZ(), 0, 0, 0, 0, dust);
									}
									
									
									}
									
									else if(as.isDead() || p.isDead() || sk.isDead()) {
										as.remove();
										as1.remove();
										cancel();
									}
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									world.spawnParticle(Particle.SNOWBALL, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 1);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 60f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									world.spawnParticle(Particle.SNOWBALL, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 1);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 120f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									world.spawnParticle(Particle.SNOWBALL, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 1);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 180f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									world.spawnParticle(Particle.SNOWBALL, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 1);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 240f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									world.spawnParticle(Particle.SNOWBALL, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 1);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1f;
								float angle = 300f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									world.spawnParticle(Particle.SNOWBALL, sk.getLocation().getX()+x, sk.getLocation().getY()+ 3.5, sk.getLocation().getZ()+z, 1);
									angle -= 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							
							
							
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 0f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 247), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 60f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 247), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 120f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 247), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 180f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 247), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 240f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 247), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							new BukkitRunnable() {
								float radius = 1.5f;
								float angle = 300f;
								public void run() {
									if(!as.isDead() && !p.isDead()) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 247), 1);
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+x, sk.getLocation().getY()+ 2.7, sk.getLocation().getZ()+z, 0, 0, 0, 0, dust);
									angle += 0.3;
									
									}
									else if(as.isDead()) {
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							
							
						}
							
						}
					}
					
					
					else if (sk.isDead()) {
						cancel();
					}
				}
				
			}.runTaskTimer(plugin, 1200L, 200L); 
				
			//ULTIMATE ATTACK
			
			
			new BukkitRunnable() {
				
				public void run() {
					
					if(!sk.isDead() && !player.isDead()) {
						if(sk.getTarget() instanceof Player) {
							
							final Player p = (Player) sk.getTarget();
							final World world = sk.getWorld();
							
							
							final Location loc = sk.getLocation().add(0, 5, 0); //the entity never holds its gravity or no AI, so I teleport it constantly to this location and add slowness 255 till the attack is over
							sk.teleport(loc);
							
							p.sendMessage(ChatColor.DARK_RED + "You have betrayed the ocean. I will send you to Hades PERSONALLY!");
							
							
							
							//ANIMATION
							
							//inner circle tasks
							final BukkitTask inner;
							
							final BukkitTask inner1;

							final BukkitTask inner2;
							
							final BukkitTask inner3;
							
							final BukkitTask inner4;
							
							final BukkitTask inner5;
							
							//2nd circle tasks
							
							final BukkitTask c2;
							
							final BukkitTask c21;

							final BukkitTask c22;
							
							final BukkitTask c23;
							
							final BukkitTask c24;
							
							final BukkitTask c25;
							
							//outer circle
							
							final BukkitTask c3;
							
							final BukkitTask c31;

							final BukkitTask c32;
							
							final BukkitTask c33;
							
							final BukkitTask c34;
							
							final BukkitTask c35;
							
							//radii
							
							final float radius = 2f; //inner circle radius
							final float radius1 = 3f; //circle 2 radius
							final float radius2 = 4f; // circle 3 radius
							
							
							//colors
							
							final Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 0, (int) 0), 1); //inner circle
							
							final Particle.DustOptions dust1 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 255, (int) 0), 1); //second circle

							final Particle.DustOptions dust2 = new Particle.DustOptions(Color.fromRGB((int) 1, (int) 255, (int) 0), 1); //outer circle
							
							
							//innermost circle
							
							
							inner = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									sk.teleport(loc);
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							inner1 = new BukkitRunnable() {
								
								float angle = 60f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							inner2 = new BukkitRunnable() {
								
								float angle = 120f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							inner3 = new BukkitRunnable() {
								
								float angle = 180f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							inner4 = new BukkitRunnable() {
								
								float angle = 240f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							inner5 = new BukkitRunnable() {
								
								float angle = 300f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 0L, 1L);
							
							
							//2nd circle
							
							c2 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust1);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 20L, 1L);
							
							c21 = new BukkitRunnable() {
								
								float angle = 60f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust1);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 20L, 1L);
							
							c22 = new BukkitRunnable() {
								
								float angle = 120f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust1);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 20L, 1L);
							
							
							c23 = new BukkitRunnable() {
								
								float angle = 180f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust1);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 20L, 1L);
							
							c24 = new BukkitRunnable() {
								
								float angle = 240f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust1);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 20L, 1L);
							
							c25 = new BukkitRunnable() {
								
								float angle = 300f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust1);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 20L, 1L);
							
							
							//outer circle
							
							c3 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust2);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							c31 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust2);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							c32 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust2);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							c33 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust2);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							c34 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust2);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							c35 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust2);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 40L, 1L);
							
							
							//cancel animation after attack
							
							new BukkitRunnable() {
								public void run() {
									
									p.sendMessage(ChatColor.DARK_RED + "Survive THIS, and ill be impressed.");
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
									
								}
							}.runTaskLater(plugin, 60L);
							
							
							
							
							//Turn all the circles orange during the attack
							final BukkitTask inner0;
							
							final BukkitTask inner11;

							final BukkitTask inner21;
							
							final BukkitTask inner31;
							
							final BukkitTask inner41;
							
							final BukkitTask inner51;
							
							//2nd circle tasks
							
							final BukkitTask c20;
							
							final BukkitTask c211;

							final BukkitTask c221;
							
							final BukkitTask c231;
							
							final BukkitTask c241;
							
							final BukkitTask c251;
							
							//outer circle
							
							final BukkitTask c30;
							
							final BukkitTask c311;

							final BukkitTask c321;
							
							final BukkitTask c331;
							
							final BukkitTask c341;
							
							final BukkitTask c351;
							
							
							
							
							
							//color
							
							final Particle.DustOptions dust0 = new Particle.DustOptions(Color.fromRGB((int) 255, (int) 160, (int) 0), 1);
							
							
							
							
							//innermost circle
							
							
							inner0 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									sk.teleport(loc);
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							inner11 = new BukkitRunnable() {
								
								float angle = 60f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							inner21 = new BukkitRunnable() {
								
								float angle = 120f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							
							inner31 = new BukkitRunnable() {
								
								float angle = 180f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							inner41 = new BukkitRunnable() {
								
								float angle = 240f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							inner51 = new BukkitRunnable() {
								
								float angle = 300f;
								public void run() {
									
									
									double y = (radius * Math.sin(angle));
									double x = (radius * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							
							//2nd circle
							
							c20 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c211 = new BukkitRunnable() {
								
								float angle = 60f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c221 = new BukkitRunnable() {
								
								float angle = 120f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							
							c231 = new BukkitRunnable() {
								
								float angle = 180f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c241 = new BukkitRunnable() {
								
								float angle = 240f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c251 = new BukkitRunnable() {
								
								float angle = 300f;
								public void run() {
									
									
									double y = (radius1 * Math.sin(angle));
									double x = (radius1 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							
							//outer circle
							
							c30 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c311 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c321 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c331 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c341 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							c351 = new BukkitRunnable() {
								
								float angle = 0f;
								public void run() {
									
									
									double y = (radius2 * Math.sin(angle));
									double x = (radius2 * Math.cos(angle));
									
									world.spawnParticle(Particle.REDSTONE, sk.getLocation().getX()+y, sk.getLocation().getY()+x, sk.getLocation().getZ(), 0, 0, 0, 0, dust0);
									angle += 0.3;
									
									
									
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							
							//cancel animation after attack
							
							new BukkitRunnable() {
								public void run() {
									
									sk.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 2));
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
									
								}
							}.runTaskLater(plugin, 180L);
							
							
							//The attack itself
							
							
							
							
							
							
							

							
							
							
								new BukkitRunnable() {
									//radius of the circle of attack
									float radius = 4.5f;
									float angle = 0f;
									public void run() {
										if(!p.isDead() && angle <=12) {
										
										double x = (radius * Math.sin(angle));
										double z = (radius * Math.cos(angle));
										
										
										
										World world = p.getWorld();
										final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
										as.setBasePlate(false);
										as.setSmall(true);
										as.setMarker(true);
										as.setArms(false);
										as.setVisible(false);
										as.setInvulnerable(true);
										as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
										
										
										Vector pV = p.getLocation().toVector();
										double pY = p.getLocation().getY();
										
										Vector asV = as.getLocation().toVector();
										Vector fV = pV.subtract(asV).normalize();
										Location asLoc = (Location) as.getLocation();
										asLoc.setDirection(fV);
										as.teleport(asLoc);
										//rate of spawning orbs
										angle += 0.1;
										
										new BukkitRunnable() {

											int i = 0;
											int distance = 70;
											
											

											public void run() {
												
												// make the armorstands face the player
												
												Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
												final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
												
												EulerAngle rot = as.getHeadPose();
												EulerAngle rotnew = rot.add(0, 0.3, 0);
												as.setHeadPose(rotnew);
												as.teleport(as.getEyeLocation().add(vector.normalize()));
												
												
												World world = as.getWorld();
												
												
												if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
													
													if(!as.isDead()) {
														
														as.remove();
														
														
														
														cancel();
													}
													
												}



												for(Entity entity: as.getLocation().getChunk().getEntities()) {
													if(!as.isDead()) {
														if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
															if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
																if(entity instanceof LivingEntity) {
																	final LivingEntity LE = (LivingEntity) entity;
																	
																	LE.damage(50, sk);
																	
																	as.remove();
																	
																	
																	cancel();

																}
															}
														}
													}
												}


												if(i>distance) {
													if(!(as.isDead())){
														
														
														
														as.remove();
														
														
														cancel();
													}
												}
												i++;

											}









										}.runTaskTimer(plugin, 20L, 1L);
										
										
										
										}
										
									}
								}.runTaskTimer(plugin, 60L, 1L);
							
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 4.5f;
								float angle = 0f;
								public void run() {
									if(!p.isDead() && angle >= -12) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle -= 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 4.5f;
								float angle = 180f;
								public void run() {
									if(!p.isDead() && angle <=192) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle += 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 4.5f;
								float angle = -180f;
								public void run() {
									if(!p.isDead() && angle >= -192) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle -= 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 5f;
								float angle = 270f;
								public void run() {
									if(!p.isDead() && angle <=282) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle += 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 5f;
								float angle = -270f;
								public void run() {
									if(!p.isDead() && angle >= -282) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle -= 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}





									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 5f;
								float angle = 90f;
								public void run() {
									if(!p.isDead() && angle <=102) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle += 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							new BukkitRunnable() {
								//radius of the circle of attack
								float radius = 5f;
								float angle = -90f;
								public void run() {
									if(!p.isDead() && angle >= -102) {
									
									double x = (radius * Math.sin(angle));
									double z = (radius * Math.cos(angle));
									
									
									
									World world = p.getWorld();
									final ArmorStand as = (ArmorStand) world.spawnEntity(new Location(world, sk.getLocation().getX()+z, sk.getLocation().getY()+x, sk.getLocation().getZ()), EntityType.ARMOR_STAND);
									as.setBasePlate(false);
									as.setSmall(true);
									as.setMarker(true);
									as.setArms(false);
									as.setVisible(false);
									as.setInvulnerable(true);
									as.setHelmet(FishingItems.getSkull("http://textures.minecraft.net/texture/88a0f7bd3d58c58fb95e48b2b44923f5eaa2c1d54dcd72fa7cefcbbc1d4c81ad"));
									
									// make the armorstands face the player
									Vector pV = p.getLocation().toVector();
									double pY = p.getLocation().getY();
									
									Vector asV = as.getLocation().toVector();
									Vector fV = pV.subtract(asV).normalize();
									Location asLoc = (Location) as.getLocation();
									asLoc.setDirection(fV);
									as.teleport(asLoc);
									
									//rate of spawning orbs
									angle -= 0.1;
									
									new BukkitRunnable() {

										int i = 0;
										int distance = 70;
										

										public void run() {
											Location dest = as.getLocation().add(as.getEyeLocation().getDirection().multiply(10));
											final Vector vector = dest.subtract(as.getEyeLocation()).toVector();
											
											EulerAngle rot = as.getHeadPose();
											EulerAngle rotnew = rot.add(0, 0.3, 0);
											as.setHeadPose(rotnew);
											as.teleport(as.getEyeLocation().add(vector.normalize()));
											
											
											World world = as.getWorld();
											
											
											if(as.getTargetBlockExact(1) != null && !(as.getTargetBlockExact(1).isPassable())) {
												
												if(!as.isDead()) {
													
													as.remove();
													
													
													
													cancel();
												}
												
											}



											for(Entity entity: as.getLocation().getChunk().getEntities()) {
												if(!as.isDead()) {
													if(as.getLocation().distanceSquared(entity.getLocation()) <= 3) {
														if(!(entity instanceof Zombie) && !(entity instanceof ArmorStand)) {
															if(entity instanceof LivingEntity) {
																final LivingEntity LE = (LivingEntity) entity;
																
																LE.damage(50, sk);
																
																as.remove();
																
																
																cancel();

															}
														}
													}
												}
											}


											if(i>distance) {
												if(!(as.isDead())){
													
													
													
													as.remove();
													
													
													cancel();
												}
											}
											i++;

										}









									}.runTaskTimer(plugin, 20L, 1L);
									
									
									
									}
									
								}
							}.runTaskTimer(plugin, 60L, 1L);
							
							sk.setInvulnerable(false);
							
							
						}
					}
				}
			}.runTaskTimer(plugin, 600L, 900L);


			
		}
		
	}
	}
	
	
	
	

}
