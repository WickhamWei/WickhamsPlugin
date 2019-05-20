package wickhamsPlugin.API.shapedRecipe;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.Recipe;

public class WLoadingRecipeListener implements Listener{
	private Recipe targeRecipe;
	public WLoadingRecipeListener(Recipe targeRecipe) {
		this.targeRecipe=targeRecipe;
	}
	
	@EventHandler
	public void WLoadingRecipe(ServerLoadEvent event) {
		Bukkit.addRecipe(targeRecipe);
	}
}
