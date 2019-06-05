package wickhamsPlugin.levelEntitySpawn;

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
			double X=targeEntity.getLocation().getX();
			double Y=targeEntity.getLocation().getY();
			if (X>=100&&X<200&&Y>=100&&Y<200) {
				targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(targeEntityMaxHeatlh * 1.1);
				return;
			}
		}
	}
}
