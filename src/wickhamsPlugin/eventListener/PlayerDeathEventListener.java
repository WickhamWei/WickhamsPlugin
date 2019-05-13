package wickhamsPlugin.eventListener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import wickhamsPlugin.backSystem.BackMain;

public class PlayerDeathEventListener implements Listener {
	private boolean keepInventoryBoolean;
	private boolean halfLevelBoolean;

	public PlayerDeathEventListener(FileConfiguration mainConfiguration) {
		keepInventoryBoolean = mainConfiguration.getBoolean("死亡是否保留背包内的物品");
		halfLevelBoolean = mainConfiguration.getBoolean("死亡后保留一半等级");
	}

	@EventHandler
	public void keepInventory(PlayerDeathEvent event) {
		Player player = event.getEntity();
		BackMain.recordBackLocation(player, player.getLocation());
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
		Player player = event.getEntity();
		if (event.getKeepLevel()) {
			event.setKeepLevel(false);
		}
		if (halfLevelBoolean) {
			player.setLevel(event.getEntity().getLevel() / 2);
		}
	}
}
