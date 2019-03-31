package com.wickham.minecraftPlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {// 只有玩家才能执行
			if (arg1.getName().equalsIgnoreCase("tp")) {
				if ((arg3.length == 1)) {// 检查参数是否为空或过多参数
					Player me = (Player) arg0;
					Player targe = Bukkit.getServer().getPlayer(arg3[0]);// 目标
					if (!(targe == null)) {// 检查目标是否在线
						if (targe == me) {// 传送自己到自己？
							arg0.sendMessage(ChatColor.RED+"你陷入了死循环");
						} else {
							me.teleport(targe);// 传送到某个实体
							arg0.sendMessage(ChatColor.GREEN+"传送成功");
							return true;
						}
					} else {
						arg0.sendMessage(ChatColor.RED+"玩家不在线或者不存在");
					}
				}
			}
		} else {
			arg0.sendMessage("你必须是个玩家");
		}
		return false;
	}

}
