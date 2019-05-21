package wickhamsPlugin.API.shapedRecipe;

import org.bukkit.inventory.ItemStack;

public class WShapedRecipe {
	
	private ItemStack[] contentsItemStacks=new ItemStack[10];
	
	public WShapedRecipe(ItemStack itemResult) {
		contentsItemStacks[0]=itemResult;
	}
	
	public void shape(int InventoryNumber,ItemStack item) {
		if (InventoryNumber>9||InventoryNumber<0) {
			return;
		}
		contentsItemStacks[InventoryNumber]=item;
	}
	
	public void addRecipeToServer() {
		WShapedRecipeListener.addInRecipeList(contentsItemStacks);
	}
}
