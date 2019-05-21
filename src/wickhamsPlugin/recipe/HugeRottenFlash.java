package wickhamsPlugin.recipe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.API.furnaceRecipe.WFurnaceRecipe;

public class HugeRottenFlash implements Listener {

	public static ItemStack hugeRottenFlash = new ItemStack(Material.ROTTEN_FLESH);
	public static ItemMeta hugeRottenFlashMeta = hugeRottenFlash.getItemMeta();

	@EventHandler
	public void newHugeRottenFlash(ServerLoadEvent event) {
		hugeRottenFlashMeta.setDisplayName(ChatColor.YELLOW + "巨大的腐肉");
		hugeRottenFlash.setItemMeta(hugeRottenFlashMeta);
		NamespacedKey key = new NamespacedKey(WickhamsPlugin.MAIN, "huge_rotten_flesh");
		ShapelessRecipe recipe = new ShapelessRecipe(key, hugeRottenFlash);
		recipe.addIngredient(9, Material.ROTTEN_FLESH);
		Bukkit.addRecipe(recipe);
		new WFurnaceRecipe().furnaveRecipe("huge_rotten_flash_to_leather", hugeRottenFlash,
				new ItemStack(Material.LEATHER), 20, 10);
		new 基础铁碎片();
		new 基础木柄();
		new 破损的铁剑();
	}

	/*
	 * @EventHandler public void newHugeRottenFlash(FurnaceSmeltEvent event)
	 * {//物品燃烧结束 if(event.getSource().equals(new ItemStack(Material.ROTTEN_FLESH)))
	 * { if(!event.getSource().getItemMeta().getDisplayName().equals(
	 * hugeRottenFlashMeta.getDisplayName())) { event.setCancelled(true); } }else {
	 * return; } }
	 * 
	 * @EventHandler public void newHugeRottenFlash(FurnaceBurnEvent event)
	 * {//燃料开始燃烧 BlockState state=event.getBlock().getState(); Furnace
	 * furnace=(Furnace)state; if(furnace.getInventory().getSmelting().equals(new
	 * ItemStack(Material.ROTTEN_FLESH))) {
	 * if(!furnace.getInventory().getSmelting().getItemMeta().getDisplayName().
	 * equals(hugeRottenFlashMeta.getDisplayName())) { event.setCancelled(true);
	 * return; }else { return; } }else { return; } }
	 * 
	 * @EventHandler public void newHugeRottenFlash(InventoryClickEvent event)
	 * {//物品放入熔炉 if(event.getCursor().getType().equals(Material.ROTTEN_FLESH)) {
	 * if(!event.getCursor().getItemMeta().getDisplayName().equals(
	 * hugeRottenFlashMeta.getDisplayName())) {
	 * if(event.getSlotType().equals(InventoryType.SlotType.CRAFTING)){
	 * event.setCancelled(true); } } } }
	 */
}
