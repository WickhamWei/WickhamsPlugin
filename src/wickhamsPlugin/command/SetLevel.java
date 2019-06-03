package wickhamsPlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLevel implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] allargStrings) {
		// TODO 自动生成的方法存根
		if (allargStrings.length == 2) {
			if (sender instanceof Player) {
				int targeLevel;
				try {
					targeLevel = Integer.parseInt(allargStrings[0]);
				} catch (NumberFormatException e) {
					return false;
					// TODO: handle exception
				}
				Player targePlayer = Bukkit.getServer().getPlayer(allargStrings[1]);
				if (targePlayer != null) {
					targePlayer.setLevel(targeLevel);
					sender.sendMessage(ChatColor.YELLOW + targePlayer.getName() + ChatColor.GREEN + " 的等级已被设为 "
							+ ChatColor.YELLOW + targeLevel);
				} else {
					sender.sendMessage(ChatColor.RED + "目标玩家不在线或者不存在");
					return false;
				}
				return true;
			} else {
				int targeLevel;
				try {
					targeLevel = Integer.parseInt(allargStrings[0]);
				} catch (NumberFormatException e) {
					return false;
					// TODO: handle exception
				}
				Player targePlayer = Bukkit.getServer().getPlayer(allargStrings[1]);
				if (targePlayer != null) {
					targePlayer.setLevel(targeLevel);
					sender.sendMessage(ChatColor.YELLOW + targePlayer.getName() + ChatColor.GREEN + " 的等级已被设为 "
							+ ChatColor.YELLOW + targeLevel);
				} else {
					sender.sendMessage(ChatColor.RED + "目标玩家不在线或者不存在");
					return false;
				}
				return true;
			}
		}
		return false;
	}

}
