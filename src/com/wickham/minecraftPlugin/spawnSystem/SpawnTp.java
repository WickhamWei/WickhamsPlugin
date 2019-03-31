package com.wickham.minecraftPlugin.spawnSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class SpawnTp extends SpawnMain{
	@Override
	public boolean spawnTeleport(Player player) {
		if(isWaiting(player)) {
			return false;
		}else {
			newWaiting(player);
			if(player.hasPermission("wickhamsplugin.teleportNoCD")||SpawnMain.WAITING_TIME==0) {
				if(player.teleport(player.getWorld().getSpawnLocation())) {
					player.sendMessage(ChatColor.GREEN+"传送成功");
					return true;
				}else {
					return false;
				}
			}else {
				Bukkit.getScheduler().runTaskLater(WickhamsPlugin.getMain(), new Runnable() {
					Player thisPlayer=player;
					@Override
					public void run() {
						if(isWaiting(thisPlayer)) {
							thisPlayer.teleport(player.getWorld().getSpawnLocation());
							thisPlayer.sendMessage(ChatColor.GREEN+"传送成功");
							cancelWaiting(thisPlayer);
						}		
					}
				}, SpawnMain.WAITING_TIME*20);
				BukkitTask COUNT_DOWN=Bukkit.getScheduler().runTaskTimer(WickhamsPlugin.getMain(), new Runnable() {
					int time=SpawnMain.WAITING_TIME;
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
				}, SpawnMain.WAITING_TIME*20);
				return true;
			}
		}
	}
}
