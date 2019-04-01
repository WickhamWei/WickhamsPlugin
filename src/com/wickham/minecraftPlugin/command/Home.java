package com.wickham.minecraftPlugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.API.teleport.WTeleportEvent;

public class Home implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player=(Player) sender;
			if (arg3.length==0) {
				if(player.getBedSpawnLocation()==null) {
					player.sendMessage(ChatColor.RED+"你还没在床上睡过觉");
					return true;
				}else {
					new WTeleportEvent().WTeleport(player, player.getBedSpawnLocation(), true);
					return true;
				}
			}else
				return false;
		}else {
			sender.sendMessage(ChatColor.RED+"你必须是个玩家");
			return false;
		}
	}

}
