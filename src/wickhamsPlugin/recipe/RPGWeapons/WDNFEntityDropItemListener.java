package wickhamsPlugin.recipe.RPGWeapons;

import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import wickhamsPlugin.recipe.RPGWeapons.normal.JiChuTieSuiPian;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.JiChuTongSuiPian;

public class WDNFEntityDropItemListener implements Listener {

	@EventHandler
	public void zDeath(EntityDeathEvent event) {
		if (event.getEntityType().equals(EntityType.ZOMBIE)) {
			Random random = new Random();
			int i = random.nextInt(100);
			if (i <= 10) {
				event.getDrops().add(JiChuTieSuiPian.itemStack);
			}
			int j = random.nextInt(100);
			if (j <= 5) {
				event.getDrops().add(JiChuTongSuiPian.itemStack);
			}
		}
	}
}
