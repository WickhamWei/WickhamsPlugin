package wickhamsPlugin.recipe;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponLevelLimitListener implements Listener{
	@EventHandler
	public void levelLimit(PlayerMoveEvent event) {
		if(LoadingAllRecipeListener.allWeaponAndLevel.isEmpty()) {
			return;
		}
		Player player=event.getPlayer();
		if(player.getInventory().getItemInMainHand()==null) {
			return;
		}else {
			ItemStack itemInLeftHandItemStack=player.getInventory().getItemInMainHand();
			if(LoadingAllRecipeListener.allWeaponAndLevel.get(itemInLeftHandItemStack) != null) {
				int level=LoadingAllRecipeListener.allWeaponAndLevel.get(itemInLeftHandItemStack);
				if(player.getLevel()<level) {
					player.sendMessage(ChatColor.RED+"你装备了超出你等级的道具");
					event.setCancelled(true);
					return;
				}
			}
		}
		
	}
}
