package wickhamsPlugin.recipe.RPGWeapons.strengthen;

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
import wickhamsPlugin.recipe.RPGWeapons.normal.JiChuMuBin;
import wickhamsPlugin.recipe.RPGWeapons.normal.TieJian_1;

public class TieJian_12 {

	public static ItemStack itemStack;
	public static int level;

	public TieJian_12() {
		itemStack = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(ChatColor.RESET + ""+ChatColor.BLUE+"黑铁剑");
//		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RESET + " ");
		lore.add(ChatColor.RESET + "稀有度：" + ChatColor.BLUE + "强化");
		lore.add(ChatColor.RESET + "所需等级：5");
		level = 5;
		lore.add(ChatColor.RESET + "绑定：自由交易");
		lore.add(ChatColor.RESET+ "基础伤害：7");
		lore.add(ChatColor.RESET+ "基础攻速：0.625");
		lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "黑铁剑一般都是给初级训练者使用的。 --林纳斯");
		lore.add(ChatColor.GREEN +"可以合成");
		lore.add(ChatColor.GREEN +"不可以升级稀有度");
		lore.add(ChatColor.GREEN +"可以强化");
		lore.add(ChatColor.RED +"不可升星");
		meta.setLore(lore);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(new UUID(1, 1), "1", 0.1,
				AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(1, 2), "2", 1,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		itemStack.setItemMeta(meta);

		WShapedRecipe recipe = new WShapedRecipe(itemStack);
		recipe.shape(2, JiChuTongSuiPian.itemStack);
		recipe.shape(4, JiChuTongSuiPian.itemStack);
		recipe.shape(5, TieJian_1.itemStack);
		recipe.shape(6, JiChuTongSuiPian.itemStack);
		recipe.shape(8, JiChuMuBin.itemStack);
		recipe.addRecipeToServer();
	}
}
