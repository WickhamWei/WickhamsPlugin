package com.wickham.minecraftPlugin.API.chunk;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class WChunk extends WChunkMain {
	@Override
	public void chunkLoading(Location chunkLocation, int keepSecond) {
		chunkLocation.getChunk().load();
		BukkitRunnable keepLoadingChunkBukkitRunnable = new BukkitRunnable() {
			Location location = chunkLocation;
			Chunk chunk = location.getChunk();

			@Override
			public void run() {
				if (chunk.isLoaded()) {
					return;
				} else {
					chunk.load();
				}
			}
		};
		keepLoadingChunkBukkitRunnable.runTaskTimer(WICKHAMS_PLUGIN, 0, 0);
		int KLCBT_ID = keepLoadingChunkBukkitRunnable.getTaskId();
		BukkitRunnable stopKeepLoadingChunk = new BukkitRunnable() {
			int ID = KLCBT_ID;

			@Override
			public void run() {
				Bukkit.getScheduler().cancelTask(ID);
			}
		};
		stopKeepLoadingChunk.runTaskLater(WICKHAMS_PLUGIN, keepSecond * 20);
	}
}
