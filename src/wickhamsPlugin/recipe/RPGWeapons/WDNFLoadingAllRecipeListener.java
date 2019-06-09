package wickhamsPlugin.recipe.RPGWeapons;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;

import wickhamsPlugin.recipe.RPGWeapons.normal.JiChuMuBin;
import wickhamsPlugin.recipe.RPGWeapons.normal.JiChuTieSuiPian;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_1;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_2;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_3;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_4;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_5;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_6;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.JiChuTongSuiPian;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.TieJian_12;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.TieJian_22;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.TieJian_32;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.TieJian_42;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.TieJian_52;
import wickhamsPlugin.recipe.RPGWeapons.strengthen.TieJian_62;

public class WDNFLoadingAllRecipeListener implements Listener {
	public static HashMap<ItemStack, Integer> allWeaponAndLevel = new HashMap<ItemStack, Integer>();

	@EventHandler
	public void newHugeRottenFlash(ServerLoadEvent event) {
		new JiChuTieSuiPian();
		new JiChuMuBin();
		new TieJian_1();
		new TieJian_2();
		new TieJian_3();
		new TieJian_4();
		new TieJian_5();
		new TieJian_6();
		new JiChuTongSuiPian();
		new TieJian_12();
		new TieJian_22();
		new TieJian_32();
		new TieJian_42();
		new TieJian_52();
		new TieJian_62();
		addInWeaponList();
	}

	private void addInWeaponList() {
		allWeaponAndLevel.put(TieJian_1.itemStack, TieJian_1.level);
		allWeaponAndLevel.put(TieJian_2.itemStack, TieJian_2.level);
		allWeaponAndLevel.put(TieJian_3.itemStack, TieJian_3.level);
		allWeaponAndLevel.put(TieJian_4.itemStack, TieJian_4.level);
		allWeaponAndLevel.put(TieJian_5.itemStack, TieJian_5.level);
		allWeaponAndLevel.put(TieJian_6.itemStack, TieJian_6.level);
		allWeaponAndLevel.put(TieJian_12.itemStack, TieJian_12.level);
		allWeaponAndLevel.put(TieJian_22.itemStack, TieJian_22.level);
		allWeaponAndLevel.put(TieJian_32.itemStack, TieJian_32.level);
		allWeaponAndLevel.put(TieJian_42.itemStack, TieJian_42.level);
		allWeaponAndLevel.put(TieJian_52.itemStack, TieJian_52.level);
		allWeaponAndLevel.put(TieJian_62.itemStack, TieJian_62.level);
	}
}
