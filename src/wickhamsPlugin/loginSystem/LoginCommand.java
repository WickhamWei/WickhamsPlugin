package wickhamsPlugin.loginSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.event.WPlayerLoginEvent;
import wickhamsPlugin.event.WPlayerRegisterEvent;

public class LoginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (WickhamsPlugin.MAIN.getConfig().getBoolean("登陆系统")) {
			if (sender instanceof Player) {
				if (cmd.getName().equalsIgnoreCase("join") && arg3.length == 1) {
					if (LoginMain.isLogin(sender.getName())) {
						sender.sendMessage(ChatColor.YELLOW + "你已经登陆啦");
					} else {
						if (LoginMain.isRegister(sender.getName())) {
							if (LoginMain.checkPasswordDtell(arg3[0])
									&& LoginMain.checkPasswordReal(sender.getName(), arg3[0])) {
								LoginMain.playerLogin((Player) sender);
								Bukkit.getPluginManager().callEvent(new WPlayerLoginEvent((Player) sender));
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "密码错误");
								return false;
							}
						} else {
							if (LoginMain.checkPasswordDtell(arg3[0])) {
								LoginMain.register((Player) sender, arg3[0]);
								LoginMain.playerLogin((Player) sender);
								Bukkit.getPluginManager().callEvent(new WPlayerRegisterEvent((Player) sender));
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
			sender.sendMessage(ChatColor.RED + "注册-登陆功能未开启");
			return true;
		}
		return false;
	}
}
