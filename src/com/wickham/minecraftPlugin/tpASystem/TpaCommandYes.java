package com.wickham.minecraftPlugin.tpASystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpACommandYes implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if(sender instanceof Player) {
			if(arg3.length==0) {
				Player targe=(Player) sender;
				Player player=null;
				if(TpAMain.isRequestSecond(targe)) {
					 for (String playerString : TpAMain.requestList.keySet()) {
						 if(targe.getName().equals(TpAMain.requestList.get(playerString))) {
							 player=Bukkit.getServer().getPlayer(playerString);
							 break;
						 }
					 }
					 if(player==null) {
						 targe.sendMessage(ChatColor.RED+"玩家已下线");
						 return true;
					 }else {
						 targe.sendMessage(ChatColor.GREEN+"已答应 "+player.getName()+" 的传送请求，在 "+TpAMain.WAITING_TIME+" 秒后传送到你的位置，请注意");
						 player.sendMessage(ChatColor.GREEN+targe.getName()+" 已答应你的请求，开始传送");
						 new TpATp().requestTeleport(player,targe);
						 return true;
					 }
				}else {
					targe.sendMessage(ChatColor.RED+"请求不存在或者已过期");
					return true;
				}
			}else {
				return false;
			}
		}else {
			sender.sendMessage(ChatColor.RED+"你必须是个玩家");
			return false;
		}
	}
	
}
