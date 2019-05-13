package wickhamsPlugin.eventListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.event.WPlayerLoginEvent;
import wickhamsPlugin.loginSystem.LoginMain;

public class WPlayerLoginEventListener implements Listener {
	private boolean joinMsgBoolean = WickhamsPlugin.MAIN.getConfig().getBoolean("玩家加入时给玩家的信息开关");
	private String joinMsgString=WickhamsPlugin.MAIN.getConfig().getString("玩家加入时给玩家的信息");

	@EventHandler
	public void onPlayerLogin(WPlayerLoginEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN + "登陆成功");
		if (joinMsgBoolean) {
			player.sendMessage(ChatColor.GREEN + joinMsgString);
		}
		Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " 加入了游戏");
		player.setGameMode(GameMode.SURVIVAL);
		if (LoginMain.teleportPlayerAfterLogin((player))) {
			player.sendMessage(ChatColor.GREEN + "已经传送到退出游戏时的位置");
		} else {
			player.sendMessage(ChatColor.RED + "退出游戏时的位置已丢失，已在出生点");
		}
	}
}
