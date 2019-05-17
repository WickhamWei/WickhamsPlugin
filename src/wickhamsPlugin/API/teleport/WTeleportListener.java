package wickhamsPlugin.API.teleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WTeleportListener implements Listener {
	@EventHandler
	public void spawnStopOn(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (WTeleportMain.isInWaitingList(player)) {
			Bukkit.getScheduler().cancelTask(WTeleportMain.getTeleportTaskID(player));
			Bukkit.getScheduler().cancelTask(WTeleportMain.getCountDownTaskID(player));
			WTeleportMain.removeFromWaitingList(player);
			stopMoveMsg(player);
		}
	}

	public void stopMoveMsg(Player player) {
		player.sendMessage(ChatColor.RED + "你移动了，传送被取消");
	}
}
