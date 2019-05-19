package wickhamsPlugin.recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class 简易巨剑 {
	public 简易巨剑() {
		ItemStack item=new ItemStack(Material.STONE_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("简易巨剑");
		meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
		List<String> lore=new ArrayList<>();
		lore.add("增加对所有目标的伤害 1");
		meta.setLore(lore);
	}
}
