package wickhamsPlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import wickhamsPlugin.loginSystem.LoginMain;

public class gm implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] dataStrings) {
		if (dataStrings.length == 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				String gamemode = dataStrings[0];
				gamemodeSwitch(gamemode, player, player);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "你必须是个玩家");
				return false;
			}
		} else if (dataStrings.length == 2) {
			Player targePlayer = Bukkit.getPlayer(dataStrings[1]);
			String gamemode = dataStrings[0];
			if (targePlayer == null||!LoginMain.isLogin(targePlayer)) {
				playerDoesntOnline(sender);
				return false;
			}
			if (sender instanceof Player) {
				Player player = (Player) sender;
				return gamemodeSwitch(gamemode, player, targePlayer);
			} else {
				return gamemodeSwitch(gamemode, sender, targePlayer);
			}
		} else
			return false;
	}

	private static void doesntHavePermissionMsg(Player player) {
		player.sendMessage(ChatColor.RED + "你没有权限做此事");
	}

	private static void playerDoesntOnline(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "目标玩家不在线");
	}

	private static boolean gamemodeSwitch(String gamemodeString, Player sendPlayer, Player targePlayer) {
		switch (gamemodeString) {
		case "0":
			targePlayer.setGameMode(GameMode.SURVIVAL);
			targePlayer.sendMessage(ChatColor.GREEN + "你的游戏模式被设置为 " + ChatColor.YELLOW + "生存模式");
			sendPlayer.sendMessage(targePlayer.getName() + " 的游戏模式被设置为 " + ChatColor.YELLOW + "生存模式");
			break;
		case "1":
			if (sendPlayer.hasPermission("wickhamsplugin.gm.1")) {
				targePlayer.setGameMode(GameMode.CREATIVE);
				targePlayer.sendMessage(ChatColor.GREEN + "你的游戏模式被设置为 " + ChatColor.YELLOW + "创造模式");
				sendPlayer.sendMessage(targePlayer.getName() + " 的游戏模式被设置为 " + ChatColor.YELLOW + "创造模式");
			} else {
				doesntHavePermissionMsg(sendPlayer);
			}
			break;
		default:
			sendPlayer.sendMessage(ChatColor.RED + "参数不存在");
			return false;
		}
		return true;
	}

	private static boolean gamemodeSwitch(String gamemodeString, CommandSender sender, Player targePlayer) {
		switch (gamemodeString) {
		case "0":
			targePlayer.setGameMode(GameMode.SURVIVAL);
			targePlayer.sendMessage(ChatColor.GREEN + "你的游戏模式被设置为 " + ChatColor.YELLOW + "生存模式");
			sender.sendMessage(targePlayer.getName() + " 的游戏模式被设置为 " + ChatColor.YELLOW + "生存模式");
			break;
		case "1":
			targePlayer.setGameMode(GameMode.CREATIVE);
			targePlayer.sendMessage(ChatColor.GREEN + "你的游戏模式被设置为 " + ChatColor.YELLOW + "创造模式");
			sender.sendMessage(targePlayer.getName() + " 的游戏模式被设置为 " + ChatColor.YELLOW + "创造模式");
			break;
		default:
			sender.sendMessage(ChatColor.RED + "参数不存在");
			return false;
		}
		return true;
	}
}
