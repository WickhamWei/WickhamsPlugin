package wickhamsPlugin.API.chunk;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;

public abstract class WChunkMain {
	protected static final Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;

	public abstract void chunkLoading(Location chunkLocation, int keepSecond);
}