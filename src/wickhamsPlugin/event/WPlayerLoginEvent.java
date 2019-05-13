package wickhamsPlugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WPlayerLoginEvent extends Event {
	
	private final Player player;
	
	public WPlayerLoginEvent(Player player) {
		this.player=player;
	}
	
	public Player getPlayer() {
		return player;
	}

	// This seems like all you need to do at a first glance, but after running your
	// program you'll get error messages from Spigot. This is because, although not
	// heavily documented, you need to incorporate the following methods into your
	// Event class

	private static final HandlerList HANDLERS = new HandlerList();

	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	// 以上为默认必须操作
	
}
