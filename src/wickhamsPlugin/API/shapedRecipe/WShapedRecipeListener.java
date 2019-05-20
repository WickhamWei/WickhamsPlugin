package wickhamsPlugin.API.shapedRecipe;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class WShapedRecipeListener implements Listener {

//	https://bukkit.org/threads/itemstack-in-craft-recipe.469390/

	private ItemStack itemResult;
	public ItemStack[] recipeContents = new ItemStack[10];

	public WShapedRecipeListener(ItemStack[] recipeContents) {
		this.recipeContents = recipeContents;
		itemResult = recipeContents[0];
	}

	@EventHandler
	public void craftItemEvent(PrepareItemCraftEvent event) {
		if (event.getInventory().getItem(0) == null) {
			return;
		} else {
			if (event.getInventory().getItem(0).isSimilar(itemResult)) {
				ItemStack[] contentItemStacks = event.getInventory().getContents();
				for (int i = 1; i <= 9; i++) {
					if (!(contentItemStacks[i].isSimilar(recipeContents[i])
							|| (recipeContents[i] == null && contentItemStacks[i].getType() == Material.AIR))) {
						event.getInventory().setResult(new ItemStack(Material.AIR));
						break;
					}
				}
			}
		}
	}
}
