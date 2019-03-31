package com.wickham.minecraftPlugin.tpASystem;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public abstract class TpAMain{
	public static HashMap<String, String> tpAList = new HashMap<>();//存储传送事件
	public static HashMap<String, String> requestList = new HashMap<>();//存储传送事件
	
	static final int WAITING_TIME=WickhamsPlugin.getMain().getConfig().getInt("非OP传送等待时间（秒）");
	static final int REQUEST_WAITING_TIME=WickhamsPlugin.getMain().getConfig().getInt("tpa请求等待时间（秒）");

	public static void newRequest(Player player,Player targe) {
		requestList.put(player.getName(),targe.getName());
	} //新建等待传送事件
	
	public static boolean isRequestFirst(Player player) {
		return requestList.containsKey(player.getName());
	}
	
	public static boolean isRequestSecond(Player player) {
		return requestList.containsValue(player.getName());
	}
	
	public static void cancelRequest(Player player) {
		requestList.remove(player.getName());
	} 
	
	public static void newWaiting(Player player,Player targe) {
		tpAList.put(player.getName(), targe.getName());
	}
	
	public static boolean isWaitingFirst(Player player) {//玩家是否等待传送
		return tpAList.containsKey(player.getName());
	}
	
	public static boolean isWaitingSecoend(Player player) {//玩家是否等待被传送
		return tpAList.containsValue(player.getName());
	}
	
	public static Player getWaitingTarge(Player player) {
		Player targe=Bukkit.getServer().getPlayer(tpAList.get(player.getName()));
		return targe;
	}
	
	public static void cancelWaiting(Player player) {
		tpAList.remove(player.getName());
	}
	
	public abstract boolean requestTeleport(Player player,Player targe);//执行传送
	
	public static void cleanAllList() {//清除存储事件表
		tpAList.clear();
		requestList.clear();
		return;
	}
}
