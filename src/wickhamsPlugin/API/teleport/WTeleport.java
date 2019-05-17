package wickhamsPlugin.API.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import wickhamsPlugin.API.chunk.WChunk;
import wickhamsPlugin.backSystem.BackMain;

public class WTeleport extends WTeleportMain {

	public static Boolean teleport(Player mainPlayer, Player targePlayer) {
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

	public static boolean teleport(Player mainPlayer, Location targeLocation, Boolean recordOldLocation) {
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
