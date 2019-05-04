package wickhamsPlugin.API.furnaceRecipe;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;

public abstract class WFurnaceRecipeMain {
	protected Plugin wickhamsPlugin=WickhamsPlugin.MAIN;
	
	public abstract void furnaveRecipe(String recipeKeyNameString,ItemStack sourceItemStack,ItemStack result​ItemStack,float experience, int cookingTimeSecond);
}
