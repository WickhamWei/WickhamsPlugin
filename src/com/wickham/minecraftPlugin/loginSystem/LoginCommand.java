package com.wickham.minecraftPlugin.loginSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class LoginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (WickhamsPlugin.getMain().getConfig().getBoolean("登陆系统")) {
			if (sender instanceof Player) {
				if (cmd.getName().equalsIgnoreCase("join") && arg3.length == 1) {
					if (LoginMain.isLogin(sender.getName())) {
						sender.sendMessage(ChatColor.YELLOW + "你已经登陆啦");
					} else {
						if (LoginMain.isRegister(sender.getName())) {
							if (LoginMain.checkPassworldDtell(arg3[0])
									&& LoginMain.checkPassworldReal(sender.getName(), arg3[0])) {
								LoginMain.playerLogin(((Player) sender));
								sender.sendMessage(ChatColor.GREEN + "登陆成功");
								if (WickhamsPlugin.getMain().getConfig().getBoolean("玩家加入时给玩家的信息开关")) {
									sender.sendMessage(ChatColor.GREEN+WickhamsPlugin.getMain().getConfig().getString("玩家加入时给玩家的信息"));
								}
								Bukkit.broadcastMessage(ChatColor.GREEN+sender.getName()+" 加入了游戏");
								((Player) sender).getPlayer().setGameMode(GameMode.SURVIVAL);
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "密码错误");
								return false;
							}
						} else {
							if (LoginMain.checkPassworldDtell(arg3[0])) {
								LoginMain.register(sender.getName(), arg3[0]);
								LoginMain.playerLogin(sender.getName());
								sender.sendMessage(ChatColor.GREEN + "注册成功，已登陆成功");
								((Player) sender).getPlayer().getPlayer().setGameMode(GameMode.SURVIVAL);
								if (WickhamsPlugin.getMain().getConfig().getBoolean("玩家加入时给玩家的信息开关")) {
									sender.sendMessage(ChatColor.GREEN+WickhamsPlugin.getMain().getConfig().getString("玩家加入时给玩家的信息"));
								}
								Bukkit.broadcastMessage(ChatColor.GREEN+sender.getName()+" 加入了游戏");
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "格式有误，密码长度不能小于5或大于20");
								return false;
							}
						}
					}
				} else if (cmd.getName().equalsIgnoreCase("join") && !(arg3.length == 1)) {
					sender.sendMessage(ChatColor.RED + "格式有误，密码不能有空格");
					return false;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "只有玩家能使用这个命令");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED+"注册-登陆功能未开启");
			return true;
		}
		return false;
	}
}
