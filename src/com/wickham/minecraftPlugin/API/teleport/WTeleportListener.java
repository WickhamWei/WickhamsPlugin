package com.wickham.minecraftPlugin.API.teleport;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.wickham.minecraftPlugin.tpASystem.TpAMain;

public class WTeleportListener implements Listener {
	@EventHandler
	public void spawnStopOn(PlayerMoveEvent event) {
		Player player=event.getPlayer();
		if(WTeleportMain.isInWaitingList(player)) {
			WTeleportMain.removeFromWaitingList(player);
			stopMoveMsg(player);
		}
	}
	@EventHandler
	public void tpAStopOn(PlayerMoveEvent event) {
		Player player=event.getPlayer();
		if(TpAMain.isWaitingFirst(player)) {
			TpAMain.getWaitingTarge(player).sendMessage(ChatColor.RED+player.getName()+" 因为自身移动被迫取消传送到你这");;
			TpAMain.cancelWaiting(player);
			stopMoveMsg(player);
		}
	}
	
	public void stopMoveMsg(Player player) {
		player.sendMessage(ChatColor.RED + "你移动了，传送被取消");
	}
}
