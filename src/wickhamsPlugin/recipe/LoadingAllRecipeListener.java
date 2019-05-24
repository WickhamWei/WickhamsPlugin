package wickhamsPlugin.recipe;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;

public class LoadingAllRecipeListener implements Listener {
	public static HashMap<ItemStack, Integer> allWeaponAndLevel = new HashMap<ItemStack, Integer>();

	@EventHandler
	public void newHugeRottenFlash(ServerLoadEvent event) {
		new JiChuTieSuiPian();
		new JiChuMuBin();
		new TieJian_1();
		new TieJian_2();
		new TieJian_3();
		new JiChuTongSuiPian();
		new TieJian_4();
		new TieJian_5();
		new TieJian_6();
		addInWeaponList();
	}

	private void addInWeaponList() {
		allWeaponAndLevel.put(TieJian_1.getItemStack(), TieJian_1.level);
		allWeaponAndLevel.put(TieJian_2.getItemStack(), TieJian_2.level);
		allWeaponAndLevel.put(TieJian_3.getItemStack(), TieJian_3.level);
		allWeaponAndLevel.put(TieJian_4.getItemStack(), TieJian_4.level);
		allWeaponAndLevel.put(TieJian_5.getItemStack(), TieJian_5.level);
		allWeaponAndLevel.put(TieJian_6.getItemStack(), TieJian_6.level);
		
	}
}
