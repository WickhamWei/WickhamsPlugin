package com.wickham.minecraftPlugin.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class HugeRottenFlash {
	public static void newHugeRottenFlash() {
		ItemStack hugeRottenFlash = new ItemStack(Material.ROTTEN_FLESH);
		ItemMeta hugeRottenFlashMeta = hugeRottenFlash.getItemMeta();
		hugeRottenFlashMeta.setDisplayName("巨大的腐肉");
		hugeRottenFlash.setItemMeta(hugeRottenFlashMeta);
		NamespacedKey key = new NamespacedKey(WickhamsPlugin.MAIN, "huge_rotten_flesh");
		ShapelessRecipe recipe = new ShapelessRecipe(key, hugeRottenFlash);
		recipe.addIngredient(9, Material.ROTTEN_FLESH);
		Bukkit.addRecipe(recipe);
	}
}
