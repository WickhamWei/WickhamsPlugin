package wickhamsPlugin.recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;

public class JiChuMuBin {
	public final static Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;
	public static ItemStack itemStack;
	public JiChuMuBin() {
		itemStack=new ItemStack(Material.STICK,1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.RESET+"基础木柄");
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore=new ArrayList<>();
		lore.add(ChatColor.WHITE+" ");
		lore.add(ChatColor.YELLOW+"普通级/强化级稀有度武器的基本材料");
		lore.add(ChatColor.RESET+"稀有度："+ChatColor.WHITE+"普通");
		meta.setLore(lore);
		itemStack.setItemMeta(meta);
		ShapedRecipe recipe=new ShapedRecipe(new NamespacedKey(WICKHAMS_PLUGIN, "JiChuMuBin"),itemStack);
		recipe.shape(" A "," A "," A ");
		recipe.setIngredient('A', Material.STICK);
		Bukkit.addRecipe(recipe);
	}
	public static ItemStack getItemStack() {
		return itemStack;
	}
}
