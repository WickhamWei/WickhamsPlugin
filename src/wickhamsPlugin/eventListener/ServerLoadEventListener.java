package wickhamsPlugin.eventListener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import wickhamsPlugin.WickhamsPluginUpdateChecker;
import wickhamsPlugin.WickhamsPlugin;

public class ServerLoadEventListener implements Listener {
	private Plugin mainPlugin=WickhamsPlugin.MAIN;
	private FileConfiguration config=mainPlugin.getConfig();
	private final boolean saveBoolean=mainPlugin.getConfig().getBoolean("自动保存地图");
	private final int saveTimes=mainPlugin.getConfig().getInt("自动保存地图时间间隔，单位分钟");
	private final boolean msgBoolean=mainPlugin.getConfig().getBoolean("自动公告");
	private final int msgTimes=mainPlugin.getConfig().getInt("自动公告时间间隔，单位分钟");

	@EventHandler
	public void 自动保存地图计时器(ServerLoadEvent event) {// 服务器启动完成后执行
		if (saveBoolean) {
			List<World> world = mainPlugin.getServer().getWorlds();
			Bukkit.getScheduler().runTaskTimer(mainPlugin, new Runnable() {
				@Override
				public void run() {
					for (World aWorld : world) {
						// Bukkit.broadcastMessage(ChatColor.BLUE + "服务器正在保存地图 " + aWorld.getName());
						aWorld.save();
						Bukkit.broadcastMessage(ChatColor.BLUE + "服务器保存地图 " + aWorld.getName() + " 完毕");
					}

				}
			}, saveTimes * 60 * 20, saveTimes * 60 * 20);
		} else
			return;

	}

	@EventHandler
	public void 自动公告计时器(ServerLoadEvent event) {
		if (msgBoolean) {
			Bukkit.getScheduler().runTaskTimer(mainPlugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(config.getString("公告1"));
				}
			}, 0, msgTimes * 60 * 20 * 3);// 三次错开执行
			Bukkit.getScheduler().runTaskTimer(mainPlugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(config.getString("公告2"));
				}
			}, msgTimes * 60 * 20, msgTimes * 60 * 20 * 3);// 三次错开执行
			Bukkit.getScheduler().runTaskTimer(mainPlugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(config.getString("公告3"));
				}
			}, msgTimes * 60 * 20 * 2, msgTimes * 60 * 20 * 3);// 三次错开执行
		} else
			return;
	}

	@EventHandler
	public void pluginMsg(ServerLoadEvent event) {
		BukkitRunnable checkUpdateBukkitRunnable=new BukkitRunnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				WickhamsPluginUpdateChecker.UpdateChecker();
			}
		};
		checkUpdateBukkitRunnable.runTask(mainPlugin);
	}
}
