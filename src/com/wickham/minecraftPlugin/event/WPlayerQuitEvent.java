package com.wickham.minecraftPlugin.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class WPlayerQuitEvent implements Listener {
	
	@EventHandler
	public void versionMsg(PlayerQuitEvent event) {// 欢迎信息
		event.setQuitMessage(ChatColor.YELLOW+event.getPlayer().getName()+" 退出了游戏");
		return;		
	}
}
