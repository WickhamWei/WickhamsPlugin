package wickhamsPlugin.entitySpawn;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnEventListener implements Listener {
	@EventHandler
	public void zSpawn(EntitySpawnEvent event) {
		if (event.getEntity() instanceof Zombie) {
			Zombie targeEntity = (Zombie) event.getEntity();
			double targeEntityMaxHeatlh = targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			if (event.getLocation().getX() >= 100 && event.getLocation().getX() < 200) {
				targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(targeEntityMaxHeatlh * 1.1);
			}
		}
	}
}
