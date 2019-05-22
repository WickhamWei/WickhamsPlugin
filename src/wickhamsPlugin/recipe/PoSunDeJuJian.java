package wickhamsPlugin.recipe;

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
import org.bukkit.plugin.Plugin;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.API.shapedRecipe.WShapedRecipe;

public class PoSunDeJuJian {
	
	public final static Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;
	public static ItemStack itemStack;
	public static int level;

	public PoSunDeJuJian() {
		itemStack = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "破损的巨剑");
//		meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RESET + " ");
		lore.add(ChatColor.RESET + "所需等级：2");
		level=2;
		lore.add(ChatColor.RESET + "绑定：自由交易");
		lore.add(ChatColor.RESET + "稀有度：" + ChatColor.WHITE + "普通");
		lore.add(ChatColor.YELLOW+""+ChatColor.ITALIC+"我的身边只剩下你了， 即使你已经损坏， 我也不会抛弃你。。");
		meta.setLore(lore);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(new UUID(1, 1), "1", -0.01, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(1, 2), "2", 2.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 3), "3", 35, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		itemStack.setItemMeta(meta);
		
		WShapedRecipe recipe = new WShapedRecipe(itemStack);
		recipe.shape(1, JiChuTieSuiPian.itemStack);
		recipe.shape(2, JiChuTieSuiPian.itemStack);
		recipe.shape(3, JiChuTieSuiPian.itemStack);
		recipe.shape(4, JiChuTieSuiPian.itemStack);
		recipe.shape(5, JiChuTieSuiPian.itemStack);
		recipe.shape(6, JiChuTieSuiPian.itemStack);
		recipe.shape(8, JiChuMuBin.itemStack);
		recipe.addRecipeToServer();
	}

	public static ItemStack getItemStack() {
		return itemStack;
	}
}
