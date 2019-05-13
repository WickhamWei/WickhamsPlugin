package wickhamsPlugin.eventListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerBedEnterEventListener implements Listener {
	@EventHandler
	public void playerBedEnter(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN + "已设置家");
	}
}
