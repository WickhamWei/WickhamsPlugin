package wickhamsPlugin.recipe.普通;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wickhamsPlugin.API.shapedRecipe.WShapedRecipe;

public class TieJian_3 {

	public static ItemStack itemStack;
	public static int level;

	public TieJian_3() {
		itemStack = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(ChatColor.RESET + "破旧的钢剑");
//		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RESET + " ");
		lore.add(ChatColor.RESET + "稀有度：" + ChatColor.WHITE + "普通");
		lore.add(ChatColor.RESET + "所需等级："+ChatColor.DARK_PURPLE+"20");
		level = 20;
		lore.add(ChatColor.RESET + "绑定：自由交易");
		lore.add(ChatColor.RESET+ "基础伤害：7");
		lore.add(ChatColor.RESET+ "基础攻速：0.625");
		lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "再破旧的剑， 也可以在火炉里得到重生。 --辛达");
		meta.setLore(lore);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(new UUID(1, 1), "1", 0.15,
				AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(1, 2), "2", 1.5,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		itemStack.setItemMeta(meta);

		WShapedRecipe recipe = new WShapedRecipe(itemStack);
		recipe.shape(2, JiChuTieSuiPian.itemStack);
		recipe.shape(4, JiChuTieSuiPian.itemStack);
		recipe.shape(5, TieJian_2.itemStack);
		recipe.shape(6, JiChuTieSuiPian.itemStack);
		recipe.shape(8, JiChuMuBin.itemStack);
		recipe.addRecipeToServer();
	}
}
