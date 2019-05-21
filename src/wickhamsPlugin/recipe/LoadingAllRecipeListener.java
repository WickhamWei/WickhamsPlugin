package wickhamsPlugin.recipe;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

public class LoadingAllRecipeListener implements Listener{
	@EventHandler
	public void newHugeRottenFlash(ServerLoadEvent event) {
		new JiChuTieSuiPian();
		new JiChuMuBin();
		new PoSunDeTieJian();
	}
}
