package wickhamsPlugin.recipe.DNFWeapons.strengthen;

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

public class JiChuTongSuiPian {
	public final static Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;
	public static ItemStack itemStack;
	public JiChuTongSuiPian() {
		itemStack=new ItemStack(Material.GOLD_NUGGET,1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.RESET+"基础铜碎片");
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore=new ArrayList<>();
		lore.add(ChatColor.WHITE+" ");
		lore.add(ChatColor.YELLOW+"强化级稀有度武器的基本材料");
		lore.add(ChatColor.RESET+"稀有度："+ChatColor.BLUE+"强化");
		meta.setLore(lore);
		itemStack.setItemMeta(meta);
		ShapelessRecipe recipe=new ShapelessRecipe(new NamespacedKey(WICKHAMS_PLUGIN, "JiChuTongSuiPian"),itemStack);
		recipe.addIngredient(8,Material.GOLD_NUGGET);
		Bukkit.addRecipe(recipe);
	}
	public static ItemStack getItemStack() {
		return itemStack;
	}
}
