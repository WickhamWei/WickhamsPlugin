package wickhamsPlugin.RPG;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import wickhamsPlugin.loginSystem.LoginMain;

public class WRPGPlayerMoveEventListener implements Listener {

	@EventHandler
	public void listen(PlayerMoveEvent event) {
		if (LoginMain.loginSystemIsEnable()) {
			if (LoginMain.isLogin(event.getPlayer())) {
				Player targePlayer = event.getPlayer();
				int locationChunk = 0;
				double X = targePlayer.getLocation().getX();
				double Y = targePlayer.getLocation().getY();
				if (X >= 100 || X <= -100 || Y >= 100 || Y <= -100) {
					locationChunk = 1;
					if (X >= 200 || X <= -200 || Y >= 200 || Y <= -200) {
						locationChunk = 2;
						if (X >= 300 || X <= -300 || Y >= 300 || Y <= -300) {
							locationChunk = 3;
							if (X >= 400 || X <= -400 || Y >= 400 || Y <= -400) {
								locationChunk = 4;
								if (X >= 500 || X <= -500 || Y >= 500 || Y <= -500) {
									locationChunk = 5;
									if (X >= 600 || X <= -600 || Y >= 600 || Y <= -600) {
										locationChunk = 6;
										if (X >= 700 || X <= -700 || Y >= 700 || Y <= -700) {
											locationChunk = 7;
											if (X >= 800 || X <= -800 || Y >= 800 || Y <= -800) {
												locationChunk = 8;
												if (X >= 900 || X <= -900 || Y >= 900 || Y <= -900) {
													locationChunk = 9;
													if (X >= 1000 || X <= -1000 || Y >= 1000 || Y <= -1000) {
														locationChunk = 10;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (PlayerLocationSave.playerLocationHashMap.get(targePlayer.getName()) == locationChunk) {
					return;
				} else {
					if (locationChunk == 0) {
						new Title().send(targePlayer, "主城", ChatColor.GREEN, "相对安全的地方", ChatColor.YELLOW, 1, 3, 1);
						PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 0);
						return;
					}
					if (locationChunk == 1) {
						new Title().send(targePlayer, "主城郊区", ChatColor.YELLOW, "僵尸生命和攻击力乘数1.1", ChatColor.RED, 1, 3,
								1);
						PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 0);
						return;
					}
				}
			} else {
				return;
			}
		} else {
			// wait
		}
	}
}
