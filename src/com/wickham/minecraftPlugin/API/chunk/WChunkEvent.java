package com.wickham.minecraftPlugin.API.chunk;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class WChunkEvent extends WChunkMain {
	private static final Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;

	@Override
	public void WChunkLoading(Location chunkLocation, int keepSecond) {
		chunkLocation.getChunk().load();
		BukkitTask keepLoadingChunkBukkitTask = Bukkit.getScheduler().runTaskTimer(WICKHAMS_PLUGIN, new Runnable() {
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
		}, 0, 0);
		int KLCBT_ID = keepLoadingChunkBukkitTask.getTaskId();
		Bukkit.getScheduler().runTaskLater(WICKHAMS_PLUGIN, new Runnable() {
			int ID = KLCBT_ID;

			@Override
			public void run() {
				Bukkit.getScheduler().cancelTask(ID);
			}
		}, keepSecond * 20);
	}
}
