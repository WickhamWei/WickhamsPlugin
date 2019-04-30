package com.wickham.minecraftPlugin.API.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import com.wickham.minecraftPlugin.WickhamsPlugin;
import com.wickham.minecraftPlugin.API.chunk.WChunk;
import com.wickham.minecraftPlugin.backSystem.BackMain;

public class WTeleport extends WTeleportMain{
	
	private static final Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;
	@Override
	public boolean teleport(Player mainPlayer, Player targePlayer) {
		if(isInWaitingList(mainPlayer)) {//是否已经在等待传送
			busyMsg(mainPlayer);
			return false;
		}else {
			if(mainPlayer.hasPermission("wickhamsplugin.teleportNoCD")||TELEPORT_WAITING_TIME==0) {//是否忽略CD
				if(Bukkit.getServer().getPlayer(targePlayer.getName())==null) {
					noOnlineMsg(mainPlayer, targePlayer);
					return false;
				}
				if(targePlayer.getName().equals(mainPlayer.getName())) {
					foolishMsg(mainPlayer);
					return false;
				}
				BackMain.recordBackLocation(mainPlayer, mainPlayer.getLocation());
				new WChunk().chunkLoading(mainPlayer.getLocation(), 10);
				if(mainPlayer.teleport(targePlayer.getLocation())) {//执行传送
					teleportSuccessMsg(mainPlayer,targePlayer);
					return true;
				}else {
					teleportFailMsg(mainPlayer,targePlayer);
					return false;
				}
			}else {
				addInWaitingList(mainPlayer);//加入等待传送列表
				Bukkit.getScheduler().runTaskLater(WICKHAMS_PLUGIN, new Runnable() {//在设定的秒数后执行传送
					Player player=mainPlayer;
					Player player2=targePlayer;
					@Override
					public void run() {
						if(isInWaitingList(player)) {
							if(Bukkit.getServer().getPlayer(targePlayer.getName())==null) {
								noOnlineMsg(player, player2);
								removeFromWaitingList(player);
								return;
							}
							if(targePlayer.getName().equals(mainPlayer.getName())) {
								foolishMsg(mainPlayer);
								return;
							}
							BackMain.recordBackLocation(player, player.getLocation());
							new WChunk().chunkLoading(mainPlayer.getLocation(), 10);
							if(player.teleport(player2.getLocation())) {
								teleportSuccessMsg(player,player2);
								removeFromWaitingList(player);
								return;
							}else {
								teleportFailMsg(player,player2);
								removeFromWaitingList(player);
								return;
							}
						}else {
							return;
						}
					}
				}, TELEPORT_WAITING_TIME*20);
				BukkitTask COUNT_DOWN=Bukkit.getScheduler().runTaskTimer(WICKHAMS_PLUGIN, new Runnable() {//倒计时
					int timeLeft=TELEPORT_WAITING_TIME;
					Player player=mainPlayer;
					@Override
					public void run() {
						if(timeLeft>0&&isInWaitingList(player)) {
							timeLeftMsg(player, timeLeft);
							timeLeft--;
						}else {
							return;
						}
					}
				}, 0, 20);
				final int countDownID=COUNT_DOWN.getTaskId();
				Bukkit.getScheduler().runTaskLater(WICKHAMS_PLUGIN, new Runnable() {//取消倒计时
					int ID=countDownID;
					@Override
					public void run() {
//						Bukkit.getLogger().info("清除传送事件"+ID);
						Bukkit.getScheduler().cancelTask(ID);
						return;
					}
				}, TELEPORT_WAITING_TIME*20);
				return true;
			}
		}
	}

	@Override
	public boolean teleport(Player mainPlayer, Location targeLocation, Boolean recordLocation) {
		if(isInWaitingList(mainPlayer)) {//是否已经在等待传送
			busyMsg(mainPlayer);
			return false;
		}else {
			if(mainPlayer.hasPermission("wickhamsplugin.teleportNoCD")||TELEPORT_WAITING_TIME==0) {//是否忽略CD
				if(recordLocation) {//是否记录旧位置
					BackMain.recordBackLocation(mainPlayer, mainPlayer.getLocation());
				}
				new WChunk().chunkLoading(mainPlayer.getLocation(), 10);
				if(mainPlayer.teleport(targeLocation)) {//执行传送
					teleportSuccessMsg(mainPlayer);
					if(!recordLocation) {
						BackMain.cleanBackLocation(mainPlayer);
					}
					return true;
				}else {
					teleportFailMsg(mainPlayer);
					return false;
				}
			}else {
				addInWaitingList(mainPlayer);//加入等待传送列表
				Bukkit.getScheduler().runTaskLater(WICKHAMS_PLUGIN, new Runnable() {//在设定的秒数后执行传送
					Player player=mainPlayer;
					Location location=targeLocation;
					Boolean recordLocationBoolean=recordLocation;
					@Override
					public void run() {
						if(isInWaitingList(player)) {
							if(recordLocationBoolean) {
								BackMain.recordBackLocation(player, player.getLocation());
							}
							new WChunk().chunkLoading(mainPlayer.getLocation(), 10);
							if(player.teleport(location)) {
								teleportSuccessMsg(player);
								if(!recordLocationBoolean) {
									BackMain.cleanBackLocation(player);
								}
								removeFromWaitingList(player);
								return;
							}else {
								teleportFailMsg(player);
								removeFromWaitingList(player);
								return;
							}
						}else {
							return;
						}
					}
				}, TELEPORT_WAITING_TIME*20);
				BukkitTask COUNT_DOWN=Bukkit.getScheduler().runTaskTimer(WICKHAMS_PLUGIN, new Runnable() {//倒计时
					int timeLeft=TELEPORT_WAITING_TIME;
					Player player=mainPlayer;
					@Override
					public void run() {
						if(timeLeft>0&&isInWaitingList(player)) {
							timeLeftMsg(player, timeLeft);
							timeLeft--;
						}else {
							return;
						}
					}
				}, 0, 20);
				final int countDownID=COUNT_DOWN.getTaskId();
				Bukkit.getScheduler().runTaskLater(WICKHAMS_PLUGIN, new Runnable() {//取消倒计时
					int ID=countDownID;
					@Override
					public void run() {
//						Bukkit.getLogger().info("清除传送事件"+ID);
						Bukkit.getScheduler().cancelTask(ID);
						return;
					}
				}, TELEPORT_WAITING_TIME*20);
				return true;
			}
		}
	}
	
}
