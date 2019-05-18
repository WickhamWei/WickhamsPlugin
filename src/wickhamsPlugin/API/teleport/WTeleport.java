package wickhamsPlugin.API.teleport;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.API.chunk.WChunk;
import wickhamsPlugin.backSystem.BackMain;

public final class WTeleport {
	protected static final Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;
	protected static HashMap<String, Integer> W_TELEPORT_WAITING_LIST = new HashMap<String, Integer>();
	protected static HashMap<String, Integer> W_TELEPORT_COUNT_DOWN = new HashMap<String, Integer>();
	public static final int TELEPORT_WAITING_TIME = WickhamsPlugin.MAIN.getConfig().getInt("非OP传送等待时间（秒）");

	// 传送等待时间
	protected static void addInWaitingList(Player player, int teleportTaskID, int countDownTaskID) {// 将玩家加入传送等待列表
		W_TELEPORT_WAITING_LIST.put(player.getName(), teleportTaskID);
		W_TELEPORT_COUNT_DOWN.put(player.getName(), countDownTaskID);
	}

	protected static boolean isInWaitingList(Player player) {// 玩家是否在传送等待列表中
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
	
	public final static Boolean teleport(Player mainPlayer, Player targePlayer) {
		if (isInWaitingList(mainPlayer)) {// 是否已经在等待传送
			busyMsg(mainPlayer);
			return false;
		} else {
			if (mainPlayer.hasPermission("wickhamsplugin.teleportNoCD") || TELEPORT_WAITING_TIME == 0) {// 是否忽略CD
				if (Bukkit.getServer().getPlayer(targePlayer.getName()) == null) {
					noOnlineMsg(mainPlayer, targePlayer);
					return false;
				}
				if (targePlayer.getName().equals(mainPlayer.getName())) {
					foolishMsg(mainPlayer);
					return false;
				}
				BackMain.recordBackLocation(mainPlayer, mainPlayer.getLocation());
				WChunk.chunkLoading(mainPlayer.getLocation(), 10);
				if (mainPlayer.teleport(targePlayer.getLocation())) {// 执行传送
					teleportSuccessMsg(mainPlayer, targePlayer);
					return true;
				} else {
					teleportFailMsg(mainPlayer, targePlayer);
					return false;
				}
			} else {
				BukkitRunnable teleportBukkitRunnable = new BukkitRunnable() {
					Player player = mainPlayer;
					Player player2 = targePlayer;

					@Override
					public void run() {
						if (isInWaitingList(player)) {
							if (Bukkit.getServer().getPlayer(targePlayer.getName()) == null) {
								noOnlineMsg(player, player2);
								removeFromWaitingList(player);
								return;
							}
							if (targePlayer.getName().equals(mainPlayer.getName())) {
								foolishMsg(mainPlayer);
								return;
							}
							BackMain.recordBackLocation(player, player.getLocation());
							WChunk.chunkLoading(mainPlayer.getLocation(), 5);
							if (player.teleport(player2.getLocation())) {
								teleportSuccessMsg(player, player2);
								removeFromWaitingList(player);
								return;
							} else {
								teleportFailMsg(player, player2);
								removeFromWaitingList(player);
								return;
							}
						} else {
							return;
						}
					}
				};
				teleportBukkitRunnable.runTaskLater(WICKHAMS_PLUGIN, TELEPORT_WAITING_TIME * 20);
				BukkitRunnable countDownBukkitRunnable = new BukkitRunnable() {
					int timeLeft = TELEPORT_WAITING_TIME;
					Player player = mainPlayer;

					@Override
					public void run() {
						if (timeLeft > 0 && isInWaitingList(player)) {
							timeLeftMsg(player, timeLeft);
							timeLeft--;
						} else {
							cancel();
						}
					}
				};
				countDownBukkitRunnable.runTaskTimer(WICKHAMS_PLUGIN, 0, 20);
				int teleportTaskID = teleportBukkitRunnable.getTaskId();
				int countDownTaskID = countDownBukkitRunnable.getTaskId();
				addInWaitingList(mainPlayer, teleportTaskID, countDownTaskID);
				return true;
			}
		}
	}

	public final static boolean teleport(Player mainPlayer, Location targeLocation, Boolean recordOldLocation) {
		if (isInWaitingList(mainPlayer)) {// 是否已经在等待传送
			busyMsg(mainPlayer);
			return false;
		} else {
			if (mainPlayer.hasPermission("wickhamsplugin.teleportNoCD") || TELEPORT_WAITING_TIME == 0) {// 是否忽略CD
				if (recordOldLocation) {// 是否记录旧位置
					BackMain.recordBackLocation(mainPlayer, mainPlayer.getLocation());
				}
				WChunk.chunkLoading(mainPlayer.getLocation(), 10);
				if (mainPlayer.teleport(targeLocation)) {// 执行传送
					teleportSuccessMsg(mainPlayer);
					if (!recordOldLocation) {
						BackMain.cleanBackLocation(mainPlayer);
					}
					return true;
				} else {
					teleportFailMsg(mainPlayer);
					return false;
				}
			} else {
				BukkitRunnable teleportBukkitRunnable = new BukkitRunnable() {
					Player player = mainPlayer;
					Location location = targeLocation;
					Boolean recordLocationBoolean = recordOldLocation;

					@Override
					public void run() {
						if (isInWaitingList(player)) {
							if (recordLocationBoolean) {
								BackMain.recordBackLocation(player, player.getLocation());
							}
							WChunk.chunkLoading(mainPlayer.getLocation(), 5);
							if (player.teleport(location)) {
								teleportSuccessMsg(player);
								if (!recordLocationBoolean) {
									BackMain.cleanBackLocation(player);
								}
								removeFromWaitingList(player);
								return;
							} else {
								teleportFailMsg(player);
								removeFromWaitingList(player);
								return;
							}
						} else {
							return;
						}
					}
				};
				teleportBukkitRunnable.runTaskLater(WICKHAMS_PLUGIN, TELEPORT_WAITING_TIME * 20);
				BukkitRunnable countDownBukkitRunnable = new BukkitRunnable() {
					int timeLeft = TELEPORT_WAITING_TIME;
					Player player = mainPlayer;

					@Override
					public void run() {
						if (timeLeft > 0 && isInWaitingList(player)) {
							timeLeftMsg(player, timeLeft);
							timeLeft--;
						} else {
							cancel();
						}
					}
				};
				countDownBukkitRunnable.runTaskTimer(WICKHAMS_PLUGIN, 0, 20);
				int teleportTaskID = teleportBukkitRunnable.getTaskId();
				int countDownTaskID = countDownBukkitRunnable.getTaskId();
				addInWaitingList(mainPlayer, teleportTaskID, countDownTaskID);
				return true;
			}
		}
	}
}
