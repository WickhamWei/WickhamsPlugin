package com.wickham.minecraftPlugin.API.furnaceRecipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class WFurnaceRecipeMain {
	public abstract void furnaveRecipe(Material sourceMaterial,ItemMeta sourceItemMeta,String sourceKeyNameString,ItemStack result​ItemStack,float experience, int cookingTimeSecond);
}
