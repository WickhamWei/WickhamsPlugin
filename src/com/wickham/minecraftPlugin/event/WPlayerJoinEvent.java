package com.wickham.minecraftPlugin.event;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class WPlayerJoinEvent implements Listener{
	private boolean levelHealthBoolean;
	private int maxHealth;
	private boolean welcomeMsgBoolean;
	private boolean loginSystemBoolean;
	private boolean versionBoolean;
	private FileConfiguration mainConfiguration;

	public WPlayerJoinEvent(FileConfiguration mainConfiguration) {
		levelHealthBoolean = mainConfiguration.getBoolean("三十级后每升级一级加血量上限");
		welcomeMsgBoolean =mainConfiguration.getBoolean("玩家加入时给玩家的信息开关");
		loginSystemBoolean=mainConfiguration.getBoolean("登陆系统");
		versionBoolean=mainConfiguration.getBoolean("玩家加入时的插件介绍");
		maxHealth = mainConfiguration.getInt("最大血量上限，一颗心为两个血");
		this.mainConfiguration=mainConfiguration;
	}
	
	@EventHandler
	public void levelHealth(PlayerJoinEvent event) {
		if (maxHealth < 20) {// 不设置小于20
			maxHealth = 20;
		}
		Player player = event.getPlayer();
		if (levelHealthBoolean) {
			if (player.getLevel() > 30) {
				if (player.getLevel() - 10 < maxHealth) {
					player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getLevel() - 10);
				} else {
					player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
				}
			} else {
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			}
		} else {
			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		}
	}
	
	@EventHandler
	public void welcomeMsg(PlayerJoinEvent event) {// 欢迎信息
		if (welcomeMsgBoolean&&!loginSystemBoolean) {
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.GREEN+mainConfiguration.getString("玩家加入时给玩家的信息"));
		}else
			return;		
	}
	
	@EventHandler
	public void versionMsg(PlayerJoinEvent event) {// 欢迎信息
		if(!loginSystemBoolean) {
			event.setJoinMessage(ChatColor.GREEN+event.getPlayer().getName()+" 加入了游戏");
			if (versionBoolean)
				event.getPlayer().sendMessage(ChatColor.GREEN+("服务器基础插件由Wickham提供技术支持，插件版本号V"+WickhamsPlugin.getMain().getDescription().getVersion()));
			return;	
		}else {//由登录插件接管
			event.setJoinMessage("");
		}
	}
}
