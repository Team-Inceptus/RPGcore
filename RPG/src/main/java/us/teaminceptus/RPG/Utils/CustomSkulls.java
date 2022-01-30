package us.teaminceptus.RPG.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;



public class CustomSkulls {
	
	public static ItemStack getSkull(String url) {
		  ItemStack head= new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);

		        if (url == null) {
					return head;
				}
		        ItemMeta skullMeta = head.getItemMeta();
		        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		        Field profileField = null;

		        try {
		            profileField = skullMeta.getClass().getDeclaredField("profile");
		        } catch (NoSuchFieldException e) {
		            e.printStackTrace();
		        }

		        profileField.setAccessible(true);

		        try {
		        	try {
						profileField.set(skullMeta, profile);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        } catch (IllegalArgumentException e) {
		            e.printStackTrace();
		        }

		        head.setItemMeta(skullMeta);
		        return head;
		}

}
