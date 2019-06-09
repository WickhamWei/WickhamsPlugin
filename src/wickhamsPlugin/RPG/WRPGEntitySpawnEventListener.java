package wickhamsPlugin.RPG;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class WRPGEntitySpawnEventListener implements Listener {
	@EventHandler
	public void zSpawn(EntitySpawnEvent event) {
		if (event.getEntity() instanceof Zombie) {
			Zombie targeEntity = (Zombie) event.getEntity();
			double targeEntityMaxHeatlh = targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			double targeEntityDamage = targeEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
			double X = targeEntity.getLocation().getX();
			double Z = targeEntity.getLocation().getY();
			int locationChunk = 0;
			if (X >= 100 || X <= -100 || Z >= 100 || Z <= -100) {
				locationChunk = 1;
				if (X >= 200 || X <= -200 || Z >= 200 || Z <= -200) {
					locationChunk = 2;
					if (X >= 300 || X <= -300 || Z >= 300 || Z <= -300) {
						locationChunk = 3;
						if (X >= 400 || X <= -400 || Z >= 400 || Z <= -400) {
							locationChunk = 4;
							if (X >= 500 || X <= -500 || Z >= 500 || Z <= -500) {
								locationChunk = 5;
								if (X >= 600 || X <= -600 || Z >= 600 || Z <= -600) {
									locationChunk = 6;
									if (X >= 700 || X <= -700 || Z >= 700 || Z <= -700) {
										locationChunk = 7;
										if (X >= 800 || X <= -800 || Z >= 800 || Z <= -800) {
											locationChunk = 8;
											if (X >= 900 || X <= -900 || Z >= 900 || Z <= -900) {
												locationChunk = 9;
												if (X >= 1000 || X <= -1000 || Z >= 1000 || Z <= -1000) {
													locationChunk = 10;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
					.setBaseValue(targeEntityMaxHeatlh * (1 + (locationChunk / 10)));
			targeEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)
					.setBaseValue(targeEntityDamage * (1 + (locationChunk / 10)));
			return;
		}
		if (event.getEntity() instanceof Skeleton) {
			Skeleton targeEntity = (Skeleton) event.getEntity();
			double targeEntityMaxHeatlh = targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			double targeEntityDamage = targeEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
			double X = targeEntity.getLocation().getX();
			double Z = targeEntity.getLocation().getY();
			int locationChunk = 0;
			if (X >= 100 || X <= -100 || Z >= 100 || Z <= -100) {
				locationChunk = 1;
				if (X >= 200 || X <= -200 || Z >= 200 || Z <= -200) {
					locationChunk = 2;
					if (X >= 300 || X <= -300 || Z >= 300 || Z <= -300) {
						locationChunk = 3;
						if (X >= 400 || X <= -400 || Z >= 400 || Z <= -400) {
							locationChunk = 4;
							if (X >= 500 || X <= -500 || Z >= 500 || Z <= -500) {
								locationChunk = 5;
								if (X >= 600 || X <= -600 || Z >= 600 || Z <= -600) {
									locationChunk = 6;
									if (X >= 700 || X <= -700 || Z >= 700 || Z <= -700) {
										locationChunk = 7;
										if (X >= 800 || X <= -800 || Z >= 800 || Z <= -800) {
											locationChunk = 8;
											if (X >= 900 || X <= -900 || Z >= 900 || Z <= -900) {
												locationChunk = 9;
												if (X >= 1000 || X <= -1000 || Z >= 1000 || Z <= -1000) {
													locationChunk = 10;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
					.setBaseValue(targeEntityMaxHeatlh * (1 + (locationChunk / 10)));
			targeEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)
					.setBaseValue(targeEntityDamage * (1 + (locationChunk / 10)));
			return;
		}
		if (event.getEntity() instanceof Creeper) {
			Creeper targeEntity = (Creeper) event.getEntity();
			double targeEntityMaxHeatlh = targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			double X = targeEntity.getLocation().getX();
			double Z = targeEntity.getLocation().getY();
			int locationChunk = 0;
			if (X >= 100 || X <= -100 || Z >= 100 || Z <= -100) {
				locationChunk = 1;
				if (X >= 200 || X <= -200 || Z >= 200 || Z <= -200) {
					locationChunk = 2;
					if (X >= 300 || X <= -300 || Z >= 300 || Z <= -300) {
						locationChunk = 3;
						if (X >= 400 || X <= -400 || Z >= 400 || Z <= -400) {
							locationChunk = 4;
							if (X >= 500 || X <= -500 || Z >= 500 || Z <= -500) {
								locationChunk = 5;
								if (X >= 600 || X <= -600 || Z >= 600 || Z <= -600) {
									locationChunk = 6;
									if (X >= 700 || X <= -700 || Z >= 700 || Z <= -700) {
										locationChunk = 7;
										if (X >= 800 || X <= -800 || Z >= 800 || Z <= -800) {
											locationChunk = 8;
											if (X >= 900 || X <= -900 || Z >= 900 || Z <= -900) {
												locationChunk = 9;
												if (X >= 1000 || X <= -1000 || Z >= 1000 || Z <= -1000) {
													locationChunk = 10;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			targeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
					.setBaseValue(targeEntityMaxHeatlh * (1 + (locationChunk / 10)));
			targeEntity.setExplosionRadius(targeEntity.getExplosionRadius()*(1 + (locationChunk / 10)));
			return;
		}
	}
}
