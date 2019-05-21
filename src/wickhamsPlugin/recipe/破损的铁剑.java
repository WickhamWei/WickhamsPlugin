package wickhamsPlugin.recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.API.shapedRecipe.WShapedRecipe;

public class 破损的铁剑 {
	public final static Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;
	public static ItemStack itemStack;

	public 破损的铁剑() {
		ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "破损的铁剑");
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RESET + "所需等级：1");
		lore.add(ChatColor.RESET + "绑定：自由交易");
		lore.add(ChatColor.RESET + "稀有度： " + ChatColor.WHITE + "普通");
		lore.add(ChatColor.RESET + "" + ChatColor.ITALIC + "喂！ 不要磨蹭了， 让我帮你修理吧。 --林纳斯。");
		meta.setLore(lore);
		itemStack.setItemMeta(meta);
		WShapedRecipe recipe = new WShapedRecipe(itemStack);
		recipe.shape(2, 基础铁碎片.itemStack);
		recipe.shape(5, 基础铁碎片.itemStack);
		recipe.shape(8, 基础木柄.itemStack);
		recipe.addRecipeToServer();
	}

	public static ItemStack getItemStack() {
		return itemStack;
	}
}
