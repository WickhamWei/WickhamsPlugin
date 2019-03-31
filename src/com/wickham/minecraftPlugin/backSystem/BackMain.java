package com.wickham.minecraftPlugin.backSystem;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;


public abstract class BackMain {
	private static HashMap<String, Location> backLocationList=new HashMap<>();
	
	private static HashSet<String> backList=new HashSet<>();
	
	public static void addBackLocation(Player player,Location location) {
		backLocationList.put(player.getName(),location);
	}
	
	public static boolean hasBackLocation(Player player) {
		return backLocationList.containsKey(player.getName());
	}
	
	public static void cleanBackLocation(Player player) {
		backLocationList.remove(player.getName());
	}
	
	public static Location getOldLocation(Player player) {
		return backLocationList.get(player.getName());
	}
	
	static final int WAITING_TIME=WickhamsPlugin.getMain().getConfig().getInt("非OP传送等待时间（秒）");
	
	public static void newWaiting(Player player) {
		backList.add(player.getName());//新建等待传送事件
	}
	
	public static boolean cancelWaiting(Player player) {//取消等待传送事件
		return backList.remove(player.getName());
	}
	
	public static boolean isWaiting(Player player) {//玩家是否等待传送
		return backList.contains(player.getName());
	}
	
	public static void recordBackLocation(Player player, Location location) {
		addBackLocation(player, location);
	}
	
	public abstract boolean backTeleport(Player player);
}
