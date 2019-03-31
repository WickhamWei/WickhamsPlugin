package com.wickham.minecraftPlugin.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class LoginEvent implements Listener {// 注意监听

	@EventHandler
	public void weicomeMsg(PlayerJoinEvent event) {// 欢迎信息
		if (WickhamsPlugin.getMain().getConfig().getBoolean("玩家加入时给玩家的信息开关")&&!(WickhamsPlugin.getMain().getConfig().getBoolean("登陆系统"))) {
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.GREEN+WickhamsPlugin.getMain().getConfig().getString("玩家加入时给玩家的信息"));
		}else
			return;		
	}
	
	@EventHandler
	public void versionMsg(PlayerJoinEvent event) {// 欢迎信息
		if(!WickhamsPlugin.getMain().getConfig().getBoolean("登陆系统")) {
			event.setJoinMessage(ChatColor.GREEN+event.getPlayer().getName()+" 加入了游戏");
			if (WickhamsPlugin.getMain().getConfig().getBoolean("玩家加入时的插件介绍"))
				event.getPlayer().sendMessage(ChatColor.GREEN+("服务器基础插件由Wickham提供技术支持，插件版本号V"+WickhamsPlugin.getMain().getDescription().getVersion()));
			return;	
		}else {//由登录插件接管
			event.setJoinMessage("");
		}
	}
}
