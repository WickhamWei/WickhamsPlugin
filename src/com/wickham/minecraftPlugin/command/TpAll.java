package com.wickham.minecraftPlugin.command;

import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] data) {
		if (sender instanceof Player && data.length == 0) {
			Collection<? extends Player> allPlayer = sender.getServer().getOnlinePlayers();
			if (!(allPlayer.size() <= 1)) {
				for (Player a : allPlayer) {
					a.teleport((Player) sender);
				}
				sender.sendMessage(ChatColor.GREEN+"已传送所有人到你身边");
				return true;
			} else {
				sender.sendMessage(ChatColor.YELLOW+"整个服务器只有你一个人。。。。");
				return true;
			}
		}else {
			return false;
		}
	}

}
