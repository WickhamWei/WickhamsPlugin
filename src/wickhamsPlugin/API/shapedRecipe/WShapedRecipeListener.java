package wickhamsPlugin.API.shapedRecipe;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class WShapedRecipeListener implements Listener {

//	https://bukkit.org/threads/itemstack-in-craft-recipe.469390/

	private static HashSet<ItemStack[]> allRecipe = new HashSet<ItemStack[]>();// 存储所有的自定义配方
	protected static HashSet<ItemStack> allSourceHashSet = new HashSet<ItemStack>();// 存储所有合成配方物品

	public static void addInRecipeList(ItemStack[] recipeContents) {
		allRecipe.add(recipeContents);
	}

	@EventHandler
	public void craftItemEvent(PrepareItemCraftEvent event) {
		if (allRecipe.isEmpty()) {
			return;
		}

		ItemStack[] getItemStacks = event.getInventory().getContents();// 获取玩家合成台上的物品
		ItemStack itemResult;// 存储合成结果为空

		for (ItemStack[] contents : allRecipe) {// 遍历所有自定义配方
			if (getItemStacks.length != contents.length) {// 检查合成表大小
				continue;
			}
			itemResult = contents[0];// 设置目的产物
			boolean pass = true;
			for (int i = 1; i < getItemStacks.length; i++) {// 检查每个格子
				if ((getItemStacks[i].isSimilar(contents[i]) && getItemStacks[i].getAmount() == contents[i].getAmount())
						|| (getItemStacks[i].getType() == Material.AIR && contents[i] == null)) {
					;
				} else {
					pass = false;
					break;// 不正确直接返回
				}
			}
			if (pass) {
				event.getInventory().setResult(itemResult);// 正确时设置目的产物后返回
				return;
			}
		}
	}

	@EventHandler
	public void craftItemEvent(InventoryClickEvent event) {
		if (event.getSlotType().equals(SlotType.CRAFTING)) {
			if (event.getCurrentItem() != null) {
				if (event.getCursor() != null) {
					if (event.getCurrentItem().isSimilar(event.getCursor())) {
						ItemStack itemStack = event.getCurrentItem();
						for (ItemStack sourceItemStack : allSourceHashSet) {
							if (sourceItemStack.isSimilar(itemStack)) {
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
}
