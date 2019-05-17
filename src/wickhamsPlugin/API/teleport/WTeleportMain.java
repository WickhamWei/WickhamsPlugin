package wickhamsPlugin.API.teleport;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;

public abstract class WTeleportMain {
	protected static final Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;
	protected static HashMap<String, Integer> W_TELEPORT_WAITING_LIST = new HashMap<String, Integer>();
	protected static HashMap<String, Integer> W_TELEPORT_COUNT_DOWN = new HashMap<String, Integer>();
	public static final int TELEPORT_WAITING_TIME = WickhamsPlugin.MAIN.getConfig().getInt("非OP传送等待时间（秒）");

	// 传送等待时间
	protected static void addInWaitingList(Player player, int teleportTaskID, int countDownTaskID) {// 将玩家加入传送等待列表
		W_TELEPORT_WAITING_LIST.put(player.getName(), teleportTaskID);
		W_TELEPORT_COUNT_DOWN.put(player.getName(), countDownTaskID);
	}

	public static boolean isInWaitingList(Player player) {// 玩家是否在传送等待列表中
		return W_TELEPORT_WAITING_LIST.containsKey(player.getName());
	}

	protected static void removeFromWaitingList(Player player) {// 将玩家移除从传送等待列表中
		W_TELEPORT_WAITING_LIST.remove(player.getName());
		W_TELEPORT_COUNT_DOWN.remove(player.getName());
	}

	protected static int getTeleportTaskID(Player player) {
		return W_TELEPORT_WAITING_LIST.get(player.getName());
	}

	protected static int getCountDownTaskID(Player player) {
		return W_TELEPORT_COUNT_DOWN.get(player.getName());
	}

	public static void clearWaitingList() {// 清理等待列表
		W_TELEPORT_WAITING_LIST.clear();
	}

	// 玩家对地址的传送方法
	protected static void busyMsg(Player player) {
		player.sendMessage(ChatColor.RED + "你的传送请求过于频繁，已自动取消多余的请求");
	}

	protected static void teleportSuccessMsg(Player player) {
		player.sendMessage(ChatColor.GREEN + "传送成功");
	}

	protected static void teleportSuccessMsg(Player mainPlayer, Player targePlayer) {
		mainPlayer.sendMessage(ChatColor.GREEN + "传送成功");
		targePlayer.sendMessage(ChatColor.GREEN + mainPlayer.getName() + " 传送成功");
	}

	protected static void teleportFailMsg(Player player) {
		player.sendMessage(ChatColor.RED + "传送失败");
	}

	protected static void teleportFailMsg(Player mainPlayer, Player targePlayer) {
		mainPlayer.sendMessage(ChatColor.GREEN + "传送失败");
		targePlayer.sendMessage(ChatColor.GREEN + mainPlayer.getName() + " 传送失败");
	}

	protected static void timeLeftMsg(Player mainPlayer, int timeLeft) {
		mainPlayer.sendMessage(ChatColor.YELLOW + "距离传送还有 " + timeLeft + " 秒，请勿移动");
	}

	protected static void noOnlineMsg(Player mainPlayer, Player targePlayer) {
		mainPlayer.sendMessage(ChatColor.RED + targePlayer.getName() + " 目标玩家已下线");
	}

	protected static void foolishMsg(Player player) {
		player.sendMessage(ChatColor.YELLOW + "你干了一件蠢事");
	}
}
