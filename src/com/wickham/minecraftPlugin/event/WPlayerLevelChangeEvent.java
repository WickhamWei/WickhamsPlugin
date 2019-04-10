package com.wickham.minecraftPlugin.event;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class WPlayerLevelChangeEvent implements Listener {
	private boolean levelHealthBoolean;

	public WPlayerLevelChangeEvent(FileConfiguration mainConfiguration) {
		levelHealthBoolean = mainConfiguration.getBoolean("三十级后每升级一级加血量上限");
	}

	@EventHandler
	public void levelHealth(PlayerLevelChangeEvent event) {
		Player player = event.getPlayer();
		if (levelHealthBoolean) {
			if (player.getLevel() > 30) {
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getLevel() - 10);
			} else {
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			}
		} else {
			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		}
	}
}
