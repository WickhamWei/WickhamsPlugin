package com.wickham.minecraftPlugin.spawnSystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			if (arg3.length==0) {
				return new SpawnTp().spawnTeleport((Player) sender);
			}else
				return false;
		}else {
			sender.sendMessage(ChatColor.RED+"你必须是个玩家");
			return false;
		}
	}
}
