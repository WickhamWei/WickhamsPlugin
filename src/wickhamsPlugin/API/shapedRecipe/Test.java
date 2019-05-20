package wickhamsPlugin.API.shapedRecipe;

import org.bukkit.inventory.ItemStack;

public class Test {
	private ItemStack[] contentsItemStacks=new ItemStack[10];
	public Test(ItemStack itemResult) {
		contentsItemStacks[0]=itemResult;
	}
	public void shape(int InventoryNumber,ItemStack item) {
		if (InventoryNumber>9||InventoryNumber<0) {
			return;
		}
		contentsItemStacks[InventoryNumber]=item;
	}
	public void addRecipeToServer() {
		TestWay.addInRecipeList(contentsItemStacks);
	}
}
