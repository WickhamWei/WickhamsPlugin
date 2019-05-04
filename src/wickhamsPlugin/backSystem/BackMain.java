package wickhamsPlugin.backSystem;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class BackMain {
	private static HashMap<String, Location> backLocationList = new HashMap<>();

	public static void addBackLocation(Player player, Location location) {
		backLocationList.put(player.getName(), location);
	}

	public static boolean hasBackLocation(Player player) {
		return backLocationList.containsKey(player.getName());
	}

	public static void cleanBackLocation(Player player) {
		backLocationList.remove(player.getName());
	}

	public static Location getOldLocation(Player player) {
		return backLocationList.get(player.getName());
	}

	public static void recordBackLocation(Player player, Location location) {
		addBackLocation(player, location);
	}
}
