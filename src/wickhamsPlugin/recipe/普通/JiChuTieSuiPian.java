package wickhamsPlugin.recipe.普通;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;

public class JiChuTieSuiPian {
	public final static Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;
	public static ItemStack itemStack;
	public JiChuTieSuiPian() {
		itemStack=new ItemStack(Material.IRON_NUGGET,1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.RESET+"基础铁碎片");
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore=new ArrayList<>();
		lore.add(ChatColor.WHITE+" ");
		lore.add(ChatColor.WHITE+"普通级稀有度武器的基本材料");
		lore.add(ChatColor.RESET+"稀有度："+ChatColor.WHITE+"普通");
		meta.setLore(lore);
		itemStack.setItemMeta(meta);
		ShapelessRecipe recipe=new ShapelessRecipe(new NamespacedKey(WICKHAMS_PLUGIN, "JiChuTieSuiPian"),itemStack);
		recipe.addIngredient(8,Material.IRON_NUGGET);
		Bukkit.addRecipe(recipe);
	}
	public static ItemStack getItemStack() {
		return itemStack;
	}
}
