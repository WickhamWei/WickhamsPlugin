package com.wickham.minecraftPlugin.tpASystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class TpATp extends TpAMain{
	@Override
	public boolean requestTeleport(Player player, Player targe) {
		if(isWaitingFirst(player)) {
			cancelRequest(player);
			targe.sendMessage(ChatColor.RED+"对方正在被其他人传送");
			return false;
		}else {
			cancelRequest(player);
			newWaiting(player,targe);
			if(player.hasPermission("wickhamsplugin.teleportNoCD")||TpAMain.WAITING_TIME==0) {
				if(player.teleport(targe.getLocation()) ){
					targe.sendMessage(ChatColor.DARK_GREEN+player.getName()+" 传送成功");
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
						if(isWaitingFirst(thisPlayer)) {
							thisPlayer.teleport(targe.getPlayer());
							thisPlayer.sendMessage(ChatColor.GREEN+"传送成功");
							targe.sendMessage(ChatColor.GREEN+player.getName()+" 传送成功");
							cancelWaiting(thisPlayer);
						}		
					}
				}, TpAMain.WAITING_TIME*20);
				BukkitTask TPA_TASK_COUNT_DOWN=Bukkit.getScheduler().runTaskTimer(WickhamsPlugin.getMain(), new Runnable() {
					int time=TpAMain.WAITING_TIME;
					Player thisPlayer=player;
					@Override
					public void run() {
						if(time>0&&isWaitingFirst(thisPlayer)) {
							thisPlayer.sendMessage(ChatColor.YELLOW+"距离传送还有 "+time+" 秒");
							time--;
						}else {
							return;
						}
					}
				}, 0, 20);
				final int countDownID=TPA_TASK_COUNT_DOWN.getTaskId();
				Bukkit.getScheduler().runTaskLater(WickhamsPlugin.getMain(), new Runnable() {
					int ID=countDownID;
					@Override
					public void run() {
//						Bukkit.getLogger().info("清除传送事件"+ID);
						Bukkit.getScheduler().cancelTask(ID);
					}
				}, TpAMain.WAITING_TIME*20);
				return true;
			}
		}
	}
}
