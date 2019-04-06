package com.wickham.minecraftPlugin.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class WPlayerBedEnterEvent implements Listener {
	@EventHandler
	public void playerBedEnter(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		player.setBedSpawnLocation(player.getLocation(), true);
		player.sendMessage(ChatColor.GREEN + "已设置家");
	}
}
