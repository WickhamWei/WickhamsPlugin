package wickhamsPlugin.eventListener;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerLevelChangeEventListener implements Listener {
	private boolean levelHealthBoolean;
	private int maxHealth;

	public PlayerLevelChangeEventListener(FileConfiguration mainConfiguration) {
		levelHealthBoolean = mainConfiguration.getBoolean("三十级后每升级一级加血量上限");
		maxHealth = mainConfiguration.getInt("最大血量上限");
	}

	@EventHandler
	public void levelHealth(PlayerLevelChangeEvent event) {
		if (maxHealth < 20) {// 不设置小于20
			maxHealth = 20;
		}
		Player player = event.getPlayer();
		if (levelHealthBoolean) {
			if (player.getLevel() > 30) {
				if (player.getLevel() - 10 < maxHealth) {
					player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getLevel() - 10);
				} else {
					player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
				}
			} else {
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			}
		} else {
			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		}
	}
}
