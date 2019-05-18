package wickhamsPlugin.eventListener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosionPrimeEventListener implements Listener{
	@EventHandler
	public void jjBoom(ExplosionPrimeEvent event) {
		if(event.getEntityType().equals(EntityType.CREEPER)) {
			event.setRadius(0);
			return;
		}else {
			return;
		}
	}
}
