package com.wickham.minecraftPlugin.spawnSystem;

import java.util.HashSet;

import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public abstract class SpawnMain{

	static HashSet<String> spawnList=new HashSet<>();
	
	static final int WAITING_TIME=WickhamsPlugin.getMain().getConfig().getInt("非OP传送等待时间（秒）");
	
	public static void newWaiting(Player player) {
		spawnList.add(player.getName());//新建等待传送事件
	}
	
	public static boolean cancelWaiting(Player player) {//取消等待传送事件
		return spawnList.remove(player.getName());
	}
	
	public static boolean isWaiting(Player player) {//玩家是否等待传送
		return spawnList.contains(player.getName());
	}
	

	public abstract boolean spawnTeleport(Player player);//执行传送
	
	public static void cleanSpawnAList() {//清除存储事件表
		spawnList.clear();
		return;
	}
}
