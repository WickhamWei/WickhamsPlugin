package wickhamsPlugin.levelEntitySpawn;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import wickhamsPlugin.loginSystem.LoginMain;

public class PlayerMoveEventListener implements Listener {

	@EventHandler
	public void listen(PlayerMoveEvent event) {
		if (LoginMain.loginSystemIsEnable()) {
			if (LoginMain.isLogin(event.getPlayer())) {
				Player targePlayer = event.getPlayer();
				int locationChunk = -1;
				double X = targePlayer.getLocation().getX();
				double Y = targePlayer.getLocation().getY();
				if (X < 100 && Y < 100) {
					locationChunk = 0;
				}
				if (PlayerLocationSave.playerLocationHashMap.get(targePlayer.getName()) == null) {
					if (locationChunk == 0) {
						new Title().send(targePlayer, "主城", ChatColor.GREEN, "相对安全的地方", ChatColor.YELLOW, 1, 3, 1);
						PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 0);
						return;
					}
				}
				if (PlayerLocationSave.playerLocationHashMap.get(targePlayer.getName()) == locationChunk) {
					return;
				}
			} else {
				return;
			}
		} else {
			// wait
		}
	}
}
