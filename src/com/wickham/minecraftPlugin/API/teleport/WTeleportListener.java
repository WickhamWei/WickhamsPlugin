package com.wickham.minecraftPlugin.API.teleport;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WTeleportListener implements Listener {
	@EventHandler
	public void spawnStopOn(PlayerMoveEvent event) {
		Player player=event.getPlayer();
		if(WTeleportMain.isInWaitingList(player)) {
			WTeleportMain.removeFromWaitingList(player);
			stopMoveMsg(player);
		}
	}
	
	public void stopMoveMsg(Player player) {
		player.sendMessage(ChatColor.RED + "你移动了，传送被取消");
	}
}
