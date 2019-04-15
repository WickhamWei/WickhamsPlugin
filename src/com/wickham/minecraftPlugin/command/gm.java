package com.wickham.minecraftPlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sun.xml.internal.ws.client.SenderException;

public class gm implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] dataStrings) {
		if (dataStrings.length == 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				String gamemode = dataStrings[0];
				switch (gamemode) {
				case "0":
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GREEN + "你的游戏模式被设置为" + ChatColor.YELLOW + "生存模式");
					break;
				case "1":
					if (player.hasPermission("")) {
						player.setGameMode(GameMode.CREATIVE);
						player.sendMessage(ChatColor.GREEN + "你的游戏模式被设置为" + ChatColor.YELLOW + "创造模式");
					} else {
						doesntHavePermissionMsg(player);
					}
					break;
				default:
					player.sendMessage(ChatColor.RED + "参数不存在");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "你必须是个玩家");
				return false;
			}
		} else if (dataStrings.length == 2) {
			Player targePlayer = Bukkit.getPlayer(dataStrings[1]);
			String gamemode = dataStrings[0];
			if (sender instanceof Player) {
				Player player = (Player) sender;

			} else {

			}
		}
		return false;
	}

	private static void doesntHavePermissionMsg(Player player) {
		player.sendMessage(ChatColor.RED + "你没有权限做此事");
	}

	private static void playerDoesntOnline(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "目标玩家不在线");
	}
}
