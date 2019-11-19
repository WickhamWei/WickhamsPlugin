package wickhamsPlugin.eventListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.loginSystem.LoginMain;

public class PlayerQuitEventListener implements Listener {

	private static final Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;

	@EventHandler
	public void versionMsg(PlayerQuitEvent event) {// 欢迎信息
		event.setQuitMessage("");
		Player player = event.getPlayer();
		if (WICKHAMS_PLUGIN.getConfig().getBoolean("登陆系统")) {
			if (!LoginMain.isLogin(player)) {
				LoginMain.playerQuit(player);
				return;
			} else {
				Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " 退出了游戏");
				LoginMain.countDownWhenPlayerLeft(player);
				LoginMain.playerQuit(player);
				LoginMain.savePlayerLastLocation(player, player.getLocation());
				LoginMain.recordPlayerIPAddressInConfig(player);
				return;
			}
		} else {
			Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " 退出了游戏");
			return;
		}
	}
}
