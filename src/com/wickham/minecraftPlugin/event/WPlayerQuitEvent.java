package com.wickham.minecraftPlugin.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import com.wickham.minecraftPlugin.WickhamsPlugin;
import com.wickham.minecraftPlugin.loginSystem.LoginMain;

public class WPlayerQuitEvent implements Listener {
	
	private static final Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;

	@EventHandler
	public void versionMsg(PlayerQuitEvent event) {// 欢迎信息
		event.setQuitMessage("");
		Player player=event.getPlayer();
		if (WICKHAMS_PLUGIN.getConfig().getBoolean("登陆系统")) {
			if (!LoginMain.isLogin(player)) {
				LoginMain.playerQuit(player);
				return;
			}else {
				Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " 退出了游戏");
				LoginMain.countDownWhenPlayerLeft(player);
				LoginMain.playerQuit(player);
				return;
			}
		}else {
			Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " 退出了游戏");
			return;
		}
	}
}
