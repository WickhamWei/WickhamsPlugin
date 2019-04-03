package com.wickham.minecraftPlugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (arg3.length == 0) {
				player.setBedSpawnLocation(player.getLocation(), true);
				player.sendMessage(ChatColor.GREEN + "已设置家");
				return true;
			} else
				return false;
		} else {
			sender.sendMessage(ChatColor.RED + "你必须是个玩家");
			return false;
		}
	}

}
