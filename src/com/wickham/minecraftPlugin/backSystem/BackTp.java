package com.wickham.minecraftPlugin.backSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class BackTp extends BackMain{

	@Override
	public boolean backTeleport(Player player) {
		// TODO 自动生成的方法存根
		if(isWaiting(player)) {
			return false;
		}else {
			if(player.hasPermission("wickhamsplugin.teleportNoCD")||BackMain.WAITING_TIME==0) {
				if(player.teleport(getOldLocation(player))) {
					player.sendMessage(ChatColor.GREEN+"传送成功");
					cleanBackLocation(player);
					return true;
				}else {
					return false;
				}
			}else {
				newWaiting(player);
				Bukkit.getScheduler().runTaskLater(WickhamsPlugin.getMain(), new Runnable() {
					Player thisPlayer=player;
					@Override
					public void run() {
						if(isWaiting(thisPlayer)) {
							thisPlayer.sendMessage(ChatColor.GREEN+"传送成功");
							cleanBackLocation(thisPlayer);
							cancelWaiting(thisPlayer);
						}		
					}
				}, BackMain.WAITING_TIME*20);
				BukkitTask COUNT_DOWN=Bukkit.getScheduler().runTaskTimer(WickhamsPlugin.getMain(), new Runnable() {
					int time=BackMain.WAITING_TIME;
					Player thisPlayer=player;
					@Override
					public void run() {
						if(time>0&&isWaiting(thisPlayer)) {
							thisPlayer.sendMessage(ChatColor.YELLOW+"距离传送还有 "+time+" 秒");
							time--;
						}else {
							return;
						}
					}
				}, 0, 20);
				final int countDownID=COUNT_DOWN.getTaskId();
				Bukkit.getScheduler().runTaskLater(WickhamsPlugin.getMain(), new Runnable() {
					int ID=countDownID;
					@Override
					public void run() {
//						Bukkit.getLogger().info("清除传送事件"+ID);
						Bukkit.getScheduler().cancelTask(ID);
					}
				}, BackMain.WAITING_TIME*20);
				return true;
			}
		}
	}
	
}
