package wickhamsPlugin.recipe;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;

public class LoadingAllRecipeListener implements Listener{
	public static HashMap<ItemStack,Integer> allWeaponAndLevel=new HashMap<ItemStack,Integer>();
	
	@EventHandler
	public void newHugeRottenFlash(ServerLoadEvent event) {
		new JiChuTieSuiPian();
		new JiChuMuBin();
		new PoSunDeTieJian();
		new PoSunDeJuJian();
		addInWeaponList();
	}
	
	private void addInWeaponList() {
		allWeaponAndLevel.put(PoSunDeTieJian.getItemStack(),PoSunDeTieJian.level);
		allWeaponAndLevel.put(PoSunDeJuJian.getItemStack(),PoSunDeJuJian.level);
	}
}
