package com.wickham.minecraftPlugin.shapedRecipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class HugeRottenFlash {
	public static void newHugeRottenFlash() {
		ItemStack hugeRottenFlash=new ItemStack(Material.ROTTEN_FLESH);
		ItemMeta hugeRottenFlashMeta=hugeRottenFlash.getItemMeta();
		hugeRottenFlashMeta.setDisplayName("巨大的腐肉");
		hugeRottenFlash.setItemMeta(hugeRottenFlashMeta);
		NamespacedKey key=new NamespacedKey(WickhamsPlugin.MAIN,"huge_rotten_flesh");
		ShapedRecipe recipe=new ShapedRecipe(key,hugeRottenFlash);
		recipe.shape("RRR","RRR","RRR");
		recipe.setIngredient('R', Material.ROTTEN_FLESH);
		Bukkit.addRecipe(recipe);
	}
}
