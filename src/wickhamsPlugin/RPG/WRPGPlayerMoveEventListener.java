package wickhamsPlugin.RPG;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.loginSystem.LoginMain;

public class WRPGPlayerMoveEventListener implements Listener {

	@EventHandler
	public void listen(PlayerMoveEvent event) {
		if (WickhamsPlugin.MAIN.loginSystemBoolean) {
			if (!LoginMain.isLogin(event.getPlayer())) {
				return;
			}
		}
		Player targePlayer = event.getPlayer();
		int locationChunk = 0;
		double X = targePlayer.getLocation().getX();
		double Z = targePlayer.getLocation().getZ();
		if (X >= 100 || X <= -100 || Z >= 100 || Z <= -100) {
			locationChunk = 1;
			if (X >= 200 || X <= -200 || Z >= 200 || Z <= -200) {
				locationChunk = 2;
				if (X >= 300 || X <= -300 || Z >= 300 || Z <= -300) {
					locationChunk = 3;
					if (X >= 400 || X <= -400 || Z >= 400 || Z <= -400) {
						locationChunk = 4;
						if (X >= 500 || X <= -500 || Z >= 500 || Z <= -500) {
							locationChunk = 5;
							if (X >= 600 || X <= -600 || Z >= 600 || Z <= -600) {
								locationChunk = 6;
								if (X >= 700 || X <= -700 || Z >= 700 || Z <= -700) {
									locationChunk = 7;
									if (X >= 800 || X <= -800 || Z >= 800 || Z <= -800) {
										locationChunk = 8;
										if (X >= 900 || X <= -900 || Z >= 900 || Z <= -900) {
											locationChunk = 9;
											if (X >= 1000 || X <= -1000 || Z >= 1000 || Z <= -1000) {
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
		if (!PlayerLocationSave.playerLocationHashMap.containsKey(targePlayer.getName())) {
			PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), -1);
		}
		if (PlayerLocationSave.playerLocationHashMap.get(targePlayer.getName()) == locationChunk) {
			return;
		} else {
			switch (locationChunk) {
			case 0:
				targePlayer.sendTitle(ChatColor.GREEN + "主城", ChatColor.YELLOW + "相对安全的地方", 10, 100, 20);
				PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 0);
				return;
			case 1:
				targePlayer.sendTitle(ChatColor.YELLOW + "主城郊区", ChatColor.YELLOW + "怪物生命和攻击力乘数1.1", 10, 100, 20);
				PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 1);
				return;
			case 2:
				targePlayer.sendTitle(ChatColor.YELLOW + "龙门外环", ChatColor.YELLOW + "怪物生命和攻击力乘数1.2", 10, 100, 20);
				PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 2);
				return;
			case 3:
				targePlayer.sendTitle(ChatColor.YELLOW + "低安区", ChatColor.YELLOW + "怪物生命和攻击力乘数1.3", 10, 100, 20);
				PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 3);
				return;
			case 4:
				targePlayer.sendTitle(ChatColor.YELLOW + "生化区", ChatColor.YELLOW + "怪物生命和攻击力乘数1.4", 10, 100, 20);
				PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 4);
				return;
			case 5:
				targePlayer.sendTitle(ChatColor.YELLOW + "切尔诺贝利", ChatColor.YELLOW + "怪物生命和攻击力乘数1.5", 10, 100, 20);
				PlayerLocationSave.playerLocationHashMap.put(targePlayer.getName(), 5);
				return;
			default:
				;
			}
		}
	}
}
