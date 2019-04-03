package com.wickham.minecraftPlugin.event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class WPlayerDeathEvent implements Listener {
	private boolean keepInventoryBoolean;
	private boolean halfLevel = false;

	public WPlayerDeathEvent(FileConfiguration mainConfiguration) {
		keepInventoryBoolean = mainConfiguration.getBoolean("死亡是否保留背包内的物品");
	}

	@EventHandler
	public void keepInventory(PlayerDeathEvent event) {
		if (keepInventoryBoolean) {
			if (!event.getKeepInventory()) {
				event.setKeepInventory(keepInventoryBoolean);
				return;
			} else {
				return;
			}
		} else {
			if (!event.getKeepInventory()) {
				return;
			} else {
				event.setKeepInventory(keepInventoryBoolean);
				return;
			}
		}
	}

	@EventHandler
	public void newLevel(PlayerDeathEvent event) {
		if (halfLevel) {
			event.setNewLevel(event.getEntity().getLevel() / 2);
		} else {
			event.setKeepLevel(true);
			return;
		}

	}
}
