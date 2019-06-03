package wickhamsPlugin.loginSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.WickhamsPluginUpdateChecker;
import wickhamsPlugin.event.WPlayerLoginEvent;
import wickhamsPlugin.event.WPlayerRegisterEvent;

public final class LoginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (WickhamsPlugin.MAIN.getConfig().getBoolean("登陆系统")) {
			if (sender instanceof Player) {
				Player player=(Player) sender;
				if (cmd.getName().equalsIgnoreCase("join") && arg3.length == 1) {
					if (LoginMain.isLogin(player.getName())) {
						player.sendMessage(ChatColor.YELLOW + "你已经登陆啦");
					} else {
						if (LoginMain.isRegister(player.getName())) {
							if (LoginMain.checkPasswordDtell(arg3[0])
									&& LoginMain.checkPasswordReal(player.getName(), arg3[0])) {
								WPlayerLoginEvent wPlayerLoginEvent=new WPlayerLoginEvent(player);
								Bukkit.getPluginManager().callEvent(wPlayerLoginEvent);
								if (!(wPlayerLoginEvent.isCancelled())) {
									if(LoginMain.PLAYER_REGISTER_IP_LIMIT) {
										if(!LoginMain.hasRegisterIP(player)) {
											LoginMain.setRegisterIP(player);
											LoginMain.savePlayerRegisterIPConfig();
										}
									}
									LoginMain.playerLogin(player);
									LoginMain.removePlayerInLoginTimesHashMap(player);
									player.sendMessage(wPlayerLoginEvent.getLoginSuccessMsg());
									if (LoginMain.joinMsgBoolean) {
										player.sendMessage(ChatColor.GREEN + LoginMain.joinMsgString);
									}
									Bukkit.broadcastMessage(wPlayerLoginEvent.getJoinMsg());
									player.setGameMode(GameMode.SURVIVAL);
									if (LoginMain.teleportPlayerAfterLogin((player))) {
										player.sendMessage(ChatColor.GREEN + "已经传送到退出游戏时的位置");
									} else {
										player.sendMessage(ChatColor.RED + "退出游戏时的位置已丢失，已在出生点");
									}
									if(player.isOp()) {
										if(!WickhamsPluginUpdateChecker.PluginsNewestVersionString.equals(WickhamsPluginUpdateChecker.PluginsnowVersionString)) {
											player.sendMessage(ChatColor.YELLOW+"管理员请注意，本服务器的 WickhamsPlugin 插件版本为 "+ChatColor.RED+WickhamsPluginUpdateChecker.PluginsnowVersionString);
											player.sendMessage(ChatColor.YELLOW+"WickhamsPlugin 插件的最新版本为 "+ChatColor.GREEN+WickhamsPluginUpdateChecker.PluginsNewestVersionString);
											player.sendMessage(ChatColor.YELLOW+"更新于 "+ChatColor.GREEN+WickhamsPluginUpdateChecker.PluginstheNewestVersionPTimeString);
											player.sendMessage(ChatColor.YELLOW+"请及时更新以确保漏洞修复和功能完善");
										}
									}
								}
								return true;
							} else {
								LoginMain.addPlayerLoginTimes(player);
								sender.sendMessage(ChatColor.RED + "密码错误");
								if(LoginMain.getPlayerLoginTimes(player)>3) {
									LoginTimesLimit.newLoginTimesLimit(player);
									player.kickPlayer("你已经输错密码3次了，被踢出了服务器");
									return true;
								}
								return false;
							}
						} else {
							if (LoginMain.checkPasswordDtell(arg3[0])) {
								WPlayerRegisterEvent wPlayerRegisterEvent=new WPlayerRegisterEvent(player);
								Bukkit.getPluginManager().callEvent(wPlayerRegisterEvent);
								if (!(wPlayerRegisterEvent.isCancelled())) {
									if(LoginMain.PLAYER_REGISTER_IP_LIMIT) {
										if(LoginMain.isIPHasBeenUsed(player)) {
											player.kickPlayer("你的IP地址已被使用，请检查是否曾经注册。如为否请联系管理员");
											return true;
										}else {
											LoginMain.setRegisterIP(player);
											LoginMain.savePlayerRegisterIPConfig();
										}
									}
									LoginMain.register(player, arg3[0]);
									LoginMain.playerLogin(player);
									player.sendMessage(wPlayerRegisterEvent.getRegisterSuccessMsg());
									player.setGameMode(GameMode.SURVIVAL);
									if (LoginMain.joinMsgBoolean) {
										player.sendMessage(
												ChatColor.GREEN + LoginMain.joinMsgString);
									}
									Bukkit.broadcastMessage(wPlayerRegisterEvent.getJoinMsg());
								}
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
