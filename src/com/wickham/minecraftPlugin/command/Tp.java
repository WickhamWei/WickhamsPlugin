package com.wickham.minecraftPlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.API.teleport.WTeleportEvent;

public class Tp implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if (sender instanceof Player) {// 只有玩家才能执行			
			if ((arg3.length == 1)) {// 检查参数是否为空或过多参数
				Player player = (Player) sender;
				Player targe = Bukkit.getServer().getPlayer(arg3[0]);// 目标
				new WTeleportEvent().WTeleport(player, targe);
				return true;
			}else {
				return false;
			}
		} else {
			sender.sendMessage("你必须是个玩家");
			return false;
		}
	}

}
