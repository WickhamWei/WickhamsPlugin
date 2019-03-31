package com.wickham.minecraftPlugin.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("unused")
public class SteppingOnEvent implements Listener {// 导入config
	private FileConfiguration config;

	public SteppingOnEvent(FileConfiguration config) {
		this.config = config;
	}

	@EventHandler
	public void 踩踏泥土(PlayerInteractEvent event) {// Represents an event that is called when a player interacts with an
													// object or air
		if (config.getBoolean("保护耕地不被踩坏")) {// 检查配置文件
			if (event.getAction() == Action.PHYSICAL)// 踩到一个块
			{
//				event.getPlayer().sendMessage(ChatColor.YELLOW+"正在保护耕地");
				if (event.getClickedBlock().getType() == Material.FARMLAND) {// 踩到的是耕地
					event.setCancelled(true);// 取消效果
				}
			}
		}
		return;
	}
}