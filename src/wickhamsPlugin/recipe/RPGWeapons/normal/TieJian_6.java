package wickhamsPlugin.recipe.RPGWeapons.normal;

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

public class TieJian_6 {

	public static ItemStack itemStack;
	public static int level;

	public TieJian_6() {
		itemStack = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(ChatColor.RESET + "崭新的钛剑");
//		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RESET + " ");
		lore.add(ChatColor.RESET + "稀有度：" + ChatColor.WHITE + "普通");
		lore.add(ChatColor.RESET + "所需等级："+ChatColor.RED+"50");
		level = 50;
		lore.add(ChatColor.RESET + "绑定：自由交易");
		lore.add(ChatColor.RESET+ "基础伤害：7");
		lore.add(ChatColor.RESET+ "基础攻速：0.625");
		lore.add(ChatColor.GREEN +"可以合成");
		lore.add(ChatColor.RED +"不可以升级稀有度");
		lore.add(ChatColor.RED +"不可以强化");
		lore.add(ChatColor.RED +"不可升星");
		meta.setLore(lore);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(new UUID(1, 1), "1", 0.3,
				AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(1, 2), "2", 3,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		itemStack.setItemMeta(meta);

		WShapedRecipe recipe = new WShapedRecipe(itemStack);
		recipe.shape(2, JiChuTieSuiPian.itemStack);
		recipe.shape(4, JiChuTieSuiPian.itemStack);
		recipe.shape(5, TieJian_5.itemStack);
		recipe.shape(6, JiChuTieSuiPian.itemStack);
		recipe.shape(8, JiChuMuBin.itemStack);
		recipe.addRecipeToServer();
	}
}
