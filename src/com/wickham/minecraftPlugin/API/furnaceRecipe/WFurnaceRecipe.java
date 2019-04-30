package com.wickham.minecraftPlugin.API.furnaceRecipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class WFurnaceRecipe extends WFurnaceRecipeMain implements Listener{
	
	private Plugin wickhamsPlugin=WickhamsPlugin.MAIN;
	private ItemStack sourceItemStack;
	private Material sourceMaterial;
	
	public void furnaveRecipe(Material sourceMaterial,ItemMeta sourceItemMeta,String sourceKeyNameString,ItemStack result​ItemStack,float experience, int cookingTimeSecond) {
		sourceItemStack=new ItemStack(sourceMaterial);
		this.sourceMaterial=sourceMaterial;
		sourceItemStack.setItemMeta(sourceItemMeta);
		NamespacedKey key = new NamespacedKey(wickhamsPlugin, sourceKeyNameString+"_furnaceRecipe");
		FurnaceRecipe fRecipe=new FurnaceRecipe(key, result​ItemStack, sourceMaterial, experience, cookingTimeSecond*20);
		Bukkit.addRecipe(fRecipe);
		wickhamsPlugin.getServer().getPluginManager().registerEvents(this, wickhamsPlugin);
	}
	
	@EventHandler
	public void eventFurnaveRecipe(FurnaceSmeltEvent event) {//物品生成
		if(event.getSource().getType().equals(sourceMaterial)) {
			if(!event.getSource().isSimilar(sourceItemStack)) {
				event.setCancelled(true);
			}
		}else {
			return;
		}
	}
	
	@EventHandler
	public void eventFurnaveRecipe(FurnaceBurnEvent event) {//燃料开始燃烧
		BlockState state=event.getBlock().getState();
		Furnace furnace=(Furnace)state;
		if(furnace.getInventory().getSmelting().getType().equals(sourceMaterial)) {
			if(!furnace.getInventory().getSmelting().isSimilar(sourceItemStack)) {
				event.setCancelled(true);
				return;
			}else {
				return;
			}
		}else {
			return;
		}
	}
	
	@EventHandler
	public void eventFurnaveRecipe(InventoryClickEvent event) {//物品放入熔炉
		if(event.getInventory() instanceof FurnaceInventory) {
			if(event.getCursor().getType().equals(sourceMaterial)) {
				if(!event.getCursor().isSimilar(sourceItemStack)) {
					if(event.getSlotType().equals(InventoryType.SlotType.CRAFTING)){
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
