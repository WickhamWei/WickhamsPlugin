package wickhamsPlugin.recipe;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;

import wickhamsPlugin.recipe.强化.JiChuTongSuiPian;
import wickhamsPlugin.recipe.强化.TieJian_12;
import wickhamsPlugin.recipe.强化.TieJian_22;
import wickhamsPlugin.recipe.强化.TieJian_32;
import wickhamsPlugin.recipe.强化.TieJian_42;
import wickhamsPlugin.recipe.强化.TieJian_52;
import wickhamsPlugin.recipe.强化.TieJian_62;
import wickhamsPlugin.recipe.普通.JiChuMuBin;
import wickhamsPlugin.recipe.普通.JiChuTieSuiPian;
import wickhamsPlugin.recipe.普通.TieJian_1;
import wickhamsPlugin.recipe.普通.TieJian_2;
import wickhamsPlugin.recipe.普通.TieJian_3;
import wickhamsPlugin.recipe.普通.TieJian_4;
import wickhamsPlugin.recipe.普通.TieJian_5;
import wickhamsPlugin.recipe.普通.TieJian_6;

public class LoadingAllRecipeListener implements Listener {
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
		allWeaponAndLevel.put(TieJian_12.itemStack, TieJian_1.level);
		allWeaponAndLevel.put(TieJian_22.itemStack, TieJian_2.level);
		allWeaponAndLevel.put(TieJian_32.itemStack, TieJian_3.level);
		allWeaponAndLevel.put(TieJian_42.itemStack, TieJian_4.level);
		allWeaponAndLevel.put(TieJian_52.itemStack, TieJian_5.level);
		allWeaponAndLevel.put(TieJian_62.itemStack, TieJian_6.level);
	}
}
