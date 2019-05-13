package wickhamsPlugin.eventListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.event.WPlayerRegisterEvent;

public class WPlayerRegisterEventListener implements Listener{
	
	private boolean joinMsgBoolean = WickhamsPlugin.MAIN.getConfig().getBoolean("玩家加入时给玩家的信息开关");
	private String joinMsgString=WickhamsPlugin.MAIN.getConfig().getString("玩家加入时给玩家的信息");
	
	@EventHandler
	public void onPlayerRegister(WPlayerRegisterEvent event) {
		Player player=event.getPlayer();
		player.sendMessage(ChatColor.GREEN + "注册成功，已登陆成功");
		player.getPlayer().setGameMode(GameMode.SURVIVAL);
		if (joinMsgBoolean) {
			player.sendMessage(
					ChatColor.GREEN + joinMsgString);
		}
		Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " 加入了游戏");
	}
}
