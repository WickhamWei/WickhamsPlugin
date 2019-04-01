package com.wickham.minecraftPlugin.event;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;

public class WorldLoadingEvent implements Listener {
	private FileConfiguration config;
	private Plugin plugin;

	public WorldLoadingEvent(FileConfiguration config, Plugin plugin) {
		this.config = config;
		this.plugin = plugin;
	}

	@EventHandler
	public void 死亡是否保留背包内的物品(WorldInitEvent event) {// 在世界初始化时 注意插件必须先于世界初始化加载才有作用 详见pluginWIKI
		World world = event.getWorld();
		if (world.setGameRule(GameRule.KEEP_INVENTORY, config.getBoolean("死亡是否保留背包内的物品"))) {// 成功执行
//			plugin.getLogger().info("死亡是否保留背包内的物品在 " + world.getName() + " 设置为 " + config.getBoolean("死亡是否保留背包内的物品"));
		}
	}

	@EventHandler
	public void 怪物能否捡起或更换物品(WorldInitEvent event) {// Whether mobs can pick up items or change blocks.
		World world = event.getWorld();
		if (world.setGameRule(GameRule.MOB_GRIEFING, config.getBoolean("怪物能否捡起或更换物品"))) {// 成功执行
//			plugin.getLogger().info("怪物能否捡起或更换物品在 " + world.getName() + " 设置为 " + config.getBoolean("怪物能否捡起或更换物品"));
		}
	}

	@EventHandler
	public void 自动保存地图计时器(ServerLoadEvent event) {//服务器启动完成后执行
		if (config.getBoolean("自动保存地图")) {
			List<World> world = plugin.getServer().getWorlds();
			Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
				@Override
				public void run() {
					for (World aWorld : world) {
						//Bukkit.broadcastMessage(ChatColor.BLUE + "服务器正在保存地图 " + aWorld.getName());
						aWorld.save();
						Bukkit.broadcastMessage(ChatColor.BLUE + "服务器保存地图 " + aWorld.getName() + " 完毕");
					}

				}
			}, config.getInt("自动保存地图时间间隔，单位分钟") * 60 * 20, config.getInt("自动保存地图时间间隔，单位分钟") * 60 * 20);
		} else
			return;

	}

	@EventHandler
	public void 自动公告计时器(ServerLoadEvent event) {
		if (config.getBoolean("自动公告")) {
			Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(config.getString("公告1"));
				}
			}, 0, config.getInt("自动公告时间间隔，单位分钟") * 60 * 20*3);//三次错开执行
			Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(config.getString("公告2"));
				}
			}, config.getInt("自动公告时间间隔，单位分钟") * 60 * 20, config.getInt("自动公告时间间隔，单位分钟") * 60 * 20*3);//三次错开执行
			Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(config.getString("公告3"));
				}
			},config.getInt("自动公告时间间隔，单位分钟") * 60 * 20*2, config.getInt("自动公告时间间隔，单位分钟") * 60 * 20*3);//三次错开执行
		} else
			return;
	}
}
