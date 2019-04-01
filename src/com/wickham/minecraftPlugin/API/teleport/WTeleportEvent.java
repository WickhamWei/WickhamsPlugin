package com.wickham.minecraftPlugin.API.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import com.wickham.minecraftPlugin.WickhamsPlugin;
import com.wickham.minecraftPlugin.backSystem.BackMain;

public class WTeleportEvent extends WTeleportMain{
	
	private static final Plugin WICKHAMS_PLUGIN=WickhamsPlugin.getMain();
	@Override
	public boolean WTeleport(Player mainPlayer, Player targePlayer, Boolean recordLocation) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean WTeleport(Player mainPlayer, Location targeLocation, Boolean recordLocation) {
		if(isInWaitingList(mainPlayer)) {//是否已经在等待传送
			busyMsg(mainPlayer);
			return false;
		}else {
			if(mainPlayer.hasPermission("wickhamsplugin.teleportNoCD")||TELEPORT_WAITING_TIME==0) {//是否忽略CD
				if(recordLocation) {//是否记录旧位置
					BackMain.recordBackLocation(mainPlayer, mainPlayer.getLocation());
				}
				if(mainPlayer.teleport(targeLocation)) {//执行传送
					teleportSuccessMsg(mainPlayer);
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
							if(player.teleport(location)) {
								teleportSuccessMsg(player);
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
				Bukkit.getScheduler().runTaskLater(WickhamsPlugin.getMain(), new Runnable() {//取消倒计时
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