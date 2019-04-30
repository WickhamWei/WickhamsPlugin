package com.wickham.minecraftPlugin.API.teleport;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public abstract class WTeleportMain {
	public static HashSet<String> W_TELEPORT_WAITING_LIST=new HashSet<>();//传送等待的存储列表
	public static final int TELEPORT_WAITING_TIME=WickhamsPlugin.MAIN.getConfig().getInt("非OP传送等待时间（秒）");
	//传送等待时间
	protected void addInWaitingList(Player player) {//将玩家加入传送等待列表
		W_TELEPORT_WAITING_LIST.add(player.getName());
	}
	public static boolean isInWaitingList(Player player) {//玩家是否在传送等待列表中
		return W_TELEPORT_WAITING_LIST.contains(player.getName());
	}
	protected static boolean removeFromWaitingList(Player player) {//将玩家移除从传送等待列表中
		return W_TELEPORT_WAITING_LIST.remove(player.getName());
	}
	public static void clearWaitingList() {//清理等待列表
		W_TELEPORT_WAITING_LIST.clear();
	}
	public abstract boolean teleport(Player mainPlayer,Player targePlayer);
	//玩家对玩家的传送方法
	public abstract boolean teleport(Player mainPlayer,Location targeLocation,Boolean recordLocation);
	//玩家对地址的传送方法
	protected void busyMsg(Player player) {
		player.sendMessage(ChatColor.RED+"你的传送请求过于频繁，已自动取消多余的请求");
	}
	protected void teleportSuccessMsg(Player player) {
		player.sendMessage(ChatColor.GREEN+"传送成功");
	}
	protected void teleportSuccessMsg(Player mainPlayer,Player targePlayer) {
		mainPlayer.sendMessage(ChatColor.GREEN+"传送成功");
		targePlayer.sendMessage(ChatColor.GREEN+mainPlayer.getName()+" 传送成功");
	}
	protected void teleportFailMsg(Player player) {
		player.sendMessage(ChatColor.RED+"传送失败");
	}
	protected void teleportFailMsg(Player mainPlayer,Player targePlayer) {
		mainPlayer.sendMessage(ChatColor.GREEN+"传送失败");
		targePlayer.sendMessage(ChatColor.GREEN+mainPlayer.getName()+" 传送失败");
	}
	protected void timeLeftMsg(Player mainPlayer,int timeLeft) {
		mainPlayer.sendMessage(ChatColor.YELLOW+"距离传送还有 "+timeLeft+" 秒，请勿移动");
	}
	protected void noOnlineMsg(Player mainPlayer,Player targePlayer) {
		mainPlayer.sendMessage(ChatColor.RED+targePlayer.getName()+" 目标玩家已下线");
	}
	protected void foolishMsg(Player player) {
		player.sendMessage(ChatColor.YELLOW+"你干了一件蠢事");
	}
}
